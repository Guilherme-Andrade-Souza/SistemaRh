package dev.sistema.SistemaRh.repository;

import dev.sistema.SistemaRh.model.FuncionarioModel;
import dev.sistema.SistemaRh.model.enums.StatusFuncionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositório JPA para operações de banco de dados com FuncionarioModel.
 */
@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, Long> {

    /**
     * Verifica se já existe um funcionário com o CPF informado.
     */
    boolean existsByCpf(String cpf);

    /**
     * Verifica se já existe um funcionário com o e-mail informado.
     */
    boolean existsByEmail(String email);

    /**
     * Busca funcionários pelo status atual.
     */
    Page<FuncionarioModel> findByStatusFuncionario(StatusFuncionario status, Pageable pageable);

    /**
     * Busca funcionários pelo nome (case-insensitive, pesquisa parcial).
     */
    Page<FuncionarioModel> findByNomeFuncionarioContainingIgnoreCase(String nome, Pageable pageable);

    /**
     * Busca funcionários por nome ou cargo (pesquisa geral).
     */
    @Query("SELECT f FROM FuncionarioModel f WHERE " +
           "LOWER(f.nomeFuncionario) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
           "LOWER(f.cargo) LIKE LOWER(CONCAT('%', :termo, '%'))")
    Page<FuncionarioModel> buscarPorNomeOuCargo(@Param("termo") String termo, Pageable pageable);

    /**
     * Conta funcionários por status.
     */
    long countByStatusFuncionario(StatusFuncionario status);
}
