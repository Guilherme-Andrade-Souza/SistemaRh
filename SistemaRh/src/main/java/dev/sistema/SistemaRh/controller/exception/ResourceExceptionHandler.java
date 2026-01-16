package dev.sistema.SistemaRh.controller.exception;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import tools.jackson.databind.exc.InvalidFormatException;


@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_CONTENT;

        ValidationError err = new ValidationError(Instant.now(), status.value(), "Erro de validação", "Dados inválidos no formulário.",  request.getRequestURI());

        for (FieldError f : e.getBindingResult().getFieldErrors()){
            err.addError(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> httpMessageNotReadable(HttpMessageNotReadableException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String errorMessage = "Erro na leitura JSON";

        if (e.getCause() instanceof InvalidFormatException){
            InvalidFormatException cause = (InvalidFormatException) e.getCause();

            if (cause.getTargetType().isEnum()){
                String acceptValues = Arrays.toString(cause.getTargetType().getEnumConstants());
                errorMessage = String.format("O valor '%s' é inválido. Valores aceitos: %s",
                    cause.getValue(), acceptValues);
            }

        }

        StandardError err = new StandardError(
            Instant.now(),
            status.value(),
            "Campo com formato inválido",
            errorMessage,
            request.getRequestURI()
        );
    
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;

        StandardError err = new StandardError(
            Instant.now(),
            status.value(),
            "Recurso não encontrado",
            e.getMessage(),
            request.getRequestURI()
        );

        return ResponseEntity.status(status).body(err);

    }

}


