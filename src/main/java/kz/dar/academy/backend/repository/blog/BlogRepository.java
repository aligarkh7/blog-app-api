package kz.dar.academy.backend.repository.blog;

import kz.dar.academy.backend.model.blog.BlogModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BlogRepository extends CrudRepository<BlogModel, UUID> {

    Optional<BlogModel> findByIdAndUserIdAndIsDeletedFalse(UUID id, UUID userId);

    Optional<BlogModel> findByIdAndUserId(UUID id, UUID userId);

    List<BlogModel> findAllByIsDeletedFalse();

    List<BlogModel> findAllByUserIdAndIsDeletedFalse(UUID id);

    Optional<BlogModel> findByIdAndIsDeletedFalse(UUID id);

}
