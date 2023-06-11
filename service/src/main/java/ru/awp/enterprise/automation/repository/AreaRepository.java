package ru.awp.enterprise.automation.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dao.AreaDAO;
import ru.awp.enterprise.automation.models.dto.AreaDTO;

import java.util.UUID;

@Repository
public interface AreaRepository extends ReactiveCrudRepository<AreaDAO, UUID> {


}
