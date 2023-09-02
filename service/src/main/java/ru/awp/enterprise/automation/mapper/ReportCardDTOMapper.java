package ru.awp.enterprise.automation.mapper;

import org.springframework.stereotype.Component;
import ru.awp.enterprise.automation.models.dao.ReportCardDAO;
import ru.awp.enterprise.automation.models.dto.ReportCardDTO;
import ru.awp.enterprise.automation.models.dto.ReportCardUserDTO;
import ru.awp.enterprise.automation.models.response.SimpleUserResponse;

import java.util.List;

@Component
public class ReportCardDTOMapper {

    public ReportCardDTO map(ReportCardDAO reportCardDAO, List<ReportCardUserDTO> reportCardUserDTOS, SimpleUserResponse userDTO, SimpleUserResponse userEditDTO) {
        return ReportCardDTO.builder()
                .id(reportCardDAO.id())
                .comment(reportCardDAO.comment())
                .created(reportCardDAO.created())
                .updated(reportCardDAO.updated())
                .area(reportCardDAO.area())
                .userDTO(userDTO)
                .userEdit(userEditDTO)
                .userReportCard(reportCardUserDTOS)
                .build();
    }

    public ReportCardDTO map(ReportCardDAO reportCardDAO, List<ReportCardUserDTO> reportCardUserDTOS) {
        return map(reportCardDAO, reportCardUserDTOS, null, null);
    }

}
