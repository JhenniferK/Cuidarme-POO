package br.edu.ifpb.es.cuidarme.controller;

import br.edu.ifpb.es.cuidarme.model.Psicologo;

import java.util.ArrayList;
import java.util.List;

public class PsicologoController {

    private static List<Psicologo> psicologos = new ArrayList<>();
    private static int idCounter = 1;

    public static Psicologo cadastrar(String nome, String email, String senha) {
        Psicologo novo = new Psicologo(idCounter++, nome, email, senha);
        psicologos.add(novo);
        return novo;
    }

    public static Psicologo login(String email, String senha) {
        for (Psicologo p : psicologos) {
            if (p.getEmail().equals(email) && p.getSenha().equals(senha)) {
                return p;
            }
        }
        return null;
    }
}

