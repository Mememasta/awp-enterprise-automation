package ru.awp.enterprise.automation.models.dto;

import lombok.Builder;

import java.time.OffsetDateTime;

@Builder
public record ProductDTO(
        /*
          Идентификатор продукта
         */
        Long id,

        /*
          Кол-во товара
         */
        Long value,

        /*
          Является ли продукт бракованным
         */
        boolean isDefect,

        /*
          Рабочий ответственный за продукт
         */
        UserDTO userDTO,

        /*
          Дата и время учета продукта
         */
        OffsetDateTime date,

        /*
          Производство на котором находится продукт
         */
        AreaDTO area,

        /*
          Состояние продукции (Произведено/Отгружено)
         */
        String status
) {
}
