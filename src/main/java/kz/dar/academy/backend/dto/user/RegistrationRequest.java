package kz.dar.academy.backend.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrationRequest {
    @Email
    private String email;
    @NotNull
    @Size(min = 6, max = 255)
    private String password;
    @NotNull
    @Size(min = 1, max = 64)
    private String firstName;
    private String lastName;
    private LocalDate birthday;
}
