package ifpr.pgua.eic.agenda.model.daos;

import com.github.hugoperlin.results.Resultado;

public interface ProfessorDAO {
    Resultado listar();
    Resultado getById(int id);
    Resultado buscarProfessorAtividade(int professorId);
}
