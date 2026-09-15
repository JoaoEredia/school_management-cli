package model;

import java.time.LocalDate;

public class Professor {
    private int id;
    private String nome;
    private String email;
    private String especialidade;
    private LocalDate dataAdmissao;

    // Construtor vazio
    public Professor() {
    }

    // Construtor para cadastro (sem id, gerado pelo banco)
    public Professor(String nome, String email, String especialidade, LocalDate dataAdmissao) {
        this.nome = nome;
        this.email = email;
        this.especialidade = especialidade;
        this.dataAdmissao = dataAdmissao;
    }

    // Construtor completo para listagem/recuperação
    public Professor(int id, String nome, String email, String especialidade, LocalDate dataAdmissao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.especialidade = especialidade;
        this.dataAdmissao = dataAdmissao;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }
}