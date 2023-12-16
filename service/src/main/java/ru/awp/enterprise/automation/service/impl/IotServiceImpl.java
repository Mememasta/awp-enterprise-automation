package ru.awp.enterprise.automation.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.mapper.IotDaoMapper;
import ru.awp.enterprise.automation.mapper.IotDtoMapper;
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
        return iotRepository.save(iotDaoMapper.apply(iotDTO)).then();
    }

    @Override
    public Mono<Void> update(UUID id, IotDTO iotDTO) {
        return iotRepository.save(iotDaoMapper.apply(id, iotDTO)).then();
    }

    @Override
    public Mono<Void> delete(UUID id) {
        return iotRepository.deleteById(id);
    }
}
