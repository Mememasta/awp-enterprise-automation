package ru.awp.enterprise.automation.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import ru.awp.enterprise.automation.models.dto.ProductDTO;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController("/v1/api/products")
@RequiredArgsConstructor
public class ProductController implements ProductApi {

    @Override
    public Flux<ProductDTO> getProductsByAreaId(UUID areaId) {
        return null;
    }

    @Override
    public Flux<ProductDTO> saveProductsByAreaId(List<ProductDTO> productDTO) {
        return null;
    }
}
