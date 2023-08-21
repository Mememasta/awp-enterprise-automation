package ru.awp.enterprise.automation.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record NoteProductDTO(

        Long id,

        /*
          Продукт
         */
        @JsonProperty(value = "product_id")
        Long productId,

        /*
          Кол-во товара
         */
        @JsonProperty(value = "value")
        Long value,

        /*
          Является ли продукт бракованным
         */
        @JsonProperty(value = "is_defect")
        boolean isDefect

) {
}
