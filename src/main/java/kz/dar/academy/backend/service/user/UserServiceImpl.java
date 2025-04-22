package kz.dar.academy.backend.service.user;

import kz.dar.academy.backend.dto.user.RegistrationRequest;
import kz.dar.academy.backend.dto.user.UserRequest;
import kz.dar.academy.backend.dto.user.UserResponse;
import kz.dar.academy.backend.exception.user.UserAlreadyDeletedException;
import kz.dar.academy.backend.exception.user.UserAlreadyExistsException;
import kz.dar.academy.backend.exception.user.UserNotFoundException;
import kz.dar.academy.backend.model.blog.BlogModel;
import kz.dar.academy.backend.model.user.UserModel;
import kz.dar.academy.backend.repository.blog.BlogRepository;
import kz.dar.academy.backend.repository.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BlogRepository blogRepository;

    static ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    private String getEmailName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    private UserModel getCurrentUserIsDeletedFalse() {
        return getCurrentUser(userRepository.findByEmailAndIsDeletedFalse(getEmailName()));
    }

    private UserModel getCurrentUser(Optional<UserModel> userModelOptional) {

        if (!userModelOptional.isPresent()) {
            throw new UserNotFoundException("User not found");
        }
        return userModelOptional.get();
    }

    @Transactional
    @Override
    public UserResponse create(RegistrationRequest registrationRequest) {

        Optional<UserModel> userModelOptional = userRepository.findByEmail(registrationRequest.getEmail());

        if (!userModelOptional.isPresent()) {
            UserModel userModel = modelMapper.map(registrationRequest, UserModel.class);

            userModel.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            userModel.setCreatedAt(LocalDateTime.now());
            userModel.setUpdatedAt(LocalDateTime.now());

            userRepository.save(userModel);

            return modelMapper.map(userModel, UserResponse.class);

        } else {

            UserModel userModel = userModelOptional.get();

            if (userModel.isDeleted()) {
                throw new UserAlreadyDeletedException("User has been deleted to register please use another email address");
            }

            throw new UserAlreadyExistsException("User already exists");
        }
    }

    @Transactional
    @Override
    public UserResponse update(UserRequest userRequest) {

        UserModel userModel = getCurrentUserIsDeletedFalse();

        userModel.setFirstName(userRequest.getFirstName());
        userModel.setLastName(userRequest.getLastName());
        userModel.setBirthday(userRequest.getBirthday());
        userModel.setUpdatedAt(LocalDateTime.now());

        userRepository.save(userModel);

        return modelMapper.map(userModel, UserResponse.class);
    }

    @Override
    public UserResponse get() {
        UserModel userModel = getCurrentUserIsDeletedFalse();

        return modelMapper.map(userModel, UserResponse.class);
    }

    @Transactional
    @Override
    public ResponseEntity<String> delete() {

        UserModel userModel = getCurrentUser(userRepository.findByEmail(getEmailName()));

        if (userModel.isDeleted()) {
            throw new UserAlreadyDeletedException("User already deleted");
        }

        for (BlogModel blogModel : blogRepository.findAllByUserIdAndIsDeletedFalse(userModel.getId())) {
            blogModel.setDeleted(true);
            blogRepository.save(blogModel);
        }

        userModel.setDeleted(true);

        userRepository.save(userModel);

        return ResponseEntity.ok().body("User deleted successfully");
    }
}
