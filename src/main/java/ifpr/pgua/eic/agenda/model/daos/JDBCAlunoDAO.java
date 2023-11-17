package ifpr.pgua.eic.agenda.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.entities.Aluno;
import ifpr.pgua.eic.agenda.model.entities.ServicoLogin;

public class JDBCAlunoDAO implements AlunoDAO{
    private FabricaConexoes fabrica;

    public JDBCAlunoDAO(FabricaConexoes fabrica){
        this.fabrica = fabrica;
    }

    @Override
    public Resultado listar() {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_alunos");

            ResultSet rs = pstm.executeQuery();

            ArrayList<Aluno> lista = new ArrayList<>();

            while(rs.next()){
                int id = rs.getInt("idAluno");
                //int idUsuario = rs.getInt("idUsuario");
                String nome = rs.getString("nome");
                String numeroMatricula = rs.getString("numeroMatricula");

                Aluno aluno = new Aluno(id, nome,  numeroMatricula);
                lista.add(aluno);
            }
            
            return Resultado.sucesso("Lista de alunos", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Aluno getById(int id) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_alunos WHERE idUsuario=?");

            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();
            
            if(rs.next()){
                int idAluno = rs.getInt("idAluno");
                String nome = rs.getString("nome");
                String numero = rs.getString("numeroMatricula");

                Aluno aluno = new Aluno(idAluno, nome, numero);
                return aluno; 
            }else{
                System.out.println("Aluno não encontrado!");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro desconhecido!");
            return null;
        }
    }

    @Override
    public Aluno getByIdAluno(int id) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_alunos WHERE idAluno=?");

            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();
            
            if(rs.next()){
                int idAluno = rs.getInt("idAluno");
                String nome = rs.getString("nome");
                String numero = rs.getString("numeroMatricula");

                Aluno aluno = new Aluno(idAluno, nome, numero);
                return aluno; 
            }else{
                System.out.println("Aluno não encontrado!");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro desconhecido!");
            return null;
        }
    }

    @Override
    public Aluno buscarAlunoAnotacao(int anotacaoId) {
        
        try (Connection con = fabrica.getConnection()) {

            PreparedStatement pstm = con.prepareStatement("SELECT idAluno FROM tb_anotacoes WHERE idAnotacao=?");

            pstm.setInt(1, anotacaoId);

            ResultSet rs = pstm.executeQuery();
            int alunoId = 0;
            while(rs.next()){
                alunoId = rs.getInt("idAluno");
            }
            return getByIdAluno(alunoId);


        } catch (SQLException e) {
            System.out.println("Erro no buscar aluno anotacao");
            return null;
        }
    }

    @Override
    public Aluno getNomeLogado(ServicoLogin logado) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_alunos where idUsuario = ?");

            pstm.setInt(1, logado.getLogado().getId());
            ResultSet rs = pstm.executeQuery();

            Aluno aluno = null;

            while(rs.next()){
                int id = rs.getInt("idAluno");
                String nome = rs.getString("nome");
                String numeroMatricula = rs.getString("numeroMatricula");

                aluno = new Aluno(id, nome,  numeroMatricula);
            }
            
            return aluno;
        } catch (SQLException e) {
            System.out.println("deu ruim no jdbcAluno :/");
            return null;
        }
    }
    
}
