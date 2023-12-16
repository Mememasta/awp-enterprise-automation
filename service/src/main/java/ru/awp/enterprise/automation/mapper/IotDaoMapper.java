package ru.awp.enterprise.automation.mapper;

import org.springframework.stereotype.Component;
import ru.awp.enterprise.automation.models.dao.IotDAO;
import ru.awp.enterprise.automation.models.dto.IotDTO;

import java.util.UUID;
import java.util.function.Function;

/**
 * @author MCHuchalov on 16.12.2023
 */
@Component
public class IotDaoMapper implements Function<IotDTO, IotDAO> {

    @Override
    public IotDAO apply(IotDTO iotDTO) {
        return IotDAO.builder()
                .name(iotDTO.name())
                .area(iotDTO.area())
                .comment(iotDTO.comment())
                .data_type(iotDTO.data_type())
                .qos(iotDTO.qos())
                .topic(iotDTO.topic())
                .location(iotDTO.location())
                .type(iotDTO.type())
                .build();
    }

    public IotDAO apply(UUID uuid, IotDTO iotDTO) {
        return IotDAO.builder()
                .id(uuid)
                .name(iotDTO.name())
                .area(iotDTO.area())
                .comment(iotDTO.comment())
                .data_type(iotDTO.data_type())
                .qos(iotDTO.qos())
                .topic(iotDTO.topic())
                .location(iotDTO.location())
                .type(iotDTO.type())
                .build();
    }
}
