package ifpr.pgua.eic.agenda.model.repositories;

import ifpr.pgua.eic.agenda.model.daos.ServicoLoginDAO;

public class RepositorioServicoLogin {
    private ServicoLoginDAO dao;

    public RepositorioServicoLogin(ServicoLoginDAO dao){
        this.dao = dao;
    }
}
