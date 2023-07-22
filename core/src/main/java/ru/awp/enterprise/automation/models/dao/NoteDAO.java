package ru.awp.enterprise.automation.models.dao;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Builder
@Table(name = "notes")
public record NoteDAO(
    @Id
    UUID id,
    @Column(value = "user_id")
    UUID user,
    @Column(value = "user_edit_id")
    UUID user_edit,
    @Column(value = "created")
    OffsetDateTime created,
    @Column(value = "updated")
    OffsetDateTime updated,
    @Column(value = "area")
    String area,
    @Column(value = "status")
    Integer status,
    @Column(value = "comment")
    String comment
) implements Persistable<UUID> {

    @Override
    public UUID getId() {
        return Objects.isNull(this.id) ? UUID.randomUUID() : this.id;
    }

    @Override
    @Transient
    public boolean isNew() {
        return Objects.isNull(this.id);
    }
}
