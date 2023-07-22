package ru.awp.enterprise.automation.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ru.awp.enterprise.automation.mapper.NoteProductDAOMapper;
import ru.awp.enterprise.automation.models.dao.NoteDAO;
import ru.awp.enterprise.automation.models.dto.NoteProductDTO;
import ru.awp.enterprise.automation.repository.NoteProductRepository;
import ru.awp.enterprise.automation.service.NoteProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteProductServiceImpl implements NoteProductService {

    private final NoteProductRepository noteProductRepository;
    private final NoteProductDAOMapper noteProductDAOMapper;

    public Flux<NoteProductDTO> save(NoteDAO noteDAO, List<NoteProductDTO> productDTO) {
        var noteProductDao = Flux.fromIterable(productDTO)
                .map(it -> noteProductDAOMapper.apply(noteDAO.id(), it));
        return noteProductRepository.saveAll(noteProductDao)
                .map(noteProductDAOMapper::apply);
    }

}
