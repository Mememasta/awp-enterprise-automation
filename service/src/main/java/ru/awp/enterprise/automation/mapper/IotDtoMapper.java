package ru.awp.enterprise.automation.mapper;

import org.springframework.stereotype.Component;
import ru.awp.enterprise.automation.models.dao.IotDAO;
import ru.awp.enterprise.automation.models.dto.IotDTO;

import java.util.function.Function;

/**
 * @author MCHuchalov on 16.12.2023
 */
@Component
public class IotDtoMapper implements Function<IotDAO, IotDTO> {
    @Override
    public IotDTO apply(IotDAO iotDAO) {
        return IotDTO.builder()
                .id(iotDAO.id())
                .name(iotDAO.name())
                .area(iotDAO.area())
                .comment(iotDAO.comment())
                .data_type(iotDAO.data_type())
                .qos(iotDAO.qos())
                .topic(iotDAO.topic())
                .location(iotDAO.location())
                .type(iotDAO.type())
                .build();
    }
}
