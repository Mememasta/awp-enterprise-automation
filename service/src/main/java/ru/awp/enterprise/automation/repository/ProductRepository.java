package ru.awp.enterprise.automation.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import ru.awp.enterprise.automation.models.dao.ProductDAO;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<ProductDAO, Long> {
}
