package ru.awp.enterprise.automation.mapper;

import org.springframework.stereotype.Component;
import ru.awp.enterprise.automation.models.dao.ReportCardDAO;
import ru.awp.enterprise.automation.models.request.ReportCardRequest;

import java.time.OffsetDateTime;
import java.util.function.Function;

@Component
public class ReportCardDAOMapper implements Function<ReportCardRequest, ReportCardDAO> {

    @Override
    public ReportCardDAO apply(ReportCardRequest request) {
        return ReportCardDAO.builder()
                .created(request.created())
                .updated(OffsetDateTime.now())
                .comment(request.comment())
                .user(request.userId())
                .area(request.area())
                .build();
    }

    public ReportCardDAO apply(ReportCardDAO reportCardDAO, ReportCardRequest request) {
        return ReportCardDAO.builder()
                .id(reportCardDAO.id())
                .created(request.created())
                .updated(OffsetDateTime.now())
                .comment(request.comment())
                .user(reportCardDAO.user())
                .userEdit(request.userEditId())
                .area(request.area())
                .build();
    }
}
