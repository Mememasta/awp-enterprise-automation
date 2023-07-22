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
                .productId(request.productId())
                .name(request.name())
                .isAvailable(request.isAvailable())
                .build();
    }

}
