package project.yara_silva.Jp_capacitacao.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import project.yara_silva.Jp_capacitacao.exceptions.*;

import java.util.Arrays;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    private ResponseEntity<String> userExistsHandler(UserAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(PromotionInvalidException.class)
    private ResponseEntity<String> promotionInvalidHandler(PromotionInvalidException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    private ResponseEntity<String> productNotFoundHandler(ProductNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    private ResponseEntity<String> categoryNotFoundHandler(CategoryNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    private ResponseEntity<String> categoryAlreadyExistsHandler(CategoryAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(CartItemAlreadyExistsException.class)
    private ResponseEntity<String> cartItemAlreadyExistsHandler(CartItemAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(CartItemNotFoundException.class)
    private ResponseEntity<String> cartItemNotFoundHandler(CartItemNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(EmptyOrderException.class)
    private ResponseEntity<String> EmptyOrderHandler(EmptyOrderException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(OrderNotFoundException.class)
    private ResponseEntity<String> OrderNotFoundHandler(OrderNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<String> excecaoGenericaHandler(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado: " + (exception.getClass().getCanonicalName() + exception.getMessage() + "\n" + Arrays.toString(exception.getStackTrace())));
    }
}
