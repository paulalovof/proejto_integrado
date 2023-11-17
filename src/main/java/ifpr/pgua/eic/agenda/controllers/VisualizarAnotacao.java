package ifpr.pgua.eic.agenda.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.App;
import ifpr.pgua.eic.agenda.model.entities.Anotacoes;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioAnotacoes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;

public class VisualizarAnotacao implements Initializable {

    @FXML
    private ListView<Anotacoes> lstAnotacoes;

    private RepositorioAnotacoes repositorio;

    public VisualizarAnotacao(RepositorioAnotacoes repositorio) {
        this.repositorio = repositorio;
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Resultado resultado = repositorio.listarAnotacoes();

        if (resultado.foiErro()) {
            Alert alert = new Alert(AlertType.ERROR, resultado.getMsg());
            alert.showAndWait();
        } else {
            List lista = (List) resultado.comoSucesso().getObj();
            lstAnotacoes.getItems().addAll(lista);
        }
    }

}