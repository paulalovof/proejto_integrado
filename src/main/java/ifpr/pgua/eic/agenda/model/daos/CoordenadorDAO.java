package ifpr.pgua.eic.agenda.model.daos;

import com.github.hugoperlin.results.Resultado;

public interface CoordenadorDAO {
    Resultado listar();
    Resultado getById(int id);
    Resultado buscarCoordenadorEvento(int coordenadorId);
}
