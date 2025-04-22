package kz.dar.academy.backend.exception;

import kz.dar.academy.backend.dto.error.ErrorDto;
import kz.dar.academy.backend.exception.blog.BlogAlreadyDeletedException;
import kz.dar.academy.backend.exception.blog.BlogNotFoundException;
import kz.dar.academy.backend.exception.comment.CommentAlreadyDeletedException;
import kz.dar.academy.backend.exception.comment.CommentNotFoundException;
import kz.dar.academy.backend.exception.user.UserAlreadyDeletedException;
import kz.dar.academy.backend.exception.user.UserAlreadyExistsException;
import kz.dar.academy.backend.exception.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    // users exception
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDto handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return new ErrorDto(HttpStatus.CONFLICT.value(), e.getMessage());
    }

    @ExceptionHandler(UserAlreadyDeletedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleUserAlreadyDeleted(UserAlreadyDeletedException e) {
        return new ErrorDto(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleUserNotFound(UserNotFoundException ex) {
        return new ErrorDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    // blogs exception
    @ExceptionHandler(BlogNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleBlogNotFound(BlogNotFoundException ex) {
        return new ErrorDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(BlogAlreadyDeletedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleBlogAlreadyDeleted(BlogAlreadyDeletedException ex) {
        return new ErrorDto(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    // comments exception
    @ExceptionHandler(CommentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleCommentNotFound(CommentNotFoundException ex) {
        return new ErrorDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(CommentAlreadyDeletedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleCommentAlreadyDeleted(CommentAlreadyDeletedException ex) {
        return new ErrorDto(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

}
