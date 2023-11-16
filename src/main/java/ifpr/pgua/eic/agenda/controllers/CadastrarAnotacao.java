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
    
    public CadastrarAnotacao(RepositorioAnotacoes repositorio, RepositorioAluno repositorioAluno) {
        this.repositorio = repositorio;
        this.repositorioAluno = repositorioAluno;
    }

    @FXML
    void cadastrar(ActionEvent event) {
        String nome = tfNome.getText();
        String descricao = tfDescricao.getText();
        LocalDate dataPicker = dtPrazo.getValue();
        //Aluno aluno = repositorioAluno.getLogado(0);
        //aqui tem que dar um jeito de pegar o aluno logado
        //String login = logado.getLogin();
        //String senha = logado.getSenha();
        //String numero = logado.getNumero();

        //Aluno aluno = new Aluno(nome, numero);
        Resultado resultado = repositorio.cadastrarAnotacao(nome, descricao, dataPicker, null);
        
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
