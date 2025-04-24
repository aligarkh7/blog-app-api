package kz.dar.academy.backend.dto.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequest {
//    @NotNull
//    @Size(min = 1, max = 64)
    private String firstName;
    private String lastName;
    private LocalDate birthday;
}
