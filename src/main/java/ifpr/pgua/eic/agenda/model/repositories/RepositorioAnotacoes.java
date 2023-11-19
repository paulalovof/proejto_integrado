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

    public Resultado listarAnotacoes(int id){
        Resultado resultado = dao.listar(id);
        if(resultado.foiSucesso()){
            //iremos finalizar de montar os objetos
            List<Anotacoes> lista = (List<Anotacoes>)resultado.comoSucesso().getObj();
            for(Anotacoes anotacao:lista){
                Aluno aluno = alunoDAO.buscarAlunoAnotacao(anotacao.getIdAnotacao());
                anotacao.setAluno(aluno);
            }
        }
    
        return resultado;
        
    }

    public Resultado atualizarAnotacao(int id, String nome, String descricao, LocalDate data, Aluno aluno) {
        Anotacoes anotacao = new Anotacoes(aluno, nome, descricao, data);
        return dao.atualizar(id, anotacao);
    }

    public Resultado deletar(int id){
        return dao.deletar(id);
    }
    
    public Resultado filtrarSemana(int id){
        Resultado resultado = dao.listarSemana(id);
        if(resultado.foiSucesso()){
                //iremos finalizar de montar os objetos
            List<Anotacoes> lista = (List<Anotacoes>)resultado.comoSucesso().getObj();
            for(Anotacoes anotacao:lista){
                Aluno aluno = alunoDAO.buscarAlunoAnotacao(anotacao.getIdAnotacao());

                anotacao.setAluno(aluno);
            }
        }

        return resultado;
    }

    public Resultado filtrarMes(int id){
        Resultado resultado = dao.listarMes(id);
        if(resultado.foiSucesso()){
                //iremos finalizar de montar os objetos
            List<Anotacoes> lista = (List<Anotacoes>)resultado.comoSucesso().getObj();
            for(Anotacoes anotacao:lista){
                Aluno aluno = alunoDAO.buscarAlunoAnotacao(anotacao.getIdAnotacao());

                anotacao.setAluno(aluno);
            }
        }

        return resultado;
    }
}
