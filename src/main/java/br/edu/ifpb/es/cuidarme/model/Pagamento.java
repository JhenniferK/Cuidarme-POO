package br.edu.ifpb.es.cuidarme.model;

import java.time.LocalDate;

public class Pagamento {
    public enum Metodo { PIX, CARTAO_DEBITO, CARTAO_CREDITO, BOLETO, DINHEIRO }
    public enum Status { PAGO, PENDENTE }

    private final long id;
    private Paciente paciente;
    private Integer valor;
    private LocalDate data;
    private Metodo metodo;
    private Status status;

    public Pagamento(Long id, Paciente paciente, Integer valor, LocalDate data, Metodo metodo, Status status) {
        this.id = id;
        this.paciente = paciente;
        this.valor = valor;
        this.data = data;
        this.metodo = metodo;
        this.status = status;
    }

    public Integer getValor() {
        return valor;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public Metodo getMetodo() {
        return metodo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Pagamento de R$" + valor + " em " + data + " via " + metodo + " - Status: " + status;
    }
}