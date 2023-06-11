package ru.awp.enterprise.automation.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.exception.AreaNotFoundException;
import ru.awp.enterprise.automation.mapper.AreaDAOMapper;
import ru.awp.enterprise.automation.mapper.AreaMapper;
import ru.awp.enterprise.automation.models.dao.AreaDAO;
import ru.awp.enterprise.automation.models.dto.AreaDTO;
import ru.awp.enterprise.automation.models.dto.ProductDTO;
import ru.awp.enterprise.automation.repository.AreaRepository;
import ru.awp.enterprise.automation.service.AreaService;

import java.awt.geom.Area;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AreaServiceImpl implements AreaService {

    private final AreaRepository areaRepository;
    private final AreaMapper areaMapper;
    private final AreaDAOMapper areaDAOMapper;
    private final ProductService productService;

    @Override
    public Mono<AreaDTO> getArea(UUID uuid) {
        return areaRepository.findById(uuid)
//                .flatMap(productService.findProdByAreaId(uuid))
                .map(areaMapper)
                .switchIfEmpty(Mono.error(new AreaNotFoundException()));
    }

    @Override
    public Mono<AreaDTO> addNewArea(AreaDTO areaDTO) {

        return areaRepository
//                .findById(areaDTO.id())
                .save(areaDAOMapper.apply(areaDTO))
                .map(areaMapper)
                .onErrorResume(e -> Mono.error(new AreaNotFoundException()));
    }

    @Override
    public Mono<AreaDTO> updateArea(AreaDTO areaDTO) {
        return areaRepository
                .save(areaDAOMapper.apply(areaDTO))
                .map(areaMapper)
                .onErrorResume(e -> Mono.error(new AreaNotFoundException()));
    }

    @Override
    public Mono<Void> deleteArea(UUID uuidAreaToDelete, UUID uuidAreaToTransferProducts) {
//        var areaDAO = getArea(uuid);
//        return areaRepository.delete(areaDAO);

//        return productService.findByAreaId.flatMap(products -> )
        return areaRepository.deleteById(uuidAreaToDelete);
    }
}
