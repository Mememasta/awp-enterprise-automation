package ru.awp.enterprise.automation.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record ProductDTO(
        @JsonProperty(value = "product_id")
        Long productId,
        @JsonProperty(value = "name")
        String name,
        @JsonProperty(value = "is_available")
        boolean isAvailable,
        @JsonProperty(value = "concrete_volume")
        double concreteVolume
) {
}
