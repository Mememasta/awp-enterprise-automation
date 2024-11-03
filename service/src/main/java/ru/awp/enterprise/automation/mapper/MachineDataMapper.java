package ru.awp.enterprise.automation.mapper;

import org.springframework.stereotype.Component;
import ru.awp.enterprise.automation.models.dao.MachineDataDAO;
import ru.awp.enterprise.automation.models.dto.MachineDataDTO;

import java.time.LocalDateTime;

@Component
public class MachineDataMapper {

    public MachineDataDAO apply(MachineDataDTO machineDataDTO) {
        return MachineDataDAO.builder()
                .id(machineDataDTO.id())
                .topic(machineDataDTO.topic())
                .value(machineDataDTO.value())
                .eventDate(machineDataDTO.eventDate())
                .build();
    }

    public MachineDataDTO apply(MachineDataDAO machineDataDAO) {
        return MachineDataDTO.builder()
                .id(machineDataDAO.getId())
                .topic(machineDataDAO.getTopic())
                .value(machineDataDAO.getValue())
                .eventDate(machineDataDAO.getEventDate())
                .build();
    }

    public MachineDataDAO apply(Long id, String topic, String value) {
        return MachineDataDAO.builder()
                .id(id)
                .topic(topic)
                .value(value)
                .eventDate(LocalDateTime.now())
                .build();
    }
}
