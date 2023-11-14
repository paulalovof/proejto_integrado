package ifpr.pgua.eic.agenda.model.daos;

import com.github.hugoperlin.results.Resultado;

public interface AlunoDAO {
    Resultado listar();
    Resultado getById(int id);
    Resultado buscarAlunoAnotacao(int alunoId);
}
