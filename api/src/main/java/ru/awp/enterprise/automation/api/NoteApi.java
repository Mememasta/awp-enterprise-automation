package ru.awp.enterprise.automation.api;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dto.NoteDTO;
import ru.awp.enterprise.automation.models.request.NoteRequest;

import java.util.UUID;

@RequestMapping(value = "/api/v1/note")
public interface NoteApi {

    @GetMapping(value = "/area/{areaId}")
    Flux<NoteDTO> findNotesByAreaId(@PathVariable("areaId") @NotNull Integer areaId);

    @GetMapping(value = "/all")
    Flux<NoteDTO> findAllNote();

    @GetMapping(value = "/{id}")
    Mono<NoteDTO> getNotesById(@PathVariable("id") @NotNull UUID uuid);

    @GetMapping(value = "/{user_id}")
    Flux<NoteDTO> getNotesByUserId(@PathVariable("user_id") @NotNull UUID userId);

    @PutMapping(value = "/{id}")
    Mono<Void> updateNote(@PathVariable("id") @NotNull UUID uuid, @RequestBody @Validated NoteRequest request);

    @PostMapping(value = "/add")
    Mono<Void> add(@RequestBody @Validated NoteRequest request);

}
