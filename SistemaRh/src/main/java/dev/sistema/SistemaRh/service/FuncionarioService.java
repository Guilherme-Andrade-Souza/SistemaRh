package dev.sistema.SistemaRh.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.sistema.SistemaRh.model.FuncionarioModel;
import dev.sistema.SistemaRh.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    //listar
    public List<FuncionarioModel> getAll() {
        return funcionarioRepository.findAll();
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
