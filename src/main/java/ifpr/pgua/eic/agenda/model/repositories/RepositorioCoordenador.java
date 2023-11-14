package ifpr.pgua.eic.agenda.model.repositories;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.daos.CoordenadorDAO;

public class RepositorioCoordenador {
    private CoordenadorDAO dao;

    public RepositorioCoordenador(CoordenadorDAO dao){
        this.dao = dao;
    }

    public Resultado getById(int id){
        return dao.getById(id);
    }
}
