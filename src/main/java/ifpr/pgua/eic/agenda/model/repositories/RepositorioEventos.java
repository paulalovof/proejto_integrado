package ifpr.pgua.eic.agenda.model.repositories;

import java.net.CookiePolicy;
import java.time.LocalDate;
import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.daos.CoordenadorDAO;
import ifpr.pgua.eic.agenda.model.daos.EventosDAO;
import ifpr.pgua.eic.agenda.model.entities.Aluno;
import ifpr.pgua.eic.agenda.model.entities.Anotacoes;
import ifpr.pgua.eic.agenda.model.entities.Coordenador;
import ifpr.pgua.eic.agenda.model.entities.Eventos;

public class RepositorioEventos {

    private EventosDAO dao;
    private CoordenadorDAO coordenadorDAO;

    public RepositorioEventos(EventosDAO dao, CoordenadorDAO coordenadorDAO){
        this.dao = dao;
        this.coordenadorDAO = coordenadorDAO;
    }

    public Resultado cadastrarEvento(String nome, String descricao, LocalDate dataPicker, Coordenador coordenador) {
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
        Eventos evento = new Eventos(coordenador, nome, descricao, dataPicker);
        Resultado resultado = dao.criar(evento);
        return resultado;
    }

    public Resultado listarEventos(int id){
        Resultado resultado = dao.listar(id);

        if(resultado.foiSucesso()){
            List<Eventos> lista = (List<Eventos>)resultado.comoSucesso().getObj();
            for(Eventos evento:lista){
                Coordenador coordenador = coordenadorDAO.buscarCoordenadorEvento(evento.getIdEvento());

                evento.setCoordenador(coordenador);
            }
        }
        
        return resultado;
    }

    

    
    
}