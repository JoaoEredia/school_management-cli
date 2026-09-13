package model;

public class Curso {
    private int id;
    private String nome;
    private String sigla;
    private int cargaHoraria;
    private String descricao;

    // Construtor vazio
    public Curso() {
    }

    // Construtor para cadastro (sem id, gerado pelo banco)
    public Curso(String nome, String sigla, int cargaHoraria, String descricao) {
        this.nome = nome;
        this.sigla = sigla;
        this.cargaHoraria = cargaHoraria;
        this.descricao = descricao;
    }

    // Construtor completo (para quando vier do banco com id)
    public Curso(int id, String nome, String sigla, int cargaHoraria, String descricao) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
        this.cargaHoraria = cargaHoraria;
        this.descricao = descricao;
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "ID: " + id + 
               " | Nome: " + nome + 
               " (" + sigla + ")" +
               " | Carga Horária: " + cargaHoraria + "h" +
               " | Descrição: " + (descricao != null ? descricao : "Sem descrição");
    }
}