package dev.sistema.SistemaRh.dto.request;

import dev.sistema.SistemaRh.model.enums.HierarchicalLevel;
import dev.sistema.SistemaRh.model.enums.StatusFuncionario;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

/**
 * DTO de entrada para criação e atualização de funcionários.
 */
@Schema(description = "Dados para cadastro ou atualização de um funcionário")
public record FuncionarioRequest(

    @NotBlank(message = "O nome do funcionário é obrigatório")
    @Schema(description = "Nome completo do funcionário", example = "João da Silva")
    String nomeFuncionario,

    @NotBlank(message = "O CPF é obrigatório")
    @CPF(message = "CPF inválido")
    @Schema(description = "CPF do funcionário (formato: 000.000.000-00)", example = "123.456.789-09")
    String cpf,

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    @Schema(description = "E-mail corporativo do funcionário", example = "joao.silva@empresa.com")
    String email,

    @NotBlank(message = "O telefone primário é obrigatório")
    @Pattern(regexp = "\\(\\d{2}\\) 9\\d{4}-\\d{4}", message = "O telefone deve seguir o formato (XX) 9XXXX-XXXX")
    @Schema(description = "Telefone primário", example = "(11) 99999-9999")
    String telefonePrimario,

    @Pattern(regexp = "(\\(\\d{2}\\) 9\\d{4}-\\d{4})?", message = "O telefone secundário deve seguir o formato (XX) 9XXXX-XXXX")
    @Schema(description = "Telefone secundário (opcional)", example = "(11) 98888-8888")
    String telefoneSegundario,

    @NotBlank(message = "O cargo é obrigatório")
    @Schema(description = "Cargo do funcionário", example = "Desenvolvedor Backend")
    String cargo,

    @NotNull(message = "O nível hierárquico é obrigatório")
    @Schema(description = "Nível hierárquico na empresa", example = "PLENO")
    HierarchicalLevel nivelHierarquico,

    @NotNull(message = "O status do funcionário é obrigatório")
    @Schema(description = "Status atual do funcionário", example = "ATIVO")
    StatusFuncionario statusFuncionario

) {}
