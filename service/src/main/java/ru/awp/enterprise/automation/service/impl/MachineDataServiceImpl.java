package ru.awp.enterprise.automation.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
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
    public Flux<MachineDataDTO> findMachineDataByTopic(String topic, LocalDate start, LocalDate end, Long limit) {
        var startDateTime = LocalDateTime.of(start, LocalTime.MIN);
        var endDateTime = LocalDateTime.of(end, LocalTime.MAX);
        var result = machineDataRepository.findByTopicAndDateRange(topic, startDateTime, endDateTime)
                .map(machineDataMapper::apply);
        return calculateAverages(result, limit);
    }

    @Override
    @Transactional
    public Mono<Void> save(String topic, String value) {
        return machineDataRepository.getNextId()
                .map(it -> machineDataMapper.apply(it, topic, value))
                .flatMap(machineDataRepository::saveOrUpdate)
                .then();
    }

    public Flux<MachineDataDTO> calculateAverages(Flux<MachineDataDTO> data, Long intervalInSeconds) {
        return data
                .groupBy(machineData -> ChronoUnit.SECONDS.between(OffsetDateTime.MIN, machineData.eventDate()) / intervalInSeconds)
                .flatMapSequential(group -> group.collectList().map(this::calculateAverage));
    }

    private MachineDataDTO calculateAverage(List<MachineDataDTO> list) {
        double sum = 0;
        OffsetDateTime eventDate = list.get(0).eventDate();
        for (MachineDataDTO machineData : list) {
            var value = Double.parseDouble(machineData.value());
            if (value != -127D) {
                sum += value;
            }
        }
        double averageValue = sum / list.size();
        return new MachineDataDTO(
                null, // id
                list.get(0).topic(), // topic
                String.valueOf(averageValue), // average value
                eventDate // average event date
        );
    }
}
