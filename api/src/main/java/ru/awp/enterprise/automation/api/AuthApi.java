package ru.awp.enterprise.automation.api;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.request.LoginRequest;
import ru.awp.enterprise.automation.models.request.SignUpRequest;
import ru.awp.enterprise.automation.models.response.LoginResponse;

public interface AuthApi {

    @PostMapping(value = "/auth")
    Mono<ResponseEntity<LoginResponse>> login(@RequestBody @Validated LoginRequest request);

    @PostMapping(value = "/signup")
    Mono<ResponseEntity<Object>> signUp(@RequestBody @Validated SignUpRequest request);

}
