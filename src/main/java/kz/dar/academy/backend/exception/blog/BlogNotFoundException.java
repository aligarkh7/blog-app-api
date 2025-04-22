package kz.dar.academy.backend.exception.blog;

public class BlogNotFoundException extends RuntimeException {
    public BlogNotFoundException(String message) {
        super(message);
    }
}
