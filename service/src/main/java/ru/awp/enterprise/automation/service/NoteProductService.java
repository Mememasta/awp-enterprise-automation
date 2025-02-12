package ru.awp.enterprise.automation.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dto.NoteProductDTO;
import ru.awp.enterprise.automation.models.dto.TotalValueByAreaAndProductId;

import java.util.List;
import java.util.UUID;

public interface NoteProductService {

    Mono<Void> save(UUID uuid, List<NoteProductDTO> productDTO);
    Mono<Void> update(UUID uuid, List<NoteProductDTO> productDTO);
    Mono<Void> updateRedirectionNote(UUID uuid, List<NoteProductDTO> productDTO);
    Flux<NoteProductDTO> findNoteProducts(UUID uuid);
    Flux<TotalValueByAreaAndProductId> findAllTotalValue();
    Mono<Void> deleteNoteProduct(Long noteProductId);
    Mono<Void> deleteNoteProductByNoteId(UUID noteId);

}
