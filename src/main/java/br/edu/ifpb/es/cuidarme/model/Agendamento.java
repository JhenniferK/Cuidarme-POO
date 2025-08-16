package br.edu.ifpb.es.cuidarme.model;

import java.time.LocalDate;

public class Agendamento {

    public enum Status { AGENDADO, CANCELAR, REMARCAR }

    private final long id;
    private Paciente paciente;
    private LocalDate data;
    private String horario;
    private String local;
    private Status status;

    public Agendamento(Long id, Paciente paciente, LocalDate data, String horario, String local, Status status) {
        this.id = id;
        this.paciente = paciente;
        this.data = data;
        this.horario = horario;
        this.local = local;
        this.status = status;
    }

    public Long getId(){
        return id;
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

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Agendamento { " +
                "paciente = " + paciente +
                ", data = " + data +
                ", horario = '" + horario + '\'' +
                ", local = '" + local + '\'' +
                ", status = " + status +
                '}';
    }
}