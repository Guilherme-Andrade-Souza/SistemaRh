package dev.sistema.SistemaRh.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.sistema.SistemaRh.dto.mapper.FuncionarioDTOMapper;
import dev.sistema.SistemaRh.dto.response.FuncionarioResponse;
import dev.sistema.SistemaRh.model.FuncionarioModel;
import dev.sistema.SistemaRh.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioDTOMapper funcionarioDTOMapper;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, FuncionarioDTOMapper funcionarioDTOMapper) {
        this.funcionarioRepository = funcionarioRepository;
        this.funcionarioDTOMapper = funcionarioDTOMapper;
    }

    //listar
    public List<FuncionarioResponse> getAll() {
        return funcionarioRepository.findAll()
            .stream()
            .map(funcionarioDTOMapper::apply)
            .toList();
    }
    //criar
    public FuncionarioModel save(FuncionarioModel funcionarioModel){
        return funcionarioRepository.save(funcionarioModel);
    }
    //deletar
    public void delete(Long id){
        funcionarioRepository.deleteById(id);
    }
    //editar
    //to-do

}
