package ifpr.pgua.eic.agenda.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.agenda.App;
import ifpr.pgua.eic.agenda.model.entities.Atividades;
import ifpr.pgua.eic.agenda.model.entities.ServicoLogin;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioAluno;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;


public class PrincipalAluno implements Initializable{
    @FXML
    private ListView<Atividades> lstAtividadesProximas;

    private ServicoLogin logado;

    public PrincipalAluno(ServicoLogin logado){
        this.logado = logado;
    }

    @FXML
    void cadastrarAnotacao(ActionEvent event){
        App.pushScreen("CADASTRAANOTACAO");
    }

    @FXML
    void sair(ActionEvent event) {
        App.popScreen();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("usuario: "+logado.getLogin());
    }
}
