package ru.awp.enterprise.automation.api;


import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import ru.awp.enterprise.automation.models.dto.ProductDTO;

import java.util.List;
import java.util.UUID;

public interface ProductApi {

    @GetMapping(value = "/{area_id}")
    Flux<ProductDTO> getProductsByAreaId(@PathVariable("area_id") @NotNull UUID areaId);

    @PostMapping(value = "/")
    Flux<ProductDTO> saveProductsByAreaId(@RequestBody @Validated List<ProductDTO> productDTO);

}
