package ru.awp.enterprise.automation.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dao.NoteDAO;
import ru.awp.enterprise.automation.models.dto.NoteDTO;
import ru.awp.enterprise.automation.models.request.NoteRequest;

import java.util.UUID;

public interface NoteService {

    Flux<NoteDTO> findByArea(String area);

    Mono<NoteDTO> findById(UUID uuid);

    Flux<NoteDTO> updateArea(String oldArea, String newArea);

    Mono<NoteDTO> updateNote(UUID uuid, NoteDTO noteDTO);

    Mono<NoteDAO> save(NoteRequest request);

}