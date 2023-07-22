package ru.awp.enterprise.automation.models.dao;

import lombok.Builder;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Builder
@Table(name = "products")
public record ProductDAO(

        @Column(value = "product_id")
        Long productId,
        @Column(value = "name")
        String name,
        @Column(value = "is_available")
        boolean isAvailable

) {
}
