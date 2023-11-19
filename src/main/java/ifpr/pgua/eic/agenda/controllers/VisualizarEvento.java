package ifpr.pgua.eic.agenda.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.agenda.App;
import ifpr.pgua.eic.agenda.model.entities.Eventos;
import ifpr.pgua.eic.agenda.model.entities.ServicoLogin;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioCoordenador;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioEventos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class VisualizarEvento implements Initializable{
    @FXML
    private Label lbNome;

    @FXML
    private ListView<Eventos> lstEventos;

    private RepositorioEventos repositorioEventos;
    private RepositorioCoordenador repositorioCoordenador;
    private ServicoLogin logado;

    public VisualizarEvento(RepositorioEventos repositorioEventos, RepositorioCoordenador repositorioCoordenador, ServicoLogin logado){
        this.repositorioEventos = repositorioEventos;
        this.repositorioCoordenador = repositorioCoordenador;
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
        lbNome.setText(repositorioCoordenador.getNomeLogado(logado));
    }
}
