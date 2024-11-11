package ru.awp.enterprise.automation.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.mapper.MachineDataMapper;
import ru.awp.enterprise.automation.models.dto.MachineDataDTO;
import ru.awp.enterprise.automation.repository.MachineDataRepository;
import ru.awp.enterprise.automation.service.MachineDataService;

@Service
@RequiredArgsConstructor
public class MachineDataServiceImpl implements MachineDataService {

    private final MachineDataRepository machineDataRepository;
    private final MachineDataMapper machineDataMapper;

    @Override
    public Flux<MachineDataDTO> findMachineDataByTopic(String topic, LocalDate start, LocalDate end) {
        var startDateTime = LocalDateTime.of(start, LocalTime.MIN);
        var endDateTime = LocalDateTime.of(end, LocalTime.MAX);
        return machineDataRepository.findByTopicAndDateRange(topic, startDateTime, endDateTime)
                .map(machineDataMapper::apply);
    }

    @Override
    @Transactional
    public Mono<Void> save(String topic, String value) {
        return machineDataRepository.getNextId()
                .map(it -> machineDataMapper.apply(it, topic, value))
                .flatMap(machineDataRepository::saveOrUpdate)
                .then();
    }
}
