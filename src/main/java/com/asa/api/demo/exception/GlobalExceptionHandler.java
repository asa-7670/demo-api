package com.asa.api.demo.exception;

import com.asa.api.demo.validator.ValidationMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> globalException(Exception exception, WebRequest request){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ErrorMessage.builder()
                        .timestamp(new Date())
                        .message(exception.getMessage())
                        .path(request.getDescription(Boolean.FALSE))
                        .build());
    }

    @ExceptionHandler(ResourceException.class)
    public ResponseEntity<ErrorMessage> resourceException(ResourceException exception, WebRequest request) {
        return ResponseEntity.badRequest().body(
                ErrorMessage.builder()
                        .timestamp(new Date())
                        .message(exception.getMessage())
                        .path(request.getDescription(Boolean.FALSE))
                        .build());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(ValidationMessage.builder()
                .error(Boolean.TRUE)
                .errors(errors)
                .build());
    }

        @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleArgumentError(IllegalArgumentException exception,  WebRequest request) {
        return ResponseEntity.badRequest().body(ErrorMessage.builder()
                .timestamp(new Date())
                .message(exception.getMessage())
                .path(request.getDescription(Boolean.FALSE))
                .build());
    }

//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity handleValidtionErrors(ConstraintViolationException exception,  WebRequest request) {
//        return ResponseEntity.badRequest().body(ErrorMessage.builder()
//                .timestamp(new Date())
//                .message(exception.getMessage().split(":")[1].trim())
//                .path(request.getDescription(Boolean.FALSE))
//                .build());
//    }

}
