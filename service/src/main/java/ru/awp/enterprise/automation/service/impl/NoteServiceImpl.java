package ru.awp.enterprise.automation.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.mapper.NoteDAOMapper;
import ru.awp.enterprise.automation.models.dao.NoteDAO;
import ru.awp.enterprise.automation.models.dto.NoteDTO;
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
    public Flux<NoteDTO> findByArea(String area) {
        return null;
    }

    @Override
    public Mono<NoteDTO> findById(UUID uuid) {
        return null;
    }

    @Override
    public Flux<NoteDTO> updateArea(String oldArea, String newArea) {
        return null;
    }

    @Override
    public Mono<NoteDTO> updateNote(UUID uuid, NoteDTO noteDTO) {
        return null;
    }

    @Override
    public Mono<NoteDAO> save(NoteRequest request) {
        return noteRepository.save(noteDAOMapper.apply(request));
    }
}