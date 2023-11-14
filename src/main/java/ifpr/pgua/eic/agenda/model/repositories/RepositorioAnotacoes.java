package ifpr.pgua.eic.agenda.model.repositories;

import java.time.LocalDate;
import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.daos.AlunoDAO;
import ifpr.pgua.eic.agenda.model.daos.AnotacoesDAO;
import ifpr.pgua.eic.agenda.model.entities.Aluno;
import ifpr.pgua.eic.agenda.model.entities.Anotacoes;

public class RepositorioAnotacoes {

    private AnotacoesDAO dao;
    private AlunoDAO alunoDAO;

    public RepositorioAnotacoes(AnotacoesDAO dao, AlunoDAO alunoDAO){
        this.dao = dao;
        this.alunoDAO = alunoDAO;
    }

    public Resultado cadastrarAnotacao(String nome, String descricao, LocalDate dataPicker, Aluno aluno) {
        if(nome.isBlank() || nome.isEmpty()){
            return Resultado.erro("Titulo inválido!");
        }
        if(descricao.isEmpty() || descricao.isBlank()){
            return Resultado.erro("Descrição inválida!");
        }
        if(dataPicker == null || dataPicker.isBefore(LocalDate.now())){
            return Resultado.erro("Data inválida!");
        }

        Anotacoes anotacao = new Anotacoes(aluno, nome, descricao, dataPicker);
        Resultado resultado = dao.criar(anotacao);
        return resultado;
    }

    public Resultado listarAnotacoes(){
        Resultado resultado = dao.listar();

        if(resultado.foiSucesso()){
            //iremos finalizar de montar os objetos
            List<Anotacoes> lista = (List<Anotacoes>)resultado.comoSucesso().getObj();
            
            for(Anotacoes anotacao:lista){
                
                Resultado r1 = alunoDAO.buscarAlunoAnotacao(anotacao.getIdAnotacao());
                if(r1.foiErro()){
                    return r1;
                }
                //Categoria categoria = (Categoria)r1.comoSucesso().getObj();
                Aluno aluno = (Aluno)r1.comoSucesso().getObj();
                anotacao.setAluno(aluno);
            }
        }

        return resultado;
    }
    
}
