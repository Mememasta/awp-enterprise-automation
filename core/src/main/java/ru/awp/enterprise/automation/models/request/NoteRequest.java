package ru.awp.enterprise.automation.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import ru.awp.enterprise.automation.models.dto.NoteProductDTO;

import java.time.LocalDate;
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
          Дата и время учета продукта
         */
        @NotNull
        @JsonProperty(value = "created")
        LocalDate created,

        /*
          Производство на котором находится продукт
         */
        @NotNull
        @JsonProperty(value = "area")
        Integer area,

        /*
          Производство на которое перенаправили продукт
         */
        @JsonProperty(value = "redirection")
        Integer redirection,

        /*
          Состояние продукции (Произведено/Отгружено)
         */
        @NotNull
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
        List<NoteProductDTO> products,

        /*
          Список продуктов на удаление
         */
        @JsonProperty(value = "deleted_products")
        List<Long> deletedProductsId
) {
}
