package ru.awp.enterprise.automation.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dto.NoteDTO;
import ru.awp.enterprise.automation.models.request.NoteRequest;

import java.util.UUID;

public interface NoteProductFacadeService {

    Flux<NoteDTO> findNoteAndProduct(Integer areaId, Integer page, Integer size);

    Flux<NoteDTO> findAll();

    Flux<NoteDTO> findNoteByUserId(UUID uuid);

    Mono<NoteDTO> findById(UUID noteId);

    Mono<Void> validateAndSaveNoteAndProduct(NoteRequest noteRequest);

    Mono<Void> validateAndUpdateNoteAndProduct(UUID noteId, NoteRequest noteRequest);

}
