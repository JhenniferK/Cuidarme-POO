package br.edu.ifpb.es.cuidarme.controller;

import br.edu.ifpb.es.cuidarme.model.Agendamento;
import br.edu.ifpb.es.cuidarme.model.Paciente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AgendamentoController {

    private static List<Agendamento> agendamentos = new ArrayList<>();

    public static void agendar(Paciente paciente, LocalDate data, String horario, String local, Agendamento.Status status) {
        agendamentos.add(new Agendamento(paciente, data, horario, local, status));
    }

    public static List<Agendamento> listarTodos() {
        return agendamentos;
    }

    public static List<Agendamento> listarPorCpf(String cpf) {
        return agendamentos.stream()
                .filter(a -> a.getPaciente().getCpf().equals(cpf))
                .collect(Collectors.toList());
    }

    public static boolean editarAgendamento(String cpfPaciente, LocalDate novaData, String novoHorario, String novoLocal) {
        Agendamento agendamentoEncontrado = agendamentos.stream()
                .filter(a -> a.getPaciente().getCpf().equals(cpfPaciente))
                .findFirst()
                .orElse(null);

        if (agendamentoEncontrado != null) {
            if (novaData != null) agendamentoEncontrado.setData(novaData);
            if (novoHorario != null) agendamentoEncontrado.setHorario(novoHorario);
            if (novoLocal != null) agendamentoEncontrado.setLocal(novoLocal);
            return true;
        }
        return false;
    }

    public static boolean cancelarAgendamento(String cpfPaciente) {
        Agendamento agendamentoEncontrado = agendamentos.stream()
                .filter(a -> a.getPaciente().getCpf().equals(cpfPaciente))
                .findFirst()
                .orElse(null);

        if (agendamentoEncontrado != null) {
            agendamentos.remove(agendamentoEncontrado);
            return true;
        }
        return false;
    }
}

