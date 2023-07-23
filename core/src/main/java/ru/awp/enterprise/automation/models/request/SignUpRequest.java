package ru.awp.enterprise.automation.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.List;

public record SignUpRequest(
        @NotBlank
        @JsonProperty("first_name")
        String firstName,

        @NotBlank
        @JsonProperty("last_name")
        String lastName,

        @NotBlank
        @JsonProperty("phone_number")
        String phoneNumber,

        @Builder.Default
        @JsonProperty("authorities")
        List<String> authorities,

        @NotBlank
        @JsonProperty("password")
        String password
) {
}
