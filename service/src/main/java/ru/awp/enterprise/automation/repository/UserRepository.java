package ru.awp.enterprise.automation.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dao.UserDAO;

import java.util.UUID;

@Repository
public interface UserRepository extends ReactiveCrudRepository<UserDAO, UUID> {

    Mono<UserDAO> findFirstByPhoneNumber(String phoneNumber);

}
