package kz.dar.academy.backend.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(value = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserModel {
    @Id
    private UUID id;
    private String email;
    private String password;
    @Column(value = "first_name")
    private String firstName;
    @Column(value = "last_name")
    private String lastName;
    private LocalDate birthday;
    @Column(value = "created_at")
    private LocalDateTime createdAt;
    @Column(value = "updated_at")
    private LocalDateTime updatedAt;
    @Column(value = "is_deleted")
    private boolean isDeleted;
}
