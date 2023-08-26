package ru.awp.enterprise.automation.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import ru.awp.enterprise.automation.models.response.NoteUserResponse;

import java.time.LocalDate;
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
        NoteUserResponse userDTO,

        /*
          Пользователь изменивший запись
         */
        @JsonProperty(value = "user_edit")
        NoteUserResponse userEdit,

        /*
          Дата и время учета продукта
         */
        @JsonProperty(value = "created")
        LocalDate created,

        /*
          Дата изменения записи
         */
        @JsonProperty(value = "updated")
        OffsetDateTime updated,

        /*
          Производство на котором находится продукт
         */
        @JsonProperty(value = "area")
        Integer area,

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
           Общий объем бетона
         */
        @JsonProperty(value = "sum_concrete_volume")
        Double sumConcreteVolume,

        /*
          Список продуктов
         */
        @JsonProperty(value = "products")
        List<NoteProductDTO> products

) {
}
