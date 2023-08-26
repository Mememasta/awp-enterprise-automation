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
                .created(request.created())
                .updated(OffsetDateTime.now())
                .comment(request.comment())
                .status(request.status())
                .user(request.userId())
                .area(request.area())
                .build();
    }

    public NoteDAO apply(NoteDAO note, NoteRequest request) {
        return NoteDAO.builder()
                .id(note.id())
                .created(request.created())
                .updated(OffsetDateTime.now())
                .comment(request.comment())
                .status(request.status())
                .user(note.user())
                .user_edit(request.userEditId())
                .area(request.area())
                .build();
    }
}
