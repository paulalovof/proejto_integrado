package ifpr.pgua.eic.agenda.controllers;

import java.time.LocalDate;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.App;
import ifpr.pgua.eic.agenda.model.entities.Atividades;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioAnotacoes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class PrincipalAluno {

    @FXML
    private TextField idAluno;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfDescricao;

    @FXML
    private DatePicker data;

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
}
