package ifpr.pgua.eic.agenda.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ifpr.pgua.eic.agenda.model.entities.Usuario;

public class JDBCServicoLoginDAO implements ServicoLoginDAO{
    private FabricaConexoes fabrica;
    private Usuario usuario;

    public JDBCServicoLoginDAO(FabricaConexoes fabrica){
        this.fabrica = fabrica;
    }

    @Override
    public Usuario validaUsuario(String login, String senha) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_usuarios where login = ? and senha = ?");

            pstm.setString(1, login);
            pstm.setString(2, senha);

            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                int id = rs.getInt("idUsuario");
                usuario = new Usuario(id, login, senha);
            }
            
            return usuario;
        } catch (SQLException e) {
            System.out.println("deu ruim no jdbc servico login :/");
            return null;
        }
    }

    

    
}
