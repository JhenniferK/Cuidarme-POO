package br.edu.ifpb.es.cuidarme.model;

import java.time.LocalDate;

public class Pagamento {
    public enum Metodo { PIX, CARTAO_DEBITO, CARTAO_CREDITO, BOLETO, DINHEIRO }
    public enum Status { PAGO, PENDENTE }

    private static final double VALOR_FIXO = 100.00;

    private Paciente paciente;
    private LocalDate data;
    private Metodo metodo;
    private Status status;

    public Pagamento(Paciente paciente, LocalDate data, Metodo metodo, Status status) {
        this.paciente = paciente;
        this.data = data;
        this.metodo = metodo;
        this.status = status;
    }

    public double getValor() {
        return VALOR_FIXO;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
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
        return "Pagamento de R$" + VALOR_FIXO + " em " + data + " via " + metodo + " - Status: " + status;
    }
}