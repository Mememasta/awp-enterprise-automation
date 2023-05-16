package ru.awp.enterprise.automation.service;

import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.request.LoginRequest;
import ru.awp.enterprise.automation.models.response.LoginResponse;

public interface LoginService {

    Mono<LoginResponse> login(LoginRequest loginRequest);
}
