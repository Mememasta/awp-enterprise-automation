package ru.awp.enterprise.automation.service;

import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dao.UserDAO;
import ru.awp.enterprise.automation.models.request.LoginRequest;
import ru.awp.enterprise.automation.models.request.SignUpRequest;
import ru.awp.enterprise.automation.models.response.LoginResponse;

public interface LoginService {

    Mono<LoginResponse> login(LoginRequest loginRequest);

    Mono<UserDAO> signUp(SignUpRequest signUpRequest);

}
