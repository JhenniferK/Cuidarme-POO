package br.edu.ifpb.es.cuidarme.service;

import br.edu.ifpb.es.cuidarme.controller.PsicologoController;
import br.edu.ifpb.es.cuidarme.model.Psicologo;

import java.util.Scanner;

public class MenuView {

    private static final Scanner scanner = new Scanner(System.in);

    public static void exibirMenuInicial() {
        while (true) {
            System.out.println("\n== Olá, psicólogo(a)! Bem-vindo ao Cuidar.me! ==");
            System.out.println("1. Cadastro");
            System.out.println("2. Login");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    cadastrarPsicologo();
                    break;
                case "2":
                    loginPsicologo();
                    break;
                case "0":
                    System.out.println("Encerrando o sistema...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void cadastrarPsicologo() {
        System.out.println("\n--- Cadastro de Psicólogo ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Psicologo novo = PsicologoController.cadastrar(nome, email, senha);
        System.out.println("Cadastro realizado com sucesso! ID do(a) psicólogo(a) " + novo.getNome() + ": " + novo.getId());
        MenuLogadoView.exibirMenuLogado(novo);
    }

    private static void loginPsicologo() {
        System.out.println("\n--- Login de Psicólogo ---");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Psicologo p = PsicologoController.login(email, senha);

        if (p != null) {
            System.out.println("Login bem-sucedido! Bem-vindo(a), " + p.getNome() + "!");
            MenuLogadoView.exibirMenuLogado(p);
        } else {
            System.out.println("Email ou senha incorretos.");
        }
    }
}
