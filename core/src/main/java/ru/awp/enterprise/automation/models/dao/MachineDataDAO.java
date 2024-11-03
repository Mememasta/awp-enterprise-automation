package ru.awp.enterprise.automation.models.dao;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder
@Table(value = "machine_data")
public class MachineDataDAO {
    @Id
    private Long id;

    @Column("topic")
    private String topic;

    @Column("value")
    private String value;

    @Column("event_date")
    private LocalDateTime eventDate;
}
