package ifpr.pgua.eic.agenda.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.App;
import ifpr.pgua.eic.agenda.model.entities.Professor;
import ifpr.pgua.eic.agenda.model.entities.ServicoLogin;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioAtividades;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioProfessor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CadastrarAtividade implements Initializable{
    @FXML
    private DatePicker dtPrazo;

    @FXML
    private TextField tfDescricao;

    @FXML
    private TextField tfNome;

    @FXML
    private CheckBox cbAvaliada;

    @FXML
    private Label lbNome;

    private RepositorioAtividades repositorio;
    private RepositorioProfessor repositorioProfessor;
    private ServicoLogin logado;
    
    public CadastrarAtividade(RepositorioAtividades repositorio, RepositorioProfessor repositorioProfessor, ServicoLogin logado) {
        this.repositorio = repositorio;
        this.repositorioProfessor = repositorioProfessor;
        this.logado = logado;
    }

    @FXML
    void cadastrar(ActionEvent event) {
        String nome = tfNome.getText();
        String descricao = tfDescricao.getText();
        LocalDate dataPicker = dtPrazo.getValue();
        Boolean avaliada = cbAvaliada.isSelected();
        
        Professor professor = repositorioProfessor.getLogado(logado);

        Resultado resultado = repositorio.cadastrarAtividade(nome, descricao, dataPicker, avaliada, professor);
        
        Alert alert;

        if(resultado.foiErro()){
            alert = new Alert(AlertType.ERROR, resultado.getMsg());
        }else{
            alert = new Alert(AlertType.INFORMATION, resultado.getMsg());
        }

        alert.showAndWait();
    }

    @FXML
    void cancelar(ActionEvent event) {
        App.popScreen();
    }

    @FXML
    void sair(ActionEvent event) {
        App.pushScreen("PRINCIPAL");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lbNome.setText(repositorioProfessor.getNomeLogado(logado));
    }
}
