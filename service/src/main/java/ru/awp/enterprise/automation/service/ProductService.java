package ru.awp.enterprise.automation.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dao.ProductWithBalanceDAO;
import ru.awp.enterprise.automation.models.dto.ProductDTO;
import ru.awp.enterprise.automation.models.dto.ProductWithBalanceDTO;
import ru.awp.enterprise.automation.models.request.ProductRequest;

import java.util.List;

public interface ProductService {

    Flux<ProductDTO> getAllProducts();
    Flux<ProductWithBalanceDTO> getAllProductsWithBalance();
    Mono<ProductWithBalanceDAO> getProductsWithBalanceByAreaIdAndProductId(Integer areaId, Long productId);
    Mono<ProductDTO> getProductById(Long productId);
    Mono<Double> getProductsVolume(List<Long> productIds);
    Mono<Void> add(ProductRequest productRequest);
    Mono<Void> updateProduct(Long id, ProductRequest productRequest);
    Mono<Void> updateProductCoefficient(ProductWithBalanceDTO request);
    Mono<Void> deleteProduct(Long id);

}
