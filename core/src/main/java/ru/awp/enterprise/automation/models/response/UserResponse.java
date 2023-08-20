package ru.awp.enterprise.automation.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record UserResponse(
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
        String lastName,

        /*
          Номер телефона работника
         */
        @JsonProperty(value = "phone_number")
        String phoneNumber,

        /*
          Список ролей работника
         */
        @JsonProperty(value = "authorities")
        List<String> authorities
) {
}
