package ru.awp.enterprise.automation.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dto.ReportCardUserDTO;

import java.util.List;
import java.util.UUID;

public interface ReportCardUserService {

    Mono<Void> save(UUID uuid, List<ReportCardUserDTO> reportCardUserDTOS);

    Mono<Void> update(UUID uuid, List<ReportCardUserDTO> reportCardUserDTOS);

    Flux<ReportCardUserDTO> findReportsCardUser(UUID uuid);

    Mono<Void> deleteReportCardUser(Long reportCardUserId);

}
