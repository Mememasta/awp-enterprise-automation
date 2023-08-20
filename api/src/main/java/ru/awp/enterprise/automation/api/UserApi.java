package ru.awp.enterprise.automation.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dto.UserDTO;
import ru.awp.enterprise.automation.models.request.UserChangeRequest;
import ru.awp.enterprise.automation.models.response.UserResponse;

import java.util.UUID;

@RequestMapping(value = "/api/v1/user")
public interface UserApi {

    @GetMapping(value = "/me")
    Mono<UserDTO> getCurrentUser();

    @GetMapping(value = "/all")
    Flux<UserResponse> getAllUser();

    @PatchMapping(value = "/{id}")
    Mono<ResponseEntity<HttpStatus>> updateUser(@PathVariable(value = "id") UUID id, @RequestBody @Validated UserChangeRequest userChangeRequest);

    @DeleteMapping(value = "/{id}")
    Mono<ResponseEntity<HttpStatus>> deleteUser(@PathVariable(value = "id") UUID id);

}
