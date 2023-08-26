package ru.awp.enterprise.automation.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.exception.ClientNotFoundException;
import ru.awp.enterprise.automation.exception.NotFoundProductException;
import ru.awp.enterprise.automation.mapper.NoteDTOMapper;
import ru.awp.enterprise.automation.mapper.NoteUserResponseMapper;
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
    private final NoteUserResponseMapper noteUserResponseMapper;

    @Override
    public Flux<NoteDTO> findNoteAndProduct(Integer areaId) {
        return noteService.findByArea(areaId)
                .flatMap(this::buildNoteToDTO);
    }

    @Override
    public Flux<NoteDTO> findAll() {
        return noteService.findAll()
                .flatMap(this::buildNoteToDTO);
    }

    @Override
    public Flux<NoteDTO> findNoteByUserId(UUID uuid) {
        return noteService.findByUserId(uuid)
                .flatMap(this::buildNoteToDTO);
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
                .map(noteUserResponseMapper);
        var editUserMono = userService.findById(note.user_edit())
                .map(noteUserResponseMapper)
                .switchIfEmpty(Mono.just(noteUserResponseMapper.apply(null)));
        var productsVolume = noteProductsMono
                .map(products -> products.stream()
                        .map(NoteProductDTO::productId)
                        .toList())
                .filter(ids -> !ids.isEmpty())
                .flatMap(productService::getProductsVolume)
                .switchIfEmpty(Mono.just(0.0));

        return Mono.zip(Mono.just(note), noteProductsMono, userMono, editUserMono, productsVolume)
                .map(tuple -> noteDTOMapper.map(tuple.getT1(), tuple.getT2(), tuple.getT3(), tuple.getT4(), tuple.getT5()));
    }

    @Override
    @Transactional
    public Mono<Void> validateAndSaveNoteAndProduct(NoteRequest noteRequest) {
        return validateUsers(noteRequest.userId())
                .then(validateProducts(noteRequest.products()))
                .then(saveNoteAndReturnUUID(noteRequest))
                .flatMap(uuid -> noteProductService.save(uuid, noteRequest.products()));
    }

    @Override
    @Transactional
    public Mono<Void> validateAndUpdateNoteAndProduct(UUID noteId, NoteRequest noteRequest) {
        return validateUsers(noteRequest.userId())
                .then(validateUsers(noteRequest.userEditId()))
                .then(deletedNoteProducts(noteRequest.deletedProductsId()))
                .then(updateNoteAndReturnUUID(noteId, noteRequest))
                .flatMap(uuid -> noteProductService.update(uuid, noteRequest.products()))
                .then();
    }

    private Mono<Void> validateUsers(UUID userId) {
        // Проверить наличие клиентской записи в бд
        return userService.findById(String.valueOf(userId))
                .switchIfEmpty(Mono.error(new ClientNotFoundException()))
                .then();
    }

    private Mono<Void> validateProducts(List<NoteProductDTO> products) {
        // Проверить наличие всех продуктов в сервисе productService
        return Flux.fromIterable(products)
                .flatMap(product -> productService.getProductById(product.productId())
                        .switchIfEmpty(Mono.error(new NotFoundProductException())))
                .then();
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

    private Mono<UUID> saveNoteAndReturnUUID(NoteRequest request) {
        // Сохранить заметку и вернуть ее UUID
        return noteService.saveNote(request)
                .map(NoteDAO::id);
    }

    private Mono<UUID> updateNoteAndReturnUUID(UUID noteId, NoteRequest noteRequest) {
        // Обновить заметку и вернуть ее UUID
        return noteService.updateNote(noteId, noteRequest)
                .map(NoteDAO::id);
    }
}
