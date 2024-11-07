package ru.awp.enterprise.automation.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.mapper.IotDaoMapper;
import ru.awp.enterprise.automation.mapper.IotDtoMapper;
import ru.awp.enterprise.automation.models.dao.IotDAO;
import ru.awp.enterprise.automation.models.dto.IotDTO;
import ru.awp.enterprise.automation.repository.IotRepository;
import ru.awp.enterprise.automation.service.IotService;

import java.util.UUID;

/**
 * @author MCHuchalov on 16.12.2023
 */
@Component
@RequiredArgsConstructor
public class IotServiceImpl implements IotService {

    private final IotRepository iotRepository;
    private final IotDtoMapper iotDtoMapper;
    private final IotDaoMapper iotDaoMapper;
    private final MqttPahoMessageDrivenChannelAdapter mqttAdapter;

    @PostConstruct
    void init() {
        iotRepository.findAll()
                .map(IotDAO::topic)
                .subscribe(it -> mqttAdapter.addTopic(it));
    }

    @Override
    public Flux<IotDTO> getIotDevicesByAreaId(Integer areaId) {
        return iotRepository.findAllByArea(areaId).map(iotDtoMapper);
    }

    @Override
    public Flux<IotDTO> getAllIotDevices() {
        return iotRepository.findAll(Sort.by("area")).map(iotDtoMapper);
    }

    @Override
    public Mono<Void> save(IotDTO iotDTO) {
        mqttAdapter.addTopic(iotDTO.topic());
        return iotRepository.save(iotDaoMapper.apply(iotDTO)).then();
    }

    @Override
    public Mono<Void> update(UUID id, IotDTO iotDTO) {
        iotRepository.findById(id)
                .subscribe(it -> {
                    mqttAdapter.removeTopic(it.topic());
                    mqttAdapter.addTopic(iotDTO.topic());
                });

        return iotRepository.save(iotDaoMapper.apply(id, iotDTO)).then();
    }

    @Override
    public Mono<Void> delete(UUID id) {
        iotRepository.findById(id)
                .subscribe(it -> {
                    mqttAdapter.removeTopic(it.topic());
                });
        return iotRepository.deleteById(id);
    }
}
