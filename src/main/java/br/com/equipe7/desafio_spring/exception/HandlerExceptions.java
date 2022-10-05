package br.com.equipe7.desafio_spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class HandlerExceptions {
    /**
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDetails> handlerNotFoundException(NotFoundException ex) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("Produto não encontrado")
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }

    /**
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(PurchaseWithInvalidQuantityException.class)
    public ResponseEntity<ExceptionDetails> handlerPurchaseWithZeroException(PurchaseWithInvalidQuantityException ex) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("A quantidade do produto na compra não pode ser zero")
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

    /**
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(EmptyPurchaseRequestException.class)
    public ResponseEntity<ExceptionDetails> handlerEmptyPurchaseRequestException(EmptyPurchaseRequestException ex) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("O payload de compra não deve estar vazio")
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }
}
