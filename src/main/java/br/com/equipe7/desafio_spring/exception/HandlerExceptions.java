package br.com.equipe7.desafio_spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class HandlerExceptions {
    /**
     * Tratamento de exceção.
     * @author Giovanna e Gabriel
     * @param ex Objeto exceção.
     * @return Retorna mensagem caso não encontre o produto erro e o status HTTP
     */
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

    /**
     * Tratamento de exceção.
     * @author Giovanna e Gabriel
     * @param ex Objeto exceção.
     * @return Retorna mensagem caso quantidade do produto é invalida e o status HTTP.
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
     * Tratamento de exceções
     * @author Giovanna e Gabriel
     * @param ex Objeto exceção.
     * @return Retorna um json com mensagem e status http
     */
    @ExceptionHandler(EmptyRequestException.class)
    public ResponseEntity<ExceptionDetails> handleProductEmptyException(EmptyRequestException ex) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("O 'payload' da requisição não pode ser vazio")
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

    /**
     * Tratamento de exceções
     * @author Gabriel
     * @param ex Objeto exceção.
     * @return Retorna um json com mensagem e status http
     */
    @ExceptionHandler(InventoryException.class)
    public ResponseEntity<ExceptionDetails> handleInventoryException(InventoryException ex) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("Houve um problema no inventário")
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value()) // Discutir
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

    /**
     * Tratamento de exceções
     * @author Felipe
     * @param ex Objeto exceção.
     * @return Retorna um json com mensagem e status http
     */
    @ExceptionHandler(FieldEmptyException.class)
    public ResponseEntity<ExceptionDetails> handleInventoryException(FieldEmptyException ex) {
        ExceptionDetails FieldEmptyException = ExceptionDetails.builder()
                .title("Houve um problema na requisição")
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value()) // Discutir
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(FieldEmptyException, HttpStatus.BAD_REQUEST);
    }
}
