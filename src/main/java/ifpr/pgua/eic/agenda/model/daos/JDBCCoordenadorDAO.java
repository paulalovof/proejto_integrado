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
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_coordenadores");

            ResultSet rs = pstm.executeQuery();

            ArrayList<Coordenador> lista = new ArrayList<>();

            while(rs.next()){
                int id = rs.getInt("idCoordenador");
                String nome = rs.getString("nome");
                String numeroSiape = rs.getString("numeroSiape");

                Coordenador coordenador = new Coordenador(id, nome, numeroSiape);
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
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_coordenadores WHERE idCoordenador=?");

            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();
            
            if(rs.next()){
                String nome = rs.getString("nome");
                String numeroSiape = rs.getString("numeroSiape");

                Coordenador coordenador = new Coordenador(id, nome, numeroSiape);

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

            PreparedStatement pstm = con.prepareStatement("SELECT idCoordenador FROM tb_eventos WHERE idEvento=?");

            pstm.setInt(1, eventoId);

            ResultSet rs = pstm.executeQuery();
            rs.next();

            int coordenadorId = rs.getInt("idCoordenador");
            return getById(coordenadorId);


        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }
    
}
