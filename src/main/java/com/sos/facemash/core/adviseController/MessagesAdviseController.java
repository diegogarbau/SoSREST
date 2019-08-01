package com.sos.facemash.core.adviseController;

import com.sos.facemash.DTO.ErrorDTO;
import com.sos.facemash.core.exceptions.messagesExceptions.MsgNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MessagesAdviseController {

    @ExceptionHandler(MsgNotFoundException.class)
    public ResponseEntity<ErrorDTO> mesaggeNotFoundError(MsgNotFoundException exception) {
        ErrorDTO ErrorDTO = new ErrorDTO(exception.getMessage());
        return new ResponseEntity<>(ErrorDTO, HttpStatus.BAD_REQUEST);
    }

}
