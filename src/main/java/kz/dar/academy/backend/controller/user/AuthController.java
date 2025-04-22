package kz.dar.academy.backend.controller.user;

import jakarta.validation.Valid;
import kz.dar.academy.backend.dto.user.RegistrationRequest;
import kz.dar.academy.backend.dto.user.UserResponse;
import kz.dar.academy.backend.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping
    public UserResponse createUser(@Valid @RequestBody RegistrationRequest registrationRequest) {
        return userService.create(registrationRequest);
    }
}
