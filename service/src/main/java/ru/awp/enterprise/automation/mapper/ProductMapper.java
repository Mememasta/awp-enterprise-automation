package ru.awp.enterprise.automation.mapper;

import org.springframework.stereotype.Component;
import ru.awp.enterprise.automation.models.dao.ProductDAO;
import ru.awp.enterprise.automation.models.dto.ProductDTO;

import java.util.function.Function;

@Component
public class ProductMapper implements Function<ProductDAO, ProductDTO> {
    @Override
    public ProductDTO apply(ProductDAO productDAO) {
        return ProductDTO.builder()
                .productId(productDAO.productId())
                .name(productDAO.name())
                .isAvailable(productDAO.isAvailable())
                .concreteVolume(productDAO.concreteVolume())
                .valueByArea(productDAO.valueByArea())
                .build();
    }
}
