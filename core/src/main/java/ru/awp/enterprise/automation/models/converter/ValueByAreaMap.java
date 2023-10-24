package ru.awp.enterprise.automation.models.converter;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValueByAreaMap {
        @JsonProperty(value = "area")
        private int area;
        @JsonProperty(value = "value")
        private long value;
}