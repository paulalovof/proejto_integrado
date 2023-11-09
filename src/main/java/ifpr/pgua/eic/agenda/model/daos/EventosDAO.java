package ifpr.pgua.eic.agenda.model.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.entities.Eventos;

public interface EventosDAO {
    Resultado criar(Eventos evento);
    Resultado buscarCoordenadorEvento(int eventoId);
    Resultado listar();
    Resultado getById(int id);
    Resultado atualizar(int id, Eventos novo);
    Resultado deletar(int id);
}

