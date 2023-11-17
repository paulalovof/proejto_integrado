package ifpr.pgua.eic.agenda.model.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.entities.Coordenador;
import ifpr.pgua.eic.agenda.model.entities.ServicoLogin;

public interface CoordenadorDAO {
    Resultado listar();
    Coordenador getById(int id);
    Coordenador getByIdCoordenador(int id);
    Coordenador buscarCoordenadorEvento(int coordenadorId);
    Coordenador getNomeLogado(ServicoLogin logado);
}
