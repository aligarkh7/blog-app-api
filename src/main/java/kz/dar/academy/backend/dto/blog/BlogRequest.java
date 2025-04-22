package kz.dar.academy.backend.dto.blog;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BlogRequest {
    @NotNull
    @Size(min = 1, max = 255)
    private String title;
    private String content;
}
