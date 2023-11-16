package ifpr.pgua.eic.agenda.model.entities;

public class Aluno{

    private int idAluno;
    private String nome;
    private String numeroMatricula;
    private Usuario usuario;

    public Aluno(int idAluno, String nome, String numeroMatricula){
        this.idAluno = idAluno;
        this.nome = nome;
        this.numeroMatricula = numeroMatricula;
    }

    public Aluno(String nome, String numeroMatricula, Usuario usuario){
        this.nome = nome;
        this.numeroMatricula = numeroMatricula;
        this.usuario = usuario;
    }

    public int getIdAluno() {
        return idAluno;
    }
    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getNumeroMatricula() {
        return numeroMatricula;
    }
    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    
}
