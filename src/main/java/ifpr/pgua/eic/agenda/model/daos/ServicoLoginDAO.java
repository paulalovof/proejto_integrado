package ifpr.pgua.eic.agenda.model.daos;


import ifpr.pgua.eic.agenda.model.entities.Usuario;

public interface ServicoLoginDAO {
    Usuario validaUsuario(String login, String senha);
}
