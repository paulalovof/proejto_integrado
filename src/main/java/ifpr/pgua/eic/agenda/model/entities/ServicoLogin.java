package ifpr.pgua.eic.agenda.model.entities;

public class ServicoLogin {
    private String login;
    private String senha;
    private String numero;
    private int tipoUsuario;

    /*public ServicoLogin(String login, String senha, String numero){
        this.login = login;
        this.senha = senha;
        this.numero = numero;
    }

    public ServicoLogin(String login, String senha, String numero, int tipo){
        this.login = login;
        this.senha = senha;
        this.numero = numero;
        this.tipoUsuario = tipo;
    }
    */

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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
