package dev.sistema.SistemaRh.controller;

import dev.sistema.SistemaRh.dto.request.FuncionarioRequest;
import dev.sistema.SistemaRh.dto.response.FuncionarioResponse;
import dev.sistema.SistemaRh.service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller REST para o módulo de Funcionários.
 * Endpoints disponíveis em /api/funcionarios
 */
@RestController
@RequestMapping("/api/funcionarios")
@RequiredArgsConstructor
@Tag(name = "Funcionários", description = "Operações CRUD para gestão de funcionários")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @GetMapping
    @Operation(summary = "Listar funcionários", description = "Retorna todos os funcionários com suporte a paginação e ordenação")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public ResponseEntity<Page<FuncionarioResponse>> getAll(
            @PageableDefault(size = 10, sort = "nomeFuncionario") Pageable pageable) {
        return ResponseEntity.ok(funcionarioService.getAll(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar funcionário por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Funcionário encontrado"),
        @ApiResponse(responseCode = "404", description = "Funcionário não encontrado")
    })
    public ResponseEntity<FuncionarioResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(funcionarioService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastrar funcionário", description = "Cria um novo funcionário no sistema")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Funcionário criado com sucesso"),
        @ApiResponse(responseCode = "422", description = "Dados inválidos no corpo da requisição")
    })
    public ResponseEntity<FuncionarioResponse> save(@RequestBody @Valid FuncionarioRequest request) {
        FuncionarioResponse response = funcionarioService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar funcionário", description = "Atualiza os dados de um funcionário existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Funcionário atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Funcionário não encontrado"),
        @ApiResponse(responseCode = "422", description = "Dados inválidos")
    })
    public ResponseEntity<FuncionarioResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid FuncionarioRequest request) {
        return ResponseEntity.ok(funcionarioService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover funcionário")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Funcionário removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Funcionário não encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        funcionarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
