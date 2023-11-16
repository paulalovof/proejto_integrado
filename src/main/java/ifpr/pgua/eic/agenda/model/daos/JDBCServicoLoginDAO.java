package ifpr.pgua.eic.agenda.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.entities.Professor;
import ifpr.pgua.eic.agenda.model.entities.Usuario;

public class JDBCServicoLoginDAO implements ServicoLoginDAO{
    private FabricaConexoes fabrica;

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
            int id = rs.getInt("idUsuario");
            Usuario usuario = new Usuario( id, login, senha);
            return Resultado.sucesso("Usuario logado!", usuario);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }
    
}
