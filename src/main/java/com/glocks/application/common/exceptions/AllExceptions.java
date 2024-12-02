package com.glocks.application.common.exceptions;

import com.glocks.application.common.model.ResponseModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class AllExceptions {
    private static final Logger logger = LogManager.getLogger(AllExceptions.class);

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Object> exception(ResourceNotFoundException exception) {
        return new ResponseEntity<>(new ResponseModel(HttpStatus.NOT_FOUND.value(), exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ResourceServicesException.class)
    public ResponseEntity<Object> exception(ResourceServicesException exception) {
        return new ResponseEntity<>(new ResponseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = FileNotFoundException.class)
    public ResponseEntity<Object> exception(FileNotFoundException exception) {
        return new ResponseEntity<>(new ResponseModel(HttpStatus.NOT_FOUND.value(), exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = FileStorageException.class)
    public ResponseEntity<Object> exception(FileStorageException exception) {
        return new ResponseEntity<>(new ResponseModel(HttpStatus.NOT_FOUND.value(), exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BlankFileException.class)
    public ResponseEntity<Object> blankFileException(BlankFileException exception) {
        ResponseModel response = new ResponseModel(HttpStatus.NOT_ACCEPTABLE.value(), exception.getMessage());
        logger.info("Blank file uploaded and response is [" + response + "]");
        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }


    @ExceptionHandler(value = InvalidFileFormatException.class)
    public ResponseEntity<Object> invalidFileException(InvalidFileFormatException exception) {
        ResponseModel response = new ResponseModel(HttpStatus.NOT_ACCEPTABLE.value(), exception.getMessage());
        logger.info("Invalid file format and response is [" + response + "]");
        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> invalidParameter(MethodArgumentNotValidException methodArgumentNotValidException) {
        Map<String, String> error = new LinkedHashMap<>();
        methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(x -> {
            error.put(x.getField(), x.getDefaultMessage());
        });
        return error;
    }
}

