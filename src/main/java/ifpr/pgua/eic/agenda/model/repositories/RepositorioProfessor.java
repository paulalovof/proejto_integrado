package ifpr.pgua.eic.agenda.model.repositories;


import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.daos.ProfessorDAO;

public class RepositorioProfessor {
    private ProfessorDAO dao;

    public RepositorioProfessor(ProfessorDAO dao){
        this.dao = dao;
    }

    public Resultado getById(int id){
        return dao.getById(id);
    }
}
