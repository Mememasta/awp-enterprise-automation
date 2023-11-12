package ru.awp.enterprise.automation.api;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dto.ReportCardDTO;
import ru.awp.enterprise.automation.models.request.ReportCardRequest;

import java.util.UUID;

@RequestMapping(value = "/api/v1/report")
public interface ReportCardApi {

    @GetMapping(value = "/area/{areaId}")
    Flux<ReportCardDTO> findReportCardByAreaId(@PathVariable("areaId") @NotNull Integer areaId,
                                               @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size);

    @GetMapping(value = "/all")
    Flux<ReportCardDTO> findAllNote();

    @GetMapping(value = "/{id}")
    Mono<ReportCardDTO> getReportCardById(@PathVariable("id") @NotNull UUID uuid);

    @GetMapping(value = "/user/{user_id}")
    Flux<ReportCardDTO> getNotesByUserId(@PathVariable("user_id") @NotNull UUID userId);

    @PutMapping(value = "/{id}")
    Mono<Void> updateReportCard(@PathVariable("id") @NotNull UUID uuid, @RequestBody @Validated ReportCardRequest request);

    @PostMapping(value = "/add")
    Mono<Void> add(@RequestBody @Validated ReportCardRequest request);
}
