package ru.awp.enterprise.automation.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.mapper.NoteDAOMapper;
import ru.awp.enterprise.automation.models.dao.NoteDAO;
import ru.awp.enterprise.automation.models.request.NoteRequest;
import ru.awp.enterprise.automation.repository.NoteRepository;
import ru.awp.enterprise.automation.service.NoteService;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final NoteDAOMapper noteDAOMapper;


    @Override
    public Flux<NoteDAO> findByArea(Integer areaId) {
        return noteRepository.findAllByArea(areaId, Sort.by("created"));
    }

    @Override
    public Flux<NoteDAO> findByUserId(UUID userId) {
        return noteRepository.findAllByUser(userId, Sort.by("created"));
    }

    @Override
    public Mono<NoteDAO> findById(UUID uuid) {
        return noteRepository.findById(uuid);
    }

    @Override
    public Flux<NoteDAO> findAll() {
        return noteRepository.findAll(Sort.by("created"));
    }

    @Override
    @Transactional
    public Mono<NoteDAO> updateNote(UUID uuid, NoteRequest noteRequest, Double productsVolume) {
        return Mono.justOrEmpty(noteRequest.redirectionId())
                .flatMap(noteId -> this.findById(noteId)
                        .map(note -> noteDAOMapper.updateRedirectionNote(note, noteRequest, productsVolume))
                        .flatMap(noteRepository::save)
                        .then(this.findById(uuid)
                                .map(note -> noteDAOMapper.apply(note, noteRequest, productsVolume))
                                .flatMap(noteRepository::save)))
                .switchIfEmpty(this.findById(uuid)
                        .filter(note -> Objects.nonNull(note.redirection()) && Objects.isNull(note.redirectionId()))
                        .flatMap(note -> noteRepository.save(noteDAOMapper.applyRedirectionNote(noteRequest, productsVolume))
                                .flatMap(redirectionNote -> noteRepository.save(noteDAOMapper.updateNoteByRedirectionId(redirectionNote.id(), note, noteRequest, productsVolume))))

                        .switchIfEmpty(this.findById(uuid)
                                .map(note -> noteDAOMapper.apply(note, noteRequest, productsVolume))
                                .flatMap(noteRepository::save)));
    }

    @Override
    @Transactional
    public Mono<NoteDAO> saveNote(NoteRequest request, Double productsVolume) {
        return Mono.justOrEmpty(request.redirection())
                .flatMap(redirection -> noteRepository.save(noteDAOMapper.applyRedirectionNote(request, productsVolume))
                        .flatMap(redirectionNote -> noteRepository.save(noteDAOMapper.applyRedirectionNote(redirectionNote, request, productsVolume))))
                .switchIfEmpty(Mono.defer(() -> noteRepository.save(noteDAOMapper.apply(request, productsVolume))));
    }
}