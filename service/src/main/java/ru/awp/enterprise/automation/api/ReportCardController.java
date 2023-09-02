package ru.awp.enterprise.automation.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dto.ReportCardDTO;
import ru.awp.enterprise.automation.models.request.ReportCardRequest;
import ru.awp.enterprise.automation.service.ReportCardFacadeService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ReportCardController implements ReportCardApi {

    private final ReportCardFacadeService service;

    @Override
    public Flux<ReportCardDTO> findReportCardByAreaId(Integer areaId) {
        return service.findReportCardAndProduct(areaId);
    }

    @Override
    public Flux<ReportCardDTO> findAllNote() {
        return service.findAll();
    }

    @Override
    public Mono<ReportCardDTO> getReportCardById(UUID uuid) {
        return service.findById(uuid);
    }

    @Override
    public Flux<ReportCardDTO> getNotesByUserId(UUID userId) {
        return service.findReportCardByUserId(userId);
    }

    @Override
    public Mono<Void> updateReportCard(UUID uuid, ReportCardRequest request) {
        return service.validateAndUpdateReportCard(uuid, request);
    }

    @Override
    public Mono<Void> add(ReportCardRequest request) {
        return service.validateAndSaveReportCard(request);
    }
}
