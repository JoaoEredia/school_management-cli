package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.ConnectionFactory;
import model.Aluno;

public class AlunoDAO {

    // Método para salvar um novo aluno (INSERT)
    public void salvar(Aluno aluno) {
        String sql = "INSERT INTO alunos (nome, email, cpf, data_nascimento) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getCpf());
            stmt.setDate(4, Date.valueOf(aluno.getDataNascimento()));

            stmt.executeUpdate();
            System.out.println("Aluno cadastrado com sucesso no banco!");

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar aluno: " + e.getMessage());
        }
    }

    // Método para listar todos os alunos (SELECT)
    public List<Aluno> listarTodos() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT id, nome, email, cpf, data_nascimento FROM alunos ORDER BY nome ASC";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Aluno a = new Aluno(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("cpf"),
                    rs.getDate("data_nascimento") != null ? rs.getDate("data_nascimento").toLocalDate() : null
                );
                alunos.add(a);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar alunos: " + e.getMessage());
        }

        return alunos;
    }
}