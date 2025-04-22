package kz.dar.academy.backend.exception.comment;

public class CommentAlreadyDeletedException extends RuntimeException {
    public CommentAlreadyDeletedException(String message) {
        super(message);
    }
}
