package dao;

import database.ConnectionFactory; // ajuste se sua classe de conexao tiver outro nome
import model.Curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    public void cadastrar(Curso curso) {
        String sql = "INSERT INTO cursos (nome, sigla, carga_horaria, descricao) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getSigla());
            stmt.setInt(3, curso.getCargaHoraria());
            stmt.setString(4, curso.getDescricao());

            stmt.executeUpdate();
            System.out.println("Curso cadastrado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar curso: " + e.getMessage());
        }
    }

    public List<Curso> listar() {
        String sql = "SELECT * FROM cursos";
        List<Curso> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Curso curso = new Curso(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("sigla"),
                    rs.getInt("carga_horaria"),
                    rs.getString("descricao")
                );
                lista.add(curso);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar cursos: " + e.getMessage());
        }

        return lista;
    }
}