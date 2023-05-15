package ru.awp.enterprise.automation.models.dao;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import ru.awp.enterprise.automation.models.dto.AreaDTO;
import ru.awp.enterprise.automation.models.dto.UserDTO;

import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
@Table(name = "products")
public record ProductDAO(
    @Id
    Long id,
    @Column(value = "value")
    Long value,
    @Column(value = "is_defect")
    boolean isDefect,
    @Column(value = "user_id")
    UUID user,
    @Column(value = "date")
    OffsetDateTime date,
    @Column(value = "area_id")
    UUID area,

    @Transient
    UserDTO userDTO,
    @Transient
    AreaDTO areaDTO
) {
}
