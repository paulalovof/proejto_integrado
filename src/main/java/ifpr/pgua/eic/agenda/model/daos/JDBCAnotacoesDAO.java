package ifpr.pgua.eic.agenda.model.daos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import java.sql.Date;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.agenda.model.entities.Anotacoes;
import ifpr.pgua.eic.agenda.utils.DBUtils;

public class JDBCAnotacoesDAO implements AnotacoesDAO {
    private FabricaConexoes fabrica;

    public JDBCAnotacoesDAO(FabricaConexoes fabrica){
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(Anotacoes anotacao) {
        //try with resources, para não precisar fechar a conexao
        try(Connection con = fabrica.getConnection()){
            
            
            PreparedStatement pstm = con.
            prepareStatement("INSERT INTO tb_anotacoes(nome,descricao, data, idUsuario) VALUES (?,?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            
            pstm.setString(1,anotacao.getNome());
            pstm.setString(2, anotacao.getDescricao());
            pstm.setDate(3, Date.valueOf(anotacao.getData()));
            pstm.setInt(4, anotacao.getAluno().getIdAluno());
            
            int ret = pstm.executeUpdate();

            if(ret == 1){
                
                //se conseguiu inserir, vamos pegar o id criado
                int id = DBUtils.getLastId(pstm);

                anotacao.setIdAnotacao(id);

                return Resultado.sucesso("Anotacao cadastrado!", anotacao);
            }
            return Resultado.erro("Erro desconhecido!");
        }catch(SQLException e){
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listar() {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_anotacoes");

            ResultSet rs = pstm.executeQuery();

            ArrayList<Anotacoes> lista = new ArrayList<>();

            while(rs.next()){
                int id = rs.getInt("idAnotacao");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                LocalDate data = rs.getDate("data").toLocalDate();

                Anotacoes anotacao = new Anotacoes(id, null, nome, descricao, data);
                lista.add(anotacao);

            }
            
            return Resultado.sucesso("Lista de anotacoes", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    
    }

    @Override
    public Resultado getById(int id){

        try (Connection con = fabrica.getConnection()) {

            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_anotacoes WHERE idAnotacao=?");

            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();
            
            if(rs.next()){
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                LocalDate data = rs.getDate("data").toLocalDate();

                Anotacoes anotacao = new Anotacoes(id, null, nome, descricao, data);

                return Resultado.sucesso("Anotacao encontrado", anotacao);
            }else{
                return Resultado.erro("Anotacao não encontrado!");
            }


        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }


    }


    @Override
    public Resultado atualizar(int id, Anotacoes novo) {
        try(Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement("UPDATE tb_anotacoes set nome = ?, descricao = ?, data = ?, idUsuario = ? where idAnotacao = ?");

            pstm.setString(1, novo.getNome());
            pstm.setString(2, novo.getDescricao());
            pstm.setDate(3, Date.valueOf(novo.getData()));
            pstm.setInt(4, novo.getAluno().getIdAluno());
            pstm.setInt(5, id);

            int ret = pstm.executeUpdate();

            if(ret == 1){
                novo.setIdAnotacao(id);
                return Resultado.sucesso("Anotacao editado!", novo);
            }
            return Resultado.erro("vixe....");

        }catch(SQLException e){
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado deletar(int id) {
        try(Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement("DELETE FROM tb_anotacoes WHERE idAnotacao = ?");

            pstm.setInt(1, id);
            int ret = pstm.executeUpdate();
            if(ret == 1){
                return Resultado.sucesso("Anotacao excluida", id);
            }
            return Resultado.erro("Erro não identificado!");

        }catch(SQLException e){
            return Resultado.erro(e.getMessage());
        }
    }
}
