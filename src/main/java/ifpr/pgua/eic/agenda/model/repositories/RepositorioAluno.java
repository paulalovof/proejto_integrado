package ifpr.pgua.eic.agenda.model.repositories;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.daos.AlunoDAO;
import ifpr.pgua.eic.agenda.model.entities.ServicoLogin;

public class RepositorioAluno {
    private AlunoDAO dao;
    private ServicoLogin logado;

    public RepositorioAluno(AlunoDAO dao){
        this.dao = dao;
    }

    public Resultado getById(int id){
        return dao.getById(id);
    }

    public void setLogado(String login, String senha, String numero){
        //logado = new ServicoLogin(login, senha, numero);
    } 

    public ServicoLogin getLogado(){
        return logado;
    }

}
