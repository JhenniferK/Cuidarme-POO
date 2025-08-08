package br.edu.ifpb.es.cuidarme.model;

import java.time.LocalDate;

public class Paciente {
    private int id;
    private String nome;
    private String cpf;
    private String rg;
    private LocalDate dataNascimento;
    private String sexo;
    private String estadoCivil;
    private String grauEscolaridade;
    private String profissao;
    private String telefonePessoal;
    private Endereco enderecoPessoal;
    private Endereco enderecoTrabalho;
    private String infoAdicionais;
    private ContatoEmergencia contatoEmergencia;

    public Paciente(int id, String nome, String cpf, String rg, LocalDate dataNascimento, String sexo, String estadoCivil,
                    String grauEscolaridade, String profissao, String telefonePessoal, Endereco enderecoPessoal,
                    Endereco enderecoTrabalho, String infoAdicionais, ContatoEmergencia contatoEmergencia) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
        this.grauEscolaridade = grauEscolaridade;
        this.profissao = profissao;
        this.telefonePessoal = telefonePessoal;
        this.enderecoPessoal = enderecoPessoal;
        this.enderecoTrabalho = enderecoTrabalho;
        this.infoAdicionais = infoAdicionais;
        this.contatoEmergencia = contatoEmergencia;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public void setGrauEscolaridade(String grauEscolaridade) {
        this.grauEscolaridade = grauEscolaridade;
    }

    public void setEnderecoPessoal(Endereco enderecoPessoal) {
        this.enderecoPessoal = enderecoPessoal;
    }

    public void setEnderecoTrabalho(Endereco enderecoTrabalho) {
        this.enderecoTrabalho = enderecoTrabalho;
    }

    public void setInfoAdicionais(String infoAdicionais) {
        this.infoAdicionais = infoAdicionais;
    }

    public void setContatoEmergencia(ContatoEmergencia contatoEmergencia) {
        this.contatoEmergencia = contatoEmergencia;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public String getGrauEscolaridade() {
        return grauEscolaridade;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getTelefonePessoal() {
        return telefonePessoal;
    }

    public void setTelefonePessoal(String telefonePessoal) {
        this.telefonePessoal = telefonePessoal;
    }

    public Endereco getEnderecoPessoal() {
        return enderecoPessoal;
    }

    public Endereco getEnderecoTrabalho() {
        return enderecoTrabalho;
    }

    public String getInfoAdicionais() {
        return infoAdicionais;
    }

    public ContatoEmergencia getContatoEmergencia() {
        return contatoEmergencia;
    }

    @Override
    public String toString() {
        return "Paciente { " +
                "id = " + id +
                ", nome = '" + nome + '\'' +
                ", cpf = '" + cpf + '\'' +
                ", rg = '" + rg + '\'' +
                ", dataNascimento = " + dataNascimento +
                ", sexo = '" + sexo + '\'' +
                ", estadoCivil = '" + estadoCivil + '\'' +
                ", grauEscolaridade = '" + grauEscolaridade + '\'' +
                ", profissao = '" + profissao + '\'' +
                ", telefonePessoal = '" + telefonePessoal + '\'' +
                ", enderecoPessoal = " + enderecoPessoal +
                ", enderecoTrabalho = " + enderecoTrabalho +
                ", infoAdicionais = '" + infoAdicionais + '\'' +
                ", contatoEmergencia = " + contatoEmergencia +
                '}';
    }
}