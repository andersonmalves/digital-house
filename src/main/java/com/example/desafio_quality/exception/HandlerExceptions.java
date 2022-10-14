package com.example.desafio_quality.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class HandlerExceptions extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDetails> handlerNotFoundException(NotFoundException ex) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("Objeto não encontrado")
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }

    @Override
    protected  ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();

        ExceptionDetails exceptionResponse = ExceptionDetails.builder()
                .title("Parâmetros inválidos")
                .message("Os campos estão inválidos")
                .fields(errors.stream()
                        .map(FieldError::getField)
                        .collect(Collectors.joining(", ")))
                .fieldsMessage(errors.stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.joining(", ")))
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionResponse, status);
    }
}