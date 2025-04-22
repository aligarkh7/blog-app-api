package kz.dar.academy.backend.service.comment;

import kz.dar.academy.backend.dto.comment.CommentRequest;
import kz.dar.academy.backend.dto.comment.CommentResponse;
import kz.dar.academy.backend.exception.blog.BlogNotFoundException;
import kz.dar.academy.backend.exception.comment.CommentAlreadyDeletedException;
import kz.dar.academy.backend.exception.comment.CommentNotFoundException;
import kz.dar.academy.backend.exception.user.UserNotFoundException;
import kz.dar.academy.backend.model.blog.BlogModel;
import kz.dar.academy.backend.model.comment.CommentModel;
import kz.dar.academy.backend.model.user.UserModel;
import kz.dar.academy.backend.repository.blog.BlogRepository;
import kz.dar.academy.backend.repository.comment.CommentRepository;
import kz.dar.academy.backend.repository.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogRepository blogRepository;

    static ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    private UserModel getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        Optional<UserModel> userModelOptional = userRepository.findByEmailAndIsDeletedFalse(email);

        if (!userModelOptional.isPresent()) {
            throw new UserNotFoundException("User not found with email: " + email);
        }

        return userModelOptional.get();
    }

    private BlogModel getCurrentBlog(UUID blogId) {
        Optional<BlogModel> blogModelOptional = blogRepository.findByIdAndUserIdAndIsDeletedFalse(blogId, getCurrentUser().getId());

        if (!blogModelOptional.isPresent()) {
            throw new BlogNotFoundException("Blog not found");
        }

        return blogModelOptional.get();
    }

    private CommentModel getCurrentComment(UUID id, UUID userId) {
        Optional<CommentModel> commentModel = commentRepository.findByIdAndUserIdAndIsDeletedFalse(id, userId);

        if (!commentModel.isPresent()) {
            throw new CommentNotFoundException("Comment not found");
        }

        return commentModel.get();
    }

    @Override
    public CommentResponse create(CommentRequest commentRequest) {
        UserModel userModel = getCurrentUser();
        BlogModel blogModel = getCurrentBlog(commentRequest.getBlogId());

        CommentModel commentModel = modelMapper.map(commentRequest, CommentModel.class);

        commentModel.setUserId(userModel.getId());
        commentModel.setCreatedAt(LocalDateTime.now());
        commentModel.setUpdatedAt(LocalDateTime.now());

        commentRepository.save(commentModel);

        return modelMapper.map(commentModel, CommentResponse.class);
    }

    @Override
    public CommentResponse update(UUID id, CommentRequest commentRequest) {
        UserModel userModel = getCurrentUser();
        BlogModel blogModel = getCurrentBlog(commentRequest.getBlogId());

        CommentModel commentModel = getCurrentComment(id, userModel.getId());

        commentModel.setContent(commentRequest.getContent());
        commentModel.setUpdatedAt(LocalDateTime.now());
        commentRepository.save(commentModel);

        return modelMapper.map(commentModel, CommentResponse.class);
    }

    @Override
    public List<CommentModel> getAllByBlogId(UUID blogId) {

        UserModel userModel = getCurrentUser();
        BlogModel blogModel = getCurrentBlog(blogId);

        List<CommentModel> commentModelList = new ArrayList<>(commentRepository.findAllByBlogIdAndUserIdAndIsDeletedFalse(blogId, userModel.getId()));

        return commentModelList;
    }

    @Override
    public ResponseEntity<String> deleteById(UUID id) {

        UserModel userModel = getCurrentUser();

        Optional<CommentModel> commentModelOptional = commentRepository.findByIdAndUserId(id, userModel.getId());

        if (!commentModelOptional.isPresent()) {
            throw new CommentNotFoundException("Comment not found");
        }

        CommentModel commentModel = commentModelOptional.get();

        if (commentModel.isDeleted()) {
            throw new CommentAlreadyDeletedException("Comment already deleted");
        }

        commentModel.setDeleted(true);

        commentRepository.save(commentModel);

        return ResponseEntity.ok("Comment deleted successfully");
    }
}
