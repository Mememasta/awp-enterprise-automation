package ru.awp.enterprise.automation.models.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserDTO(
        /*
          Идентификатор работника
         */
        String id,

        /*
          Имя работника
         */
        String firstName,

        /*
          Фамилия работника
         */
        String lastName,

        /*
          Номер телефона работника
         */
        String phoneNumber,

        /*
          Производство к которому привязан работник
         */
        UUID area

) {
}
