package ru.awp.enterprise.automation.mapper;

import org.springframework.stereotype.Component;
import ru.awp.enterprise.automation.models.dao.ProductWithBalanceDAO;
import ru.awp.enterprise.automation.models.dto.ProductWithBalanceDTO;

@Component
public class ProductWithBalanceMapper {

    public ProductWithBalanceDTO buildProduct(Integer balance, ProductWithBalanceDAO productWithBalanceDAO) {
        return ProductWithBalanceDTO.builder()
                .areaId(productWithBalanceDAO.areaId())
                .productId(productWithBalanceDAO.productId())
                .coefficient(productWithBalanceDAO.coefficient())
                .balance(balance)
                .build();
    }

    public ProductWithBalanceDAO buildProductDAO(Long id, ProductWithBalanceDTO productWithBalanceDTO) {
        return ProductWithBalanceDAO.builder()
                .id(id)
                .areaId(productWithBalanceDTO.areaId())
                .productId(productWithBalanceDTO.productId())
                .coefficient(productWithBalanceDTO.coefficient())
                .build();
    }

}
