package dev.sistema.SistemaRh.controller.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Arrays;

/**
 * Handler global de exceções da API.
 * Centraliza o tratamento de erros e garante respostas padronizadas.
 */
@RestControllerAdvice
public class ResourceExceptionHandler {

    /**
     * Trata erros de validação do Bean Validation (@Valid).
     * Retorna HTTP 422 com a lista de campos inválidos.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> handleValidation(
            MethodArgumentNotValidException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError err = new ValidationError(
            Instant.now(), status.value(),
            "Erro de validação", "Dados inválidos no formulário.",
            request.getRequestURI()
        );

        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            err.addError(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(err);
    }

    /**
     * Trata erros de leitura do JSON (enum inválido, tipo errado, etc.).
     * Retorna HTTP 400 com mensagem explicativa.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> handleHttpMessageNotReadable(
            HttpMessageNotReadableException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        String errorMessage = "Erro na leitura do JSON. Verifique o formato dos dados enviados.";

        if (e.getCause() instanceof InvalidFormatException cause) {
            if (cause.getTargetType().isEnum()) {
                String acceptedValues = Arrays.toString(cause.getTargetType().getEnumConstants());
                errorMessage = String.format(
                    "O valor '%s' é inválido para o campo '%s'. Valores aceitos: %s",
                    cause.getValue(),
                    cause.getPath().isEmpty() ? "desconhecido" : cause.getPath().get(0).getFieldName(),
                    acceptedValues
                );
            }
        }

        return ResponseEntity.status(status).body(new StandardError(
            Instant.now(), status.value(),
            "Campo com formato inválido", errorMessage,
            request.getRequestURI()
        ));
    }

    /**
     * Trata ResourceNotFoundException (recurso não encontrado).
     * Retorna HTTP 404.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> handleResourceNotFound(
            ResourceNotFoundException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(new StandardError(
            Instant.now(), status.value(),
            "Recurso não encontrado", e.getMessage(),
            request.getRequestURI()
        ));
    }

    /**
     * Handler genérico para erros não tratados.
     * Retorna HTTP 500 sem expor detalhes internos.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> handleGeneric(
            Exception e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status).body(new StandardError(
            Instant.now(), status.value(),
            "Erro interno do servidor", "Ocorreu um erro inesperado. Tente novamente.",
            request.getRequestURI()
        ));
    }
}
