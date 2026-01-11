package dev.sistema.SistemaRh.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dev.sistema.SistemaRh.dto.response.FuncionarioResponse;
import dev.sistema.SistemaRh.model.FuncionarioModel;
import dev.sistema.SistemaRh.service.FuncionarioService;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    public List<FuncionarioResponse> getAll(){
        return funcionarioService.getAll();
    }
    
    @PostMapping    
    public FuncionarioModel save(@RequestBody FuncionarioModel funcionarioModel){
        return funcionarioService.save(funcionarioModel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        funcionarioService.delete(id);
    }

}
