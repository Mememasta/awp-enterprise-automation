package ru.awp.enterprise.automation.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dto.MachineDataDTO;

public interface MachineDataService {

    Flux<MachineDataDTO> findMachineDataByTopic(String topic, Integer page, Integer size);

    Mono<Void> save(String topic, String value);

}
