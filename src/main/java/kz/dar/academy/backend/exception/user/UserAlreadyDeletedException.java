package kz.dar.academy.backend.exception.user;

public class UserAlreadyDeletedException extends RuntimeException {
    public UserAlreadyDeletedException(String message) {
        super(message);
    }
}
