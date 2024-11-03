package ru.awp.enterprise.automation.models.dto;

import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDateTime;


@Builder
public record MachineDataDTO(
        Long id,
        String topic,
        String value,
        LocalDateTime eventDate
) implements Serializable {}