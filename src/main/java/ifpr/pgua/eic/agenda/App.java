package ifpr.pgua.eic.agenda;

import ifpr.pgua.eic.agenda.controllers.CadastrarAnotacao;
import ifpr.pgua.eic.agenda.controllers.CadastrarAtividade;
import ifpr.pgua.eic.agenda.controllers.CadastrarEvento;
import ifpr.pgua.eic.agenda.controllers.Principal;
import ifpr.pgua.eic.agenda.controllers.PrincipalAluno;
import ifpr.pgua.eic.agenda.controllers.PrincipalCoordenador;
import ifpr.pgua.eic.agenda.controllers.PrincipalProfessor;
import ifpr.pgua.eic.agenda.model.daos.AnotacoesDAO;
import ifpr.pgua.eic.agenda.model.daos.AtividadesDAO;
import ifpr.pgua.eic.agenda.model.daos.EventosDAO;
import ifpr.pgua.eic.agenda.model.daos.FabricaConexoes;
import ifpr.pgua.eic.agenda.model.daos.JDBCAnotacoesDAO;
import ifpr.pgua.eic.agenda.model.daos.JDBCAtividadesDAO;
import ifpr.pgua.eic.agenda.model.daos.JDBCEventosDAO;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioAnotacoes;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioAtividades;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioEventos;
import io.github.hugoperlin.navigatorfx.BaseAppNavigator;
import io.github.hugoperlin.navigatorfx.ScreenRegistryFXML;

/**
 * JavaFX App
 */
public class App extends BaseAppNavigator {
    
    
    private AnotacoesDAO anotacoesDAO = new JDBCAnotacoesDAO(FabricaConexoes.getInstance());
    private RepositorioAnotacoes repositorioAnotacoes = new RepositorioAnotacoes(anotacoesDAO);

    private AtividadesDAO atividadesDAO = new JDBCAtividadesDAO(FabricaConexoes.getInstance());
    private RepositorioAtividades repositorioAtividades = new RepositorioAtividades(atividadesDAO);

    private EventosDAO eventosDAO = new JDBCEventosDAO(FabricaConexoes.getInstance());
    private RepositorioEventos repositorioEventos = new RepositorioEventos(eventosDAO);

    public static void main(String[] args) {
        launch();
    }

    @Override
    public String getHome() {
        // TODO Auto-generated method stub
        return "PRINCIPAL";
    }


    @Override
    public String getAppTitle() {
        // TODO Auto-generated method stub
        return "Agenda";
    }

    @Override
    public void registrarTelas() {
        registraTela("PRINCIPAL", new ScreenRegistryFXML(App.class, "principal.fxml", o->new Principal()));
        registraTela("PRINCIPALALUNO", new ScreenRegistryFXML(App.class, "principal_aluno.fxml", o->new PrincipalAluno()));
        registraTela("PRINCIPALCOORDENADOR", new ScreenRegistryFXML(App.class, "principal_coordenador.fxml", o->new PrincipalCoordenador()));
        registraTela("PRINCIPALPROFESSOR", new ScreenRegistryFXML(App.class, "principal_professor.fxml", o->new PrincipalProfessor()));
        registraTela("CADASTRAANOTACAO", new ScreenRegistryFXML(App.class, "cadastrar_anotacao", o-> new CadastrarAnotacao(repositorioAnotacoes)));
        registraTela("CADASTRAATIVIDADE", new ScreenRegistryFXML(App.class, "cadastrar_atividade", o-> new CadastrarAtividade(repositorioAtividades)));
        registraTela("CADASTRAEVENTO", new ScreenRegistryFXML(App.class, "cadastrar_evento", o-> new CadastrarEvento(repositorioEventos)));
        
    }

}