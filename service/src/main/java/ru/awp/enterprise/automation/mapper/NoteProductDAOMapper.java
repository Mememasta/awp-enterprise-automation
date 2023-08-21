package ru.awp.enterprise.automation.mapper;

import org.apache.commons.lang3.function.TriFunction;
import org.springframework.stereotype.Component;
import ru.awp.enterprise.automation.models.dao.NoteProductDAO;
import ru.awp.enterprise.automation.models.dto.NoteProductDTO;

import java.util.UUID;

@Component
public class NoteProductDAOMapper implements TriFunction<Long, UUID, NoteProductDTO, NoteProductDAO> {

    @Override
    public NoteProductDAO apply(Long noteProductId, UUID noteId, NoteProductDTO noteProductDTO) {
        return NoteProductDAO.builder()
                .id(noteProductId)
                .noteId(noteId)
                .productId(noteProductDTO.productId())
                .value(noteProductDTO.value())
                .isDefect(noteProductDTO.isDefect())
                .build();
    }
    public NoteProductDAO apply(UUID noteId, NoteProductDTO noteProductDTO) {
        return this.apply(null, noteId, noteProductDTO);
    }

    public NoteProductDTO apply(NoteProductDAO noteProductDAO) {
        return NoteProductDTO.builder()
                .id(noteProductDAO.id())
                .productId(noteProductDAO.productId())
                .value(noteProductDAO.value())
                .isDefect(noteProductDAO.isDefect())
                .build();
    }
}
