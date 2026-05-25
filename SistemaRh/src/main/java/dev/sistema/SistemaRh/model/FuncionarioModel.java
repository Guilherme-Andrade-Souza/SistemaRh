package dev.sistema.SistemaRh.model;

import dev.sistema.SistemaRh.model.enums.HierarchicalLevel;
import dev.sistema.SistemaRh.model.enums.StatusFuncionario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

/**
 * Entidade que representa um Funcionário no sistema de RH.
 */
@Entity
@Table(name = "funcionarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FuncionarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do funcionário é obrigatório")
    @Column(nullable = false)
    private String nomeFuncionario;

    @NotBlank(message = "O CPF é obrigatório")
    @CPF(message = "CPF inválido")
    @Column(nullable = false, unique = true)
    private String cpf;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "O telefone primário é obrigatório")
    @Pattern(regexp = "\\(\\d{2}\\) 9\\d{4}-\\d{4}", message = "O telefone deve seguir o formato (XX) 9XXXX-XXXX")
    private String telefonePrimario;

    @Pattern(regexp = "(\\(\\d{2}\\) 9\\d{4}-\\d{4})?", message = "O telefone secundário deve seguir o formato (XX) 9XXXX-XXXX")
    private String telefoneSegundario;

    @NotBlank(message = "O cargo é obrigatório")
    private String cargo;

    @NotNull(message = "O nível hierárquico é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HierarchicalLevel nivelHierarquico;

    @NotNull(message = "O status do funcionário é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusFuncionario statusFuncionario;
}
