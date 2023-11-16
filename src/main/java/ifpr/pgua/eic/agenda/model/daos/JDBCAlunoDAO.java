package ifpr.pgua.eic.agenda.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.entities.Aluno;

public class JDBCAlunoDAO implements AlunoDAO{
    private FabricaConexoes fabrica;

    public JDBCAlunoDAO(FabricaConexoes fabrica){
        this.fabrica = fabrica;
    }

    @Override
    public Resultado listar() {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_usuarios where tipoUsuario = 3");

            ResultSet rs = pstm.executeQuery();

            ArrayList<Aluno> lista = new ArrayList<>();

            while(rs.next()){
                int id = rs.getInt("idUsuario");
                String nome = rs.getString("nome");
                String numeroMatricula = rs.getString("numeroIdentificacao");

                Aluno aluno = new Aluno(id, nome,  numeroMatricula);
                lista.add(aluno);
            }
            
            return Resultado.sucesso("Lista de alunos", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado getById(int id) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_usuarios WHERE idUsuario=? and tipoUsuario = 3");

            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();
            
            if(rs.next()){
                String nome = rs.getString("nome");
                String login = rs.getString("login");
                String senha = rs.getString("senha");
                String numeroMatricula = rs.getString("numeroIdentificacao");

                Aluno aluno = new Aluno(id, nome, numeroMatricula);

                return Resultado.sucesso("Aluno encontrado", aluno);
            }else{
                return Resultado.erro("Aluno não encontrado!");
            }
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }
    @Override
    public Resultado buscarAlunoAnotacao(int anotacaoId) {
        
        try (Connection con = fabrica.getConnection()) {

            PreparedStatement pstm = con.prepareStatement("SELECT idUsuario FROM tb_anotacoes WHERE idAnotacao=?");

            pstm.setInt(1, anotacaoId);

            ResultSet rs = pstm.executeQuery();
            rs.next();

            int alunoId = rs.getInt("idUsuario");

            return getById(alunoId);


        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }
    
}
