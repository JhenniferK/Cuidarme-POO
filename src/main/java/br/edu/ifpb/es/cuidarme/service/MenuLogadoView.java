package br.edu.ifpb.es.cuidarme.service;

import br.edu.ifpb.es.cuidarme.controller.AgendamentoController;
import br.edu.ifpb.es.cuidarme.controller.PacienteController;
import br.edu.ifpb.es.cuidarme.controller.PagamentoController;
import br.edu.ifpb.es.cuidarme.controller.ProntuarioController;
import br.edu.ifpb.es.cuidarme.model.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

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
        System.out.println("4. Logar novo paciente");
        System.out.println("5. Deslogar paciente por cpf");
        System.out.println("6. Voltar");
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
                String cpfEdit = lerApenasNumeros("Digite o CPF do paciente: ");

                if (PacienteController.buscarPorCpf(cpfEdit) == null){
                    System.out.println("ERRO: Paciente com o CPF informado não foi encontrado");
                    break;
                }
                String novoNome = lerTextoPuro("Novo nome (ou ENTER para manter): ");

                LocalDate novaDataNasc = lerDataOpcional("Nova data de nascimento (AAAA-MM-DD): ");

                List<String> novoSexo = Arrays.asList("Feminino", "Masculino", "Outro");
                String sexo = lerOpcaoDeLista("Novo sexo (ou ENTER para manter): ", novoSexo);

                List <String> novoEstadoCivil = Arrays.asList("Casado(a)", "Solteiro(a)", "Outro");
                String estadoCivil = lerOpcaoDeLista("Novo estado civil (ou ENTER para manter): ", novoEstadoCivil);

                List<String> novoGrauEscolaridade = Arrays.asList("Analfabeto / Sem Instrução Formal", "Ensino Fundamental Incompleto",
                        "Ensino Fundamental Completo",
                        "Ensino Médio Incompleto",
                        "Ensino Médio Completo",
                        "Ensino Superior Incompleto (Graduação)",
                        "Ensino Superior Completo (Graduação)",
                        "Pós-Graduação (Especialização / MBA)",
                        "Mestrado",
                        "Doutorado",
                        "Pós-Doutorado");
                String grauEscolaridade = lerOpcaoDeLista("Novo grau de escolaridade (ou ENTER para manter): ", novoGrauEscolaridade);

                String novaProfissao = lerTextoPuro("Nova profissão (ou ENTER para manter): ");

                String novoTelPessoal = lerApenasNumeros("Novo telefone pessoal (ou ENTER para manter): ");

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
                        novoSexo.isEmpty() ? null : sexo,
                        novoEstadoCivil.isEmpty() ? null : estadoCivil,
                        novoGrauEscolaridade.isEmpty() ? null : grauEscolaridade,
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
                String cpfLogin = lerApenasNumeros("Digite o CPF do paciente para iniciar a sessão: ");
                boolean sucessoLogin = PacienteController.loginPaciente(cpfLogin);

                if (sucessoLogin) {
                    System.out.println("Sessão iniciada com sucesso para o paciente: " + PacienteController.getPacienteLogado().getNome());
                } else {
                    System.out.println("ERRO: Paciente com o CPF informado não foi encontrado.");
                }
                break;
            case "5":
                Paciente pacienteSessao = PacienteController.getPacienteLogado();
                if (pacienteSessao == null) {
                    System.out.println("Nenhuma sessão de paciente está ativa para ser encerrada.");
                } else {
                    PacienteController.deslogarPaciente();
                }
                break;

            case "6":
                return;

            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void cadastrarNovoPaciente() {
        System.out.println("\n--- Cadastro de Paciente ---");
        String cpf;
        while(true){
            cpf = lerApenasNumeros("CPF (digite apenas números): ");
            if(PacienteController.buscarPorCpf(cpf) == null){
                break;
            } else {
                System.out.println("ERRO: Já existe um paciente cadastrado com este CPF. Tente novamente");
            }
        }

        String nome = lerTextoPuro("Nome: ");
        String rg = lerApenasNumeros("RG: (apenas números): ");

        LocalDate nascimento = lerDataObrigatoria("Data de nascimento (AAAA-MM-DD): ");
        if(nascimento == null) {
            System.out.println("Cadastro cancelado devido a data e nascimento inválida");
            return;
        }
        List<String> sexos = Arrays.asList("Feminino", "Masculino", "Outro");
        String sexo = lerOpcaoDeLista("Sexo: ", sexos);

        List <String> estadosCivis = Arrays.asList("Casado(a)", "Solteiro(a)", "Outro");
        String estadoCivil = lerOpcaoDeLista("Estado civil: ", estadosCivis);

        List<String> grauDeEscolaridade = Arrays.asList("Analfabeto / Sem Instrução Formal", "Ensino Fundamental Incompleto",
                "Ensino Fundamental Completo",
                "Ensino Médio Incompleto",
                "Ensino Médio Completo",
                "Ensino Superior Incompleto (Graduação)",
                "Ensino Superior Completo (Graduação)",
                "Pós-Graduação (Especialização / MBA)",
                "Mestrado",
                "Doutorado",
                "Pós-Doutorado");
        String grauEscolaridade =lerOpcaoDeLista("Grau de escolaridade: ", grauDeEscolaridade);

        String profissao = lerTextoPuro("Profissão: ");
        String tel = lerApenasNumeros("Telefone pessoal (apenas números): ");

        System.out.println("\n== Endereço Pessoal ==");
        Endereco endPessoal = criarEndereco();

        System.out.println("\n== Endereço de Trabalho ==");
        Endereco endTrabalho = criarEndereco();

        System.out.println("\n== Contato de Emergência ==");
        String nomeContato = lerTextoPuro("Nome do contato: ");
        String telContato = lerApenasNumeros("Telefone do contato: ");
        String parentesco = lerTextoPuro("Parentesco: ");

        ContatoEmergencia contato = new ContatoEmergencia(nomeContato, telContato, parentesco);

        String info = lerTextoPuro("Informações adicionais sobre o paciente: ");

        Paciente paciente = new Paciente(
                0, nome, cpf, rg, nascimento, sexo, estadoCivil,
                grauEscolaridade, profissao, tel, endPessoal, endTrabalho, info, contato
        );

        PacienteController.adicionarPaciente(paciente);
        ProntuarioController.criarProntuarioPara(paciente);

        System.out.println("\nPaciente " + paciente.getNome() + " cadastrado com sucesso.");
    }

    private static Endereco criarEndereco() {
        String rua = lerTextoPuro("Rua: ");
        String numero = lerApenasNumeros("Numero: ");
        String cidade = lerTextoPuro("Cidade: ");
        String estado = lerTextoPuro("Estado: ");
        String cep = lerApenasNumeros("CEP: ");

        return new Endereco(rua, numero, cidade, estado, cep);
    }

    private static ContatoEmergencia criarContatoEmergencia() {
        String nomeContato = lerTextoPuro("Nome: ");
        String telContato = lerApenasNumeros("Telefone do contato: ");
        String parentesco = lerTextoPuro("Parentesco: ");

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
                String cpfEdit = lerApenasNumeros("Digite o CPF do paciente que deseja editar agendamento: ");
                List<Agendamento> agendamentosPaciente = AgendamentoController.listarPorCpf(cpfEdit);

                if (agendamentosPaciente.isEmpty()) {
                    System.out.println("Nenhum agendamento encontrado para este CPF.");
                } else {
                    agendamentosPaciente.forEach(System.out::println);
                    LocalDate novaData = lerDataObrigatoria("Nova data (AAAA-MM-DD): ");
                    if(novaData == null){
                        break;
                    }
                    String novoHorario = lerHorarioAgendamento("Digite o horário desejado: ");
                    System.out.print("Novo local: ");
                    String novoLocal = lerTextoPuro("Novo local: ");

                    boolean editado = AgendamentoController.editarAgendamento(cpfEdit, novaData, novoHorario, novoLocal);
                    if (editado) {
                        System.out.println("Agendamento atualizado com sucesso.");
                    } else {
                        System.out.println("Erro ao atualizar o agendamento.");
                    }
                }
                break;

            case "3":
                String cpfCancel = lerApenasNumeros("Digite o CPF do paciente: ");
                boolean cancelado = AgendamentoController.cancelarAgendamento(cpfCancel);
                if (cancelado) {
                    System.out.println("Agendamento cancelado com sucesso.");
                } else {
                    System.out.println("Nenhum agendamento encontrado para este CPF.");
                }
                break;

            case "4":
                String cpf = lerApenasNumeros("Digite o CPF do paciente que deseja editar: ");
                Paciente paciente = PacienteController.buscarPorCpf(cpf);
                if (paciente == null) {
                    System.out.println("Paciente não encontrado.");
                    break;
                }
                LocalDate data = lerDataObrigatoria("Data da consulta (AAAA-MM-DD): ");
                if(data == null){
                    break;
                }
                String horario = lerHorarioAgendamento("Horário (HH:MM) - Manhã 08h às 11h, Tarde 13h às 17h: ");

                List<String> locaisValidos = Arrays.asList("Alagoa Grande", "Campina Grande", "Nova Cruz");
                String local = lerOpcaoDeLista("Local: ", locaisValidos);

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
        System.out.println("2. Editar Prontuário");
        System.out.println("3. Voltar");
        System.out.print("Escolha uma opção: ");
        String opcao = scanner.nextLine();

        switch (opcao) {
            case "1":
                String cpf = lerApenasNumeros("\nDigite o CPF do paciente para ver o prontuário: ");

                Prontuario prontuario = ProntuarioController.buscarPorCpf(cpf);
                if (prontuario != null) {
                    System.out.println(prontuario);
                    System.out.print("Deseja adicionar nova anotação? (s/n): ");
                    if (scanner.nextLine().equalsIgnoreCase("s")) {
                        String anotacao = lerTextoPuro("Digite a anotação: ");
                        ProntuarioController.adicionarAnotacao(cpf, anotacao);
                        System.out.println("Anotação adicionada com sucesso.");
                    }
                } else {
                    System.out.println("Prontuário não encontrado.");
                }
                break;

            case "2":
                String cpfEditar = lerApenasNumeros("\nDigite o CPF do paciente para editar uma anotação: ");
                Prontuario prontuarioEditar = ProntuarioController.buscarPorCpf(cpfEditar);

                if (prontuarioEditar != null) {
                    System.out.println("Anotações atuais: ");
                    List<String> anotacoes = prontuarioEditar.getAnotacoes();
                    for (String a : anotacoes) {
                        System.out.println("- " + a);
                    }

                    String anotacaoAntiga = lerTextoPuro("Digite a anotação que deseja substituir: ");

                    String novaAnotacao = lerTextoPuro("Digite a nova anotação: ");

                    boolean sucesso = ProntuarioController.editarAnotacao(cpfEditar, anotacaoAntiga, novaAnotacao);
                    if (sucesso) {
                        System.out.println("Anotação editada com sucesso!");
                    } else {
                        System.out.println("Não foi possível editar. Anotação antiga não encontrada.");
                    }
                } else {
                    System.out.println("Prontuário não encontrado.");
                }
                break;

            case "3":
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
                    String cpf = lerApenasNumeros("Digite o CPF do paciente: ");
                    List<Pagamento> lista = PagamentoController.listarPorCpf(cpf);

                    if (lista.isEmpty()) {
                        System.out.println("Nenhum pagamento encontrado para este CPF.");
                    } else {
                        lista.forEach(System.out::println);
                    }

                } else if (escolha2.equals("2")) {
                    LocalDate dataBusca = lerDataObrigatoria("Digite a data (AAAA-MM-DD): ");
                    if(dataBusca == null){
                        break;
                    }
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
                String cpfNovo = lerApenasNumeros("Digite novo CPF do paciente: ");
                Paciente paciente = PacienteController.buscarPorCpf(cpfNovo);

                if (paciente == null) {
                    System.out.println("Paciente não encontrado.");
                    break;
                }
                LocalDate dataPagamento = lerDataObrigatoria("Digite a data do pagamento (AAAA-MM-DD): ");
                if(dataPagamento == null){
                    break;
                }

                int valor = lerInteiro("Digite o valor do pagamento: ");

                Pagamento.Metodo metodo = lerEnum("Método: ", Pagamento.Metodo.class);

                Pagamento.Status status = lerEnum("Status: ", Pagamento.Status.class);

                PagamentoController.registrar(paciente, valor, dataPagamento, metodo, status);
                System.out.println("Pagamento registrado com sucesso.");
                break;

            case "3":
                return;

            default:
                System.out.println("Opção inválida.");
        }
    }

    private static String lerTextoPuro(String prompt) {
        Pattern pattern = Pattern.compile("^[\\p{L}\\s.,'-]+$");
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (pattern.matcher(input).matches()) {
                return input;
            } else {
                System.out.println("ERRO: Entrada inválida. Use apenas letras e espaços.");
            }
        }
    }

    private static String lerApenasNumeros(String prompt){
        Pattern pattern = Pattern.compile("^[0-9]+$");
        while (true) {
            System.out.println(prompt);
            String numero = scanner.nextLine();
            if(pattern.matcher(numero).matches()) {
                return numero;
            } else {
                System.out.println("ERRO: Entrada inválida, use apenas números, sem pontos ou traços");
            }
        }
    }

    private static String lerHorarioAgendamento(String prompt){
        Pattern pattern = Pattern.compile("^([01][0-9]):([0-5][0-9])$");
        while(true){
            System.out.println(prompt);
            String horario = scanner.nextLine();
            if(pattern.matcher(horario).matches()){
                try {
                    LocalTime tempo = LocalTime.parse(horario);
                    int hora = tempo.getHour();
                    if((hora >= 8 && hora <= 11) || (hora >= 13 && hora <= 17)) {
                        return horario;
                    } else {
                        System.out.println("ERRO: Horário fora do expediente (Manhã 08h às 11h, Tarde 13h às 17h)");
                    }
                } catch (DateTimeException e){
                    System.out.println("ERRO: Formato de inválido, tente novamente");
                }
            } else {
            System.out.println("ERRO: Formato de hora inválido. use HH:MM (exemplo: 09:30)");
            }
        }
    }


    private static LocalDate lerDataOpcional(String prompt){
        System.out.println(prompt);
        String input = scanner.nextLine();
        if(input.isEmpty()){
            return null;
        }
        try{
            return LocalDate.parse(input);
        } catch (java.time.format.DateTimeParseException e){
            System.out.println("ERRO: Formato de data inválido. Use AAAA-MM-DD");
            return null;
        }
    }

    private static LocalDate lerDataObrigatoria(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                System.out.println("ERRO: Este campo não pode ser vazio.");
                continue;
            }
            try {
                return LocalDate.parse(input);
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("ERRO: Formato de data inválido. Use AAAA-MM-DD.");
            }
        }
    }
    private static int lerInteiro(String prompt){
        while(true){
            try {
                System.out.println(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e){
                System.out.println("ERRO: Entrada inválida!! Por favor, digite um número inteiro");
            }
        }
    }

    private static String lerOpcaoDeLista(String prompt, List<String> opcoesValidas) {
        while (true) {
            System.out.println("Opções disponíveis: " + String.join(", ", opcoesValidas));
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toUpperCase();

            for (String opcao : opcoesValidas) {
                if (input.equalsIgnoreCase(opcao)) {
                    return opcao;
                }
            }
            System.out.println("ERRO: Opção inválida. Por favor, escolha uma das opções listadas.");
        }
    }

    private static <T extends  Enum<T>> T lerEnum(String prompt, Class<T> enumClass){
        while(true){
            System.out.println("Opções disponíveis: ");
            for(T constant : enumClass.getEnumConstants()){
                System.out.println(constant.name() + " ");
            }
            System.out.println();
            System.out.print(prompt);
            String input = scanner.nextLine().toUpperCase();
            try {
                return Enum.valueOf(enumClass, input);
            } catch (IllegalArgumentException e) {
                System.out.println("ERRO: Opção inválida!! Por favor, escolha uma das opções listadas");
            }
        }
    }
}
