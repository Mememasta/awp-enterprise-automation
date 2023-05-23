package ru.awp.enterprise.automation.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record LoginResponse(
        @JsonProperty("access_token")
        String accessToken
) {
}
