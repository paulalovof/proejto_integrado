package ifpr.pgua.eic.agenda.model.entities;

import java.sql.Date;

public class Eventos {
    
    private int idCoordenador;
    private int idEvento;
    private String nome;
    private String descricao;
    private Date data;

    public int getIdCoordenador() {
        return idCoordenador;
    }
    public void setIdCoordenador(int idCoordenador) {
        this.idCoordenador = idCoordenador;
    }

    public int getIdEvento() {
        return idEvento;
    }
    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
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
}
