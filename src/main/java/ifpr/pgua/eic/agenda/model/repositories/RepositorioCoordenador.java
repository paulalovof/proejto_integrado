package ifpr.pgua.eic.agenda.model.repositories;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.daos.CoordenadorDAO;
import ifpr.pgua.eic.agenda.model.entities.ServicoLogin;

public class RepositorioCoordenador {
    private CoordenadorDAO dao;
    private ServicoLogin logado;

    public RepositorioCoordenador(CoordenadorDAO dao, ServicoLogin logado){
        this.dao = dao;
        this.logado = logado;
    }

    public int getById(int id){
        return dao.getById(id);
    }

    public String getNomeLogado(ServicoLogin logado){
        return dao.getNomeLogado(logado).getNome();
    }
}
