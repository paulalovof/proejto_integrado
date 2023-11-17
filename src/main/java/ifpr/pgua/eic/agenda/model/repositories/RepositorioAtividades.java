package ifpr.pgua.eic.agenda.model.repositories;

import java.time.LocalDate;
import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.daos.AtividadesDAO;
import ifpr.pgua.eic.agenda.model.daos.ProfessorDAO;
import ifpr.pgua.eic.agenda.model.entities.Atividades;
import ifpr.pgua.eic.agenda.model.entities.Coordenador;
import ifpr.pgua.eic.agenda.model.entities.Eventos;
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

    
    
}