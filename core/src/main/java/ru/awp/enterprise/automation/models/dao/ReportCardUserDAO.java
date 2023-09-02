package ru.awp.enterprise.automation.models.dao;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;
import java.util.UUID;

@Builder
@Table(name = "report_card_user")
public record ReportCardUserDAO(

        @Id
        Long id,

        @Column(value = "report_id")
        UUID reportCardId,

        @Column(value = "user_id")
        UUID userId,

        @Column(value = "hours")
        Double hours

) implements Persistable<Long> {

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    @Transient
    public boolean isNew() {
        return Objects.isNull(this.id);
    }

}
