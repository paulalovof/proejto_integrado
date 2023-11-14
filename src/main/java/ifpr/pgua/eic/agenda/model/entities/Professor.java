package ifpr.pgua.eic.agenda.model.entities;

public class Professor{
    
    private int idProfessor;
    private String nome;
    private String login;
    private String senha;
    private String numeroSiape;

    public Professor(int idProfessor, String nome, String login, String senha, String numeroSiape){
        this.idProfessor = idProfessor;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.numeroSiape = numeroSiape;
    }

    public Professor(String nome, String login, String senha, String numeroSiape){
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.numeroSiape = numeroSiape;
    }

    public int getIdProfessor() {
        return idProfessor;
    }
    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
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

    public String getNumeroSiape() {
        return numeroSiape;
    }
    public void setNumeroSiape(String numeroSiape) {
        this.numeroSiape = numeroSiape;
    }

    
}
