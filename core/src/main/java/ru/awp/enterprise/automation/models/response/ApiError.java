package ru.awp.enterprise.automation.models.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ApiError(

        int code,
        String message,
        LocalDateTime timestamp

) {
}
