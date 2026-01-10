package dev.sistema.SistemaRh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.sistema.SistemaRh.model.FuncionarioModel;

public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, Long>{

}
