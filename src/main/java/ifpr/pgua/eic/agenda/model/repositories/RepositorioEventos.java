package ifpr.pgua.eic.agenda.model.repositories;

import java.time.LocalDate;
import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.daos.CoordenadorDAO;
import ifpr.pgua.eic.agenda.model.daos.EventosDAO;
import ifpr.pgua.eic.agenda.model.entities.Atividades;
import ifpr.pgua.eic.agenda.model.entities.Coordenador;
import ifpr.pgua.eic.agenda.model.entities.Eventos;
import ifpr.pgua.eic.agenda.model.entities.Professor;

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

    public Resultado atualizarEvento(int id, String nome, String descricao, LocalDate data, Coordenador coordenador) {
        //Anotacoes anotacao = new Anotacoes(aluno, nome, descricao, data);
        Eventos evento = new Eventos(coordenador, nome, descricao, data);
        return dao.atualizar(id, evento);
    }

    public Resultado deletar(int id){
        return dao.deletar(id);
    } 

    public Resultado filtrarSemana(){
        Resultado res = dao.listarSemana();
        if(res.foiSucesso()){
            List<Eventos> lista = (List<Eventos>)res.comoSucesso().getObj();
            for(Eventos eventos: lista){
                Coordenador coordenador = coordenadorDAO.buscarCoordenadorEvento(eventos.getIdEvento());
                eventos.setCoordenador(coordenador);
            }
        }

        return res;
    }

    public Resultado filtrarMes(){
        Resultado res = dao.listarMes();
        if(res.foiSucesso()){
            List<Eventos> lista = (List<Eventos>)res.comoSucesso().getObj();
            for(Eventos eventos: lista){
                Coordenador coordenador = coordenadorDAO.buscarCoordenadorEvento(eventos.getIdEvento());
                eventos.setCoordenador(coordenador);
            }
        }

        return res;
    }
    
}