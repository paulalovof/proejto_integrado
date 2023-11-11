package ifpr.pgua.eic.agenda.model.repositories;

import java.time.LocalDate;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.daos.AnotacoesDAO;

public class RepositorioAnotacoes {

    private AnotacoesDAO dao;

    public RepositorioAnotacoes(AnotacoesDAO dao){
        this.dao = dao;
    }

    public Resultado cadastrarAnotacao(String nome, String descricao, LocalDate dataPicker) {
        return null;
    }
    
}
