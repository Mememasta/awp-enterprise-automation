package ru.awp.enterprise.automation.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dto.ProductDTO;
import ru.awp.enterprise.automation.models.dto.ProductWithBalanceDTO;
import ru.awp.enterprise.automation.models.request.ProductRequest;
import ru.awp.enterprise.automation.service.ProductService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController implements ProductApi {

    private final ProductService productService;

    @Override
    public Flux<ProductDTO> getProducts() {
        return productService.getAllProducts();
    }

    @Override
    public Flux<ProductWithBalanceDTO> getProductsWithBalance() {
        return productService.getAllProductsWithBalance();
    }

    @Override
    public Mono<ProductDTO> getProduct(Long id) {
        return productService.getProductById(id);
    }

    @Override
    public Mono<ResponseEntity<HttpStatus>> updateProduct(Long id, ProductRequest request) {
        return productService.updateProduct(id, request)
                .then(Mono.defer(() -> Mono.just(new ResponseEntity<>(HttpStatus.OK))));
    }

    @Override
    public Mono<ResponseEntity<HttpStatus>> updateCoefficient(ProductWithBalanceDTO request) {
        return productService.updateProductCoefficient(request)
                .then(Mono.defer(() -> Mono.just(new ResponseEntity<>(HttpStatus.OK))));
    }

    @Override
    public Mono<ResponseEntity<HttpStatus>> deleteProduct(Long id) {
        return productService.deleteProduct(id)
                .then(Mono.defer(() -> Mono.just(new ResponseEntity<>(HttpStatus.OK))));
    }

    @Override
    public Mono<ResponseEntity<HttpStatus>> add(ProductRequest request) {
        return productService.add(request)
                .then(Mono.defer(() -> Mono.just(new ResponseEntity<>(HttpStatus.CREATED))));
    }
}
