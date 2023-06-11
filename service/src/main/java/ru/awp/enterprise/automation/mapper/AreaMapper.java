package ru.awp.enterprise.automation.mapper;

import org.springframework.stereotype.Component;
import ru.awp.enterprise.automation.models.dao.AreaDAO;
import ru.awp.enterprise.automation.models.dto.AreaDTO;

import java.util.function.Function;

@Component
public class AreaMapper implements Function<AreaDAO, AreaDTO> {

    @Override
    public AreaDTO apply(AreaDAO areaDAO) {
        return AreaDTO.builder()
                .id(areaDAO.id())
                .name(areaDAO.name())
//              products().
                .build();

    }
}
