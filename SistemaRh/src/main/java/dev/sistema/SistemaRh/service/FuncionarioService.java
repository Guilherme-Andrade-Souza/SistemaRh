package dev.sistema.SistemaRh.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.sistema.SistemaRh.controller.exception.ResourceNotFoundException;
import dev.sistema.SistemaRh.dto.mapper.FuncionarioDTOMapperRequest;
import dev.sistema.SistemaRh.dto.mapper.FuncionarioDTOMapperResponse;
import dev.sistema.SistemaRh.dto.request.FuncionarioRequest;
import dev.sistema.SistemaRh.dto.response.FuncionarioResponse;
import dev.sistema.SistemaRh.model.FuncionarioModel;
import dev.sistema.SistemaRh.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
    
    
    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioDTOMapperResponse funcionarioDTOMapperResponse;
    private final FuncionarioDTOMapperRequest funcionarioDTOMapperRequest;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, FuncionarioDTOMapperResponse funcionarioDTOMapperResponse, FuncionarioDTOMapperRequest funcionarioDTOMapperRequest) {
        this.funcionarioRepository = funcionarioRepository;
        this.funcionarioDTOMapperResponse = funcionarioDTOMapperResponse;
        this.funcionarioDTOMapperRequest = funcionarioDTOMapperRequest;
    }

    //listar
    public List<FuncionarioResponse> getAll() {
        return funcionarioRepository.findAll()
            .stream()
            .map(funcionarioDTOMapperResponse::apply)
            .toList();
    }
    //criar
    public FuncionarioResponse save(FuncionarioRequest funcionarioRequest){
        // Converte Request -> Model
        FuncionarioModel funcionarioParaSalvar = funcionarioDTOMapperRequest.apply(funcionarioRequest);
        
        // Salva no banco (retorna um Model com ID)
        FuncionarioModel funcionarioSalvo = funcionarioRepository.save(funcionarioParaSalvar);
        
        //Converte Model -> Response usando o mapper de saída
        return funcionarioDTOMapperResponse.apply(funcionarioSalvo);
    }
    //deletar
    public void delete(Long id){
        if (!funcionarioRepository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        funcionarioRepository.deleteById(id);
    }
    //editar
    //to-do

}
