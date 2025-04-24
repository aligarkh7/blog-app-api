package kz.dar.academy.backend.repository.user;

import kz.dar.academy.backend.model.user.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<UserModel, UUID> {

    Optional<UserModel> findByEmail(String email);

    Optional<UserModel> findByEmailAndIsDeletedFalse(String email);

    Optional<UserModel> findById(UUID id);

    Optional<UserModel> findByIdAndIsDeletedFalse(UUID id);

    List<UserModel> findAllByIsDeletedFalse();

}
