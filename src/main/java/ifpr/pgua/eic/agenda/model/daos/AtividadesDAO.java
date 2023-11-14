package ifpr.pgua.eic.agenda.model.daos;
import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.entities.Atividades;

public interface AtividadesDAO {
    Resultado criar(Atividades atividade);
    Resultado listar();
    Resultado getById(int id);
    Resultado atualizar(int id, Atividades novo);
    Resultado deletar(int id);
}
