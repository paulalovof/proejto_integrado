package ifpr.pgua.eic.agenda.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.agenda.App;
import ifpr.pgua.eic.agenda.model.entities.Atividades;
import ifpr.pgua.eic.agenda.model.entities.ServicoLogin;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioAtividades;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioProfessor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class VisualizarAtividade implements Initializable{
    @FXML
    private Label lbNome;

    @FXML
    private ListView<Atividades> lstAtividades;

    private RepositorioAtividades repositorioAtividades;
    private RepositorioProfessor repositorioProfessor;
    private ServicoLogin logado;

    public VisualizarAtividade(RepositorioAtividades repositorioAtividades, RepositorioProfessor repositorioProfessor, ServicoLogin logado){
        this.repositorioAtividades = repositorioAtividades;
        this.repositorioProfessor = repositorioProfessor;
        this.logado = logado;
    }

    @FXML
    void editar(ActionEvent event) {

    }

    @FXML
    void excluir(ActionEvent event) {

    }

    @FXML
    void sair(ActionEvent event) {
        App.pushScreen("PRINCIPAL");
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lbNome.setText(repositorioProfessor.getNomeLogado(logado));
    }
}