package ru.awp.enterprise.automation.service;

import java.time.LocalDate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dto.MachineDataDTO;

public interface MachineDataService {

    Flux<MachineDataDTO> findMachineDataByTopic(String topic, LocalDate start, LocalDate end);

    Mono<Void> save(String topic, String value);

}
