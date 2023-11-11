package ifpr.pgua.eic.agenda.model.repositories;

import java.time.LocalDate;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.daos.AtividadesDAO;
import ifpr.pgua.eic.agenda.model.entities.Atividades;

public class RepositorioAtividades {

    private AtividadesDAO dao;
    //fazer DAO professor

    public RepositorioAtividades(AtividadesDAO dao){
        this.dao = dao;
    }

    public Resultado cadastrarAtividade(String nome, String descricao, LocalDate dataPicker, Boolean avaliada) {
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
        Atividades atividade = new Atividades(null, nome, descricao, dataPicker, avaliada);
        Resultado resultado = dao.criar(atividade);
        return resultado;
    }

    
    
}