package ifpr.pgua.eic.agenda.controllers;

import ifpr.pgua.eic.agenda.App;
import ifpr.pgua.eic.agenda.model.entities.Atividades;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class PrincipalAluno {
       @FXML
    private ListView<Atividades> lstAtividadesProximas;

    @FXML
    void sair(ActionEvent event) {
        App.popScreen();
    }
}
