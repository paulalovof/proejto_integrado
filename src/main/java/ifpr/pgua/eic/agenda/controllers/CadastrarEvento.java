package ifpr.pgua.eic.agenda.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.App;
import ifpr.pgua.eic.agenda.model.entities.Coordenador;
import ifpr.pgua.eic.agenda.model.entities.Eventos;
import ifpr.pgua.eic.agenda.model.entities.ServicoLogin;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioCoordenador;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioEventos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CadastrarEvento implements Initializable{
    @FXML
    private DatePicker dtPrazo;

    @FXML
    private TextField tfDescricao;

    @FXML
    private TextField tfNome;

    @FXML
    private Label lbNome;

    @FXML
    private Label lbEvento;

    private RepositorioEventos repositorio;
    private RepositorioCoordenador repositorioCoordenador; 
    private ServicoLogin logado;

    private Eventos antigo;
    
    public CadastrarEvento(RepositorioEventos repositorio, RepositorioCoordenador repositorioCoordenador, ServicoLogin logado) {
        this.repositorio = repositorio;
        this.repositorioCoordenador = repositorioCoordenador;
        this.logado = logado;
    }

    public CadastrarEvento(RepositorioEventos repositorio, RepositorioCoordenador repositorioCoordenador, ServicoLogin logado, Eventos evento) {
        this.repositorio = repositorio;
        this.repositorioCoordenador = repositorioCoordenador;
        this.logado = logado;
        this.antigo = evento;
    }

    @FXML
    void cadastrar(ActionEvent event) {
        String nome = tfNome.getText();
        String descricao = tfDescricao.getText();
        LocalDate dataPicker = dtPrazo.getValue();
        
        Coordenador coordenador = repositorioCoordenador.getLogado(logado);

        Resultado resultado;

        if(antigo == null){
            resultado = repositorio.cadastrarEvento(nome, descricao, dataPicker, coordenador);
        }else{
            resultado = repositorio.atualizarEvento(antigo.getIdEvento(), nome, descricao, dataPicker, coordenador);
        }
        
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
        if(antigo != null){
            App.pushScreen("VISUALIZAEVENTO");
        }
    }

    @FXML
    void sair(ActionEvent event) {
        App.pushScreen("PRINCIPAL");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lbNome.setText(repositorioCoordenador.getNomeLogado(logado));
        if(antigo != null){
            tfNome.setText(antigo.getNome());
            tfDescricao.setText(antigo.getDescricao());
            dtPrazo.setValue(antigo.getData());
            lbEvento.setText("Editar Evento");
        }
    }
}
