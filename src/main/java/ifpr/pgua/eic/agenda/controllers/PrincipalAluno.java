package ifpr.pgua.eic.agenda.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.agenda.App;
import ifpr.pgua.eic.agenda.model.entities.Atividades;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;


public class PrincipalAluno implements Initializable{
    @FXML
    private ListView<Atividades> lstAtividadesProximas;

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
        //inicializar a lista com atividades proximas 5 dias
    }
}
