package ifpr.pgua.eic.agenda.controllers;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.App;
import ifpr.pgua.eic.agenda.model.entities.Atividades;
import ifpr.pgua.eic.agenda.model.entities.ServicoLogin;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioAtividades;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioProfessor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
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
        Atividades atividade = lstAtividades.getSelectionModel().getSelectedItem();

        Alert alert;
        if(atividade != null){
            App.pushScreen("CADASTRAATIVIDADE", o -> new CadastrarAtividade(repositorioAtividades, repositorioProfessor, logado, atividade));
        }else{
            alert = new Alert(AlertType.WARNING, "Selecione uma Atividade para editar!");
            alert.showAndWait();
        }
    }

    @FXML
    void excluir(ActionEvent event) {
        Atividades atividade = lstAtividades.getSelectionModel().getSelectedItem();

        Alert alert;
        if(atividade != null){
                alert = new Alert(AlertType.INFORMATION, "Tem certeza que deseja exlcuir essa atividade?", ButtonType.OK, ButtonType.CANCEL);
                Optional<ButtonType> result = alert.showAndWait();

                if(result.get() == ButtonType.OK){
                    Resultado res = repositorioAtividades.deletar(atividade.getIdAtividade());
                    alert = new Alert(AlertType.INFORMATION, res.getMsg());
                    App.pushScreen("VISUALIZAATIVIDADE");
                    alert.showAndWait();
                }
            }else{
                alert = new Alert(AlertType.WARNING, "Selecione uma Atividade para excluir!");
                alert.showAndWait();
            }
    }

    @FXML
    void sair(ActionEvent event) {
        App.pushScreen("PRINCIPAL");
    }

    @FXML
    void voltar(ActionEvent event) {
        App.pushScreen("PRINCIPALPROFESSOR");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lbNome.setText(repositorioProfessor.getNomeLogado(logado));

        lstAtividades.getItems().clear();

        Resultado res = repositorioAtividades.listarAtividades(repositorioProfessor.getLogado(logado).getIdProfessor());
        
        Alert alert;
        if(res.foiSucesso()){
            List<Atividades> lista = (List)res.comoSucesso().getObj();
            lstAtividades.getItems().addAll(lista);
        }else{
            alert = new Alert(AlertType.ERROR, res.getMsg());
            alert.showAndWait();
        }
    }
}