package model;

import java.time.LocalDate;

public class Aluno {
    private int id;
    private String nome;
    private String email;
    private String cpf;
    private LocalDate dataNascimento;

    // Construtor vazio
    public Aluno() {}

    // Construtor para novos cadastros (sem ID, pois o banco gera com AUTO_INCREMENT)
    public Aluno(String nome, String email, String cpf, LocalDate dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    // Construtor completo (para quando buscarmos do banco com ID)
    public Aluno(int id, String nome, String email, String cpf, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    @Override
    public String toString() {
        return String.format("[%d] %s | Email: %s | CPF: %s | Nasc: %s", 
                id, nome, email, cpf, dataNascimento);
    }
}