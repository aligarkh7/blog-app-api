package kz.dar.academy.backend.controller.blog;

import jakarta.validation.Valid;
import kz.dar.academy.backend.dto.blog.BlogRequest;
import kz.dar.academy.backend.dto.blog.BlogResponse;
import kz.dar.academy.backend.dto.blog.BlogWithUserInfoResponse;
import kz.dar.academy.backend.service.blog.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping
    public BlogWithUserInfoResponse createBlog(@Valid @RequestBody BlogRequest blogRequest) {
        return blogService.create(blogRequest);
    }

    @PutMapping("/{id}")
    public BlogResponse updateBlog(@PathVariable UUID id, @Valid @RequestBody BlogRequest blogRequest) {
        return blogService.update(id, blogRequest);
    }

    @GetMapping("/{id}")
    public BlogWithUserInfoResponse getBlogById(@PathVariable UUID id) {
        return blogService.getById(id);
    }

    @GetMapping
    public List<BlogResponse> getAllBlogs() {
        return blogService.getAllByUserId();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable UUID id) {
        return blogService.delete(id);
    }
}
