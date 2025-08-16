package br.edu.ifpb.es.cuidarme.controller;

import br.edu.ifpb.es.cuidarme.model.Paciente;
import br.edu.ifpb.es.cuidarme.model.Prontuario;

import java.util.ArrayList;
import java.util.List;

public class ProntuarioController {

    private static List<Prontuario> prontuarios = new ArrayList<>();
    private static long idCounter = 1;


    public static void criarProntuarioPara(Paciente paciente) {
        prontuarios.add(new Prontuario(idCounter++, paciente));
    }

    public static Prontuario buscarPorCpf(String cpf) {
        for (Prontuario p : prontuarios) {
            if (p.getPaciente().getCpf().equals(cpf)) return p;
        }
        return null;
    }

    public static boolean adicionarAnotacao(String cpf, String anotacao) {
        Prontuario prontuario = buscarPorCpf(cpf);
        if (prontuario != null) {
            prontuario.getAnotacoes().add(anotacao);
            return true;
        }
        return false;
    }

    public static boolean editarAnotacao(String cpf, String anotacaoAntiga, String novaAnotacao){
        Prontuario prontuario = buscarPorCpf(cpf);
        if(prontuario != null) {
            List<String> anotacoes = prontuario.getAnotacoes();
            int index = anotacoes.indexOf(anotacaoAntiga);
            if(index != -1){
                anotacoes.set(index, novaAnotacao);
                return true;
            }
        }
        return false;
    }
}

