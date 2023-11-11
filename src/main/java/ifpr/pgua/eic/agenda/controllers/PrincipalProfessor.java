package ifpr.pgua.eic.agenda.controllers;

import ifpr.pgua.eic.agenda.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class PrincipalProfessor {

    @FXML
    void cadastrarAtividade(ActionEvent event){
        App.pushScreen("CADASTRAATIVIDADE");
    }

    
    @FXML
    void sair(ActionEvent event) {
        App.popScreen();
    }
}
