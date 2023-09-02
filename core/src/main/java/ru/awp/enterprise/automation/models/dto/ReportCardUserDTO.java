package ru.awp.enterprise.automation.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.UUID;

@Builder
public record ReportCardUserDTO(

        Long id,

        /*
          Продукт
         */
        @JsonProperty(value = "user_id")
        UUID userId,

        /*
          Кол-во товара
         */
        @JsonProperty(value = "hours")
        Double hours

) {
}
