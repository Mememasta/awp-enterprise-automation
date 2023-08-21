package ru.awp.enterprise.automation.service.impl;

import lombok.RequiredArgsConstructor;
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
        return noteRepository.findAllByArea(areaId);
    }

    @Override
    public Mono<NoteDAO> findById(UUID uuid) {
        return noteRepository.findById(uuid);
    }

    @Override
    public Flux<NoteDAO> findAll() {
        return noteRepository.findAll();
    }

    @Override
    public Mono<NoteDAO> updateNote(UUID uuid, NoteRequest noteRequest) {
        return this.findById(uuid)
                .map(note -> noteDAOMapper.apply(uuid, noteRequest))
                .flatMap(noteRepository::save);
    }

    @Override
    public Mono<NoteDAO> saveNote(NoteRequest request) {
        return noteRepository.save(noteDAOMapper.apply(request));
    }
}