package ru.awp.enterprise.automation.models.dto;

import lombok.Builder;

@Builder
public record ProductDTO(
        Long productId,
        String name,
        boolean isAvailable
) {
}
