package ru.awp.enterprise.automation.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dto.ProductDTO;
import ru.awp.enterprise.automation.models.dto.ProductWithBalanceDTO;
import ru.awp.enterprise.automation.models.request.ProductRequest;

@RequestMapping(value = "/api/v1/product")
public interface ProductApi {

    @GetMapping(value = "/all")
    Flux<ProductDTO> getProducts();

    @GetMapping(value = "/balance/all")
    Flux<ProductWithBalanceDTO> getProductsWithBalance();

    @GetMapping(value = "/{id}")
    Mono<ProductDTO> getProduct(@PathVariable(value = "id") Long id);

    @PatchMapping(value = "/{id}")
    Mono<ResponseEntity<HttpStatus>> updateProduct(@PathVariable(value = "id") Long id, @RequestBody @Validated ProductRequest request);

    @PatchMapping(value = "/coefficient")
    Mono<ResponseEntity<HttpStatus>> updateCoefficient(@RequestBody ProductWithBalanceDTO request);

    @DeleteMapping(value = "/{id}")
    Mono<ResponseEntity<HttpStatus>> deleteProduct(@PathVariable(value = "id") Long id);

    @PostMapping(value = "/add")
    Mono<ResponseEntity<HttpStatus>> add(@RequestBody @Validated ProductRequest request);

}
