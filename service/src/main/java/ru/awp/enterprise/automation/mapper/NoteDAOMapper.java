package ru.awp.enterprise.automation.mapper;

import org.springframework.stereotype.Component;
import ru.awp.enterprise.automation.models.dao.NoteDAO;
import ru.awp.enterprise.automation.models.request.NoteRequest;

import java.time.OffsetDateTime;
import java.util.function.Function;

@Component
public class NoteDAOMapper implements Function<NoteRequest, NoteDAO> {

    @Override
    public NoteDAO apply(NoteRequest request) {
        return NoteDAO.builder()
                .created(OffsetDateTime.now())
                .updated(OffsetDateTime.now())
                .comment(request.comment())
                .status(request.status())
                .user(request.userId())
                .area(request.area())
                .user_edit(request.userEditId())
                .build();
    }
}
