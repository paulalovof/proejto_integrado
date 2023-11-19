package ifpr.pgua.eic.agenda.model.repositories;

import java.time.LocalDate;
import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.daos.AtividadesDAO;
import ifpr.pgua.eic.agenda.model.daos.ProfessorDAO;
import ifpr.pgua.eic.agenda.model.entities.Aluno;
import ifpr.pgua.eic.agenda.model.entities.Anotacoes;
import ifpr.pgua.eic.agenda.model.entities.Atividades;
import ifpr.pgua.eic.agenda.model.entities.Professor;

public class RepositorioAtividades {

    private AtividadesDAO dao;
    private ProfessorDAO professorDAO;

    public RepositorioAtividades(AtividadesDAO dao, ProfessorDAO professorDAO){
        this.dao = dao;
        this.professorDAO = professorDAO;
    }

    public Resultado cadastrarAtividade(String nome, String descricao, LocalDate dataPicker, Boolean avaliada, Professor professor) {
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
        Atividades atividade = new Atividades(professor, nome, descricao, dataPicker, avaliada);
        Resultado resultado = dao.criar(atividade);
        return resultado;
    }

    public Resultado listarAtividades(int id){
        Resultado resultado = dao.listar(id);

        if(resultado.foiSucesso()){
            List<Atividades> lista = (List<Atividades>)resultado.comoSucesso().getObj();
            for(Atividades atividade:lista){
                //Atividades atividade = professorDAO.buscarProfessorAtividade(atividade);
                Professor professor = professorDAO.buscarProfessorAtividade(atividade.getIdAtividade());
                atividade.setProfessor(professor);
            }
        }

        return resultado;
    }

    public Resultado listarAtividadesProximas(){
        Resultado resultado = dao.listarProximas();

        if(resultado.foiSucesso()){
            List<Atividades> lista = (List<Atividades>)resultado.comoSucesso().getObj();
            for(Atividades atividade: lista){
                Professor professor = professorDAO.buscarProfessorAtividade(atividade.getIdAtividade());
                atividade.setProfessor(professor);
            }
        }

        return resultado;
    }

    public Resultado atualizarAtividade(int id, String nome, String descricao, LocalDate data, Boolean avaliada, Professor professor) {
        //Anotacoes anotacao = new Anotacoes(aluno, nome, descricao, data);
        Atividades atividade = new Atividades(professor, nome, descricao, data, avaliada);
        return dao.atualizar(id, atividade);
    }

    public Resultado deletar(int id){
        return dao.deletar(id);
    }

    public Resultado filtrarSemana(){
        Resultado res = dao.listarSemana();
        if(res.foiSucesso()){
            List<Atividades> lista = (List<Atividades>)res.comoSucesso().getObj();
            for(Atividades atividade: lista){
                Professor professor = professorDAO.buscarProfessorAtividade(atividade.getIdAtividade());
                atividade.setProfessor(professor);
            }
        }

        return res;
    }

    public Resultado filtrarMes(){
        Resultado res = dao.listarMes();
        if(res.foiSucesso()){
            List<Atividades> lista = (List<Atividades>)res.comoSucesso().getObj();
            for(Atividades atividade: lista){
                Professor professor = professorDAO.buscarProfessorAtividade(atividade.getIdAtividade());
                atividade.setProfessor(professor);
            }
        }

        return res;
    }
    
}