package ifpr.pgua.eic.agenda.model.daos;

import com.github.hugoperlin.results.Resultado;

public interface ServicoLoginDAO {
    Resultado validaUsuario(String login, String senha);
}
