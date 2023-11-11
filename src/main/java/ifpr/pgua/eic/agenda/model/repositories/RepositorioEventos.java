package ifpr.pgua.eic.agenda.model.repositories;

import java.time.LocalDate;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.daos.EventosDAO;
import ifpr.pgua.eic.agenda.model.entities.Eventos;

public class RepositorioEventos {

    private EventosDAO dao;
    //fazer DAO coordenador

    public RepositorioEventos(EventosDAO dao){
        this.dao = dao;
    }

    public Resultado cadastrarEvento(String nome, String descricao, LocalDate dataPicker) {
        if(nome.isBlank() || nome.isEmpty()){
            return Resultado.erro("Titulo inválido!");
        }
        if(descricao.isEmpty() || descricao.isBlank()){
            return Resultado.erro("Descrição inválida!");
        }
        if(dataPicker == null || dataPicker.isBefore(LocalDate.now())){
            return Resultado.erro("Data inválida!");
        }

        //Tarefa tarefa = new Tarefa(titulo, descricao, data, categoria);
        Eventos evento = new Eventos(null, nome, descricao, dataPicker);
        Resultado resultado = dao.criar(evento);
        return resultado;
    }

    
    
}