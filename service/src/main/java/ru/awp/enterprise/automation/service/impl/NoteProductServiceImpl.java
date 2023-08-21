package ru.awp.enterprise.automation.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.exception.NoteNotFoundException;
import ru.awp.enterprise.automation.mapper.NoteProductDAOMapper;
import ru.awp.enterprise.automation.models.dto.NoteProductDTO;
import ru.awp.enterprise.automation.repository.NoteProductRepository;
import ru.awp.enterprise.automation.service.NoteProductService;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoteProductServiceImpl implements NoteProductService {

    private final NoteProductRepository noteProductRepository;
    private final NoteProductDAOMapper noteProductDAOMapper;

    public Mono<Void> save(UUID uuid, List<NoteProductDTO> productDTO) {
        if (Objects.isNull(uuid)) {
            throw new NoteNotFoundException();
        }
        return Flux.fromIterable(productDTO)
                .map(noteProduct -> noteProductDAOMapper.apply(uuid, noteProduct))
                .flatMap(noteProductRepository::save)
                .then(Mono.empty());
    }

    @Override
    public Flux<NoteProductDTO> findNoteProducts(UUID uuid) {
        return noteProductRepository.findAllByNoteId(uuid)
                .map(noteProductDAOMapper::apply);
    }

}
