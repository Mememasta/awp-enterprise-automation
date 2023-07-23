package ru.awp.enterprise.automation.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record UserChangeRequest(

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
