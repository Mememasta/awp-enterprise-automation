package ru.awp.enterprise.automation.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dto.IotDTO;

import java.util.UUID;

/**
 * @author MCHuchalov on 16.12.2023
 */
public interface IotService {

    Flux<IotDTO> getIotDevicesByAreaId(Integer areaId);
    Flux<IotDTO> getAllIotDevices();
    Mono<Void> save(IotDTO iotDTO);
    Mono<Void> update(UUID id, IotDTO iotDTO);
    Mono<Void> delete(UUID id);

}
