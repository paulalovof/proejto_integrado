package ifpr.pgua.eic.agenda;

import ifpr.pgua.eic.agenda.controllers.CadastrarAnotacao;
import ifpr.pgua.eic.agenda.controllers.CadastrarAtividade;
import ifpr.pgua.eic.agenda.controllers.CadastrarEvento;
import ifpr.pgua.eic.agenda.controllers.Principal;
import ifpr.pgua.eic.agenda.controllers.PrincipalAluno;
import ifpr.pgua.eic.agenda.controllers.PrincipalCoordenador;
import ifpr.pgua.eic.agenda.controllers.PrincipalProfessor;
import ifpr.pgua.eic.agenda.controllers.VisualizarAnotacao;
import ifpr.pgua.eic.agenda.controllers.VisualizarAtividade;
import ifpr.pgua.eic.agenda.controllers.VisualizarEvento;
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
import ifpr.pgua.eic.agenda.model.daos.JDBCServicoLoginDAO;
import ifpr.pgua.eic.agenda.model.daos.ProfessorDAO;
import ifpr.pgua.eic.agenda.model.daos.ServicoLoginDAO;
import ifpr.pgua.eic.agenda.model.entities.ServicoLogin;
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

    private ServicoLoginDAO servicoLoginDAO = new JDBCServicoLoginDAO(FabricaConexoes.getInstance());
    private static ServicoLogin servicoLogin = null;

    private AnotacoesDAO anotacoesDAO = new JDBCAnotacoesDAO(FabricaConexoes.getInstance());
    private AlunoDAO alunoDAO = new JDBCAlunoDAO(FabricaConexoes.getInstance());
    private RepositorioAnotacoes repositorioAnotacoes = new RepositorioAnotacoes(anotacoesDAO, alunoDAO);
    private RepositorioAluno repositorioAluno = new RepositorioAluno(alunoDAO, servicoLogin);

    private AtividadesDAO atividadesDAO = new JDBCAtividadesDAO(FabricaConexoes.getInstance());
    private ProfessorDAO professorDAO = new JDBCProfessorDAO(FabricaConexoes.getInstance());
    private RepositorioAtividades repositorioAtividades = new RepositorioAtividades(atividadesDAO, professorDAO);
    private RepositorioProfessor repositorioProfessor = new RepositorioProfessor(professorDAO, servicoLogin);

    private EventosDAO eventosDAO = new JDBCEventosDAO(FabricaConexoes.getInstance());
    private CoordenadorDAO coordenadorDAO = new JDBCCoordenadorDAO(FabricaConexoes.getInstance());
    private RepositorioEventos repositorioEventos = new RepositorioEventos(eventosDAO, coordenadorDAO);
    private RepositorioCoordenador repositorioCoordenador = new RepositorioCoordenador(coordenadorDAO, servicoLogin);
    // como pegar usuario e senha digitados?

    public static void main(String[] args) {
        launch();
    }

    public static void pegaLogado(ServicoLogin logado) {
        servicoLogin = logado;
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
        registraTela("PRINCIPAL", new ScreenRegistryFXML(App.class, "principal.fxml", o->new Principal(servicoLoginDAO, servicoLogin, alunoDAO, coordenadorDAO, professorDAO)));
        registraTela("PRINCIPALALUNO", new ScreenRegistryFXML(App.class, "principal_aluno.fxml", o->new PrincipalAluno(servicoLogin, repositorioAluno, repositorioAtividades)));
        registraTela("PRINCIPALCOORDENADOR", new ScreenRegistryFXML(App.class, "principal_coordenador.fxml", o->new PrincipalCoordenador(servicoLogin, repositorioCoordenador)));
        registraTela("PRINCIPALPROFESSOR", new ScreenRegistryFXML(App.class, "principal_professor.fxml", o->new PrincipalProfessor(servicoLogin, repositorioProfessor)));
        registraTela("CADASTRAANOTACAO", new ScreenRegistryFXML(App.class, "cadastrar_anotacao.fxml", o-> new CadastrarAnotacao(repositorioAnotacoes, repositorioAluno, servicoLogin)));
        registraTela("CADASTRAATIVIDADE", new ScreenRegistryFXML(App.class, "cadastrar_atividade.fxml", o-> new CadastrarAtividade(repositorioAtividades, repositorioProfessor, servicoLogin)));
        registraTela("CADASTRAEVENTO", new ScreenRegistryFXML(App.class, "cadastrar_evento.fxml", o-> new CadastrarEvento(repositorioEventos, repositorioCoordenador, servicoLogin)));
        registraTela("VISUALIZAANOTACAO", new ScreenRegistryFXML(App.class, "visualizar_anotacao.fxml", o-> new VisualizarAnotacao(repositorioAluno, repositorioAnotacoes, servicoLogin, repositorioAtividades, repositorioEventos)));
        registraTela("VISUALIZAATIVIDADE", new ScreenRegistryFXML(App.class, "visualizar_atividades.fxml", o-> new VisualizarAtividade(repositorioAtividades, repositorioProfessor, servicoLogin)));
        registraTela("VISUALIZAEVENTO", new ScreenRegistryFXML(App.class, "visualizar_eventos.fxml", o-> new VisualizarEvento(repositorioEventos, repositorioCoordenador, servicoLogin)));

    }

}