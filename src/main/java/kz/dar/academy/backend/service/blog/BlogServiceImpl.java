package kz.dar.academy.backend.service.blog;

import kz.dar.academy.backend.dto.blog.BlogRequest;
import kz.dar.academy.backend.dto.blog.BlogResponse;
import kz.dar.academy.backend.dto.blog.BlogWithUserInfoResponse;
import kz.dar.academy.backend.dto.blog.UserInfo;
import kz.dar.academy.backend.exception.blog.BlogAlreadyDeletedException;
import kz.dar.academy.backend.exception.blog.BlogNotFoundException;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

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

    private BlogModel getCurrentBlog(Optional<BlogModel> blogModelOptional) {

        if (!blogModelOptional.isPresent()) {
            throw new BlogNotFoundException("Blog not found");
        }

        return blogModelOptional.get();
    }

    @Transactional
    @Override
    public BlogWithUserInfoResponse create(BlogRequest blogRequest) {

        UserModel userModel = getCurrentUser();

        BlogModel blogModel = modelMapper.map(blogRequest, BlogModel.class);

        blogModel.setCreatedAt(LocalDateTime.now());
        blogModel.setUpdatedAt(LocalDateTime.now());
        blogModel.setUserId(userModel.getId());

        blogRepository.save(blogModel);

        BlogWithUserInfoResponse blogWithUserInfoResponse = modelMapper.map(blogModel, BlogWithUserInfoResponse.class);

        blogWithUserInfoResponse.setUserInfo(modelMapper.map(userModel, UserInfo.class));

        return blogWithUserInfoResponse;
    }

    @Override
    public BlogResponse update(UUID id, BlogRequest blogRequest) {

        UserModel userModel = getCurrentUser();

        BlogModel blogModel = getCurrentBlog(blogRepository.findByIdAndUserIdAndIsDeletedFalse(id, userModel.getId()));

        blogModel.setTitle(blogRequest.getTitle());
        blogModel.setContent(blogRequest.getContent());
        blogModel.setUpdatedAt(LocalDateTime.now());

        blogRepository.save(blogModel);

        return modelMapper.map(blogModel, BlogResponse.class);
    }

    @Override
    public BlogWithUserInfoResponse getById(UUID id) {

        UserModel userModel = getCurrentUser();

        BlogModel blogModel = getCurrentBlog(blogRepository.findByIdAndUserIdAndIsDeletedFalse(id, userModel.getId()));

        BlogWithUserInfoResponse blogWithUserInfoResponse = modelMapper.map(blogModel, BlogWithUserInfoResponse.class);

        blogWithUserInfoResponse.setUserInfo(modelMapper.map(userModel, UserInfo.class));

        return blogWithUserInfoResponse;
    }

    @Override
    public List<BlogResponse> getAllByUserId() {

        UserModel userModel = getCurrentUser();

        List<BlogResponse> blogResponseList = new ArrayList<>();

        for (BlogModel blogModel : blogRepository.findAllByUserIdAndIsDeletedFalse(userModel.getId())) {
            blogResponseList.add(modelMapper.map(blogModel, BlogResponse.class));
        }

        return blogResponseList;
    }

    @Override
    public ResponseEntity<String> delete(UUID id) {

        UserModel userModel = getCurrentUser();

        BlogModel blogModel = getCurrentBlog(blogRepository.findByIdAndUserId(id, userModel.getId()));

        if (blogModel.isDeleted()) {
            throw new BlogAlreadyDeletedException("Blog already deleted");
        }

        blogModel.setDeleted(true);

        blogRepository.save(blogModel);

        return ResponseEntity.ok().body("Blog deleted successfully");
    }

}
