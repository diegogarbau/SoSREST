package com.sopra.onBoarding.controllers.imp;

import com.sopra.onBoarding.DTO.ErrorDTO;
import com.sopra.onBoarding.exceptions.notFoundExceptions.NotFoundException;
import com.sopra.onBoarding.exceptions.wrongInputExceptions.BadRequestMotherException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MovAdviseController {

    @ExceptionHandler(BadRequestMotherException.class)
    public ResponseEntity<ErrorDTO> duplicatedError(BadRequestMotherException exception) {
        ErrorDTO ErrorDTO = new ErrorDTO(exception.getMessage());
        ResponseEntity<ErrorDTO> re = new ResponseEntity<>(ErrorDTO, HttpStatus.BAD_REQUEST);
        return re;
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDTO> emptyListError(NotFoundException exception) {
        if (exception instanceof NotFoundException) {
            ErrorDTO ErrorDTO = new ErrorDTO(exception.getMessage());
            ResponseEntity<ErrorDTO> re = new ResponseEntity<>(ErrorDTO, HttpStatus.NOT_FOUND);
            return re;
        }
        return null;
    }
}
