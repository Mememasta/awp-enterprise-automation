package ru.awp.enterprise.automation.mapper;

import org.springframework.stereotype.Component;
import ru.awp.enterprise.automation.models.dao.NoteDAO;
import ru.awp.enterprise.automation.models.dto.NoteDTO;
import ru.awp.enterprise.automation.models.dto.NoteProductDTO;
import ru.awp.enterprise.automation.models.response.SimpleUserResponse;

import java.util.List;

@Component
public class NoteDTOMapper {

    public NoteDTO map(NoteDAO noteDAO, List<NoteProductDTO> noteProductDTOS, SimpleUserResponse userDTO, SimpleUserResponse userEditDTO) {
        return NoteDTO.builder()
                .id(noteDAO.id())
                .status(noteDAO.status())
                .comment(noteDAO.comment())
                .created(noteDAO.created())
                .updated(noteDAO.updated())
                .area(noteDAO.area())
                .userDTO(userDTO)
                .userEdit(userEditDTO)
                .products(noteProductDTOS)
                .sumConcreteVolume(noteDAO.sumConcreteVolume())
                .build();
    }

    public NoteDTO map(NoteDAO noteDAO, List<NoteProductDTO> noteProductDTOS) {
        return map(noteDAO, noteProductDTOS, null, null);
    }

}