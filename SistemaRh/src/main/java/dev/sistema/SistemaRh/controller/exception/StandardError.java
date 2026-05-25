package dev.sistema.SistemaRh.controller.exception;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

/**
 * Estrutura padrão de erro retornada pela API.
 */
@Getter
@Setter
public class StandardError {

    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public StandardError(Instant timestamp, Integer status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
