package ifpr.pgua.eic.agenda.model.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.entities.Atividades;
import ifpr.pgua.eic.agenda.model.entities.Eventos;
import ifpr.pgua.eic.agenda.utils.DBUtils;

public class JDBCEventosDAO implements EventosDAO{

    private FabricaConexoes fabrica;

    public JDBCEventosDAO(FabricaConexoes fabrica){
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(Eventos evento) {
        try(Connection con = fabrica.getConnection()){
            
            
            PreparedStatement pstm = con.prepareStatement("INSERT INTO tb_eventos(nome,descricao, data, idCoordenador) VALUES (?,?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            
            pstm.setString(1, evento.getNome());
            pstm.setString(2, evento.getDescricao());
            pstm.setDate(3, Date.valueOf(evento.getData()));
            pstm.setInt(4, evento.getCoordenador().getIdCoordenador());
            
            int ret = pstm.executeUpdate();

            if(ret == 1){
                
                //se conseguiu inserir, vamos pegar o id criado
                int id = DBUtils.getLastId(pstm);

                evento.setIdEvento(id);

                return Resultado.sucesso("Evento cadastrado!", evento);
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
                pstm = con.prepareStatement("SELECT * FROM tb_eventos where idCoordenador = ? ORDER BY data ASC");
                pstm.setInt(1, id);
            }else{
                pstm = con.prepareStatement("SELECT * FROM tb_eventos ORDER BY data ASC");
            }
            
            ResultSet rs = pstm.executeQuery();

            ArrayList<Eventos> lista = new ArrayList<>();

            while(rs.next()){
                int idEvento = rs.getInt("idEvento");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                LocalDate data = rs.getDate("data").toLocalDate();

                Eventos evento = new Eventos(idEvento, null, nome, descricao, data);
                lista.add(evento);

            }
            return Resultado.sucesso("Lista de eventos", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado getById(int id) {
        try (Connection con = fabrica.getConnection()) {

            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_eventos WHERE idEvento=?");

            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();
            
            if(rs.next()){
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                LocalDate data = rs.getDate("data").toLocalDate();

                //Anotacoes anotacao = new Anotacoes(id, null, nome, descricao, data);
                Eventos evento = new Eventos(id, null, nome, descricao, data);

                return Resultado.sucesso("Evento encontrado", evento);
            }else{
                return Resultado.erro("Evento não encontrado!");
            }
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado atualizar(int id, Eventos novo) {
        try(Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement("UPDATE tb_eventos set nome = ?, descricao = ?, data = ?, idCoordenador = ? where idEvento = ?");

            pstm.setString(1, novo.getNome());
            pstm.setString(2, novo.getDescricao());
            pstm.setDate(3, Date.valueOf(novo.getData()));
            pstm.setInt(4, novo.getCoordenador().getIdCoordenador());
            pstm.setInt(5, id);

            int ret = pstm.executeUpdate();

            if(ret == 1){
                novo.setIdEvento(id);
                return Resultado.sucesso("Evento Editado!", novo);
            }
            return Resultado.erro("vixe....");

        }catch(SQLException e){
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado deletar(int id) {
        try(Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement("DELETE FROM tb_eventos WHERE idEvento = ?");

            pstm.setInt(1, id);
            int ret = pstm.executeUpdate();
            if(ret == 1){
                return Resultado.sucesso("Evento excluido", id);
            }
            return Resultado.erro("Erro não identificado!");

        }catch(SQLException e){
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listarSemana() {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_eventos where WEEK(data) = WEEK(now())");

            ResultSet rs = pstm.executeQuery();

            ArrayList<Eventos> lista = new ArrayList<>();

            while(rs.next()){
                int idEvento = rs.getInt("idEvento");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                LocalDate data = rs.getDate("data").toLocalDate();

                //Anotacoes anotacao = new Anotacoes(idAtividade, null, nome, descricao, data);
                Eventos evento = new Eventos(idEvento, null, nome, descricao, data);
                lista.add(evento);

            }

            return Resultado.sucesso("Lista de eventos filtrada por semana", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listarMes() {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_eventos where MONTH(data) = MONTH(now())");

            ResultSet rs = pstm.executeQuery();

            ArrayList<Eventos> lista = new ArrayList<>();

            while(rs.next()){
                int idEvento = rs.getInt("idEvento");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                LocalDate data = rs.getDate("data").toLocalDate();

                //Anotacoes anotacao = new Anotacoes(idAtividade, null, nome, descricao, data);
                Eventos evento = new Eventos(idEvento, null, nome, descricao, data);
                lista.add(evento);
            }

            return Resultado.sucesso("Lista de eventos filtrada por mes", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }
    
}
