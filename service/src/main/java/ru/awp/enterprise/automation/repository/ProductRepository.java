package ru.awp.enterprise.automation.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dao.ProductDAO;

import java.util.List;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<ProductDAO, Long> {

    Mono<ProductDAO> findByNameIgnoreCase(String name);

    @Query("SELECT SUM(concrete_volume) FROM awp_enterprise_automation.products WHERE product_id IN (:ids)")
    Mono<Double> getSumVolume(@Param("ids") List<Long> productIds);

}
