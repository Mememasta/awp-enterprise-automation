package ru.awp.enterprise.automation.api;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dto.IotDTO;
import ru.awp.enterprise.automation.models.dto.MachineDataDTO;
import ru.awp.enterprise.automation.models.request.HistoricDataRequest;
import ru.awp.enterprise.automation.service.IotService;
import ru.awp.enterprise.automation.service.MachineDataService;

/**
 * @author MCHuchalov on 16.12.2023
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class IotController implements IotApi {

    private final IotService iotService;
    private final MachineDataService machineDataService;

    @Override
    public Flux<IotDTO> getIot(Integer areaId) {
        return iotService.getIotDevicesByAreaId(areaId);
    }

    @Override
    public Flux<IotDTO> getAllIot() {
        return iotService.getAllIotDevices();
    }

    @Override
    public Mono<ResponseEntity<HttpStatus>> updateIot(UUID id, IotDTO request) {
        return iotService.update(id, request)
                .then(Mono.defer(() -> Mono.just(new ResponseEntity<>(HttpStatus.OK))));
    }

    @Override
    public Mono<ResponseEntity<HttpStatus>> deleteIot(UUID id) {
        return iotService.delete(id)
                .then(Mono.defer(() -> Mono.just(new ResponseEntity<>(HttpStatus.OK))));
    }

    @Override
    public Mono<ResponseEntity<HttpStatus>> addIot(IotDTO request) {
        return iotService.save(request)
                .then(Mono.defer(() -> Mono.just(new ResponseEntity<>(HttpStatus.OK))));
    }

    @Override
    public Flux<MachineDataDTO> getHistoryData(HistoricDataRequest request) {
        return machineDataService.findMachineDataByTopic(request.topic(), request.start(), request.end());
    }
}
