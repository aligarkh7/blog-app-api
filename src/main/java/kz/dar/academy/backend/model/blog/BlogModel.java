package kz.dar.academy.backend.model.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(value = "blogs")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BlogModel {
    @Id
    private UUID id;
    @Column(value = "user_id")
    private UUID userId;
    private String title;
    private String content;
    @Column(value = "created_at")
    private LocalDateTime createdAt;
    @Column(value = "updated_at")
    private LocalDateTime updatedAt;
    @Column(value = "is_deleted")
    private boolean isDeleted;
}
