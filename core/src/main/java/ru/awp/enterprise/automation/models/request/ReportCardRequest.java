package ru.awp.enterprise.automation.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import ru.awp.enterprise.automation.models.dto.ReportCardUserDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ReportCardRequest(

         /*
          Рабочий ответственный за запись в табеле
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
          Дата и время учета записи
         */
        @NotNull
        @JsonProperty(value = "created")
        LocalDate created,

        /*
          Производство в котором отработали время
         */
        @NotNull
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
        @JsonProperty(value = "userReportCard")
        List<ReportCardUserDTO> userReportCard,

        /*
          Список продуктов на удаление
         */
        @JsonProperty(value = "deleted_users_report_card")
        List<Long> deletedUsersReportCard

) {
}
