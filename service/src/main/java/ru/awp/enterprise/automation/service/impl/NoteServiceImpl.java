package ru.awp.enterprise.automation.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.mapper.NoteDAOMapper;
import ru.awp.enterprise.automation.models.dao.NoteDAO;
import ru.awp.enterprise.automation.models.request.NoteRequest;
import ru.awp.enterprise.automation.repository.NoteRepository;
import ru.awp.enterprise.automation.service.NoteService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final NoteDAOMapper noteDAOMapper;


    @Override
    public Flux<NoteDAO> findByArea(Integer areaId) {
        return noteRepository.findAllByArea(areaId, Sort.by("id"));
    }

    @Override
    public Flux<NoteDAO> findByUserId(UUID userId) {
        return noteRepository.findAllByUser(userId, Sort.by("id"));
    }

    @Override
    public Mono<NoteDAO> findById(UUID uuid) {
        return noteRepository.findById(uuid);
    }

    @Override
    public Flux<NoteDAO> findAll() {
        return noteRepository.findAll(Sort.by("id"));
    }

    @Override
    public Mono<NoteDAO> updateNote(UUID uuid, NoteRequest noteRequest, Double productsVolume) {
        return this.findById(uuid)
                .map(note -> noteDAOMapper.apply(note, noteRequest, productsVolume))
                .flatMap(noteRepository::save);
    }

    @Override
    public Mono<NoteDAO> saveNote(NoteRequest request, Double productsVolume) {
        return noteRepository.save(noteDAOMapper.apply(request, productsVolume));
    }
}