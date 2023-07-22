package ru.awp.enterprise.automation.api;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dto.ProductDTO;
import ru.awp.enterprise.automation.models.request.ProductRequest;

@RequestMapping(value = "/v1/api/product")
public interface ProductApi {

    @GetMapping(value = "/")
    Flux<ProductDTO> getProducts();

    @GetMapping(value = "/{id}")
    Mono<ProductDTO> getProduct(@PathVariable(value = "id") Long id);

    @PostMapping(value = "/")
    Mono add(@RequestBody @Validated ProductRequest request);

}
