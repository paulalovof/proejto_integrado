package ifpr.pgua.eic.agenda.model.entities;

import java.time.LocalDate;

public class Eventos {
    
    private Coordenador coordenador;
    private int idEvento;
    private String nome;
    private String descricao;
    private LocalDate data;

    public Eventos(Coordenador coordenador, String nome, String descricao, LocalDate data){
        this.coordenador = coordenador;
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
    }

    public Eventos(int idEvento, Coordenador coordenador, String nome, String descricao, LocalDate data){
        this.idEvento = idEvento;
        this.coordenador = coordenador;
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
    }
    

    public Coordenador getCoordenador() {
        return coordenador;
    }
    public void setCoordenador(Coordenador coordenador) {
        this.coordenador = coordenador;
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
    
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }

    public String toString(){
        return "Data de Prazo: "+ data+" \n"+ nome + "("+ descricao+ ")";
    }
}
