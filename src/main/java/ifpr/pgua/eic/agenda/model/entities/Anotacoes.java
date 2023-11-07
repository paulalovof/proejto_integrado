package ifpr.pgua.eic.agenda.model.entities;

public class Anotacoes {
    
    private int idAluno;
    private int idAnotacao;
    private String nome;
    private String descricao;

    public int getIdAluno() {
        return idAluno;
    }
    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
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

    
}
