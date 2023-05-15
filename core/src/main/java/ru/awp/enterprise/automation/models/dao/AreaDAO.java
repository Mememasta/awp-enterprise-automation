package ru.awp.enterprise.automation.models.dao;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import ru.awp.enterprise.automation.models.dto.ProductDTO;
import ru.awp.enterprise.automation.models.dto.UserDTO;

import java.util.List;
import java.util.UUID;

@Builder
@Table(name = "areas")
public record AreaDAO(
        @Id
        UUID id,
        @Column(value = "name")
        String name,

        @Transient
        List<UserDTO> userDTOList,
        @Transient
        List<ProductDTO> productDTOList
) {
}
