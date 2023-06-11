package ru.awp.enterprise.automation.mapper;

import org.springframework.stereotype.Component;
import ru.awp.enterprise.automation.models.dao.AreaDAO;
import ru.awp.enterprise.automation.models.dto.AreaDTO;

import java.util.UUID;
import java.util.function.Function;

@Component
public class AreaDAOMapper implements Function<AreaDTO, AreaDAO> {

    @Override
    public AreaDAO apply(AreaDTO areaDTO) {
        return AreaDAO.builder()
                .id(areaDTO.id())
                .name(areaDTO.name())
//                products().
                .build();

    }
}
