package ifpr.pgua.eic.agenda.model.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.entities.Coordenador;
import ifpr.pgua.eic.agenda.model.entities.ServicoLogin;

public interface CoordenadorDAO {
    Resultado listar();
    int getById(int id);
    Resultado buscarCoordenadorEvento(int coordenadorId);
    
    Coordenador getNomeLogado(ServicoLogin logado);
}
