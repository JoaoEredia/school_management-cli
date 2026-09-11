import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import dao.AlunoDAO;
import model.Aluno;

public class Main {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AlunoDAO alunoDAO = new AlunoDAO();
        int opcao = -1;

        System.out.println("=== SISTEMA DE GESTÃO DE ALUNOS ===");

        while (opcao != 0) {
            System.out.println("\n-----------------------------");
            System.out.println("1 - Cadastrar Aluno");
            System.out.println("2 - Listar Alunos");
            System.out.println("3 - Atualizar Aluno");
            System.out.println("4 - Excluir Aluno");
            System.out.println("0 - Sair");
            System.out.println("-----------------------------");
            System.out.print("Escolha uma opção: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Por favor, digite um número válido.");
                scanner.nextLine();
                continue;
            }

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarAluno(scanner, alunoDAO);
                    break;
                case 2:
                    listarAlunos(alunoDAO);
                    break;
                case 3:
                    atualizarAluno(scanner, alunoDAO);
                    break;
                case 4:
                    excluirAluno(scanner, alunoDAO);
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }

        scanner.close();
    }

    private static void cadastrarAluno(Scanner scanner, AlunoDAO alunoDAO) {
        System.out.println("\n--- Novo Cadastro ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        LocalDate dataNascimento = null;
        while (dataNascimento == null) {
            System.out.print("Data de Nascimento (dd/MM/yyyy): ");
            String dataStr = scanner.nextLine().trim();

            if (dataStr.matches("\\d{8}")) {
                dataStr = dataStr.substring(0, 2) + "/" + dataStr.substring(2, 4) + "/" + dataStr.substring(4);
            }

            try {
                dataNascimento = LocalDate.parse(dataStr, FORMATTER);
            } catch (Exception e) {
                System.out.println("Formato de data inválido! Digite como dd/MM/yyyy ou 8 dígitos seguidos.");
            }
        }

        Aluno aluno = new Aluno(nome, email, cpf, dataNascimento);

        try {
            alunoDAO.salvar(aluno);
            System.out.println("Aluno cadastrado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar: " + e.getMessage());
        }
    }

    private static void listarAlunos(AlunoDAO alunoDAO) {
        System.out.println("\n--- Lista de Alunos ---");
        try {
            List<Aluno> lista = alunoDAO.listarTodos();

            if (lista.isEmpty()) {
                System.out.println("Nenhum aluno encontrado.");
            } else {
                for (Aluno a : lista) {
                    System.out.println(a);
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar alunos: " + e.getMessage());
        }
    }

    private static void atualizarAluno(Scanner scanner, AlunoDAO alunoDAO) {
        System.out.println("\n--- Atualização de Aluno ---");
        System.out.print("Informe o ID do aluno que deseja atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Novo Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Novo Email: ");
        String email = scanner.nextLine();

        System.out.print("Novo CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Nova Data de Nascimento (dd/MM/yyyy): ");
        String dataStr = scanner.nextLine();
        LocalDate dataNascimento = LocalDate.parse(dataStr, FORMATTER);

        Aluno alunoAtualizado = new Aluno(id, nome, email, cpf, dataNascimento);

        try {
            alunoDAO.atualizar(alunoAtualizado);
            System.out.println("Aluno atualizado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar: " + e.getMessage());
        }
    }

    private static void excluirAluno(Scanner scanner, AlunoDAO alunoDAO) {
        System.out.println("\n--- Exclusão de Aluno ---");
        System.out.print("Informe o ID do aluno que deseja remover: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Tem certeza que deseja excluir o ID " + id + "? (s/n): ");
        String confirmacao = scanner.nextLine().trim().toLowerCase();

        if (confirmacao.equalsIgnoreCase("s")) {
            try {
                alunoDAO.deletar(id);
                System.out.println("Aluno removido com sucesso!");
            } catch (Exception e) {
                System.err.println("Erro ao excluir: " + e.getMessage());
            }
        } else {
            System.out.println("Operação cancelada.");
        }
    }
}