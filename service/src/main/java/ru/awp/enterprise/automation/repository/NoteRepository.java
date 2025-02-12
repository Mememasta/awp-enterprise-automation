package ru.awp.enterprise.automation.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ru.awp.enterprise.automation.models.dao.NoteDAO;

import java.util.UUID;

@Repository
public interface NoteRepository extends ReactiveCrudRepository<NoteDAO, UUID> {

    Flux<NoteDAO> findAllByArea(Integer areaId, Sort sort);
    Flux<NoteDAO> findAllByArea(Integer areaId, Pageable pageable);
    Flux<NoteDAO> findAllByUser(UUID userId, Sort sort);
    Flux<NoteDAO> findAll(Sort sort);

}
