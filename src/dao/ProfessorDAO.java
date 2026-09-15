package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.ConnectionFactory;
import model.Professor;

public class ProfessorDAO {

    public void cadastrar(Professor professor) {
        String sql = "INSERT INTO professores (nome, email, especialidade, data_admissao) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setString(3, professor.getEspecialidade());
            stmt.setDate(4, Date.valueOf(professor.getDataAdmissao()));

            stmt.executeUpdate();
            System.out.println("Professor cadastrado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar professor: " + e.getMessage());
        }
    }

    public List<Professor> listar() {
        List<Professor> professores = new ArrayList<>();
        String sql = "SELECT * FROM professores";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Professor professor = new Professor(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("especialidade"),
                    rs.getDate("data_admissao").toLocalDate()
                );
                professores.add(professor);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar professores: " + e.getMessage());
        }

        return professores;
    }
}