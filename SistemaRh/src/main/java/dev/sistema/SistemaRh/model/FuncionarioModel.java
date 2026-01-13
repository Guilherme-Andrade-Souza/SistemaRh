package dev.sistema.SistemaRh.model;

import org.hibernate.validator.constraints.br.CPF;

import dev.sistema.SistemaRh.model.enums.HierarchicalLevel;
import dev.sistema.SistemaRh.model.enums.StatusFuncionario;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "funcionarios")
public class FuncionarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nomeFuncionario;
    
    @NotBlank
    @CPF
    private String cpf;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String telefonePrimario;

    private String telefoneSegundario;

    @NotBlank
    private String cargo;
    @NotNull
    @Enumerated(EnumType.STRING)
    private HierarchicalLevel nivelHierarquico;
    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusFuncionario statusFuncionario;

    public FuncionarioModel() {
    }


    public FuncionarioModel(Long id, String nomeFuncionario, String cpf, String email, String telefonePrimario, String telefoneSegundario, String cargo, HierarchicalLevel nivelHierarquico, StatusFuncionario statusFuncionario) {
        this.id = id;
        this.nomeFuncionario = nomeFuncionario;
        this.cpf = cpf;
        this.email = email;
        this.telefonePrimario = telefonePrimario;
        this.telefoneSegundario = telefoneSegundario;
        this.cargo = cargo;
        this.nivelHierarquico = nivelHierarquico;
        this.statusFuncionario = statusFuncionario;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeFuncionario() {
        return this.nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefonePrimario() {
        return this.telefonePrimario;
    }

    public void setTelefonePrimario(String telefonePrimario) {
        this.telefonePrimario = telefonePrimario;
    }

    public String getTelefoneSegundario() {
        return this.telefoneSegundario;
    }

    public void setTelefoneSegundario(String telefoneSegundario) {
        this.telefoneSegundario = telefoneSegundario;
    }

    public String getCargo() {
        return this.cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public HierarchicalLevel getNivelHierarquico() {
        return this.nivelHierarquico;
    }

    public void setNivelHierarquico(HierarchicalLevel nivelHierarquico) {
        this.nivelHierarquico = nivelHierarquico;
    }

    public StatusFuncionario getStatusFuncionario() {
        return this.statusFuncionario;
    }

    public void setStatusFuncionario(StatusFuncionario statusFuncionario) {
        this.statusFuncionario = statusFuncionario;
    }

}
