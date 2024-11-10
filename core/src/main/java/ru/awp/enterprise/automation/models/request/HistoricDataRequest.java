package ru.awp.enterprise.automation.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public record HistoricDataRequest(
        @JsonProperty(value = "start")
        LocalDate start,

        @JsonProperty(value = "end")
        LocalDate end,

        @JsonProperty(value = "limit")
        String limit,

        @JsonProperty(value = "topic")
        String topic
) {}
