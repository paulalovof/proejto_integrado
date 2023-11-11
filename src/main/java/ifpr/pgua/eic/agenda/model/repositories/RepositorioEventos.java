package ifpr.pgua.eic.agenda.model.repositories;

import java.time.LocalDate;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.daos.EventosDAO;

public class RepositorioEventos {

    private EventosDAO dao;

    public RepositorioEventos(EventosDAO dao){
        this.dao = dao;
    }

    public Resultado cadastrarEvento(String nome, String descricao, LocalDate dataPicker) {
        return null;
    }

    
    
}