package br.edu.ifpb.es.cuidarme.model;

public class ContatoEmergencia {
    private String nome;
    private String telefone;
    private String parentesco;

    public ContatoEmergencia(String nome, String telefone, String parentesco) {
        this.nome = nome;
        this.telefone = telefone;
        this.parentesco = parentesco;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getParentesco() {
        return parentesco;
    }

    @Override
    public String toString() {
        return nome + " (" + parentesco + ") - " + telefone;
    }
}