package ru.awp.enterprise.automation.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dao.NoteDAO;
import ru.awp.enterprise.automation.models.request.NoteRequest;

import java.util.UUID;

public interface NoteService {

    Flux<NoteDAO> findByArea(Integer areaId);

    Flux<NoteDAO> findByUserId(UUID userId);

    Mono<NoteDAO> findById(UUID uuid);

    Flux<NoteDAO> findAll();

    Mono<NoteDAO> updateNote(UUID uuid, NoteRequest noteRequest, Double productsVolume);

    Mono<NoteDAO> saveNote(NoteRequest request, Double productsVolume);
    Mono<Void> deleteNote(UUID noteId);

}