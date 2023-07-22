package ru.awp.enterprise.automation.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dto.ProductDTO;
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
    public Mono<ProductDTO> getProduct(Long id) {
        return productService.getProductById(id);
    }

    @Override
    public Mono add(ProductRequest request) {
        return productService.add(request)
                .flatMap(it -> Mono.just(new ResponseEntity(HttpStatus.CREATED)));
    }
}
