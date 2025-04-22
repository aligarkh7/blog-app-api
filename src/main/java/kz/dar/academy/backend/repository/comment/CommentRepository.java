package kz.dar.academy.backend.repository.comment;

import kz.dar.academy.backend.model.comment.CommentModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommentRepository extends CrudRepository<CommentModel, UUID> {

    Optional<CommentModel> findByIdAndUserId(UUID id, UUID userId);

    Optional<CommentModel> findByIdAndUserIdAndIsDeletedFalse(UUID id, UUID userId);

    List<CommentModel> findAllByBlogIdAndUserIdAndIsDeletedFalse(UUID blogId, UUID userId);
}
