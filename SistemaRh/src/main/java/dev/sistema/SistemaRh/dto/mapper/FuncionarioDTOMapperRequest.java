package dev.sistema.SistemaRh.dto.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import dev.sistema.SistemaRh.dto.request.FuncionarioRequest;
import dev.sistema.SistemaRh.model.FuncionarioModel;

@Component
public class FuncionarioDTOMapperRequest implements Function<FuncionarioRequest, FuncionarioModel>{

    @Override
    public FuncionarioModel apply(FuncionarioRequest funcionarioRequest){
        return new FuncionarioModel(
            null,
            funcionarioRequest.nomeFuncionario(),
            funcionarioRequest.cpf(),
            funcionarioRequest.email(),
            funcionarioRequest.telefonePrimario(),
            funcionarioRequest.telefoneSegundario(),
            funcionarioRequest.cargo(),
            funcionarioRequest.nivelHierarquico(),
            funcionarioRequest.statusFuncionario()
        );
    }

}
