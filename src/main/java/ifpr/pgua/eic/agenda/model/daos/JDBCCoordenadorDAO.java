package ifpr.pgua.eic.agenda.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;
import ifpr.pgua.eic.agenda.model.entities.Coordenador;

public class JDBCCoordenadorDAO implements CoordenadorDAO{
    private FabricaConexoes fabrica;

    public JDBCCoordenadorDAO(FabricaConexoes fabrica){
        this.fabrica = fabrica;
    }

    @Override
    public Resultado listar() {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_usuarios where tipoUsuario = 1");

            ResultSet rs = pstm.executeQuery();

            ArrayList<Coordenador> lista = new ArrayList<>();

            while(rs.next()){
                int id = rs.getInt("idUsuario");
                String nome = rs.getString("nome");
                String login = rs.getString("login");
                String senha = rs.getString("senha");
                String numeroSiape = rs.getString("numeroIdentificacao");

                Coordenador coordenador = new Coordenador(id, nome, login, senha, numeroSiape);
                lista.add(coordenador);
            }
            
            return Resultado.sucesso("Lista de coordenadores", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado getById(int id) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_usuarios WHERE idUsuario=? and tipoUsuario = 1");

            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();
            
            if(rs.next()){
                String nome = rs.getString("nome");
                String login = rs.getString("login");
                String senha = rs.getString("senha");
                String numeroSiape = rs.getString("numeroIdentificacao");

                Coordenador coordenador = new Coordenador(id, nome, login, senha, numeroSiape);

                return Resultado.sucesso("Coordenador encontrado", coordenador);
            }else{
                return Resultado.erro("Coordenador não encontrado!");
            }
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado buscarCoordenadorEvento(int eventoId) {
        try (Connection con = fabrica.getConnection()) {

            PreparedStatement pstm = con.prepareStatement("SELECT idUsuario FROM tb_eventos WHERE idEvento=?");

            pstm.setInt(1, eventoId);

            ResultSet rs = pstm.executeQuery();
            rs.next();

            int coordenadorId = rs.getInt("idUsuario");
            return getById(coordenadorId);


        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }
    
}
