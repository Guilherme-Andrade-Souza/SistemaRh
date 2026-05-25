package dev.sistema.SistemaRh.controller.exception;

/**
 * Exceção lançada quando um recurso não é encontrado pelo ID fornecido.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Long id) {
        super("Recurso não encontrado com id: " + id);
    }
}
