package ifpr.pgua.eic.agenda.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.agenda.App;
import ifpr.pgua.eic.agenda.model.entities.ServicoLogin;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioProfessor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


public class PrincipalProfessor implements Initializable{

    @FXML
    private Label nomeProfessor;

    private ServicoLogin logado;
    private RepositorioProfessor repositorio;

    public PrincipalProfessor(ServicoLogin logado, RepositorioProfessor repositorio){
        this.logado= logado;
        this.repositorio = repositorio;
    }

    @FXML
    void cadastrarAtividade(ActionEvent event){
        App.pushScreen("CADASTRAATIVIDADE");
    }

    
    @FXML
    void sair(ActionEvent event) {
        logado.logout();
        App.pegaLogado(logado);
        App.pushScreen("PRINCIPAL");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        nomeProfessor.setText(repositorio.getNomeLogado(logado));
    }
}
