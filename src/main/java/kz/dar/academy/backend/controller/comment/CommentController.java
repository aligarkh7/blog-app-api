package kz.dar.academy.backend.controller.comment;

import jakarta.validation.Valid;
import kz.dar.academy.backend.dto.comment.CommentRequest;
import kz.dar.academy.backend.dto.comment.CommentResponse;
import kz.dar.academy.backend.model.comment.CommentModel;
import kz.dar.academy.backend.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public CommentResponse create(@Valid @RequestBody CommentRequest commentRequest) {
        return commentService.create(commentRequest);
    }

    @PutMapping("/{id}")
    public CommentResponse update(@PathVariable UUID id, @Valid @RequestBody CommentRequest commentRequest) {
        return commentService.update(id, commentRequest);
    }

    @GetMapping("/{blogId}")
    public List<CommentModel> getAllByBlogId(@PathVariable UUID blogId) {
        return commentService.getAllByBlogId(blogId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable UUID id) {
        return commentService.deleteById(id);
    }

}
