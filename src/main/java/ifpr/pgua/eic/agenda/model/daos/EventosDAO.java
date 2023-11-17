package ifpr.pgua.eic.agenda.model.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.entities.Eventos;

public interface EventosDAO {
    Resultado criar(Eventos evento);
    Resultado listar(int id);
    Resultado getById(int id);
    Resultado atualizar(int id, Eventos novo);
    Resultado deletar(int id);
}

