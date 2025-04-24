package kz.dar.academy.backend.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentsByBlogIdAndUserIdResponse {
    private UUID id;
    private String email;
    private String content;
    private LocalDateTime createdAt;
}
