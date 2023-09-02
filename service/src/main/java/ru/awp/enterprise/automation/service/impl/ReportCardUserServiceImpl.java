package ru.awp.enterprise.automation.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.exception.NoteProductNotFoundException;
import ru.awp.enterprise.automation.exception.ReportCardNotFoundException;
import ru.awp.enterprise.automation.exception.UserDeleteException;
import ru.awp.enterprise.automation.mapper.ReportCardUserDAOMapper;
import ru.awp.enterprise.automation.models.dao.ReportCardUserDAO;
import ru.awp.enterprise.automation.models.dto.ReportCardUserDTO;
import ru.awp.enterprise.automation.repository.ReportCardUserRepository;
import ru.awp.enterprise.automation.service.ReportCardUserService;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ReportCardUserServiceImpl implements ReportCardUserService {

    private final ReportCardUserRepository repository;
    private final ReportCardUserDAOMapper mapper;

    @Override
    public Mono<Void> save(UUID uuid, List<ReportCardUserDTO> reportCardUserDTOS) {
        if (Objects.isNull(uuid)) {
            throw new ReportCardNotFoundException();
        }
        return Flux.fromIterable(reportCardUserDTOS)
                .map(reportCardUser -> mapper.apply(uuid, reportCardUser))
                .flatMap(repository::save)
                .then(Mono.empty());
    }

    @Override
    public Mono<Void> update(UUID uuid, List<ReportCardUserDTO> reportCardUserDTOS) {
        if (Objects.isNull(uuid)) {
            throw new ReportCardNotFoundException();
        }
        if (Objects.isNull(reportCardUserDTOS) || reportCardUserDTOS.isEmpty()) {
            return Mono.empty();
        }
        return Flux.fromIterable(reportCardUserDTOS)
                .flatMap(reportCardUser -> buildNoteProduct(uuid, reportCardUser))
                .flatMap(repository::save)
                .then(Mono.empty());
    }

    private Mono<ReportCardUserDAO> buildNoteProduct(UUID uuid, ReportCardUserDTO reportCardUserDTO) {
        if (Objects.isNull(reportCardUserDTO.id())) {
            return Mono.just(mapper.apply(uuid, reportCardUserDTO));
        }
        return repository.findById(reportCardUserDTO.id())
                .switchIfEmpty(Mono.error(new NoteProductNotFoundException()));
    }

    @Override
    public Flux<ReportCardUserDTO> findReportsCardUser(UUID uuid) {
        return repository.findAllByReportCardId(uuid)
                .map(mapper::apply);
    }

    @Override
    public Mono<Void> deleteReportCardUser(Long reportCardUserId) {
        return repository.findById(reportCardUserId)
                .switchIfEmpty(Mono.error(UserDeleteException::new))
                .flatMap(repository::delete);
    }
}
