package dev.sistema.SistemaRh.dto.response;

import dev.sistema.SistemaRh.model.enums.HierarchicalLevel;
import dev.sistema.SistemaRh.model.enums.StatusFuncionario;

public record FuncionarioResponse(
    String nomeFuncionario,
    String cpf,
    String email,
    String telefonePrimario,
    String telefoneSegundario,
    String cargo,
    HierarchicalLevel nivelHierarquico,
    StatusFuncionario statusFuncionario
) {}
