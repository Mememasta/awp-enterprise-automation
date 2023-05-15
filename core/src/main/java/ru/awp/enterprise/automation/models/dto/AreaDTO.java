package ru.awp.enterprise.automation.models.dto;

import java.util.List;
import java.util.UUID;

public record AreaDTO(
        /*
          Идентификатор производства
         */
        UUID id,

        /*
          Список рабочих
         */
        List<UserDTO> users,

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
