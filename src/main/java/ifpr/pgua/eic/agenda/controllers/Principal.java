package ifpr.pgua.eic.agenda.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import ifpr.pgua.eic.agenda.App;
import ifpr.pgua.eic.agenda.model.daos.AlunoDAO;
import ifpr.pgua.eic.agenda.model.daos.CoordenadorDAO;
import ifpr.pgua.eic.agenda.model.daos.ProfessorDAO;
import ifpr.pgua.eic.agenda.model.daos.ServicoLoginDAO;
import ifpr.pgua.eic.agenda.model.entities.Coordenador;
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

    private AlunoDAO alunoDAO;
    private CoordenadorDAO coordenadorDAO;
    private ProfessorDAO professorDAO;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        preencherComboBox();
        tfUser.clear();
        tfSenha.clear();
    }

    public Principal(ServicoLoginDAO dao, ServicoLogin logado, AlunoDAO alunoDAO, CoordenadorDAO coordenadorDAO, ProfessorDAO professorDAO){
        this.dao = dao;
        this.logado = logado;
        this.alunoDAO = alunoDAO;
        this.coordenadorDAO = coordenadorDAO;
        this.professorDAO = professorDAO;
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

        System.out.println("usuario: "+usuario.getLogin());
        System.out.println("senha: "+usuario.getSenha());

        usuario = dao.validaUsuario(login, senha);
        
        Alert alert;

        if(usuario == null){
            alert = new Alert(AlertType.ERROR);
            alert.showAndWait();
        }else{
            alert = new Alert(AlertType.INFORMATION);
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
