package ifpr.pgua.eic.agenda.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.App;
import ifpr.pgua.eic.agenda.model.entities.Anotacoes;
import ifpr.pgua.eic.agenda.model.entities.Atividades;
import ifpr.pgua.eic.agenda.model.entities.Eventos;
import ifpr.pgua.eic.agenda.model.entities.ServicoLogin;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioAluno;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioAnotacoes;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioAtividades;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioEventos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class VisualizarAnotacao implements Initializable{
    @FXML
    private ListView<Anotacoes> lstAnotacoes;

    @FXML
    private Label nomeAluno;

    @FXML
    private ListView<Atividades> lstAtividades;

    @FXML
    private ListView<Eventos> lstEventos;

    private RepositorioAluno repositorioAluno;
    private RepositorioAnotacoes repositorioAnotacoes;
    private RepositorioAtividades repositorioAtividades;
    private RepositorioEventos repositorioEventos;
    private ServicoLogin logado;

    public VisualizarAnotacao(RepositorioAluno repositorioAluno, RepositorioAnotacoes repositorioAnotacoes, ServicoLogin logado,RepositorioAtividades repositorioAtividades,RepositorioEventos repositorioEventos){
        this.repositorioAluno = repositorioAluno;
        this.repositorioAnotacoes = repositorioAnotacoes;
        this.logado = logado;
        this.repositorioAtividades = repositorioAtividades;
        this.repositorioEventos = repositorioEventos;
    }

    @FXML
    void editar(ActionEvent event) {
        App.pushScreen(null);
    }

    @FXML
    void excluir(ActionEvent event) {
        //tem certeza?
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
        lstAnotacoes.getItems().clear();
        lstEventos.getItems().clear();
        lstAtividades.getItems().clear();

        nomeAluno.setText(repositorioAluno.getNomeLogado(logado));

        Resultado res = repositorioAnotacoes.listarAnotacoes(repositorioAluno.getLogado(logado).getIdAluno());
        Resultado res1 = repositorioEventos.listarEventos(0);
        Resultado res2 = repositorioAtividades.listarAtividades(0);

        Alert alert;
        if(res.foiSucesso() && res1.foiSucesso()){

            List<Anotacoes> lista = (List)res.comoSucesso().getObj();
            lstAnotacoes.getItems().addAll(lista);

            List<Eventos> lista1 = (List)res1.comoSucesso().getObj();
            lstEventos.getItems().addAll(lista1);

            List<Atividades> lista2 = (List)res2.comoSucesso().getObj();
            lstAtividades.getItems().addAll(lista2);

            alert = new Alert(AlertType.INFORMATION, res.getMsg());
        }else{
            alert = new Alert(AlertType.ERROR, res.getMsg());
        }

         alert.showAndWait();
    }
}
