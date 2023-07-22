package ru.awp.enterprise.automation.api;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dto.NoteDTO;
import ru.awp.enterprise.automation.models.request.NoteRequest;

import java.util.UUID;

@RequestMapping(value = "/v1/api/note")
public interface NoteApi {

    @GetMapping(value = "/")
    Flux<NoteDTO> findNotesByAreaId(@RequestParam("area") @NotNull Integer areaId);

    @GetMapping(value = "/{id}")
    Mono<NoteDTO> getNotesById(@PathVariable("id") @NotNull UUID uuid);

    @PutMapping(value = "/{id}")
    Mono<NoteDTO> updateNote(@PathVariable("id") @NotNull UUID uuid, @RequestBody @Validated NoteRequest request);

    @PutMapping(value = "/")
    Flux<NoteDTO> updateAllNoteArea(@RequestParam("old_area") @NotNull String oldArea, @RequestParam("new_area") @NotNull String newArea);

    @PostMapping(value = "/add")
    Mono<NoteDTO> add(@RequestBody @Validated NoteRequest request);

}
