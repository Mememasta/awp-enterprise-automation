package ru.awp.enterprise.automation.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dto.ReportCardDTO;
import ru.awp.enterprise.automation.models.request.ReportCardRequest;

import java.util.UUID;

public interface ReportCardFacadeService {

    Flux<ReportCardDTO> findReportCardAndProduct(Integer areaId, Integer page, Integer size);

    Flux<ReportCardDTO> findAll();

    Flux<ReportCardDTO> findReportCardByUserId(UUID userId);

    Mono<ReportCardDTO> findById(UUID reportId);

    Mono<Void> validateAndSaveReportCard(ReportCardRequest request);

    Mono<Void> validateAndUpdateReportCard(UUID reportId, ReportCardRequest request);

}
