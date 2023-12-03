package ru.awp.enterprise.automation.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record ProductWithBalanceDTO(

        @JsonProperty(value = "area_id")
        Integer areaId,
        @JsonProperty(value = "product_id")
        Long productId,
        @JsonProperty(value = "coefficient")
        Integer coefficient,
        @JsonProperty(value = "balance")
        Integer balance

) {
}
