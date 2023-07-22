package ru.awp.enterprise.automation.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import ru.awp.enterprise.automation.models.dao.NoteDAO;

import java.util.UUID;

@Repository
public interface NoteRepository extends ReactiveCrudRepository<NoteDAO, UUID> {
}
