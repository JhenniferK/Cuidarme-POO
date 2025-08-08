package br.edu.ifpb.es.cuidarme.service;

import br.edu.ifpb.es.cuidarme.controller.AgendamentoController;
import br.edu.ifpb.es.cuidarme.controller.PacienteController;
import br.edu.ifpb.es.cuidarme.controller.PagamentoController;
import br.edu.ifpb.es.cuidarme.controller.ProntuarioController;
import br.edu.ifpb.es.cuidarme.model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuLogadoView {

    private static final Scanner scanner = new Scanner(System.in);

    public static void exibirMenuLogado(Psicologo psicologoLogado) {
        while (true) {
            System.out.println("\n== MENU DO PSICÓLOGO: " + psicologoLogado.getNome() + " ==");
            System.out.println("1. Pacientes");
            System.out.println("2. Agendamentos");
            System.out.println("3. Prontuários");
            System.out.println("4. Pagamentos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    menuPacientes();
                    break;
                case "2":
                    menuAgendamento();
                    break;
                case "3":
                    menuProntuarios();
                    break;
                case "4":
                    menuPagamentos();
                    break;
                case "0":
                    System.out.println("Saindo do menu do psicólogo...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void menuPacientes() {
        System.out.println("\n--- PACIENTES ---");
        System.out.println("1. Listar todos");
        System.out.println("2. Editar paciente por CPF");
        System.out.println("3. Cadastrar novo paciente");
        System.out.println("4. Voltar");
        System.out.print("Escolha uma opção: ");
        String opcao = scanner.nextLine();

        switch (opcao) {
            case "1":
                List<Paciente> pacientes = PacienteController.listarTodos();
                if (pacientes.isEmpty()) {
                    System.out.println("Nenhum paciente cadastrado.");
                } else {
                    pacientes.forEach(System.out::println);
                }
                break;

            case "2":
                System.out.print("Digite o CPF do paciente: ");
                String cpfEdit = scanner.nextLine();

                System.out.print("Novo nome (ou ENTER para manter): ");
                String novoNome = scanner.nextLine();

                System.out.print("Nova data de nascimento (AAAA-MM-DD) (ou ENTER para manter): ");
                String novaDataNascStr = scanner.nextLine();
                LocalDate novaDataNasc = null;
                if (!novaDataNascStr.isEmpty()) {
                    try {
                        novaDataNasc = LocalDate.parse(novaDataNascStr);
                    } catch (Exception e) {
                        System.out.println("Data inválida. Manterá a data antiga.");
                    }
                }

                System.out.print("Novo sexo (ou ENTER para manter): ");
                String novoSexo = scanner.nextLine();

                System.out.print("Novo estado civil (ou ENTER para manter): ");
                String novoEstadoCivil = scanner.nextLine();

                System.out.print("Novo grau de escolaridade (ou ENTER para manter): ");
                String novoGrauEscolaridade = scanner.nextLine();

                System.out.print("Nova profissão (ou ENTER para manter): ");
                String novaProfissao = scanner.nextLine();

                System.out.print("Novo telefone pessoal (ou ENTER para manter): ");
                String novoTelPessoal = scanner.nextLine();

                Endereco novoEndPessoal = null;
                System.out.print("Deseja alterar endereço pessoal? (s/n): ");
                if (scanner.nextLine().equalsIgnoreCase("s")) {
                    System.out.println("Digite o novo endereço pessoal:");
                    novoEndPessoal = criarEndereco();
                }

                Endereco novoEndTrabalho = null;
                System.out.print("Deseja alterar endereço de trabalho? (s/n): ");
                if (scanner.nextLine().equalsIgnoreCase("s")) {
                    System.out.println("Digite o novo endereço de trabalho:");
                    novoEndTrabalho = criarEndereco();
                }

                String novaInfo = null;
                System.out.print("Novas informações (ou ENTER para manter): ");
                String novaInfoInput = scanner.nextLine();
                if (!novaInfoInput.isEmpty()) {
                    novaInfo = novaInfoInput;
                }

                ContatoEmergencia novoConttEmerg = null;
                System.out.print("Deseja alterar contato de emergência? (s/n): ");
                if (scanner.nextLine().equalsIgnoreCase("s")) {
                    System.out.println("Digite os dados do contato de emergência:");
                    novoConttEmerg = criarContatoEmergencia();
                }

                boolean editado = PacienteController.editarPaciente(
                        novoNome.isEmpty() ? null : novoNome,
                        cpfEdit,
                        novaDataNasc,
                        novoSexo.isEmpty() ? null : novoSexo,
                        novoEstadoCivil.isEmpty() ? null : novoEstadoCivil,
                        novoGrauEscolaridade.isEmpty() ? null : novoGrauEscolaridade,
                        novaProfissao.isEmpty() ? null : novaProfissao,
                        novoTelPessoal.isEmpty() ? null : novoTelPessoal,
                        novoEndPessoal,
                        novoEndTrabalho,
                        novaInfo,
                        novoConttEmerg
                );

                if (editado) {
                    System.out.println("Paciente atualizado com sucesso.");
                } else {
                    System.out.println("Paciente não encontrado.");
                }
                break;

            case "3":
                cadastrarNovoPaciente();
                break;

            case "4":
                return;

            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void cadastrarNovoPaciente() {
        System.out.println("\n--- Cadastro de Paciente ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("RG: ");
        String rg = scanner.nextLine();
        System.out.print("Data de nascimento (AAAA-MM-DD): ");
        LocalDate nascimento = LocalDate.parse(scanner.nextLine());
        System.out.print("Sexo: ");
        String sexo = scanner.nextLine();
        System.out.print("Estado civil: ");
        String estadoCivil = scanner.nextLine();
        System.out.print("Grau de escolaridade: ");
        String grau = scanner.nextLine();
        System.out.print("Profissão: ");
        String profissao = scanner.nextLine();
        System.out.print("Telefone pessoal: ");
        String tel = scanner.nextLine();

        System.out.println("\n== Endereço Pessoal ==");
        Endereco endPessoal = criarEndereco();

        System.out.println("\n== Endereço de Trabalho ==");
        Endereco endTrabalho = criarEndereco();

        System.out.println("\n== Contato de Emergência ==");
        System.out.print("Nome: ");
        String nomeContato = scanner.nextLine();
        System.out.print("Telefone: ");
        String telContato = scanner.nextLine();
        System.out.print("Parentesco: ");
        String parentesco = scanner.nextLine();

        ContatoEmergencia contato = new ContatoEmergencia(nomeContato, telContato, parentesco);

        System.out.print("\nInformações adicionais sobre o paciente: ");
        String info = scanner.nextLine();

        Paciente paciente = new Paciente(
                0, nome, cpf, rg, nascimento, sexo, estadoCivil,
                grau, profissao, tel, endPessoal, endTrabalho, info, contato
        );

        PacienteController.adicionarPaciente(paciente);
        ProntuarioController.criarProntuarioPara(paciente);

        System.out.println("\nPaciente " + paciente.getNome() + " cadastrado com sucesso.");
    }

    private static Endereco criarEndereco() {
        System.out.print("Rua: ");
        String rua = scanner.nextLine();
        System.out.print("Número: ");
        String numero = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
        System.out.print("CEP: ");
        String cep = scanner.nextLine();

        return new Endereco(rua, numero, cidade, estado, cep);
    }

    private static ContatoEmergencia criarContatoEmergencia() {
        System.out.print("Nome do contato: ");
        String nomeContato = scanner.nextLine();
        System.out.print("Telefone do contato: ");
        String telContato = scanner.nextLine();
        System.out.print("Parentesco: ");
        String parentesco = scanner.nextLine();

        return new ContatoEmergencia(nomeContato, telContato, parentesco);
    }

    private static void menuAgendamento() {
        System.out.println("\n--- AGENDAMENTO ---");
        System.out.println("1. Listar todos os agendamentos");
        System.out.println("2. Remarcar agendamento");
        System.out.println("3. Cancelar agendamento");
        System.out.println("4. Realizar agendamento");
        System.out.println("5. Voltar");
        System.out.println("Escolha uma opção:");
        String opcao = scanner.nextLine();

        switch (opcao) {
            case "1":
                List<Agendamento> agendamentos = AgendamentoController.listarTodos();
                if (agendamentos.isEmpty()) {
                    System.out.println("Nenhum agendamento cadastrado.");
                } else {
                    agendamentos.forEach(System.out::println);
                }
                break;

            case "2":
                System.out.print("Digite o CPF do paciente: ");
                String cpfEdit = scanner.nextLine();
                List<Agendamento> agendamentosPaciente = AgendamentoController.listarPorCpf(cpfEdit);

                if (agendamentosPaciente.isEmpty()) {
                    System.out.println("Nenhum agendamento encontrado para este CPF.");
                } else {
                    agendamentosPaciente.forEach(System.out::println);
                    System.out.print("Nova data (AAAA-MM-DD): ");
                    LocalDate novaData = LocalDate.parse(scanner.nextLine());
                    System.out.print("Novo horário (HH:MM): ");
                    String novoHorario = scanner.nextLine();
                    System.out.print("Novo local: ");
                    String novoLocal = scanner.nextLine();

                    boolean editado = AgendamentoController.editarAgendamento(cpfEdit, novaData, novoHorario, novoLocal);
                    if (editado) {
                        System.out.println("Agendamento atualizado com sucesso.");
                    } else {
                        System.out.println("Erro ao atualizar o agendamento.");
                    }
                }
                break;

            case "3":
                System.out.print("Digite o CPF do paciente: ");
                String cpfCancel = scanner.nextLine();
                boolean cancelado = AgendamentoController.cancelarAgendamento(cpfCancel);
                if (cancelado) {
                    System.out.println("Agendamento cancelado com sucesso.");
                } else {
                    System.out.println("Nenhum agendamento encontrado para este CPF.");
                }
                break;

            case "4":
                System.out.print("Digite o CPF do paciente: ");
                String cpf = scanner.nextLine();
                Paciente paciente = PacienteController.buscarPorCpf(cpf);
                if (paciente == null) {
                    System.out.println("Paciente não encontrado.");
                    break;
                }
                System.out.print("Data da consulta (AAAA-MM-DD): ");
                LocalDate data = LocalDate.parse(scanner.nextLine());
                System.out.print("Horário (HH:MM): ");
                String horario = scanner.nextLine();
                System.out.print("Local (Alagoa Grande, Campina Grande, Nova Cruz): ");
                String local = scanner.nextLine();

                AgendamentoController.agendar(paciente, data, horario, local, Agendamento.Status.AGENDADO);
                System.out.println("Consulta agendada com sucesso.");
                break;

            case "5":
                return;

            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void menuProntuarios() {
        System.out.println("\n--- PRONTUÁRIOS ---");
        System.out.println("1. Buscar prontuário");
        System.out.println("2. Voltar");
        System.out.print("Escolha uma opção: ");
        String opcao = scanner.nextLine();

        switch (opcao) {
            case "1":
                System.out.print("\nDigite o CPF do paciente para ver o prontuário: ");
                String cpf = scanner.nextLine();

                Prontuario prontuario = ProntuarioController.buscarPorCpf(cpf);
                if (prontuario != null) {
                    System.out.println(prontuario);
                    System.out.print("Deseja adicionar nova anotação? (s/n): ");
                    if (scanner.nextLine().equalsIgnoreCase("s")) {
                        System.out.print("Digite a anotação: ");
                        String anotacao = scanner.nextLine();
                        ProntuarioController.adicionarAnotacao(cpf, anotacao);
                        System.out.println("Anotação adicionada com sucesso.");
                    }
                } else {
                    System.out.println("Prontuário não encontrado.");
                }

            case "2":
                return;

            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void menuPagamentos() {
        System.out.println("\n--- PAGAMENTOS ---");
        System.out.println("1. Buscar pagamento");
        System.out.println("2. Registrar novo pagamento");
        System.out.println("3. Voltar");
        System.out.print("Escolha uma opção: ");
        String escolha = scanner.nextLine();

        switch (escolha) {
            case "1":
                System.out.println("Selecione como deseja realizar a busca:");
                System.out.println("1. Buscar por CPF do paciente");
                System.out.println("2. Buscar por data");
                System.out.print("Escolha uma opção: ");
                String escolha2 = scanner.nextLine();

                if (escolha2.equals("1")) {
                    System.out.print("Digite o CPF do paciente: ");
                    String cpf = scanner.nextLine();
                    List<Pagamento> lista = PagamentoController.listarPorCpf(cpf);

                    if (lista.isEmpty()) {
                        System.out.println("Nenhum pagamento encontrado para este CPF.");
                    } else {
                        lista.forEach(System.out::println);
                    }

                } else if (escolha2.equals("2")) {
                    System.out.print("Digite a data (AAAA-MM-DD): ");
                    LocalDate dataBusca = LocalDate.parse(scanner.nextLine());
                    List<Pagamento> lista = PagamentoController.listarPorData(dataBusca);

                    if (lista.isEmpty()) {
                        System.out.println("Nenhum pagamento nessa data.");
                    } else {
                        lista.forEach(System.out::println);
                    }
                } else {
                    System.out.println("Opção inválida.");
                }
                break;

            case "2":
                System.out.print("Digite o CPF do paciente: ");
                String cpfNovo = scanner.nextLine();
                Paciente paciente = PacienteController.buscarPorCpf(cpfNovo);

                if (paciente == null) {
                    System.out.println("Paciente não encontrado.");
                    break;
                }

                System.out.print("Digite a data do pagamento (AAAA-MM-DD): ");
                LocalDate dataPagamento = LocalDate.parse(scanner.nextLine());

                System.out.println("Métodos disponíveis: PIX, CARTAO_DEBITO, CARTAO_CREDITO, BOLETO, DINHEIRO");
                System.out.print("Método: ");
                Pagamento.Metodo metodo = Pagamento.Metodo.valueOf(scanner.nextLine().toUpperCase());

                System.out.print("Status (PAGO ou PENDENTE): ");
                Pagamento.Status status = Pagamento.Status.valueOf(scanner.nextLine().toUpperCase());

                PagamentoController.registrar(paciente, dataPagamento, metodo, status);
                System.out.println("Pagamento registrado com sucesso.");
                break;

            case "3":
                return;

            default:
                System.out.println("Opção inválida.");
        }
    }
}