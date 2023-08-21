package ru.awp.enterprise.automation.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.awp.enterprise.automation.models.dto.NoteProductDTO;

import java.util.List;
import java.util.UUID;

public record NoteRequest(


        /*
          Рабочий ответственный за продукт
         */
        @NotNull
        @JsonProperty(value = "user_id")
        UUID userId,

        /*
          Пользователь изменивший запись
         */
        @JsonProperty(value = "user_id_edit")
        UUID userEditId,

        /*
          Производство на котором находится продукт
         */
        @NotNull
        @JsonProperty(value = "area")
        Integer area,

        /*
          Состояние продукции (Произведено/Отгружено)
         */
        @NotNull
        @JsonProperty(value = "status")
        Integer status,

        /*
          Комментарий к записи
         */
        @NotBlank
        @JsonProperty(value = "comment")
        String comment,

        /*
          Список продуктов
         */
        @JsonProperty(value = "products")
        List<NoteProductDTO> products
) {
}
