package ru.awp.enterprise.automation.models.dto;

import java.io.Serializable;
import java.time.OffsetDateTime;
import lombok.Builder;


@Builder
public record MachineDataDTO(
        Long id,
        String topic,
        String value,
        OffsetDateTime eventDate
) implements Serializable {}