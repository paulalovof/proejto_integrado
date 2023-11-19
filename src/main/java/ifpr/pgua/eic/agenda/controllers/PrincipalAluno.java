package ifpr.pgua.eic.agenda.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.App;
import ifpr.pgua.eic.agenda.model.entities.Atividades;
import ifpr.pgua.eic.agenda.model.entities.ServicoLogin;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioAluno;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioAtividades;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;


public class PrincipalAluno implements Initializable{
    @FXML
    private ListView<Atividades> lstAtividadesProximas;

    @FXML
    private Label nomeAluno;

    private ServicoLogin logado;
    private RepositorioAluno repositorio;
    private RepositorioAtividades repositorioAtividades;

    public PrincipalAluno(ServicoLogin logado, RepositorioAluno repositorio, RepositorioAtividades repositorioAtividades){
        this.logado = logado;
        this.repositorio = repositorio;
        this.repositorioAtividades = repositorioAtividades;
    }
    
    @FXML
    void cadastrarAnotacao(ActionEvent event){
        App.pushScreen("CADASTRAANOTACAO");
    }

    @FXML
    void sair(ActionEvent event) {
        logado.logout();
        App.pegaLogado(logado);
        App.pushScreen("PRINCIPAL");
    }

    @FXML
    void visualizar(ActionEvent event) {
        App.pushScreen("VISUALIZAANOTACAO");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        nomeAluno.setText(repositorio.getNomeLogado(logado));

        lstAtividadesProximas.getItems().clear();
        Resultado res = repositorioAtividades.listarAtividadesProximas();

         if(res.foiSucesso()){
            List<Atividades> lista = (List)res.comoSucesso().getObj();
            if(lista.isEmpty()){
                Atividades atividade = new Atividades(0, null, "Não há atividades proximas!", null, null, false);
                lstAtividadesProximas.getItems().add(atividade);
            }else{
                lstAtividadesProximas.getItems().addAll(lista);
            }
         }
    }
}
