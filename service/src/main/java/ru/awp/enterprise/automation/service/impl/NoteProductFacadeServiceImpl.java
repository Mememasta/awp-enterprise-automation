package ru.awp.enterprise.automation.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.exception.ClientNotFoundException;
import ru.awp.enterprise.automation.exception.NotFoundProductException;
import ru.awp.enterprise.automation.mapper.NoteDTOMapper;
import ru.awp.enterprise.automation.mapper.SimpleUserResponseMapper;
import ru.awp.enterprise.automation.models.dao.NoteDAO;
import ru.awp.enterprise.automation.models.dto.NoteDTO;
import ru.awp.enterprise.automation.models.dto.NoteProductDTO;
import ru.awp.enterprise.automation.models.request.NoteRequest;
import ru.awp.enterprise.automation.service.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoteProductFacadeServiceImpl implements NoteProductFacadeService {

    private final UserService userService;
    private final NoteService noteService;
    private final ProductService productService;
    private final NoteProductService noteProductService;

    private final NoteDTOMapper noteDTOMapper;
    private final SimpleUserResponseMapper simpleUserResponseMapper;

    @Override
    public Flux<NoteDTO> findNoteAndProduct(Integer areaId) {
        return noteService.findByArea(areaId)
                .concatMap(this::buildNoteToDTO);
    }

    @Override
    public Flux<NoteDTO> findAll() {
        return noteService.findAll()
                .concatMap(this::buildNoteToDTO);
    }

    @Override
    public Flux<NoteDTO> findNoteByUserId(UUID uuid) {
        return noteService.findByUserId(uuid)
                .concatMap(this::buildNoteToDTO);
    }

    @Override
    public Mono<NoteDTO> findById(UUID noteId) {
        return noteService.findById(noteId)
                .flatMap(this::buildNoteToDTO);
    }

    private Mono<NoteDTO> buildNoteToDTO(NoteDAO note) {
        var noteProductsMono = noteProductService.findNoteProducts(note.id())
                .collectList();
        var userMono = userService.findById(note.user())
                .map(simpleUserResponseMapper);
        var editUserMono = userService.findById(note.userEdit())
                .map(simpleUserResponseMapper)
                .switchIfEmpty(Mono.just(simpleUserResponseMapper.apply(null)));

        return Mono.zip(Mono.just(note), noteProductsMono, userMono, editUserMono)
                .map(tuple -> noteDTOMapper.map(tuple.getT1(), tuple.getT2(), tuple.getT3(), tuple.getT4()));
    }

    @Override
    @Transactional
    public Mono<Void> validateAndSaveNoteAndProduct(NoteRequest noteRequest) {
        return validateUsers(noteRequest.userId())
                .then(validateProducts(noteRequest.products()))
                .flatMap(volume -> saveNoteAndReturnUUID(noteRequest, volume));
    }

    @Override
    @Transactional
    public Mono<Void> validateAndUpdateNoteAndProduct(UUID noteId, NoteRequest noteRequest) {
        return validateUsers(noteRequest.userId())
                .then(validateUsers(noteRequest.userEditId()))
                .then(deletedNoteProducts(noteRequest.deletedProductsId()))
                .then(validateProductsForUpdateNote(noteRequest.products()))
                .flatMap(volume -> updateNoteAndReturnUUID(noteId, noteRequest, volume))
                .then();
    }

    private Mono<Void> validateUsers(UUID userId) {
        // Проверить наличие клиентской записи в бд
        return userService.findById(String.valueOf(userId))
                .switchIfEmpty(Mono.error(new ClientNotFoundException()))
                .then();
    }

    private Mono<Double> validateProducts(List<NoteProductDTO> products) {
        // Проверить наличие всех продуктов в сервисе productService
        return Flux.fromIterable(products)
                .flatMap(product -> productService.getProductById(product.productId())
                        .switchIfEmpty(Mono.error(new NotFoundProductException()))
                        .map(p -> p.concreteVolume() * product.value()))
                        .reduce(Double::sum);
    }

    private Mono<Double> validateProductsForUpdateNote(List<NoteProductDTO> products) {
        // Проверить наличие всех продуктов в сервисе productService
        if (Objects.isNull(products) || products.isEmpty()) {
            return Mono.just(0.0);
        }
        return Flux.fromIterable(products)
                .flatMap(product -> productService.getProductById(product.productId())
                        .switchIfEmpty(Mono.error(new NotFoundProductException()))
                        .map(p -> p.concreteVolume() * product.value()))
                .reduce(Double::sum);
    }

    private Mono<Void> deletedNoteProducts(List<Long> productIds) {
        // Проверить наличие всех продуктов и удалить их
        if (Objects.isNull(productIds) || productIds.isEmpty()) {
            return Mono.empty();
        }
        return Flux.fromIterable(productIds)
                .flatMap(noteProductService::deleteNoteProduct)
                .then();
    }

    private Mono<Void> saveNoteAndReturnUUID(NoteRequest noteRequest, Double productsVolume) {
        // Сохранить заметку и вернуть ее UUID
        return noteService.saveNote(noteRequest, productsVolume)
                .flatMap(note -> noteProductService.save(note.id(), noteRequest.products())
                            .then(noteProductService.save(note.redirectionId(), noteRequest.products())));
    }

    private Mono<Void> updateNoteAndReturnUUID(UUID noteId, NoteRequest noteRequest, Double productsVolume) {
        // Обновить заметку и вернуть ее UUID
        return noteService.updateNote(noteId, noteRequest, productsVolume)
                .flatMap(note -> noteProductService.update(note.id(), noteRequest.products())
                        .then(noteProductService.save(note.redirectionId(), noteRequest.products())));
    }
}
