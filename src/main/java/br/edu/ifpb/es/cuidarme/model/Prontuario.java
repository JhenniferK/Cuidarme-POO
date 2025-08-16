package br.edu.ifpb.es.cuidarme.model;

import java.util.ArrayList;
import java.util.List;

public class Prontuario {
    private Long id;
    private Paciente paciente;
    private List<String> anotacoes;

    public Prontuario(Long id, Paciente paciente) {
        this.id = id;
        this.paciente = paciente;
        this.anotacoes = new ArrayList<>();
    }
    public Long getId(){
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public List<String> getAnotacoes() {
        return anotacoes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Prontuário de " + paciente.getNome() + ":\n");
        for (int i = 0; i < anotacoes.size(); i++) {
            sb.append("Anotação ").append(i + 1).append(": ").append(anotacoes.get(i)).append("\n").append(getId()).append("\n");
        }
        return sb.toString();
    }
}