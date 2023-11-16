package ifpr.pgua.eic.agenda.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.agenda.App;
import ifpr.pgua.eic.agenda.model.entities.ServicoLogin;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioCoordenador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


public class PrincipalCoordenador implements Initializable{

    @FXML
    private Label nomeCoordenador;

    private ServicoLogin logado;
    private RepositorioCoordenador repositorio;

    public PrincipalCoordenador(ServicoLogin logado, RepositorioCoordenador repositorio){
        this.logado = logado;
        this.repositorio = repositorio;
    }

    @FXML
    void cadastrarEvento(ActionEvent event){
        App.pushScreen("CADASTRAEVENTO");
    }

    
    @FXML
    void sair(ActionEvent event) {
        logado.logout();
        App.pegaLogado(logado);
        App.pushScreen("PRINCIPAL");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        nomeCoordenador.setText(repositorio.getNomeLogado(logado));
    }
}
