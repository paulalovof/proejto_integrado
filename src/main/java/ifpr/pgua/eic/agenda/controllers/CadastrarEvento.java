package ifpr.pgua.eic.agenda.controllers;

import java.time.LocalDate;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.App;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioCoordenador;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioEventos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class CadastrarEvento {
    @FXML
    private DatePicker dtPrazo;

    @FXML
    private TextField tfDescricao;

    @FXML
    private TextField tfNome;

    private RepositorioEventos repositorio;
    private RepositorioCoordenador repositorioCoordenador; 
    
    public CadastrarEvento(RepositorioEventos repositorio, RepositorioCoordenador repositorioCoordenador) {
        this.repositorio = repositorio;
        this.repositorioCoordenador = repositorioCoordenador;
    }

    @FXML
    void cadastrar(ActionEvent event) {
        String nome = tfNome.getText();
        String descricao = tfDescricao.getText();
        LocalDate dataPicker = dtPrazo.getValue();
        //Coordenador coordenador = repositorioCoordenador.getLogado();
        //como pegar o usuario que esta logado?

        Resultado resultado = repositorio.cadastrarEvento(nome, descricao, dataPicker, null);
        
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
}
