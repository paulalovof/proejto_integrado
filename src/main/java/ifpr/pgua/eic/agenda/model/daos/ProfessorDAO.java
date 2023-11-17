package ifpr.pgua.eic.agenda.model.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.entities.Professor;
import ifpr.pgua.eic.agenda.model.entities.ServicoLogin;

public interface ProfessorDAO {
    Resultado listar();
    Professor getById(int id);
    Professor getByIdProfessor(int id);
    Professor buscarProfessorAtividade(int atividadeId);
    Professor getNomeLogado(ServicoLogin logado);
}
