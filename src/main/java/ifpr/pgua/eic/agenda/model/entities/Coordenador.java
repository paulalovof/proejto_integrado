package ifpr.pgua.eic.agenda.model.entities;

public class Coordenador{

    private int idCoordenador;
    private String nome;
    private String login;
    private String senha;
    private String numeroSiape;

    public Coordenador(int idCoordenador, String nome, String login, String senha, String numeroSiape){
        this.idCoordenador = idCoordenador;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.numeroSiape = numeroSiape;
    }

    public Coordenador(String nome, String login, String senha, String numeroSiape){
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.numeroSiape = numeroSiape;
    }


    public int getIdCoordenador() {
        return idCoordenador;
    }
    public void setIdCoordenador (int idCoordenador) {
        this.idCoordenador = idCoordenador;
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
