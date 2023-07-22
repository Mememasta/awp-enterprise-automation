package ru.awp.enterprise.automation.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import ru.awp.enterprise.automation.models.dao.NoteProductDAO;

@Repository
public interface NoteProductRepository extends ReactiveCrudRepository<NoteProductDAO, Long> {
}
