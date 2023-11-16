package ifpr.pgua.eic.agenda.model.entities;

public class Coordenador{

    private int idCoordenador;
    private String nome;
    private String numeroSiape;
    private Usuario usuario;

    public Coordenador(int idCoordenador, String nome, String numeroSiape){
        this.idCoordenador = idCoordenador;
        this.nome = nome;
        this.numeroSiape = numeroSiape;
    }

    public Coordenador(String nome, String numeroSiape, Usuario usuario){
        this.nome = nome;
        this.numeroSiape = numeroSiape;
        this.usuario = usuario;
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
