package kz.dar.academy.backend.dto.blog;

import kz.dar.academy.backend.dto.comment.CommentsByBlogIdAndUserIdResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BlogWithUserInfoResponse {
    private UUID id;
    private UserInfo user;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CommentsByBlogIdAndUserIdResponse> comments;
}
