package ifpr.pgua.eic.agenda.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.entities.Professor;
import ifpr.pgua.eic.agenda.model.entities.ServicoLogin;

public class JDBCProfessorDAO implements ProfessorDAO{
    private FabricaConexoes fabrica;

    public JDBCProfessorDAO(FabricaConexoes fabrica){
        this.fabrica = fabrica;
    }

    @Override
    public Resultado listar() {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_professores");

            ResultSet rs = pstm.executeQuery();

            ArrayList<Professor> lista = new ArrayList<>();

            while(rs.next()){
                int id = rs.getInt("idUsuario");
                String nome = rs.getString("nome");
                String numeroSiape = rs.getString("numeroSiape");

                Professor professor = new Professor(id, nome, numeroSiape);
                lista.add(professor);
            }
            
            return Resultado.sucesso("Lista de professores", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

     @Override
    public Professor getById(int id) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_professores WHERE idUsuario=?");

            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();
            
            if(rs.next()){
                int idProfessor = rs.getInt("idProfessor");
                String nome = rs.getString("nome");
                String numero = rs.getString("numeroSiape");

                Professor professor = new Professor(idProfessor, nome, numero);
                return professor; 
            }else{
                System.out.println("Professor não encontrado!");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro desconhecido!");
            return null;
        }
    }

    @Override
    public Professor getByIdProfessor(int id) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_professores WHERE idProfessor=?");

            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();
            
            if(rs.next()){
                int idProfessor = rs.getInt("idProfessor");
                String nome = rs.getString("nome");
                String numero = rs.getString("numeroSiape");

                Professor professor = new Professor(idProfessor, nome, numero);
                return professor;
            }else{
                System.out.println("Professor não encontrado!");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro desconhecido!");
            return null;
        }
    }


    @Override
    public Professor buscarProfessorAtividade(int atividadeId){
        try (Connection con = fabrica.getConnection()) {

            PreparedStatement pstm = con.prepareStatement("SELECT idProfessor FROM tb_atividades WHERE idAtividade=?");

            pstm.setInt(1, atividadeId);

            ResultSet rs = pstm.executeQuery();
            rs.next();

            int idProfessor = rs.getInt("idProfessor");
            
            return getByIdProfessor(idProfessor);


        } catch (SQLException e) {
            System.out.println("erro no buscar coordenador evento");
            return null;
        }
    }

    @Override
    public Professor getNomeLogado(ServicoLogin logado) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_professores where idUsuario = ?");

            pstm.setInt(1, logado.getLogado().getId());
            ResultSet rs = pstm.executeQuery();

            Professor professor = null;

            while(rs.next()){
                int id = rs.getInt("idProfessor");
                String nome = rs.getString("nome");
                String numeroSiape = rs.getString("numeroSiape");

                professor = new Professor(id, nome,  numeroSiape);
            }
            
            return professor;
        } catch (SQLException e) {
            System.out.println("deu ruim no jdbcProfessor :/");
            return null;
        }
    }
}
