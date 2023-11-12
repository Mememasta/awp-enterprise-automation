package ru.awp.enterprise.automation.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.exception.ClientNotFoundException;
import ru.awp.enterprise.automation.mapper.ReportCardDTOMapper;
import ru.awp.enterprise.automation.mapper.SimpleUserResponseMapper;
import ru.awp.enterprise.automation.models.dao.ReportCardDAO;
import ru.awp.enterprise.automation.models.dto.ReportCardDTO;
import ru.awp.enterprise.automation.models.dto.ReportCardUserDTO;
import ru.awp.enterprise.automation.models.request.ReportCardRequest;
import ru.awp.enterprise.automation.service.ReportCardFacadeService;
import ru.awp.enterprise.automation.service.ReportCardService;
import ru.awp.enterprise.automation.service.ReportCardUserService;
import ru.awp.enterprise.automation.service.UserService;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ReportCardFacadeServiceImpl implements ReportCardFacadeService {

    private final ReportCardService reportCardService;
    private final ReportCardUserService reportCardUserService;
    private final UserService userService;

    private final SimpleUserResponseMapper simpleUserResponseMapper;
    private final ReportCardDTOMapper mapper;

    @Override
    public Flux<ReportCardDTO> findReportCardAndProduct(Integer areaId, Integer page, Integer size) {
        return reportCardService.findByArea(areaId, page, size)
                .concatMap(this::buildReportCardDTO);
    }

    @Override
    public Flux<ReportCardDTO> findAll() {
        return reportCardService.findAll()
                .concatMap(this::buildReportCardDTO);
    }

    @Override
    public Flux<ReportCardDTO> findReportCardByUserId(UUID userId) {
        return reportCardService.findByUserId(userId)
                .concatMap(this::buildReportCardDTO);
    }

    @Override
    public Mono<ReportCardDTO> findById(UUID reportId) {
        return reportCardService.findById(reportId)
                .flatMap(this::buildReportCardDTO);
    }

    private Mono<ReportCardDTO> buildReportCardDTO(ReportCardDAO reportCardDAO) {
        var noteProductsMono = reportCardUserService.findReportsCardUser(reportCardDAO.id())
                .collectList();
        var userMono = userService.findById(reportCardDAO.user())
                .map(simpleUserResponseMapper);
        var editUserMono = userService.findById(reportCardDAO.userEdit())
                .map(simpleUserResponseMapper)
                .switchIfEmpty(Mono.just(simpleUserResponseMapper.apply(null)));

        return Mono.zip(Mono.just(reportCardDAO), noteProductsMono, userMono, editUserMono)
                .map(tuple -> mapper.map(tuple.getT1(), tuple.getT2(), tuple.getT3(), tuple.getT4()));
    }

    @Override
    @Transactional
    public Mono<Void> validateAndSaveReportCard(ReportCardRequest request) {
        return validateUser(request.userId())
                .then(validateUsers(request.userReportCard()))
                .then(saveReportCardAndReturnUUID(request))
                .flatMap(uuid -> reportCardUserService.save(uuid, request.userReportCard()));
    }

    @Override
    @Transactional
    public Mono<Void> validateAndUpdateReportCard(UUID reportId, ReportCardRequest request) {
        return validateUser(request.userId())
                .then(validateUser(request.userEditId()))
                .then(deletedReportCardUser(request.deletedUsersReportCard()))
                .then(validateUsersForUpdateNote(request.userReportCard()))
                .then(updateReportCardAndReturnUUID(reportId, request))
                .flatMap(uuid -> reportCardUserService.update(uuid, request.userReportCard()))
                .then();
    }

    private Mono<Void> validateUser(UUID userId) {
        // Проверить наличие клиентской записи в бд
        return userService.findById(String.valueOf(userId))
                .switchIfEmpty(Mono.error(new ClientNotFoundException()))
                .then();
    }

    private Mono<Void> validateUsers(List<ReportCardUserDTO> reportCardUserDTOS) {
        // Проверить наличие всех работников
        return Flux.fromIterable(reportCardUserDTOS)
                .flatMap(reportCardUserDTO -> this.validateUser(reportCardUserDTO.userId()))
                .then();
    }

    private Mono<Void> validateUsersForUpdateNote(List<ReportCardUserDTO> reportCardUserDTOS) {
        // Проверить наличие всех работников
        if (Objects.isNull(reportCardUserDTOS) || reportCardUserDTOS.isEmpty()) {
            return Mono.empty();
        }
        return Flux.fromIterable(reportCardUserDTOS)
                .flatMap(reportCardUserDTO -> this.validateUser(reportCardUserDTO.userId()))
                .then();
    }

    private Mono<Void> deletedReportCardUser(List<Long> userReportCard) {
        // Проверить наличие всех клиентских записей в табеле и удалить их
        if (Objects.isNull(userReportCard) || userReportCard.isEmpty()) {
            return Mono.empty();
        }
        return Flux.fromIterable(userReportCard)
                .flatMap(reportCardUserService::deleteReportCardUser)
                .then();
    }

    private Mono<UUID> saveReportCardAndReturnUUID(ReportCardRequest request) {
        // Сохранить табель и вернуть ее UUID
        return reportCardService.saveReportCard(request)
                .map(ReportCardDAO::id);
    }

    private Mono<UUID> updateReportCardAndReturnUUID(UUID reportCardId, ReportCardRequest request) {
        // Обновить табель и вернуть ее UUID
        return reportCardService.updateReportCard(reportCardId, request)
                .map(ReportCardDAO::id);
    }

}
