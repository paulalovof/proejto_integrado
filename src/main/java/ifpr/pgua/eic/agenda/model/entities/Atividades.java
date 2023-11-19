package ifpr.pgua.eic.agenda.model.entities;

import java.time.LocalDate;

public class Atividades {
    
    private Professor professor;
    private int idAtividade;
    private String nome;
    private String descricao;
    private LocalDate data;
    private boolean atividadeAvaliada;

    public Atividades(Professor professor, String nome, String descricao, LocalDate data, boolean atividadeAvaliada){
        this.professor = professor;
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.atividadeAvaliada = atividadeAvaliada;
    }

    public Atividades(int idAtividade, Professor professor, String nome, String descricao, LocalDate data, boolean atividadeAvaliada){
        this.idAtividade = idAtividade;
        this.professor = professor;
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.atividadeAvaliada = atividadeAvaliada;
    }

    public Professor getProfessor() {
        return professor;
    }
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public int getIdAtividade() {
        return idAtividade;
    }
    public void setIdAtividade(int idAtividade) {
        this.idAtividade = idAtividade;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    
    public boolean isAtividadeAvaliada() {
        return atividadeAvaliada;
    }
    public void setAtividadeAvaliada(boolean atividadeAvaliada) {
        this.atividadeAvaliada = atividadeAvaliada;
    }

    public String toString(){
        if(atividadeAvaliada){
            return "Data de Prazo: "+ data+" \n"+ nome + " AVALIADA("+ descricao+ ")";
        }else{
            if(descricao != null){
                return "Data de Prazo: "+ data+" \n"+ nome + "("+ descricao+ ")";
            }else{
                return nome;
            }
            
        }
    }
}
