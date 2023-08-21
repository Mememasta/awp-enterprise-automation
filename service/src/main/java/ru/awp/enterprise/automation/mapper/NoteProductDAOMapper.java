package ru.awp.enterprise.automation.mapper;

import org.springframework.stereotype.Component;
import ru.awp.enterprise.automation.models.dao.NoteProductDAO;
import ru.awp.enterprise.automation.models.dto.NoteProductDTO;

import java.util.UUID;
import java.util.function.BiFunction;

@Component
public class NoteProductDAOMapper implements BiFunction<UUID, NoteProductDTO, NoteProductDAO> {
    @Override
    public NoteProductDAO apply(UUID noteId, NoteProductDTO noteProductDTO) {
        return NoteProductDAO.builder()
                .noteId(noteId)
                .productId(noteProductDTO.productId())
                .value(noteProductDTO.value())
                .isDefect(noteProductDTO.isDefect())
                .build();
    }

    public NoteProductDTO apply(NoteProductDAO noteProductDAO) {
        return NoteProductDTO.builder()
                .productId(noteProductDAO.productId())
                .value(noteProductDAO.value())
                .isDefect(noteProductDAO.isDefect())
                .build();
    }
}
