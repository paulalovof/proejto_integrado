package ifpr.pgua.eic.agenda.model.repositories;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.daos.AlunoDAO;

public class RepositorioAluno {
    private AlunoDAO dao;

    public RepositorioAluno(AlunoDAO dao){
        this.dao = dao;
    }

    public Resultado getById(int id){
        return dao.getById(id);
    }
}
