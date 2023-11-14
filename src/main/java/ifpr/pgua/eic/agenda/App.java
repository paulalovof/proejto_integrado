package ifpr.pgua.eic.agenda;

import ifpr.pgua.eic.agenda.controllers.CadastrarAnotacao;
import ifpr.pgua.eic.agenda.controllers.CadastrarAtividade;
import ifpr.pgua.eic.agenda.controllers.CadastrarEvento;
import ifpr.pgua.eic.agenda.controllers.Principal;
import ifpr.pgua.eic.agenda.controllers.PrincipalAluno;
import ifpr.pgua.eic.agenda.controllers.PrincipalCoordenador;
import ifpr.pgua.eic.agenda.controllers.PrincipalProfessor;
import ifpr.pgua.eic.agenda.model.daos.AlunoDAO;
import ifpr.pgua.eic.agenda.model.daos.AnotacoesDAO;
import ifpr.pgua.eic.agenda.model.daos.AtividadesDAO;
import ifpr.pgua.eic.agenda.model.daos.CoordenadorDAO;
import ifpr.pgua.eic.agenda.model.daos.EventosDAO;
import ifpr.pgua.eic.agenda.model.daos.FabricaConexoes;
import ifpr.pgua.eic.agenda.model.daos.JDBCAlunoDAO;
import ifpr.pgua.eic.agenda.model.daos.JDBCAnotacoesDAO;
import ifpr.pgua.eic.agenda.model.daos.JDBCAtividadesDAO;
import ifpr.pgua.eic.agenda.model.daos.JDBCCoordenadorDAO;
import ifpr.pgua.eic.agenda.model.daos.JDBCEventosDAO;
import ifpr.pgua.eic.agenda.model.daos.JDBCProfessorDAO;
import ifpr.pgua.eic.agenda.model.daos.ProfessorDAO;
import ifpr.pgua.eic.agenda.model.entities.Professor;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioAluno;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioAnotacoes;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioAtividades;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioCoordenador;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioEventos;
import ifpr.pgua.eic.agenda.model.repositories.RepositorioProfessor;
import io.github.hugoperlin.navigatorfx.BaseAppNavigator;
import io.github.hugoperlin.navigatorfx.ScreenRegistryFXML;

/**
 * JavaFX App
 */
public class App extends BaseAppNavigator {
    
    
    private AnotacoesDAO anotacoesDAO = new JDBCAnotacoesDAO(FabricaConexoes.getInstance());
    private AlunoDAO alunoDAO = new JDBCAlunoDAO(FabricaConexoes.getInstance());
    private RepositorioAnotacoes repositorioAnotacoes = new RepositorioAnotacoes(anotacoesDAO, alunoDAO);
    private RepositorioAluno repositorioAluno = new RepositorioAluno(alunoDAO);

    private AtividadesDAO atividadesDAO = new JDBCAtividadesDAO(FabricaConexoes.getInstance());
    private ProfessorDAO professorDAO = new JDBCProfessorDAO(FabricaConexoes.getInstance());
    private RepositorioAtividades repositorioAtividades = new RepositorioAtividades(atividadesDAO, professorDAO);
    private RepositorioProfessor repositorioProfessor= new RepositorioProfessor(professorDAO);


    private EventosDAO eventosDAO = new JDBCEventosDAO(FabricaConexoes.getInstance());
    private CoordenadorDAO coordenadorDAO = new JDBCCoordenadorDAO(FabricaConexoes.getInstance());
    private RepositorioEventos repositorioEventos = new RepositorioEventos(eventosDAO, coordenadorDAO);
    private RepositorioCoordenador repositorioCoordenador = new RepositorioCoordenador(coordenadorDAO);

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
        registraTela("CADASTRAANOTACAO", new ScreenRegistryFXML(App.class, "cadastrar_anotacao", o-> new CadastrarAnotacao(repositorioAnotacoes, repositorioAluno)));
        registraTela("CADASTRAATIVIDADE", new ScreenRegistryFXML(App.class, "cadastrar_atividade", o-> new CadastrarAtividade(repositorioAtividades, repositorioProfessor)));
        registraTela("CADASTRAEVENTO", new ScreenRegistryFXML(App.class, "cadastrar_evento", o-> new CadastrarEvento(repositorioEventos, repositorioCoordenador)));
        
    }

}