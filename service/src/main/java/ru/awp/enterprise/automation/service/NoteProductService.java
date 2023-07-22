package ru.awp.enterprise.automation.service;

import reactor.core.publisher.Flux;
import ru.awp.enterprise.automation.models.dao.NoteDAO;
import ru.awp.enterprise.automation.models.dto.NoteProductDTO;

import java.util.List;

public interface NoteProductService {

    Flux<NoteProductDTO> save(NoteDAO noteDAO, List<NoteProductDTO> productDTO);

}
