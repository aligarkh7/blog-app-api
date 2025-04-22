package kz.dar.academy.backend.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentResponse {
    private UUID id;
    private UUID blogId;
    private UUID userId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
