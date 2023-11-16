package ifpr.pgua.eic.agenda.model.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.entities.Aluno;
import ifpr.pgua.eic.agenda.model.entities.ServicoLogin;

public interface AlunoDAO {
    Resultado listar();
    int getById(int id);
    Resultado buscarAlunoAnotacao(int alunoId);
    Aluno getNomeLogado(ServicoLogin logado);
}
