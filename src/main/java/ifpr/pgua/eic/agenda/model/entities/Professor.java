package ifpr.pgua.eic.agenda.model.entities;

public class Professor{
    
    private int idProfessor;
    private String nome;
    private String numeroSiape;
    private Usuario usuario;

    public Professor(int idProfessor, String nome, String numeroSiape){
        this.idProfessor = idProfessor;
        this.nome = nome;
        this.numeroSiape = numeroSiape;
    }

    public Professor(String nome, String numeroSiape, Usuario usuario){
        this.nome = nome;
        this.numeroSiape = numeroSiape;
        this.usuario = usuario;
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

    public String getNumeroSiape() {
        return numeroSiape;
    }
    public void setNumeroSiape(String numeroSiape) {
        this.numeroSiape = numeroSiape;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
