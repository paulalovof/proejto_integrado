package ifpr.pgua.eic.agenda.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;
import ifpr.pgua.eic.agenda.model.entities.Professor;

public class JDBCProfessorDAO implements ProfessorDAO{
    private FabricaConexoes fabrica;

    public JDBCProfessorDAO(FabricaConexoes fabrica){
        this.fabrica = fabrica;
    }

    @Override
    public Resultado listar() {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_usuarios where tipoUsuario = 2");

            ResultSet rs = pstm.executeQuery();

            ArrayList<Professor> lista = new ArrayList<>();

            while(rs.next()){
                int id = rs.getInt("idUsuario");
                String nome = rs.getString("nome");
                String login = rs.getString("login");
                String senha = rs.getString("senha");
                String numeroSiape = rs.getString("numeroIdentificacao");

                Professor professor = new Professor(id, nome, login, senha, numeroSiape);
                lista.add(professor);
            }
            
            return Resultado.sucesso("Lista de professores", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado getById(int id) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_usuarios WHERE idUsuario=? and tipoUsuario = 2");

            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();
            
            if(rs.next()){
                String nome = rs.getString("nome");
                String login = rs.getString("login");
                String senha = rs.getString("senha");
                String numeroSiape = rs.getString("numeroIdentificacao");

                Professor professor = new Professor(id, nome, login, senha, numeroSiape);

                return Resultado.sucesso("Professor encontrado", professor);
            }else{
                return Resultado.erro("Professor não encontrado!");
            }
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }
    
    @Override
    public Resultado buscarProfessorAtividade(int atividadeId) {
        try (Connection con = fabrica.getConnection()) {

            PreparedStatement pstm = con.prepareStatement("SELECT idUsuario FROM tb_atividades WHERE idAtividade=?");

            pstm.setInt(1, atividadeId);

            ResultSet rs = pstm.executeQuery();
            rs.next();

            int professorId = rs.getInt("idUsuario");
            return getById(professorId);


        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }
}
