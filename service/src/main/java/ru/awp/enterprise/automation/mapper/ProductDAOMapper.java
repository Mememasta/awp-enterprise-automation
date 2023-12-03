package ru.awp.enterprise.automation.mapper;

import org.springframework.stereotype.Component;
import ru.awp.enterprise.automation.models.dao.ProductDAO;
import ru.awp.enterprise.automation.models.request.ProductRequest;

import java.util.function.Function;

@Component
public class ProductDAOMapper implements Function<ProductRequest, ProductDAO> {

    @Override
    public ProductDAO apply(ProductRequest request) {
        return ProductDAO.builder()
                .name(request.name())
                .isAvailable(request.isAvailable())
                .concreteVolume(request.concreteVolume())
                .build();
    }

    public ProductDAO apply(Long id, ProductRequest request) {
        return ProductDAO.builder()
                .productId(id)
                .name(request.name())
                .isAvailable(request.isAvailable())
                .concreteVolume(request.concreteVolume())
                .build();
    }
}
