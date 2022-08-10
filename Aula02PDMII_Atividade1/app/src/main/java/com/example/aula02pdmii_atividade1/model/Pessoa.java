package com.example.aula02pdmii_atividade1.model;
import java.util.List;

public class Pessoa {

    private List<Agenda> agenda = null;
    private List<Adicional> adicional = null;

    public List<Agenda> getAgenda() {
        return agenda;
    }

    public void setAgenda(List<Agenda> agenda) {
        this.agenda = agenda;
    }

    public List<Adicional> getAdicional() {
        return adicional;
    }

    public void setAdicional(List<Adicional> adicional) {
        this.adicional = adicional;
    }

}