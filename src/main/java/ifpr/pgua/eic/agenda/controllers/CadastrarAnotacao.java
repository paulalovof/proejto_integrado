package ifpr.pgua.eic.agenda.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.App;
import ifpr.pgua.eic.agenda.model.entities.Aluno;
import ifpr.pgua.eic.agenda.model.entities.ServicoLogin;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioAluno;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioAnotacoes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CadastrarAnotacao implements Initializable{
    @FXML
    private DatePicker dtPrazo;

    @FXML
    private TextField tfDescricao;

    @FXML
    private TextField tfNome;

    @FXML
    private Label nomeAluno;

    private RepositorioAnotacoes repositorio;
    private RepositorioAluno repositorioAluno;
    private ServicoLogin logado;
    
    public CadastrarAnotacao(RepositorioAnotacoes repositorio, RepositorioAluno repositorioAluno, ServicoLogin logado) {
        this.repositorio = repositorio;
        this.repositorioAluno = repositorioAluno;
        this.logado = logado;
    }

    @FXML
    void cadastrar(ActionEvent event) {
        String nome = tfNome.getText();
        String descricao = tfDescricao.getText();
        LocalDate dataPicker = dtPrazo.getValue();
        
        Aluno aluno = repositorioAluno.getLogado(logado);

        Resultado resultado = repositorio.cadastrarAnotacao(nome, descricao, dataPicker, aluno);
        
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
        nomeAluno.setText(repositorioAluno.getNomeLogado(logado));
    }
}
