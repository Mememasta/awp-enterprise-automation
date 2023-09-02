package ru.awp.enterprise.automation.mapper;

import org.apache.commons.lang3.function.TriFunction;
import org.springframework.stereotype.Component;
import ru.awp.enterprise.automation.models.dao.ReportCardUserDAO;
import ru.awp.enterprise.automation.models.dto.ReportCardUserDTO;

import java.util.UUID;

@Component
public class ReportCardUserDAOMapper implements TriFunction<Long, UUID, ReportCardUserDTO, ReportCardUserDAO> {

    @Override
    public ReportCardUserDAO apply(Long reportCardUserId, UUID reportCardId, ReportCardUserDTO reportCardUserDTO) {
        return ReportCardUserDAO.builder()
                .id(reportCardUserId)
                .reportCardId(reportCardId)
                .userId(reportCardUserDTO.userId())
                .hours(reportCardUserDTO.hours())
                .build();
    }

    public ReportCardUserDAO apply(UUID reportCardId, ReportCardUserDTO reportCardUserDTO) {
        return this.apply(null, reportCardId, reportCardUserDTO);
    }

    public ReportCardUserDTO apply(ReportCardUserDAO reportCardUserDAO) {
        return ReportCardUserDTO.builder()
                .id(reportCardUserDAO.id())
                .userId(reportCardUserDAO.reportCardId())
                .hours(reportCardUserDAO.hours())
                .build();
    }
}
