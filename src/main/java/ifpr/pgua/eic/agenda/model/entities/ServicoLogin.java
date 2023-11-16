package ifpr.pgua.eic.agenda.model.entities;

import ifpr.pgua.eic.agenda.model.daos.ServicoLoginDAO;

public class ServicoLogin {
    
    private Usuario logado;
    private ServicoLoginDAO dao;

    public ServicoLogin(Usuario logado, ServicoLoginDAO dao){
        this.logado = logado;
        this.dao = dao;
    }

    public Usuario login(String login, String senha){
        //busca dao na tabela usuario se existe esse login e senha
        dao.validaUsuario(login, senha);
        //seta o logado com os dados vindo do banco
        logado = new Usuario(login, senha);

        return logado;
    }

    public void logout(){
        logado = null;
    }

    public Usuario getLogado(){
        return logado;
    }
}
