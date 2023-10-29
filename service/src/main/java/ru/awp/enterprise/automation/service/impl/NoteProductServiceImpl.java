package ru.awp.enterprise.automation.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.exception.NoteNotFoundException;
import ru.awp.enterprise.automation.exception.NoteProductNotFoundException;
import ru.awp.enterprise.automation.exception.ProductDeleteException;
import ru.awp.enterprise.automation.mapper.NoteProductDAOMapper;
import ru.awp.enterprise.automation.models.dao.NoteProductDAO;
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

    @Override
    public Mono<Void> save(UUID uuid, List<NoteProductDTO> productDTO) {
        if (Objects.isNull(uuid)) {
            return Mono.empty();
        }
        return Flux.fromIterable(productDTO)
                .map(noteProduct -> noteProductDAOMapper.apply(uuid, noteProduct))
                .flatMap(noteProductRepository::save)
                .then(Mono.empty());
    }

    @Override
    public Mono<Void> update(UUID uuid, List<NoteProductDTO> productDTO) {
        if (Objects.isNull(uuid)) {
            return Mono.empty();
        }
        if (Objects.isNull(productDTO) || productDTO.isEmpty()) {
            return Mono.empty();
        }
        return Flux.fromIterable(productDTO)
                .flatMap(product -> buildNoteProduct(uuid, product))
                .flatMap(noteProductRepository::save)
                .then(Mono.empty());
    }

    @Override
    public Mono<Void> updateRedirectionNote(UUID uuid, List<NoteProductDTO> productDTO) {
        return deleteNoteProductByNoteId(uuid)
                .then(save(uuid, productDTO));
    }

    private Mono<NoteProductDAO> buildNoteProduct(UUID uuid, NoteProductDTO noteProductDTO) {
        if (Objects.isNull(noteProductDTO.id())) {
            return Mono.just(noteProductDAOMapper.apply(uuid, noteProductDTO));
        }
        return noteProductRepository.findById(noteProductDTO.id())
                .switchIfEmpty(Mono.error(new NoteProductNotFoundException()));
    }

    @Override
    public Flux<NoteProductDTO> findNoteProducts(UUID uuid) {
        return noteProductRepository.findAllByNoteId(uuid)
                .map(noteProductDAOMapper::apply);
    }

    @Override
    public Mono<Void> deleteNoteProduct(Long noteProductId) {
        return noteProductRepository.findById(noteProductId)
                .switchIfEmpty(Mono.error(ProductDeleteException::new))
                .flatMap(noteProductRepository::delete);
    }

    @Override
    public Mono<Void> deleteNoteProductByNoteId(UUID noteId) {
        return noteProductRepository.findAllByNoteId(noteId)
                .flatMap(noteProduct -> deleteNoteProduct(noteProduct.id()))
                .then();
    }

}
