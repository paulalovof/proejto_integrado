package ifpr.pgua.eic.agenda.model.daos;
import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.entities.Anotacoes;

public interface AnotacoesDAO {
    Resultado criar(Anotacoes anotacao);
    Resultado listar(int id);
    Resultado getById(int id);
    Resultado atualizar(int id, Anotacoes novo);
    Resultado deletar(int id);
}
