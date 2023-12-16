package ru.awp.enterprise.automation.models.dao;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * @author MCHuchalov on 16.12.2023
 */
@Builder
@Table(name = "iot_devices")
public record IotDAO(
        @Id
        UUID id,
        String name,
        String topic,
        Integer qos,
        Integer area,
        String location,
        String type,
        String data_type,
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