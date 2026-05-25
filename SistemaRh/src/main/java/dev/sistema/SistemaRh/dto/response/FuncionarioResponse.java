package dev.sistema.SistemaRh.dto.response;

import dev.sistema.SistemaRh.model.enums.HierarchicalLevel;
import dev.sistema.SistemaRh.model.enums.StatusFuncionario;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO de saída com os dados públicos de um funcionário.
 * O campo {@code id} é exposto para que o frontend possa identificar o recurso.
 */
@Schema(description = "Dados de retorno de um funcionário")
public record FuncionarioResponse(

    @Schema(description = "Identificador único do funcionário")
    Long id,

    @Schema(description = "Nome completo do funcionário")
    String nomeFuncionario,

    @Schema(description = "CPF do funcionário")
    String cpf,

    @Schema(description = "E-mail corporativo")
    String email,

    @Schema(description = "Telefone primário")
    String telefonePrimario,

    @Schema(description = "Telefone secundário (pode ser nulo)")
    String telefoneSegundario,

    @Schema(description = "Cargo do funcionário")
    String cargo,

    @Schema(description = "Nível hierárquico")
    HierarchicalLevel nivelHierarquico,

    @Schema(description = "Status atual")
    StatusFuncionario statusFuncionario

) {}
