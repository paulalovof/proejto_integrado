package ifpr.pgua.eic.agenda.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import ifpr.pgua.eic.agenda.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
public class Principal implements Initializable{
    @FXML
    private ComboBox<String> cbTipoUser;

    private ArrayList<String> lista;
    private ObservableList<String> obsTipoUser;

    @FXML
    private TextField tfSenha;

    @FXML
    private TextField tfUser;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        preencherComboBox();
    }


    private void preencherComboBox(){
        String tipoAluno = "Aluno";
        String tipoProfessor = "Professor";
        String tipoCoordenador = "Coordenador";

        lista.add(tipoCoordenador);
        lista.add(tipoProfessor);
        lista.add(tipoAluno);

        obsTipoUser = FXCollections.observableArrayList(lista);
        cbTipoUser.setItems(obsTipoUser);
    }

    @FXML
    void logar(ActionEvent event) {
        if(cbTipoUser.getValue().equals("Aluno")){
            //verificar se o login e senha existem no banco
            App.pushScreen("PRINCIPALALUNO");
        }else if(cbTipoUser.getValue().equals("Professor")){
            //verificar se o login e senha existem no banco
            App.pushScreen("PRINCIPALPROFESSOR");
        }else if(cbTipoUser.getValue().equals("Coordenador")){
            //verificar se o login e senha existem no banco
            App.pushScreen("PRINCIPALCOORDENADOR");
        }
        App.pushScreen("PRINCIPAL");
    }

    
}
