package ru.awp.enterprise.automation.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import ru.awp.enterprise.automation.models.dto.UserDTO;

@Builder
public record LoginResponse(
        @JsonProperty("access_token")
        String accessToken,

        @JsonProperty("user")
        UserDTO user
) {
}
