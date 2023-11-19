package ifpr.pgua.eic.agenda.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.entities.Anotacoes;
import ifpr.pgua.eic.agenda.model.entities.Atividades;
import ifpr.pgua.eic.agenda.utils.DBUtils;

public class JDBCAtividadesDAO implements AtividadesDAO{

    private FabricaConexoes fabrica;

    public JDBCAtividadesDAO(FabricaConexoes fabrica){
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(Atividades atividade) {
        try(Connection con = fabrica.getConnection()){
            
            PreparedStatement pstm = con.
            prepareStatement("INSERT INTO tb_atividades(nome, descricao, data, avaliada, idProfessor) VALUES (?,?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            
            pstm.setString(1,atividade.getNome());
            pstm.setString(2, atividade.getDescricao());
            pstm.setDate(3, Date.valueOf(atividade.getData()));
            pstm.setBoolean(4, atividade.isAtividadeAvaliada());
            pstm.setInt(5, atividade.getProfessor().getIdProfessor());
            
            int ret = pstm.executeUpdate();

            if(ret == 1){
                
                //se conseguiu inserir, vamos pegar o id criado
                int id = DBUtils.getLastId(pstm);

                atividade.setIdAtividade(id);

                return Resultado.sucesso("Atividade cadastrada!", atividade);
            }
            return Resultado.erro("Erro desconhecido!");
        }catch(SQLException e){
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listar(int id) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = null;
            if(id != 0){
                pstm = con.prepareStatement("SELECT * FROM tb_atividades where idProfessor = ? ORDER BY data ASC");
                pstm.setInt(1,id);
            }else{
                pstm = con.prepareStatement("SELECT * FROM tb_atividades ORDER BY data ASC");
            }
            
            ResultSet rs = pstm.executeQuery();

            ArrayList<Atividades> lista = new ArrayList<>();

            while(rs.next()){
                int idAtividade = rs.getInt("idAtividade");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                LocalDate data = rs.getDate("data").toLocalDate();
                Boolean avaliada = rs.getBoolean("avaliada");

                Atividades atividade = new Atividades(idAtividade, null, nome, descricao, data, avaliada);
                lista.add(atividade);

            }
            return Resultado.sucesso("Lista de atividades", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    public Resultado listarProximas() {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = null;
            
            pstm = con.prepareStatement("SELECT * FROM tb_atividades where data between CURDATE() and CURDATE() + INTERVAL 7 DAY");
            
            
            ResultSet rs = pstm.executeQuery();

            ArrayList<Atividades> lista = new ArrayList<>();

            while(rs.next()){
                int idAtividade = rs.getInt("idAtividade");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                LocalDate data = rs.getDate("data").toLocalDate();
                Boolean avaliada = rs.getBoolean("avaliada");

                Atividades atividade = new Atividades(idAtividade, null, nome, descricao, data, avaliada);
                lista.add(atividade);

            }
            return Resultado.sucesso("Lista de atividades", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado getById(int id) {
        try (Connection con = fabrica.getConnection()) {

            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_atividades WHERE idAtividade=?");

            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();
            
            if(rs.next()){
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                LocalDate data = rs.getDate("data").toLocalDate();
                Boolean avaliada = rs.getBoolean("avaliada");

                //Anotacoes anotacao = new Anotacoes(id, null, nome, descricao, data);
                Atividades atividade = new Atividades(null, nome, descricao, data, avaliada);

                return Resultado.sucesso("Atividade encontrada", atividade);
            }else{
                return Resultado.erro("Atividade não encontrada!");
            }
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado atualizar(int id, Atividades novo) {
        try(Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement("UPDATE tb_atividades set nome = ?, descricao = ?, data = ?, avaliada = ?, idProfessor = ? where idAtividade = ?");

            pstm.setString(1, novo.getNome());
            pstm.setString(2, novo.getDescricao());
            pstm.setDate(3, Date.valueOf(novo.getData()));
            pstm.setBoolean(4, novo.isAtividadeAvaliada());
            pstm.setInt(5, novo.getProfessor().getIdProfessor());
            pstm.setInt(6, id);

            int ret = pstm.executeUpdate();

            if(ret == 1){
                novo.setIdAtividade(id);
                return Resultado.sucesso("Atividade editada!", novo);
            }
            return Resultado.erro("vixe....");

        }catch(SQLException e){
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado deletar(int id) {
        try(Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement("DELETE FROM tb_atividades WHERE idAtividade = ?");

            pstm.setInt(1, id);
            int ret = pstm.executeUpdate();
            if(ret == 1){
                return Resultado.sucesso("Atividade excluida", id);
            }
            return Resultado.erro("Erro não identificado!");

        }catch(SQLException e){
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listarSemana() {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_atividades where WEEK(data) = WEEK(now())");

            ResultSet rs = pstm.executeQuery();

            ArrayList<Atividades> lista = new ArrayList<>();

            while(rs.next()){
                int idAtividade = rs.getInt("idAtividade");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                LocalDate data = rs.getDate("data").toLocalDate();
                Boolean avaliada = rs.getBoolean("avaliada");

                //Anotacoes anotacao = new Anotacoes(idAtividade, null, nome, descricao, data);
                Atividades atividade = new Atividades(idAtividade, null, nome, descricao, data, avaliada);
                lista.add(atividade);

            }

            return Resultado.sucesso("Lista de atividades filtrada por semana", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listarMes() {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_atividades where MONTH(data) = MONTH(now())");

            ResultSet rs = pstm.executeQuery();

            ArrayList<Atividades> lista = new ArrayList<>();

            while(rs.next()){
                int idAtividade = rs.getInt("idAtividade");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                LocalDate data = rs.getDate("data").toLocalDate();
                Boolean avaliada = rs.getBoolean("avaliada");

                //Anotacoes anotacao = new Anotacoes(idAtividade, null, nome, descricao, data);
                Atividades atividade = new Atividades(idAtividade, null, nome, descricao, data, avaliada);
                lista.add(atividade);

            }

            return Resultado.sucesso("Lista de atividades filtrada por mes", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }
    
}
