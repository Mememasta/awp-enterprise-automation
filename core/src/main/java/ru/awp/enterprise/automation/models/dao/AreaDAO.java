package ru.awp.enterprise.automation.models.dao;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Builder
@Table(name = "areas")
public record AreaDAO(
        @Id
        UUID id,
        @Column(value = "name")
        String name
) {
}
