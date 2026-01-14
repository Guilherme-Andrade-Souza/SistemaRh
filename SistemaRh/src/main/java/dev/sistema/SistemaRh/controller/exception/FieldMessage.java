package dev.sistema.SistemaRh.controller.exception;

public record FieldMessage(
    String fieldName,
    String message
) {}
