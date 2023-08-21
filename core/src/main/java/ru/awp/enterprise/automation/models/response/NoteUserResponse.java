package ru.awp.enterprise.automation.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record NoteUserResponse(
        /*
          Идентификатор работника
         */
        String id,

        /*
          Имя работника
         */
        @JsonProperty(value = "first_name")
        String firstName,

        /*
          Фамилия работника
         */
        @JsonProperty(value = "last_name")
        String lastName
) {
}
