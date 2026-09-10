import java.time.LocalDate;
import java.util.List;

import dao.AlunoDAO;
import model.Aluno;

public class Main {
    public static void main(String[] args) {
        AlunoDAO alunoDAO = new AlunoDAO();

        // 1. Cadastrando um novo aluno no MySQL
        System.out.println("--- Testando Cadastro de Aluno ---");
        Aluno novoAluno = new Aluno(
            "Carlos Eduardo",
            "carlos.edu@email.com",
            "333.444.555-66",
            LocalDate.of(2002, 7, 15)
        );
        alunoDAO.salvar(novoAluno);

        // 2. Listando os alunos salvos no banco
        System.out.println("\n--- Lista de Alunos no Banco ---");
        List<Aluno> lista = alunoDAO.listarTodos();
        for (Aluno a : lista) {
            System.out.println(a);
        }
    }
}