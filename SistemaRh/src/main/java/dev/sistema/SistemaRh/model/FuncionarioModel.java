package dev.sistema.SistemaRh.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FuncionarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeFuncionario;
    private String cpf;
    private String email;
    private String telefonePrimario;
    private String telefoneSegundario;
    private String cargo;
    private String nivelHierarquico;
    private boolean ativo;


    public FuncionarioModel() {
    }


    public FuncionarioModel(Long id, String nomeFuncionario, String cpf, String email, String telefonePrimario, String telefoneSegundario, String cargo, String nivelHierarquico, boolean ativo) {
        this.id = id;
        this.nomeFuncionario = nomeFuncionario;
        this.cpf = cpf;
        this.email = email;
        this.telefonePrimario = telefonePrimario;
        this.telefoneSegundario = telefoneSegundario;
        this.cargo = cargo;
        this.nivelHierarquico = nivelHierarquico;
        this.ativo = ativo;
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

    public String getNivelHierarquico() {
        return this.nivelHierarquico;
    }

    public void setNivelHierarquico(String nivelHierarquico) {
        this.nivelHierarquico = nivelHierarquico;
    }

    public boolean isAtivo() {
        return this.ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }


}
