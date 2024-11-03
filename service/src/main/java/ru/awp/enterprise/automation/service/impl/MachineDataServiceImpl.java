package ru.awp.enterprise.automation.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public Flux<MachineDataDTO> findMachineDataByTopic(String topic, Integer page, Integer size) {
        var pages = PageRequest.of(page, size, Sort.by("event_date"));
        return machineDataRepository.findAllByTopic(topic, pages)
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
