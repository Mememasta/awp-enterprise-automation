package ru.awp.enterprise.automation.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ru.awp.enterprise.automation.models.dao.IotDAO;

import java.util.UUID;

/**
 * @author MCHuchalov on 16.12.2023
 */
@Repository
public interface IotRepository extends ReactiveCrudRepository<IotDAO, UUID> {

    Flux<IotDAO> findAllByArea(Integer areaId);

    Flux<IotDAO> findAll(Sort sort);

}
