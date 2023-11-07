package ifpr.pgua.eic.agenda.model.entities;

import java.sql.Date;

public class Atividades {
    
    private int idProfessor;
    private int idAtividade;
    private String nome;
    private String descricao;
    private Date data;
    private boolean atividadeAvaliada;

    public int getIdProfessor() {
        return idProfessor;
    }
    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
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

    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }
    
    public boolean isAtividadeAvaliada() {
        return atividadeAvaliada;
    }
    public void setAtividadeAvaliada(boolean atividadeAvaliada) {
        this.atividadeAvaliada = atividadeAvaliada;
    }

    
}
