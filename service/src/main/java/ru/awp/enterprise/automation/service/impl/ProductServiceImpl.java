package ru.awp.enterprise.automation.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.mapper.ProductDAOMapper;
import ru.awp.enterprise.automation.mapper.ProductMapper;
import ru.awp.enterprise.automation.models.dto.ProductDTO;
import ru.awp.enterprise.automation.models.request.ProductRequest;
import ru.awp.enterprise.automation.repository.ProductRepository;
import ru.awp.enterprise.automation.service.ProductService;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductDAOMapper productDaoMapper;

    @Override
    public Flux<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .map(productMapper);
    }

    @Override
    public Mono<ProductDTO> getProductById(Long productId) {
        return productRepository.findById(productId)
                .map(productMapper);
    }

    @Override
    public Mono<Void> add(ProductRequest productRequest) {
        return productRepository.save(productDaoMapper.apply(productRequest))
                .flatMap(it -> Mono.empty());
    }
}
