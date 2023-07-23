package ru.awp.enterprise.automation.models.dao;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Builder
@Table(name = "products")
public record ProductDAO(

        @Id
        @Column(value = "product_id")
        Long productId,
        @Column(value = "name")
        String name,
        @Column(value = "is_available")
        boolean isAvailable

)  implements Persistable<Long> {
        @Override
        public Long getId() {
                return productId;
        }

        @Override
        public boolean isNew() {
                return Objects.isNull(getId());
        }
}
