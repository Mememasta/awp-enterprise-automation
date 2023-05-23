package ru.awp.enterprise.automation.models.dao;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Builder
@Table(value = "users")
public record UserDAO(
        @Id
        UUID id,
        @Column(value = "first_name")
        String firstName,
        @Column(value = "last_name")
        String lastName,
        @Column(value = "phone_number")
        String phoneNumber,
        @Column(value = "password")
        String password,
        @Column(value = "authorities")
        List<String> authorities,
        @Column(value = "hash")
        String hash
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
