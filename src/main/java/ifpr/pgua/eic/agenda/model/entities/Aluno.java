package ifpr.pgua.eic.agenda.model.entities;

public class Aluno{

    private int idAluno;
    private String nome;
    private String login;
    private String senha;
    private String numeroMatricula;

    public Aluno(int idAluno, String nome, String login, String senha, String numeroMatricula){
        this.idAluno = idAluno;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.numeroMatricula = numeroMatricula;
    }

    public Aluno(String nome, String login, String senha, String numeroMatricula){
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.numeroMatricula = numeroMatricula;
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
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String getNumeroMatricula() {
        return numeroMatricula;
    }
    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    
}
