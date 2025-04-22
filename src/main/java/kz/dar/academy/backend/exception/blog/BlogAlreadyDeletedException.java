package kz.dar.academy.backend.exception.blog;

public class BlogAlreadyDeletedException extends RuntimeException {
    public BlogAlreadyDeletedException(String message) {
        super(message);
    }
}
