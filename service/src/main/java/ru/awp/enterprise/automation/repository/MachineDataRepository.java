package ru.awp.enterprise.automation.repository;

import java.time.LocalDateTime;
import org.springframework.data.domain.Sort;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dao.MachineDataDAO;

/**
 * @author MCHuchalov on 16.12.2023
 */
@Repository
public interface MachineDataRepository extends ReactiveSortingRepository<MachineDataDAO, Long> {

    @Query("SELECT nextval('machine_data_seq')")
    Mono<Long> getNextId();

    @Query("SELECT * FROM awp_enterprise_automation.machine_data m WHERE m.topic = :topic AND m.event_date BETWEEN :startDate AND :endDate")
    Flux<MachineDataDAO> findByTopicAndDateRange(@Param("topic") String topic, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, Sort sort);

    @Modifying
    @Query("INSERT INTO machine_data (id, topic, value, event_date) VALUES (:#{#dao.id}, :#{#dao.topic}, :#{#dao.value}, :#{#dao.eventDate}) ON CONFLICT (id) DO UPDATE SET topic = :#{#dao.topic}, value = :#{#dao.value}, event_date = :#{#dao.eventDate}")
    Mono<Void> saveOrUpdate(@Param("dao") MachineDataDAO dao);

}
