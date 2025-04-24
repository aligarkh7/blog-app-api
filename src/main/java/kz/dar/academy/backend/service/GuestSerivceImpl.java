package kz.dar.academy.backend.service;

import kz.dar.academy.backend.dto.blog.BlogResponse;
import kz.dar.academy.backend.dto.blog.BlogWithUserInfoResponse;
import kz.dar.academy.backend.dto.blog.UserInfo;
import kz.dar.academy.backend.dto.comment.CommentsByBlogIdAndUserIdResponse;
import kz.dar.academy.backend.dto.user.UserResponse;
import kz.dar.academy.backend.exception.blog.BlogNotFoundException;
import kz.dar.academy.backend.model.blog.BlogModel;
import kz.dar.academy.backend.model.comment.CommentModel;
import kz.dar.academy.backend.model.user.UserModel;
import kz.dar.academy.backend.repository.blog.BlogRepository;
import kz.dar.academy.backend.repository.comment.CommentRepository;
import kz.dar.academy.backend.repository.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GuestSerivceImpl implements GuestSerivce {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CommentRepository commentRepository;

    static ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }


    @Override
    public BlogWithUserInfoResponse getBlogsByBlogId(UUID blogId) {

        Optional<BlogModel> blogModelOptional = blogRepository.findByIdAndIsDeletedFalse(blogId);

        if (!blogModelOptional.isPresent()) {
            throw new BlogNotFoundException("Blog not found");
        }

        BlogModel blogModel = blogModelOptional.get();

        BlogWithUserInfoResponse blogWithUserInfoResponse = modelMapper.map(blogModel, BlogWithUserInfoResponse.class);

        Optional<UserModel> userModelOptional = userRepository.findByIdAndIsDeletedFalse(blogModel.getUserId());

        UserModel userModel = userModelOptional.get();

        blogWithUserInfoResponse.setUser(modelMapper.map(userModel, UserInfo.class));

        List<CommentsByBlogIdAndUserIdResponse> commentsByBlogIdResponseList = new ArrayList<>();

        for (CommentModel commentModel : commentRepository.findAllByBlogIdAndIsDeletedFalse(blogId)) {
            CommentsByBlogIdAndUserIdResponse commentsByBlogIdAndUserIdResponse = modelMapper.map(commentModel, CommentsByBlogIdAndUserIdResponse.class);
            commentsByBlogIdAndUserIdResponse.setEmail(userRepository.findByIdAndIsDeletedFalse(commentModel.getUserId()).get().getEmail());
            commentsByBlogIdResponseList.add(commentsByBlogIdAndUserIdResponse);

        }

        blogWithUserInfoResponse.setComments(commentsByBlogIdResponseList);

        return blogWithUserInfoResponse;
    }

    @Override
    public List<BlogResponse> getBlogs() {
        List<BlogResponse> blogResponseList = new ArrayList<>();

        for (BlogModel blogModel : blogRepository.findAllByIsDeletedFalse()) {
            blogResponseList.add(modelMapper.map(blogModel, BlogResponse.class));
        }

        return blogResponseList;
    }

    @Override
    public List<UserResponse> getUsers() {

        List<UserResponse> userResponseList = new ArrayList<>();

        for (UserModel userModel : userRepository.findAllByIsDeletedFalse()) {
            userResponseList.add(modelMapper.map(userModel, UserResponse.class));
        }

        return userResponseList;
    }
}
