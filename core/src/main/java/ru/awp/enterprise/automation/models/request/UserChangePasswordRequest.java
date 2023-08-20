package ru.awp.enterprise.automation.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UserChangePasswordRequest(

        @NotNull
        @JsonProperty(value = "user_id")
        UUID id,

        @NotNull
        @JsonProperty(value = "new_password")
        String password

) {
}
