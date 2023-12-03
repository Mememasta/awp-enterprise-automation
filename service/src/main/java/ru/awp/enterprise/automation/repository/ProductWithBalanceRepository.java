package ru.awp.enterprise.automation.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dao.ProductWithBalanceDAO;

public interface ProductWithBalanceRepository extends ReactiveCrudRepository<ProductWithBalanceDAO, Integer> {

    Mono<ProductWithBalanceDAO> findByAreaIdAndProductId(Integer areaId, Long productId);

}
