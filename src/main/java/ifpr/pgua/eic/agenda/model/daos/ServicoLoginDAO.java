package ifpr.pgua.eic.agenda.model.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.entities.Usuario;

public interface ServicoLoginDAO {
    Usuario validaUsuario(String login, String senha);
}
