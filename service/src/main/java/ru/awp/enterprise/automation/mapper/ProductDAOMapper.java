package ru.awp.enterprise.automation.mapper;

import org.springframework.stereotype.Component;
import ru.awp.enterprise.automation.models.converter.ValueByAreaMap;
import ru.awp.enterprise.automation.models.dao.ProductDAO;
import ru.awp.enterprise.automation.models.dto.ProductDTO;
import ru.awp.enterprise.automation.models.request.ProductRequest;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@Component
public class ProductDAOMapper implements Function<ProductRequest, ProductDAO> {

    @Override
    public ProductDAO apply(ProductRequest request) {
        return ProductDAO.builder()
                .name(request.name())
                .isAvailable(request.isAvailable())
                .concreteVolume(request.concreteVolume())
                .valueByArea(List.of())
                .build();
    }

    public ProductDAO apply(Long id, ProductRequest request) {
        return ProductDAO.builder()
                .productId(id)
                .name(request.name())
                .isAvailable(request.isAvailable())
                .concreteVolume(request.concreteVolume())
                .valueByArea(request.valueByArea())
                .build();
    }

    public ProductDAO apply(ProductDTO productDTO, ValueByAreaMap valueByAreaMap) {
        var currentValueByAreaMap = productDTO.valueByArea();
        for (ValueByAreaMap valueByArea: currentValueByAreaMap) {
            if (Objects.equals(valueByArea.getArea(), valueByAreaMap.getArea())) {
                var value = valueByArea.getValue() + valueByAreaMap.getValue();
                valueByArea.setValue(value);
            }
        }
        if (currentValueByAreaMap.isEmpty() || currentValueByAreaMap.stream().noneMatch(product -> Objects.equals(product.getArea(), valueByAreaMap.getArea()))) {
            currentValueByAreaMap.add(valueByAreaMap);
        }
        return ProductDAO.builder()
                .productId(productDTO.productId())
                .name(productDTO.name())
                .isAvailable(productDTO.isAvailable())
                .concreteVolume(productDTO.concreteVolume())
                .valueByArea(currentValueByAreaMap)
                .build();
    }

    public ProductDAO apply(ProductDTO productDTO, List<ValueByAreaMap> valueByAreaMap) {
        return ProductDAO.builder()
                .productId(productDTO.productId())
                .name(productDTO.name())
                .isAvailable(productDTO.isAvailable())
                .concreteVolume(productDTO.concreteVolume())
                .valueByArea(valueByAreaMap)
                .build();
    }

}
