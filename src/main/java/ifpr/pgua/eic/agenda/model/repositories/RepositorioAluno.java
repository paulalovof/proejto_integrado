package ifpr.pgua.eic.agenda.model.repositories;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.daos.AlunoDAO;
import ifpr.pgua.eic.agenda.model.entities.ServicoLogin;

public class RepositorioAluno {
    private AlunoDAO dao;
    private ServicoLogin logado;

    public RepositorioAluno(AlunoDAO dao, ServicoLogin logado){
        this.dao = dao;
        this.logado = logado;
    }

    public Resultado getById(int id){
        return dao.getById(id);
    }

    public String getNomeLogado(ServicoLogin logado){
        return dao.getNomeLogado(logado).getNome();
    }

}
