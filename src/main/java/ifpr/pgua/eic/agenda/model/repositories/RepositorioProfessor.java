package ifpr.pgua.eic.agenda.model.repositories;


import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.daos.ProfessorDAO;
import ifpr.pgua.eic.agenda.model.entities.ServicoLogin;

public class RepositorioProfessor {
    private ProfessorDAO dao;
    private ServicoLogin logado;

    public RepositorioProfessor(ProfessorDAO dao, ServicoLogin logado){
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
