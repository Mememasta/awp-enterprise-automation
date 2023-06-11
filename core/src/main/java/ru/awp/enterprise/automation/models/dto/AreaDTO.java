package ru.awp.enterprise.automation.models.dto;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record AreaDTO(
        /*
          Идентификатор производства
         */
        UUID id,

        /*
          Название производства
         */
        String name,

        /*
          Продукция на производстве
         */
        List<ProductDTO> products
) {
}
