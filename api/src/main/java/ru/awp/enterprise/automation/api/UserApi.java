package ru.awp.enterprise.automation.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dto.UserDTO;

@RequestMapping(value = "/api/v1/user")
public interface UserApi {

    @GetMapping(value = "/me")
    Mono<UserDTO> getCurrentUser();

    @GetMapping(value = "/all")
    Flux<UserDTO> getAllUser();

}
