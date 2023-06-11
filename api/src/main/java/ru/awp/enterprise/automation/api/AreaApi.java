package ru.awp.enterprise.automation.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dao.AreaDAO;
import ru.awp.enterprise.automation.models.dto.AreaDTO;

import java.util.UUID;

public interface AreaApi {

    @GetMapping(value = "/{area_id}")
    Mono<AreaDTO> getArea(@PathVariable("area_id") UUID areaId);

    @PostMapping(value = "/")
    Mono<AreaDTO> addNewArea(AreaDTO areaDTO);

    @PutMapping(value = "/{area_id}")
    Mono<AreaDTO> changeArea(@PathVariable("area_id") AreaDTO areaDTO);

    @DeleteMapping(value = "/{area_id}")
    Mono<AreaDTO> deleteArea(@PathVariable("area_id") UUID areaId);

}
/*
    POST {{url}}/v1/api/area
        Добавление площадки
        405 - ?
        PUT {{url}}/v1/api/area/{{id}}
        Изменение данных площадки
        400 - неверный идентификатор, 404 - не существует, 405 - ?
        DELETE {{url}}/v1/api/area/{{id}}
        Удаление площадки
        400 - неверный идентификатор, 404 - не существует*/
