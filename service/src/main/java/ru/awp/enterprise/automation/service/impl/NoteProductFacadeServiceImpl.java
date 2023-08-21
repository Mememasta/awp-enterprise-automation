package ru.awp.enterprise.automation.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.exception.ClientNotFoundException;
import ru.awp.enterprise.automation.exception.ProductNotFoundException;
import ru.awp.enterprise.automation.mapper.NoteDTOMapper;
import ru.awp.enterprise.automation.mapper.NoteUserResponseMapper;
import ru.awp.enterprise.automation.models.dao.NoteDAO;
import ru.awp.enterprise.automation.models.dto.NoteDTO;
import ru.awp.enterprise.automation.models.dto.NoteProductDTO;
import ru.awp.enterprise.automation.models.request.NoteRequest;
import ru.awp.enterprise.automation.service.*;

import java.util.List;
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
    public Mono<NoteDTO> findById(UUID noteId) {
        return noteService.findById(noteId)
                .flatMap(this::buildNoteToDTO);    }

    private Mono<NoteDTO> buildNoteToDTO(NoteDAO note) {
        var noteProductsMono = noteProductService.findNoteProducts(note.id())
                .collectList();
        var userMono = userService.findById(note.user())
                .map(noteUserResponseMapper);
        var editUserMono = userService.findById(note.user_edit())
                .map(noteUserResponseMapper)
                .switchIfEmpty(Mono.just(noteUserResponseMapper.apply(null)));

        return Mono.zip(Mono.just(note), noteProductsMono, userMono, editUserMono)
                .map(tuple -> noteDTOMapper.map(tuple.getT1(), tuple.getT2(), tuple.getT3(), tuple.getT4()));
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
    public Mono<Void> validateAndUpdateNoteAndProduct(UUID noteId, NoteRequest noteRequest) {
        return validateUsers(noteRequest.userId())
                .then(validateUsers(noteRequest.userEditId()))
                .then(validateProducts(noteRequest.products()))
                .then(updateNoteAndReturnUUID(noteId, noteRequest))
                .flatMap(uuid -> noteProductService.save(uuid, noteRequest.products()));
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
                .flatMap(p -> productService.getProductById(p.productId())
                        .switchIfEmpty(Mono.error(new ProductNotFoundException())))
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
