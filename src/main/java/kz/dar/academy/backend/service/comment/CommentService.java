package kz.dar.academy.backend.service.comment;

import kz.dar.academy.backend.dto.comment.CommentRequest;
import kz.dar.academy.backend.dto.comment.CommentResponse;
import kz.dar.academy.backend.model.comment.CommentModel;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface CommentService {

    CommentResponse create(CommentRequest commentRequest);

    CommentResponse update(UUID id, CommentRequest commentRequest);

    List<CommentModel> getAllByBlogId(UUID blogId);

    ResponseEntity<String> deleteById(UUID id);

}
