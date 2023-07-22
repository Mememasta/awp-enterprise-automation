package ru.awp.enterprise.automation.models.dto;

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
        UserDTO userDTO,

        /*
          Пользователь изменивший запись
         */
        UserDTO userEdit,

        /*
          Дата и время учета продукта
         */
        OffsetDateTime created,

        /*
          Дата изменения записи
         */
        OffsetDateTime updated,

        /*
          Производство на котором находится продукт
         */
        String area,

        /*
          Состояние продукции (Произведено/Отгружено)
         */
        Integer status,

        /*
          Комментарий к записи
         */
        String comment,

        /*
          Список продуктов
         */
        List<NoteProductDTO> products

) {
}
