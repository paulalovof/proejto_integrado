package ifpr.pgua.eic.agenda.controllers;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.App;
import ifpr.pgua.eic.agenda.model.entities.Eventos;
import ifpr.pgua.eic.agenda.model.entities.ServicoLogin;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioCoordenador;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioEventos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
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
        Eventos evento = lstEventos.getSelectionModel().getSelectedItem();

        Alert alert;
        if(evento != null){
            App.pushScreen("CADASTRAEVENTO", o -> new CadastrarEvento(repositorioEventos, repositorioCoordenador, logado, evento));
        }else{
            alert = new Alert(AlertType.WARNING, "Selecione um Evento para editar!");
            alert.showAndWait();
        }
    }

    @FXML
    void excluir(ActionEvent event) {
        Eventos evento = lstEventos.getSelectionModel().getSelectedItem();

        Alert alert;
        if(evento != null){
                alert = new Alert(AlertType.INFORMATION, "Tem certeza que deseja exlcuir essa atividade?", ButtonType.OK, ButtonType.CANCEL);
                Optional<ButtonType> result = alert.showAndWait();

                if(result.get() == ButtonType.OK){
                    Resultado res = repositorioEventos.deletar(evento.getIdEvento());
                    alert = new Alert(AlertType.INFORMATION, res.getMsg());
                    App.pushScreen("VISUALIZAEVENTO");
                    alert.showAndWait();
                }
            }else{
                alert = new Alert(AlertType.WARNING, "Selecione uma Evento para excluir!");
                alert.showAndWait();
            }
    }

    @FXML
    void sair(ActionEvent event) {
        App.pushScreen("PRINCIPAL");
    }

    @FXML
    void voltar(ActionEvent event) {
        App.pushScreen("PRINCIPALCOORDENADOR");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lbNome.setText(repositorioCoordenador.getNomeLogado(logado));

        lstEventos.getItems().clear();

        Resultado res = repositorioEventos.listarEventos(repositorioCoordenador.getLogado(logado).getIdCoordenador());
        
        Alert alert;
        if(res.foiSucesso()){
            List<Eventos> lista = (List)res.comoSucesso().getObj();
            lstEventos.getItems().addAll(lista);
        }else{
            alert = new Alert(AlertType.ERROR, res.getMsg());
            alert.showAndWait();
        }
    }
}
