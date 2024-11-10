package ru.awp.enterprise.automation.api;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dto.IotDTO;
import ru.awp.enterprise.automation.models.dto.MachineDataDTO;
import ru.awp.enterprise.automation.models.request.HistoricDataRequest;

/**
 * @author MCHuchalov on 16.12.2023
 */
@RequestMapping(value = "/api/v1/iot_devices")
public interface IotApi {

    @GetMapping(value = "/area/{area_id}")
    Flux<IotDTO> getIot(@PathVariable(value = "area_id") Integer areaId);

    @GetMapping(value = "/all")
    Flux<IotDTO> getAllIot();

    @PatchMapping(value = "/{id}")
    Mono<ResponseEntity<HttpStatus>> updateIot(@PathVariable(value = "id") UUID id, @RequestBody @Validated IotDTO request);

    @DeleteMapping(value = "/{id}")
    Mono<ResponseEntity<HttpStatus>> deleteIot(@PathVariable(value = "id") UUID id);

    @PostMapping(value = "/add")
    Mono<ResponseEntity<HttpStatus>> addIot(@RequestBody @Validated IotDTO request);

    @GetMapping(value = "/data/history")
    Flux<MachineDataDTO> getHistoryData(@RequestBody HistoricDataRequest request);

}
