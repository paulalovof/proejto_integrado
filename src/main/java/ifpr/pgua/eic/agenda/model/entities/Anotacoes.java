package ifpr.pgua.eic.agenda.model.entities;

import java.time.LocalDate;

public class Anotacoes {
    
    private Aluno aluno;
    private int idAnotacao;
    private String nome;
    private String descricao;
    private LocalDate data;

    public Anotacoes(Aluno aluno, String nome, String descricao,LocalDate data){
        this.aluno = aluno;
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
    }

    public Anotacoes(int idAnotacao, Aluno aluno, String nome, String descricao, LocalDate data){
        this.idAnotacao = idAnotacao;
        this.aluno = aluno;
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
    }

    public Aluno getAluno() {
        return aluno;
    }
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public int getIdAnotacao() {
        return idAnotacao;
    }
    public void setIdAnotacao(int idAnotacao) {
        this.idAnotacao = idAnotacao;
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
