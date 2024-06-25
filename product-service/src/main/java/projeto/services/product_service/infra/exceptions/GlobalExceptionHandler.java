package projeto.services.product_service.infra.exceptions;

import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<?> validationExceptionHandler(ValidationException exc) {
        var response = new DefaultExceptionResponse(HttpStatusCode.valueOf(400),exc.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exc) {
        var erros = exc.getFieldErrors().stream().map(DefaultArgumentNotValidExceptionResponse::new).toList();
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity<?> productNotFoundException(ProductNotFoundException exc) {
        var response = new DefaultExceptionResponse(HttpStatusCode.valueOf(404),exc.getMessage());
        return ResponseEntity.status(404).body(response);
    }
}

record DefaultExceptionResponse(HttpStatusCode status, String error){}

record DefaultArgumentNotValidExceptionResponse(String field, String message) {
    DefaultArgumentNotValidExceptionResponse(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }
}