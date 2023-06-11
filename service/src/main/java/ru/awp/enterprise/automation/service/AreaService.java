package ru.awp.enterprise.automation.service;

import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dao.AreaDAO;
import ru.awp.enterprise.automation.models.dto.AreaDTO;

import java.util.UUID;

public interface AreaService {


    public Mono<AreaDTO> getArea(UUID uuid);

    public Mono<AreaDTO> addNewArea(AreaDTO areaDTO);

    public Mono<AreaDTO> updateArea(AreaDTO areaDTO);

    public Mono<Void> deleteArea(UUID uuidAreaToDelete, UUID uuidAreaToTransferProducts);


}
