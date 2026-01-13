package dev.sistema.SistemaRh.dto.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import dev.sistema.SistemaRh.dto.response.FuncionarioResponse;
import dev.sistema.SistemaRh.model.FuncionarioModel;

@Component
public class FuncionarioDTOMapperResponse implements Function<FuncionarioModel, FuncionarioResponse>{

    @Override
    public FuncionarioResponse apply(FuncionarioModel funcionarioModel){
        return new FuncionarioResponse(
            funcionarioModel.getNomeFuncionario(),
            funcionarioModel.getCpf(),
            funcionarioModel.getEmail(),
            funcionarioModel.getTelefonePrimario(),
            funcionarioModel.getTelefoneSegundario(),
            funcionarioModel.getCargo(),
            funcionarioModel.getNivelHierarquico(),
            funcionarioModel.getStatusFuncionario()
        );
    }

}
