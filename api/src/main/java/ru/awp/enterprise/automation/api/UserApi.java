package ru.awp.enterprise.automation.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dto.UserDTO;

@RequestMapping(value = "/v1/api/user")
public interface UserApi {

    @GetMapping(value = "/me")
    Mono<UserDTO> getCurrentUser();

}
