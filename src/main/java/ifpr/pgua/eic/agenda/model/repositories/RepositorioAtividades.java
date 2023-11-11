package ifpr.pgua.eic.agenda.model.repositories;

import java.time.LocalDate;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.daos.AtividadesDAO;

public class RepositorioAtividades {

    private AtividadesDAO dao;

    public RepositorioAtividades(AtividadesDAO dao){
        this.dao = dao;
    }

    public Resultado cadastrarAtividade(String nome, String descricao, LocalDate dataPicker, Boolean avaliada) {
        return null;
    }

    
    
}