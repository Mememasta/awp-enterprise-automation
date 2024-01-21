package ru.awp.enterprise.automation.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.exception.NotFoundProductException;
import ru.awp.enterprise.automation.exception.ProductAlreadyExist;
import ru.awp.enterprise.automation.mapper.ProductDAOMapper;
import ru.awp.enterprise.automation.mapper.ProductMapper;
import ru.awp.enterprise.automation.mapper.ProductWithBalanceMapper;
import ru.awp.enterprise.automation.models.dao.ProductWithBalanceDAO;
import ru.awp.enterprise.automation.models.dto.ProductDTO;
import ru.awp.enterprise.automation.models.dto.ProductWithBalanceDTO;
import ru.awp.enterprise.automation.models.request.ProductRequest;
import ru.awp.enterprise.automation.repository.ProductRepository;
import ru.awp.enterprise.automation.repository.ProductWithBalanceRepository;
import ru.awp.enterprise.automation.service.NoteProductService;
import ru.awp.enterprise.automation.service.ProductService;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductWithBalanceRepository productWithBalanceRepository;
    private final ProductMapper productMapper;
    private final ProductWithBalanceMapper productWithBalanceMapper;
    private final ProductDAOMapper productDaoMapper;
    private final NoteProductService noteProductService;

    @Override
    public Flux<ProductDTO> getAllProducts() {
        return productRepository.findAll(Sort.by("product_id"))
                .map(productMapper);
    }

    @Override
    public Flux<ProductWithBalanceDTO> getAllProductsWithBalance() {
        return productWithBalanceRepository.cleanBalance()
                .thenMany(noteProductService.findAllTotalValue()
                .flatMap(total -> getProductsWithBalanceByAreaIdAndProductId(total.area(), total.product_id())
                        .flatMapMany(product -> {
                            var balance = total.status_0() - total.status_1() + total.status_2();
                            return productWithBalanceRepository.save(productWithBalanceMapper.buildProductDAO(product, balance));
                        })
                )).thenMany(productWithBalanceRepository.findAll().map(productWithBalanceMapper::buildProduct));
    }

    @Override
    public Mono<ProductWithBalanceDAO> getProductsWithBalanceByAreaIdAndProductId(Integer areaId, Long productId) {
        return productWithBalanceRepository.findByAreaIdAndProductId(areaId, productId)
                .switchIfEmpty(Mono.defer(() -> Mono.just(ProductWithBalanceDAO.builder().productId(productId).areaId(areaId).coefficient(0).build())));
    }

    @Override
    public Mono<ProductDTO> getProductById(Long productId) {
        return productRepository.findById(productId)
                .map(productMapper)
                .switchIfEmpty(Mono.error(NotFoundProductException::new));
    }

    @Override
    public Mono<Double> getProductsVolume(List<Long> productIds) {
        return productRepository.getSumVolume(productIds);
    }

    @Override
    @Transactional
    public Mono<Void> add(ProductRequest productRequest) {
        var name = productRequest.name().trim();
        return productRepository.findByNameIgnoreCase(name)
                .flatMap(it -> Mono.error(new ProductAlreadyExist(name)))
                .switchIfEmpty(productRepository.save(productDaoMapper.apply(productRequest)))
                .ofType(Void.class);
    }

    @Override
    @Transactional
    public Mono<Void> updateProduct(Long id, ProductRequest productRequest) {
        var name = productRequest.name().trim();
        return productRepository.findByNameIgnoreCase(name)
                .filter(product -> !Objects.equals(product.productId(), id))
                .flatMap(it -> Mono.error(new ProductAlreadyExist(name)))
                .then(getProductById(id))
                .switchIfEmpty(Mono.error(NotFoundProductException::new))
                .flatMap(product -> productRepository.save(productDaoMapper.apply(id, productRequest)))
                .flatMap(it -> Mono.empty());
    }

    @Override
    @Transactional
    public Mono<Void> updateProductCoefficient(ProductWithBalanceDTO request) {
        return getProductsWithBalanceByAreaIdAndProductId(request.areaId(), request.productId())
                .flatMap(product -> productWithBalanceRepository.save(productWithBalanceMapper.buildProductDAO(product.id(), request)))
                .switchIfEmpty(productWithBalanceRepository.save(productWithBalanceMapper.buildProductDAO(null, request)))
                .then();
    }

    @Override
    public Mono<Void> deleteProduct(Long id) {
        return getProductById(id)
                .switchIfEmpty(Mono.error(NotFoundProductException::new))
                .flatMap(product -> productRepository.deleteById(id));
    }
}
