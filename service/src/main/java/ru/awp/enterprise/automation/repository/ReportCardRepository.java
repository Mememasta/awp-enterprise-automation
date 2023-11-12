package ru.awp.enterprise.automation.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import ru.awp.enterprise.automation.models.dao.ReportCardDAO;

import java.util.UUID;

public interface ReportCardRepository extends ReactiveCrudRepository<ReportCardDAO, UUID> {

    Flux<ReportCardDAO> findAllByArea(Integer areaId, Sort sort);
    Flux<ReportCardDAO> findAllByArea(Integer areaId, Pageable pageable);
    Flux<ReportCardDAO> findAllByUser(UUID userId, Sort sort);
    Flux<ReportCardDAO> findAll(Sort sort);

}
