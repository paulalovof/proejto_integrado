package ifpr.pgua.eic.agenda.model.repositories;


import ifpr.pgua.eic.agenda.model.daos.AlunoDAO;
import ifpr.pgua.eic.agenda.model.entities.Aluno;
import ifpr.pgua.eic.agenda.model.entities.ServicoLogin;

public class RepositorioAluno {
    private AlunoDAO dao;
    private ServicoLogin logado;

    public RepositorioAluno(AlunoDAO dao, ServicoLogin logado){
        this.dao = dao;
        this.logado = logado;
    }

    public int getById(int id){
        return dao.getById(id).getIdAluno();
    }

    public String getNomeLogado(ServicoLogin logado){
        return dao.getNomeLogado(logado).getNome();
    }

    public Aluno getLogado(ServicoLogin logado){
        return dao.getNomeLogado(logado);
    }

}
