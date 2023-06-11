package ru.awp.enterprise.automation.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.mapper.AreaDAOMapper;
import ru.awp.enterprise.automation.models.dao.AreaDAO;
import ru.awp.enterprise.automation.models.dto.AreaDTO;
import ru.awp.enterprise.automation.service.AreaService;

import java.util.UUID;

@RequiredArgsConstructor
@RestController("/v1/api/area")
public class AreaController implements AreaApi {

    private final AreaService areaService;
    private final AreaDAOMapper areaDAOMapper;

    @Override
    public Mono<AreaDTO> getArea(UUID areaId) {
        return areaService.getArea(areaId);
    }

    @Override
    public Mono<AreaDTO> addNewArea(AreaDTO areaDTO) {
        return areaService.addNewArea(areaDTO)
//                .map(area -> ResponseEntity.status(HttpStatus.OK).body(area))
//                .cast(ResponseEntity.class);
//                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(areaDAOMapper.apply(areaDTO))));
                  .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<AreaDTO> changeArea(AreaDTO areaDTO) {
        return areaService.addNewArea(areaDTO)
//                .map(area -> ResponseEntity.status(HttpStatus.OK).body(area))
//                .cast(ResponseEntity.class);
//                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(areaDAOMapper.apply(areaDTO))));
                .switchIfEmpty(Mono.empty());

    }

    @Override
    public Mono<AreaDTO> deleteArea(UUID areaId) {
        return null;
    }
}
