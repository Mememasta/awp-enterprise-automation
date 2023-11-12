package ru.awp.enterprise.automation.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dto.NoteDTO;
import ru.awp.enterprise.automation.models.request.NoteRequest;
import ru.awp.enterprise.automation.service.NoteProductFacadeService;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NoteController implements NoteApi {

    private final NoteProductFacadeService noteProductFacadeService;

    @Override
    public Flux<NoteDTO> findNotesByAreaId(Integer areaId, Integer page, Integer size) {
        return noteProductFacadeService.findNoteAndProduct(areaId, page, size);
    }

    @Override
    public Flux<NoteDTO> findAllNote() {
        return noteProductFacadeService.findAll();
    }

    @Override
    public Mono<NoteDTO> getNotesById(UUID uuid) {
        return noteProductFacadeService.findById(uuid);
    }

    @Override
    public Flux<NoteDTO> getNotesByUserId(UUID userId) {
        return noteProductFacadeService.findNoteByUserId(userId);
    }

    @Override
    public Mono<Void> updateNote(UUID uuid, NoteRequest request) {
        return noteProductFacadeService.validateAndUpdateNoteAndProduct(uuid, request);
    }

    @Override
    public Mono<Void> add(NoteRequest request) {
        return noteProductFacadeService.validateAndSaveNoteAndProduct(request);
    }
}
