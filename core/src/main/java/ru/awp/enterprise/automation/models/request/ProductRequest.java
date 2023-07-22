package ru.awp.enterprise.automation.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequest(
        @NotNull
        @JsonProperty(value = "product_id")
        Long productId,

        @NotBlank
        @JsonProperty(value = "name")
        String name,

        @NotNull
        @JsonProperty(value = "is_available")
        Boolean isAvailable
) {
}
