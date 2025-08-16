package br.edu.ifpb.es.cuidarme.controller;

import br.edu.ifpb.es.cuidarme.model.Paciente;
import br.edu.ifpb.es.cuidarme.model.Pagamento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PagamentoController {

    private static List<Pagamento> pagamentos = new ArrayList<>();
    private static long idCounter = 1;

    public static void registrar(Paciente paciente, Integer valor, LocalDate data, Pagamento.Metodo metodo, Pagamento.Status status) {
        pagamentos.add(new Pagamento(idCounter++, paciente, valor, data, metodo, status));
    }

    public static List<Pagamento> listarPorCpf(String cpf) {
        List<Pagamento> encontrados = new ArrayList<>();
        for (Pagamento p : pagamentos) {
            if (p.getPaciente().getCpf().equals(cpf)) {
                encontrados.add(p);
            }
        }
        return encontrados;
    }

    public static List<Pagamento> listarPorData(LocalDate data) {
        return pagamentos.stream()
                .filter(p -> p.getData().equals(data))
                .collect(Collectors.toList());
    }
}

