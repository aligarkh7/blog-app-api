package kz.dar.academy.backend.controller;

import kz.dar.academy.backend.dto.blog.BlogResponse;
import kz.dar.academy.backend.dto.blog.BlogWithUserInfoResponse;
import kz.dar.academy.backend.dto.user.UserResponse;
import kz.dar.academy.backend.service.GuestSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/public")
public class GuestController {

    @Autowired
    private GuestSerivce guestSerivce;

    @GetMapping("/blog/{blogId}")
    public BlogWithUserInfoResponse getBlogByBlogId(@PathVariable UUID blogId) {
        return guestSerivce.getBlogsByBlogId(blogId);
    }

    @GetMapping("blog")
    public List<BlogResponse> getBlogs() {
        return guestSerivce.getBlogs();
    }

    @GetMapping("user")
    public List<UserResponse> getUsers() {
        return guestSerivce.getUsers();
    }

}
