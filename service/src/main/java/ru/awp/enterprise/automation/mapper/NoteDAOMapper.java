package ru.awp.enterprise.automation.mapper;

import org.springframework.stereotype.Component;
import ru.awp.enterprise.automation.models.dao.NoteDAO;
import ru.awp.enterprise.automation.models.request.NoteRequest;

import java.time.OffsetDateTime;
import java.util.function.BiFunction;

@Component
public class NoteDAOMapper implements BiFunction<NoteRequest, Double, NoteDAO> {

    @Override
    public NoteDAO apply(NoteRequest request, Double productsVolume) {
        return NoteDAO.builder()
                .created(request.created())
                .updated(OffsetDateTime.now())
                .comment(request.comment())
                .status(request.status())
                .user(request.userId())
                .area(request.area())
                .redirection(request.redirection())
                .sumConcreteVolume(productsVolume)
                .build();
    }

    public NoteDAO apply(NoteDAO note, NoteRequest request, Double productsVolume) {
        return NoteDAO.builder()
                .id(note.id())
                .created(request.created())
                .updated(OffsetDateTime.now())
                .comment(request.comment())
                .status(request.status())
                .user(note.user())
                .userEdit(request.userEditId())
                .area(request.area())
                .redirection(request.redirection())
                .sumConcreteVolume(productsVolume)
                .build();
    }
}
