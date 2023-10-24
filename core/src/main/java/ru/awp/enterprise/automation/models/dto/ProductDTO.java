package ru.awp.enterprise.automation.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import ru.awp.enterprise.automation.models.converter.ValueByAreaMap;

import java.util.List;

@Builder
public record ProductDTO(
        @JsonProperty(value = "product_id")
        Long productId,
        @JsonProperty(value = "name")
        String name,
        @JsonProperty(value = "is_available")
        boolean isAvailable,
        @JsonProperty(value = "concrete_volume")
        double concreteVolume,
        @JsonProperty(value = "value_by_area")
        List<ValueByAreaMap> valueByArea
) {
}
