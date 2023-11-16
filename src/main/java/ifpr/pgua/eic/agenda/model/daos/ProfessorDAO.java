package ifpr.pgua.eic.agenda.model.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.entities.Professor;
import ifpr.pgua.eic.agenda.model.entities.ServicoLogin;

public interface ProfessorDAO {
    Resultado listar();
    int getById(int id);
    Resultado buscarProfessorAtividade(int professorId);
    Professor getNomeLogado(ServicoLogin logado);
}
