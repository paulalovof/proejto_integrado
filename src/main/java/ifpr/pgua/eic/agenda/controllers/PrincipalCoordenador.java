package ifpr.pgua.eic.agenda.controllers;

import ifpr.pgua.eic.agenda.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class PrincipalCoordenador {

    @FXML
    void cadastrarEvento(ActionEvent event){
        App.pushScreen("CADASTRAEVENTO");
    }

    
    @FXML
    void sair(ActionEvent event) {
        App.popScreen();
    }
}
