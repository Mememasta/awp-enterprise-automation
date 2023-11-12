package ru.awp.enterprise.automation.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dao.ReportCardDAO;
import ru.awp.enterprise.automation.models.request.ReportCardRequest;

import java.util.UUID;

public interface ReportCardService {

    Flux<ReportCardDAO> findByArea(Integer areaId);

    Flux<ReportCardDAO> findByArea(Integer areaId, Integer page, Integer size);

    Flux<ReportCardDAO> findByUserId(UUID userId);

    Mono<ReportCardDAO> findById(UUID uuid);

    Flux<ReportCardDAO> findAll();

    Mono<ReportCardDAO> updateReportCard(UUID uuid, ReportCardRequest request);

    Mono<ReportCardDAO> saveReportCard(ReportCardRequest request);

}
