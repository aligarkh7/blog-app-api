package kz.dar.academy.backend.service.blog;

import kz.dar.academy.backend.dto.blog.BlogRequest;
import kz.dar.academy.backend.dto.blog.BlogResponse;
import kz.dar.academy.backend.dto.blog.BlogWithUserInfoResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface BlogService {
    BlogWithUserInfoResponse create(BlogRequest blogRequest);

    BlogResponse update(UUID id, BlogRequest blogRequest);

    BlogWithUserInfoResponse getById(UUID id);

    List<BlogResponse> getAllByUserId();

    ResponseEntity<String> delete(UUID id);

}
