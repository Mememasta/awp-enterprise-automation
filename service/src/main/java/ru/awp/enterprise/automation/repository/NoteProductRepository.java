package ru.awp.enterprise.automation.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ru.awp.enterprise.automation.models.dao.NoteProductDAO;

import java.util.UUID;

@Repository
public interface NoteProductRepository extends ReactiveCrudRepository<NoteProductDAO, Long> {

    Flux<NoteProductDAO> findAllByNoteId(UUID noteId);
    Flux<NoteProductDAO> findAllByNoteIdAndProductId(UUID noteId, Long productId);

}
