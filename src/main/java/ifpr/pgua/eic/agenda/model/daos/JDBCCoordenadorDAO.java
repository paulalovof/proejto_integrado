package ifpr.pgua.eic.agenda.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.entities.Coordenador;
import ifpr.pgua.eic.agenda.model.entities.ServicoLogin;

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
    public int getById(int id) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_coordenadores WHERE idUsuario=?");

            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();
            
            if(rs.next()){
                int idCoordenador = rs.getInt("idCoordenador");

                return idCoordenador;
            }else{
                System.out.println("Coordenador não encontrado!");
                return 0;
            }
        } catch (SQLException e) {
            System.out.println("Erro desconhecido!");
            return 0;
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
            int idCoordenador = getById(coordenadorId);
            return Resultado.sucesso("Coordenador encontrado!", idCoordenador);


        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Coordenador getNomeLogado(ServicoLogin logado) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_coordenadores where idUsuario = ?");

            pstm.setInt(1, logado.getLogado().getId());
            ResultSet rs = pstm.executeQuery();

            Coordenador coordenador = null;

            while(rs.next()){
                int id = rs.getInt("idCoordenador");
                String nome = rs.getString("nome");
                String numeroSiape = rs.getString("numeroSiape");

                coordenador = new Coordenador(id, nome,  numeroSiape);
            }
            
            return coordenador;
        } catch (SQLException e) {
            System.out.println("deu ruim no jdbcCoordenador :/");
            return null;
        }
    }
    
}
