package ru.awp.enterprise.automation.models.dto;

import lombok.Builder;

@Builder
public record NoteProductDTO(

        /*
          Продукт
         */
        Long productId,

        /*
          Кол-во товара
         */
        Long value,

        /*
          Является ли продукт бракованным
         */
        boolean isDefect

) {
}
