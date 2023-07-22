package ru.awp.enterprise.automation.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.exception.ProductNotFoundException;
import ru.awp.enterprise.automation.mapper.NoteDTOMapper;
import ru.awp.enterprise.automation.models.dto.NoteDTO;
import ru.awp.enterprise.automation.models.request.NoteRequest;
import ru.awp.enterprise.automation.service.NoteProductService;
import ru.awp.enterprise.automation.service.NoteService;
import ru.awp.enterprise.automation.service.ProductService;
import ru.awp.enterprise.automation.service.UserService;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NoteController implements NoteApi {

    private final UserService userService;
    private final NoteService noteService;
    private final ProductService productService;
    private final NoteProductService noteProductService;
    private final NoteDTOMapper noteDTOMapper;


    @Override
    public Flux<NoteDTO> findNotesByAreaId(Integer areaId) {
        return null;
    }

    @Override
    public Mono<NoteDTO> getNotesById(UUID uuid) {
        return null;
    }

    @Override
    public Mono<NoteDTO> updateNote(UUID uuid, NoteRequest request) {
        return null;
    }

    @Override
    public Flux<NoteDTO> updateAllNoteArea(String oldArea, String newArea) {
        return null;
    }

    @Override
    public Mono<NoteDTO> add(NoteRequest request) {
        var user = userService.findById(String.valueOf(request.userId()));
        var userEdit = userService.findById(Optional.ofNullable(request.userId()).map(String::valueOf).orElse(null));
        Flux.fromIterable(request.products())
                .flatMap(p -> productService.getProductById(p.productId()))
                .switchIfEmpty(Mono.error(new ProductNotFoundException()));

        return noteService.save(request)
                .flatMap(note -> Mono.zip(Mono.just(note), noteProductService.save(note, request.products()).collectList(), user, userEdit))
                .map(tuple -> noteDTOMapper.map(tuple.getT1(), tuple.getT2(), tuple.getT3(), tuple.getT4()));
    }
}
