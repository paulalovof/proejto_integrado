package ifpr.pgua.eic.agenda.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.entities.Usuario;

public class JDBCServicoLoginDAO implements ServicoLoginDAO{
    private FabricaConexoes fabrica;
    private Usuario usuario;

    public JDBCServicoLoginDAO(FabricaConexoes fabrica){
        this.fabrica = fabrica;
    }

    @Override
    public Resultado validaUsuario(String login, String senha) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_usuarios where login = ? and senha = ?");

            pstm.setString(1, login);
            pstm.setString(2, senha);

            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                int id = rs.getInt("idUsuario");
                usuario = new Usuario(id, login, senha);
            }
            
            return Resultado.sucesso("Usuario logado!", usuario);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    
}
