package ifpr.pgua.eic.agenda.model.repositories;

import java.time.LocalDate;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.daos.AnotacoesDAO;
import ifpr.pgua.eic.agenda.model.entities.Anotacoes;

public class RepositorioAnotacoes {

    private AnotacoesDAO dao;
    //fazer DAO Aluno

    public RepositorioAnotacoes(AnotacoesDAO dao){
        this.dao = dao;
    }

    public Resultado cadastrarAnotacao(String nome, String descricao, LocalDate dataPicker) {
        if(nome.isBlank() || nome.isEmpty()){
            return Resultado.erro("Titulo inválido!");
        }
        if(descricao.isEmpty() || descricao.isBlank()){
            return Resultado.erro("Descrição inválida!");
        }
        if(dataPicker == null || dataPicker.isBefore(LocalDate.now())){
            return Resultado.erro("Data inválida!");
        }

        //Tarefa tarefa = new Tarefa(titulo, descricao, data, categoria);
        Anotacoes anotacao = new Anotacoes(null, nome, descricao, dataPicker);
        Resultado resultado = dao.criar(anotacao);
        return resultado;
    }
    
}
