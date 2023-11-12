package ru.awp.enterprise.automation.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.mapper.ReportCardDAOMapper;
import ru.awp.enterprise.automation.models.dao.ReportCardDAO;
import ru.awp.enterprise.automation.models.request.ReportCardRequest;
import ru.awp.enterprise.automation.repository.ReportCardRepository;
import ru.awp.enterprise.automation.service.ReportCardService;

import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ReportCardServiceImpl implements ReportCardService {

    private final ReportCardRepository repository;
    private final ReportCardDAOMapper mapper;

    @Override
    public Flux<ReportCardDAO> findByArea(Integer areaId) {
        return repository.findAllByArea(areaId, Sort.by("created").descending());
    }

    @Override
    public Flux<ReportCardDAO> findByArea(Integer areaId, Integer page, Integer size) {
        if (Objects.isNull(page) || Objects.isNull(size)) {
            return findByArea(areaId);
        }
        var anyPage = PageRequest.of(page, size, Sort.by("created").descending());
        return repository.findAllByArea(areaId, anyPage);
    }

    @Override
    public Flux<ReportCardDAO> findByUserId(UUID userId) {
        return repository.findAllByUser(userId, Sort.by("created"));
    }

    @Override
    public Mono<ReportCardDAO> findById(UUID uuid) {
        return repository.findById(uuid);
    }

    @Override
    public Flux<ReportCardDAO> findAll() {
        return repository.findAll(Sort.by("created"));
    }

    @Override
    public Mono<ReportCardDAO> updateReportCard(UUID uuid, ReportCardRequest request) {
        return this.findById(uuid)
                .map(reportCard -> mapper.apply(reportCard, request))
                .flatMap(repository::save);
    }

    @Override
    public Mono<ReportCardDAO> saveReportCard(ReportCardRequest request) {
        return repository.save(mapper.apply(request));
    }
}
