package ru.awp.enterprise.automation.models.dao;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;
import java.util.UUID;

@Builder
@Table(name = "notes_products")
public record NoteProductDAO(

        @Id
        Long id,
        @Column(value = "note_id")
        UUID noteId,
        @Column(value = "product_id")
        Long productId,
        @Column(value = "value")
        Long value,
        @Column(value = "is_defect")
        boolean isDefect

) implements Persistable<Long> {


        @Override
        public Long getId() {
                return this.id;
        }

        @Override
        @Transient
        public boolean isNew() {
                return Objects.isNull(this.id);
        }
}
