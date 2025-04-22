package kz.dar.academy.backend.dto.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInfo {
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
}
