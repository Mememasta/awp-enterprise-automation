package ru.awp.enterprise.automation.models.dao;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Builder
@Table(name = "products_with_balance")
public record ProductWithBalanceDAO(

        @Id
        @Column(value = "id")
        Long id,
        @Column(value = "area_id")
        Integer areaId,
        @Column(value = "product_id")
        Long productId,
        @Column(value = "coefficient")
        Integer coefficient
) {
}
