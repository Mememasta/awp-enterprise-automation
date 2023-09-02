package ru.awp.enterprise.automation.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import ru.awp.enterprise.automation.models.dao.ReportCardUserDAO;

import java.util.UUID;

public interface ReportCardUserRepository extends ReactiveCrudRepository<ReportCardUserDAO, Long> {

    Flux<ReportCardUserDAO> findAllByReportCardId(UUID reportCardId);

}
