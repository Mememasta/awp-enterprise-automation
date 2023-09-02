package ru.awp.enterprise.automation.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import ru.awp.enterprise.automation.models.response.SimpleUserResponse;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record ReportCardDTO(

    /*
      Идентификатор записи
     */
    UUID id,

    /*
      Рабочий ответственный за продукт
     */
    @JsonProperty(value = "user")
    SimpleUserResponse userDTO,

    /*
      Пользователь изменивший запись
     */
    @JsonProperty(value = "user_edit")
    SimpleUserResponse userEdit,

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
      Комментарий к записи
     */
    @JsonProperty(value = "comment")
    String comment,


    /*
      Список продуктов
     */
    @JsonProperty(value = "user_report_card")
    List<ReportCardUserDTO> userReportCard

) {
}
