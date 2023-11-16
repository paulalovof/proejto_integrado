package ifpr.pgua.eic.agenda.controllers;

import java.time.LocalDate;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.App;
import ifpr.pgua.eic.agenda.model.entities.Aluno;
import ifpr.pgua.eic.agenda.model.entities.ServicoLogin;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioAluno;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioAnotacoes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class CadastrarAnotacao {
    @FXML
    private DatePicker dtPrazo;

    @FXML
    private TextField tfDescricao;

    @FXML
    private TextField tfNome;

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
}
