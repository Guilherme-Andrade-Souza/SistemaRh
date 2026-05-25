package dev.sistema.SistemaRh.service;

import dev.sistema.SistemaRh.controller.exception.ResourceNotFoundException;
import dev.sistema.SistemaRh.dto.mapper.FuncionarioMapper;
import dev.sistema.SistemaRh.dto.request.FuncionarioRequest;
import dev.sistema.SistemaRh.dto.response.FuncionarioResponse;
import dev.sistema.SistemaRh.model.FuncionarioModel;
import dev.sistema.SistemaRh.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Serviço responsável pela lógica de negócio relacionada a Funcionários.
 */
@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioMapper funcionarioMapper;

    /**
     * Retorna todos os funcionários paginados.
     *
     * @param pageable configuração de paginação e ordenação
     * @return página de FuncionarioResponse
     */
    @Transactional(readOnly = true)
    public Page<FuncionarioResponse> getAll(Pageable pageable) {
        return funcionarioRepository.findAll(pageable)
                .map(funcionarioMapper::toResponse);
    }

    /**
     * Busca um funcionário por ID.
     *
     * @param id identificador do funcionário
     * @return FuncionarioResponse com os dados
     * @throws ResourceNotFoundException se não encontrado
     */
    @Transactional(readOnly = true)
    public FuncionarioResponse findById(Long id) {
        FuncionarioModel funcionario = findOrThrow(id);
        return funcionarioMapper.toResponse(funcionario);
    }

    /**
     * Cadastra um novo funcionário.
     *
     * @param request dados do novo funcionário
     * @return FuncionarioResponse com os dados salvos
     */
    @Transactional
    public FuncionarioResponse save(FuncionarioRequest request) {
        FuncionarioModel model = funcionarioMapper.toModel(request);
        FuncionarioModel saved = funcionarioRepository.save(model);
        return funcionarioMapper.toResponse(saved);
    }

    /**
     * Atualiza todos os dados de um funcionário existente (substituição completa).
     *
     * @param id      identificador do funcionário
     * @param request novos dados
     * @return FuncionarioResponse atualizado
     * @throws ResourceNotFoundException se não encontrado
     */
    @Transactional
    public FuncionarioResponse update(Long id, FuncionarioRequest request) {
        FuncionarioModel existente = findOrThrow(id);
        funcionarioMapper.updateModelFromRequest(request, existente);
        FuncionarioModel atualizado = funcionarioRepository.save(existente);
        return funcionarioMapper.toResponse(atualizado);
    }

    /**
     * Remove um funcionário pelo ID.
     *
     * @param id identificador do funcionário
     * @throws ResourceNotFoundException se não encontrado
     */
    @Transactional
    public void delete(Long id) {
        findOrThrow(id); // garante que existe antes de deletar
        funcionarioRepository.deleteById(id);
    }

    // ─── Helpers ─────────────────────────────────────────────────────────────

    private FuncionarioModel findOrThrow(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
