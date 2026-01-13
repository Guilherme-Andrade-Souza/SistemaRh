package dev.sistema.SistemaRh.dto.request;

import org.hibernate.validator.constraints.br.CPF;

import dev.sistema.SistemaRh.model.enums.HierarchicalLevel;
import dev.sistema.SistemaRh.model.enums.StatusFuncionario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FuncionarioRequest(
    @NotBlank
    String nomeFuncionario,
    @NotBlank
    @CPF
    String cpf,
    @NotBlank
    @Email
    String email,
    @NotBlank
    String telefonePrimario,
    String telefoneSegundario,
    @NotBlank
    String cargo,
    @NotNull
    HierarchicalLevel nivelHierarquico,
    @NotNull
    StatusFuncionario statusFuncionario
) {}
