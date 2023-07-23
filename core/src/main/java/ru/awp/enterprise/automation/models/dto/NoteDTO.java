package ru.awp.enterprise.automation.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record NoteDTO(
        /*
          Идентификатор записи
         */
        UUID id,

        /*
          Рабочий ответственный за продукт
         */
        @JsonProperty(value = "user")
        UserDTO userDTO,

        /*
          Пользователь изменивший запись
         */
        @JsonProperty(value = "user_edit")
        UserDTO userEdit,

        /*
          Дата и время учета продукта
         */
        @JsonProperty(value = "created")
        OffsetDateTime created,

        /*
          Дата изменения записи
         */
        @JsonProperty(value = "updated")
        OffsetDateTime updated,

        /*
          Производство на котором находится продукт
         */
        @JsonProperty(value = "area")
        String area,

        /*
          Состояние продукции (Произведено/Отгружено)
         */
        @JsonProperty(value = "status")
        Integer status,

        /*
          Комментарий к записи
         */
        @JsonProperty(value = "comment")
        String comment,

        /*
          Список продуктов
         */
        @JsonProperty(value = "products")
        List<NoteProductDTO> products

) {
}
