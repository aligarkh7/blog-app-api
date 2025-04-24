package kz.dar.academy.backend.service;

import kz.dar.academy.backend.dto.blog.BlogResponse;
import kz.dar.academy.backend.dto.blog.BlogWithUserInfoResponse;
import kz.dar.academy.backend.dto.user.UserResponse;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

public interface GuestSerivce {

    BlogWithUserInfoResponse getBlogsByBlogId(UUID blogId);

    List<BlogResponse> getBlogs();

    List<UserResponse> getUsers();

}
