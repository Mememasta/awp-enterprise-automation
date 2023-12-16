package ru.awp.enterprise.automation.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author MCHuchalov on 16.12.2023
 */
@Builder
public record IotDTO(
        UUID id,
        String name,
        String topic,
        Integer qos,
        Integer area,
        String location,
        String type,
        String data_type,
        String comment
) implements Serializable {
}
