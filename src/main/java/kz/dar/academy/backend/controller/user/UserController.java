package kz.dar.academy.backend.controller.user;

import jakarta.validation.Valid;
import kz.dar.academy.backend.dto.user.UserRequest;
import kz.dar.academy.backend.dto.user.UserResponse;
import kz.dar.academy.backend.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public UserResponse getUser() {
        return userService.get();
    }

    @PutMapping
    public UserResponse updateUser(@Valid @RequestBody UserRequest userRequest) {
        return userService.update(userRequest);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser() {
        return userService.delete();
    }

}
