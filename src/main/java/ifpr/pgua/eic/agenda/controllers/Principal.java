package ifpr.pgua.eic.agenda.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.App;
import ifpr.pgua.eic.agenda.model.daos.ServicoLoginDAO;
import ifpr.pgua.eic.agenda.model.entities.ServicoLogin;
import ifpr.pgua.eic.agenda.model.entities.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
public class Principal implements Initializable{
    @FXML
    private ComboBox<String> cbTipoUser;

    private ArrayList<String> lista = new ArrayList<>();
    private ObservableList<String> obsTipoUser;

    @FXML
    private TextField tfSenha;

    @FXML
    private TextField tfUser;

    private ServicoLogin logado;
    private ServicoLoginDAO dao;
    private Usuario usuario;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        preencherComboBox();
    }

    public Principal(ServicoLoginDAO dao, ServicoLogin logado){
        this.dao = dao;
        this.logado = logado;
    }

    private void preencherComboBox(){
        String tipoAluno = "Aluno";
        String tipoProfessor = "Professor";
        String tipoCoordenador = "Coordenador";

        lista.add(tipoCoordenador);
        lista.add(tipoProfessor);
        lista.add(tipoAluno);

        obsTipoUser = FXCollections.observableArrayList(lista);
        cbTipoUser.setItems(obsTipoUser);
    }

    /**
     * @param event
     */
    @FXML
    void logar(ActionEvent event) {
        String login = tfUser.getText();
        String senha = tfSenha.getText();

        usuario = new Usuario(login, senha);
        //ServicoLogin logado = new ServicoLogin(usuario, dao);
        //dao.validaUsuario(login, senha)
        System.out.println("usuario: "+usuario.getLogin());
        System.out.println("senha: "+usuario.getSenha());

        Resultado resultado = dao.validaUsuario(login, senha);
        
        Alert alert;

        if(resultado.foiErro()){
            alert = new Alert(AlertType.ERROR, resultado.getMsg());
            alert.showAndWait();
        }else{
            alert = new Alert(AlertType.INFORMATION, resultado.getMsg());
            System.out.println("usuario: "+usuario.getLogin());
            logado = new ServicoLogin(usuario, dao);
            App.pegaLogado(logado);
            if(cbTipoUser.getValue().equals("Aluno")){
                App.pushScreen("PRINCIPALALUNO");
                alert.showAndWait();
            }else if(cbTipoUser.getValue().equals("Professor")){
                App.pushScreen("PRINCIPALPROFESSOR");
                alert.showAndWait();
            }else if(cbTipoUser.getValue().equals("Coordenador")){
                App.pushScreen("PRINCIPALCOORDENADOR");
                alert.showAndWait();
            }
        }

    }

    
}
