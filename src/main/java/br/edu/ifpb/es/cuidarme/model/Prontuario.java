package br.edu.ifpb.es.cuidarme.model;

import java.util.ArrayList;
import java.util.List;

public class Prontuario {
    private Paciente paciente;
    private List<String> anotacoes;

    public Prontuario(Paciente paciente) {
        this.paciente = paciente;
        this.anotacoes = new ArrayList<>();
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void adicionarAnotacao(String anotacao) {
        anotacoes.add(anotacao);
    }

    public List<String> getAnotacoes() {
        return anotacoes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Prontuário de " + paciente.getNome() + ":\n");
        for (int i = 0; i < anotacoes.size(); i++) {
            sb.append("Anotação ").append(i + 1).append(": ").append(anotacoes.get(i)).append("\n");
        }
        return sb.toString();
    }
}