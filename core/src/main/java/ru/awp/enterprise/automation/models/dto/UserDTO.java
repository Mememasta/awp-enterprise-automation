package ru.awp.enterprise.automation.models.dto;

import lombok.Builder;

import java.util.List;

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
          Список ролей работника
         */
        List<String> authorities,

        /*
          Продукты рабочего
         */
        List<ProductDTO> products
) {
}
