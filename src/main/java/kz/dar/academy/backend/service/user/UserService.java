package kz.dar.academy.backend.service.user;

import kz.dar.academy.backend.dto.user.RegistrationRequest;
import kz.dar.academy.backend.dto.user.UserRequest;
import kz.dar.academy.backend.dto.user.UserResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    UserResponse create(RegistrationRequest registrationRequest);

    UserResponse update(UserRequest userRequest);

    UserResponse get();

    ResponseEntity<String> delete();

}
