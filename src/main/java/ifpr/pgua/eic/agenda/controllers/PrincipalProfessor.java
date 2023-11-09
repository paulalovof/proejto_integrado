package ifpr.pgua.eic.agenda.controllers;

import java.time.LocalDate;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.App;
import ifpr.pgua.eic.agenda.model.entities.Atividades;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioAtividades;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class PrincipalProfessor {
    
    private RepositorioAtividades repositorio;

    @FXML
    private TextField idProfessor;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfDescricao;

    @FXML
    private DatePicker data;
    
    @FXML
    private ListView<Atividades> lstAtividadesAvaliadas;

    //nao sei se aqui passa o idAluno ou o aluno inteiro...
    @FXML
    void cadastrarAtividade(ActionEvent event){
        String nome = tfNome.getText();
        String descricao = tfDescricao.getText();
        LocalDate dataPicker = data.getValue();

        Resultado resultado = repositorio.cadastrarAtividade(nome, descricao, dataPicker);
        
        Alert alert;

        if(resultado.foiErro()){
            alert = new Alert(AlertType.ERROR, resultado.getMsg());
        }else{
            alert = new Alert(AlertType.INFORMATION, resultado.getMsg());
        }

        alert.showAndWait();
    }

    
    @FXML
    void sair(ActionEvent event) {
        App.popScreen();
    }
}
