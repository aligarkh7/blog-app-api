package kz.dar.academy.backend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
