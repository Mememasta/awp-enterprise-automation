package ru.awp.enterprise.automation.models.dto;

public record TotalValueByAreaAndProductId(
        Integer area,
        Long product_id,
        Integer status_0,
        Integer status_1,
        Integer status_2
) {
}
