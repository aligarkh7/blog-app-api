package kz.dar.academy.backend.dto.comment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentRequest {
    @NotNull
    private UUID blogId;
    @NotNull
    @Size(min = 1)
    private String content;
}
