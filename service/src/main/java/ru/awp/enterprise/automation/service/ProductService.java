package ru.awp.enterprise.automation.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dto.ProductDTO;
import ru.awp.enterprise.automation.models.request.ProductRequest;

public interface ProductService {

    Flux<ProductDTO> getAllProducts();

    Mono<ProductDTO> getProductById(Long productId);

    Mono<Void> add(ProductRequest productRequest);

    Mono<Void> updateProduct(Long id, ProductRequest productRequest);

    Mono<Void> deleteProduct(Long id);

}
