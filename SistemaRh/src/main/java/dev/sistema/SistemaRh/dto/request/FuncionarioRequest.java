package dev.sistema.SistemaRh.dto.request;

import org.hibernate.validator.constraints.br.CPF;

import dev.sistema.SistemaRh.model.enums.HierarchicalLevel;
import dev.sistema.SistemaRh.model.enums.StatusFuncionario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

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
    @Pattern(regexp = "\\(\\d{2}\\) 9\\d{4}-\\d{4}", message = "O telefone deve seguir o formato (XX) 9XXXX-XXXX")
    String telefonePrimario,
    @Pattern(regexp = "(\\(\\d{2}\\) 9\\d{4}-\\d{4})?", message = "O telefone secundário deve seguir o formato (XX) 9XXXX-XXXX")
    String telefoneSegundario,
    @NotBlank
    String cargo,
    @NotNull
    HierarchicalLevel nivelHierarquico,
    @NotNull
    StatusFuncionario statusFuncionario
) {}
