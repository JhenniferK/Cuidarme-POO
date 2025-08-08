package br.edu.ifpb.es.cuidarme.controller;

import br.edu.ifpb.es.cuidarme.model.ContatoEmergencia;
import br.edu.ifpb.es.cuidarme.model.Endereco;
import br.edu.ifpb.es.cuidarme.model.Paciente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PacienteController {

    private static List<Paciente> pacientes = new ArrayList<>();
    private static int idCounter = 1;

    public static void adicionarPaciente(Paciente paciente) {
        paciente = new Paciente(idCounter++, paciente.getNome(), paciente.getCpf(), paciente.getRg(),
                paciente.getDataNascimento(), paciente.getSexo(), paciente.getEstadoCivil(),
                paciente.getGrauEscolaridade(), paciente.getProfissao(), paciente.getTelefonePessoal(),
                paciente.getEnderecoPessoal(), paciente.getEnderecoTrabalho(),
                paciente.getInfoAdicionais(), paciente.getContatoEmergencia());

        pacientes.add(paciente);
    }

    public static List<Paciente> listarTodos() {
        return pacientes;
    }

    public static Paciente buscarPorCpf(String cpf) {
        for (Paciente p : pacientes) {
            if (p.getCpf().equals(cpf)) return p;
        }
        return null;
    }

    public static boolean editarPaciente(String novoNome, String cpf, LocalDate novaDataNascimento, String novoSexo, String novoEstadoCivil, String novoGrauEscolaridade, String novaProfissao, String novoTelefone, Endereco novoEnderecoPessoal, Endereco novoEnderecoTrabalho, String novoInfoAdicionais, ContatoEmergencia novoContatoEmergencia) {
        Paciente p = buscarPorCpf(cpf);
        if (p != null) {
            if (novoNome != null) p.setNome(novoNome);
            if (novaDataNascimento != null) p.setDataNascimento(novaDataNascimento);
            if (novoSexo != null) p.setSexo(novoSexo);
            if (novoEstadoCivil != null) p.setEstadoCivil(novoEstadoCivil);
            if (novoGrauEscolaridade != null) p.setGrauEscolaridade(novoGrauEscolaridade);
            if (novaProfissao != null) p.setProfissao(novaProfissao);
            if (novoTelefone != null) p.setTelefonePessoal(novoTelefone);
            if (novoEnderecoPessoal != null) p.setEnderecoPessoal(novoEnderecoPessoal);
            if (novoEnderecoTrabalho != null) p.setEnderecoTrabalho(novoEnderecoTrabalho);
            if (novoInfoAdicionais != null) p.setInfoAdicionais(novoInfoAdicionais);
            if (novoContatoEmergencia != null) p.setContatoEmergencia(novoContatoEmergencia);
            return true;
        }

        return false;
    }
}
