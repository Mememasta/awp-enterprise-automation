package ru.awp.enterprise.automation.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dto.NoteProductDTO;

import java.util.List;
import java.util.UUID;

public interface NoteProductService {

    Mono<Void> save(UUID uuid, List<NoteProductDTO> productDTO);

    Flux<NoteProductDTO> findNoteProducts(UUID uuid);

}
