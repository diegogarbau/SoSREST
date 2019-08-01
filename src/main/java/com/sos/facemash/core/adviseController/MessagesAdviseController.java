package com.sos.facemash.core.adviseController;

import com.sos.facemash.DTO.ErrorDTO;
import com.sos.facemash.core.exceptions.usersExceptions.AlreadyFriendsException;
import com.sos.facemash.core.exceptions.usersExceptions.DuplicatedUserException;
import com.sos.facemash.core.exceptions.usersExceptions.NotFriendsException;
import com.sos.facemash.core.exceptions.usersExceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UsersAdviseController {

    @ExceptionHandler(AlreadyFriendsException.class)
    public ResponseEntity<ErrorDTO> alreadyFriendsError(AlreadyFriendsException exception) {
        ErrorDTO ErrorDTO = new ErrorDTO(exception.getMessage());
        return new ResponseEntity<>(ErrorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicatedUserException.class)
    public ResponseEntity<ErrorDTO> duplicatedUserError(DuplicatedUserException exception) {
        ErrorDTO ErrorDTO = new ErrorDTO(exception.getMessage());
        return new ResponseEntity<>(ErrorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFriendsException.class)
    public ResponseEntity<ErrorDTO> notfriendsError(NotFriendsException exception) {
        ErrorDTO ErrorDTO = new ErrorDTO(exception.getMessage());
        return new ResponseEntity<>(ErrorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDTO> userNotFoundError(UserNotFoundException exception) {
        ErrorDTO ErrorDTO = new ErrorDTO(exception.getMessage());
        return new ResponseEntity<>(ErrorDTO, HttpStatus.BAD_REQUEST);
    }

}
