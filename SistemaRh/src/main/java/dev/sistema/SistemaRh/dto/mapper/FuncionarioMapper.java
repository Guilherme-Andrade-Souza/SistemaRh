package dev.sistema.SistemaRh.dto.mapper;

import dev.sistema.SistemaRh.dto.request.FuncionarioRequest;
import dev.sistema.SistemaRh.dto.response.FuncionarioResponse;
import dev.sistema.SistemaRh.model.FuncionarioModel;
import org.mapstruct.*;

/**
 * Mapper gerado automaticamente pelo MapStruct em tempo de compilação.
 * Elimina código manual de conversão entre Request, Model e Response.
 */
@Mapper(componentModel = "spring")
public interface FuncionarioMapper {

    /**
     * Converte um FuncionarioRequest em FuncionarioModel.
     * O campo {@code id} é ignorado pois é gerado pelo banco.
     */
    @Mapping(target = "id", ignore = true)
    FuncionarioModel toModel(FuncionarioRequest request);

    /**
     * Converte um FuncionarioModel em FuncionarioResponse.
     */
    FuncionarioResponse toResponse(FuncionarioModel model);

    /**
     * Atualiza um FuncionarioModel existente com dados de um FuncionarioRequest.
     * Usado na operação de edição (PUT).
     * Ignora o ID para não sobrescrever o identificador do recurso.
     */
    @Mapping(target = "id", ignore = true)
    void updateModelFromRequest(FuncionarioRequest request, @MappingTarget FuncionarioModel model);
}
