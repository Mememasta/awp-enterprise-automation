package ru.awp.enterprise.automation.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @JsonProperty("phone_number")
        @NotBlank
        String phoneNumber,
        @NotBlank
        String password
) {
}
