package DAO;

import Classes.Tarefas;
import Util.Conexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author 291021
 */
public class TarefasDAO {
    private Connection conn;

    public TarefasDAO() {
        try {
            this.conn = Conexao.getConnection();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro de conexão: \n" + e.getMessage());
        }
    }
    
    public ArrayList listar() {
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;
        ArrayList tarefas = new ArrayList();
        try {
            String SQL = "SELECT * FROM tarefas";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idTarefa = rs.getInt("idTarefa");
                int idProjeto = rs.getInt("idProjeto");
                String nomeTarefa = rs.getString("nomeTarefa");
                String descricaoTarefa = rs.getString("descricaoTarefa");
                Date dataCriacaoTarefa = rs.getDate("dataCriacaoTarefa");
                Date dataConslusaoTarefa = rs.getDate("dataConslusaoTarefa");
                String estadoTarefa = rs.getString("estadoTarefa");
                tarefas.add(new Tarefas(idTarefa, idProjeto, nomeTarefa, descricaoTarefa, dataCriacaoTarefa, dataConslusaoTarefa, estadoTarefa));
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao listar tarefas " + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
        return tarefas;
    }

    public int inserir(Tarefas tarefa) {
        PreparedStatement ps = null;
        Connection connL = null;
        int idTarefa = -1;

        if (tarefa == null) {
            JOptionPane.showMessageDialog(null, "O objeto tarefa não pode ser nulo.");
        }
        try {
            String SQL = "INSERT INTO tarefas (idProjeto, nomeTarefa, descricaoTarefa, dataCriacaoTarefa, dataConslusaoTarefa, estadoTarefa"
                    + ") values (?,?,?,?,?,?)";
            connL = this.conn;
            ps = connL.prepareStatement(SQL, new String[]{"idTarefa"});
            ps.setInt(1, tarefa.getIdProjeto());
            ps.setString(2, tarefa.getNomeTarefa());
            ps.setString(3, tarefa.getDescricaoTarefa());
            java.util.Date dataJAVA = tarefa.getDataCriacaoTarefa();
            java.sql.Date dataSQL = new java.sql.Date(dataJAVA.getTime());
            ps.setDate(4, dataSQL);
            dataJAVA = null;
            dataSQL = null;
            dataJAVA = tarefa.getDataConslusaoTarefa();
            if(dataJAVA != null){
                dataSQL = new java.sql.Date(dataJAVA.getTime());
                ps.setDate(5, dataSQL);
            } else{
                ps.setDate(5, dataSQL);
            }
            ps.setString(6, tarefa.getEstadoTarefa());
            int afetados = ps.executeUpdate();
            
            if (afetados > 0) {
                // Obtendo as chaves geradas
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    idTarefa = rs.getInt(1);
                }
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir uma nova tarefa" + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
        
        return idTarefa;
    }

    public ArrayList<Tarefas> procurarIdProjeto(int idProjeto) {
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;
        ArrayList<Tarefas> tarefas = new ArrayList<Tarefas>();
        try {
            String SQL = "SELECT * FROM tarefas WHERE idProjeto = ?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, idProjeto);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idTarefa = rs.getInt("idTarefa");
                String nomeTarefa = rs.getString("nomeTarefa");
                String descricaoTarefa = rs.getString("descricaoTarefa");
                Date dataCriacaoProjeto = rs.getDate("dataCriacaoTarefa");
                Date dataConslusaoTarefa = rs.getDate("dataConslusaoTarefa");
                String estadoTarefa= rs.getString("estadoTarefa");
                tarefas.add(new Tarefas(idTarefa, idProjeto, nomeTarefa, descricaoTarefa, dataCriacaoProjeto, dataConslusaoTarefa, estadoTarefa));
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao listar tarefas " + sqle);
        }
        return tarefas;
    }
    
    public Tarefas procurar(int idTarefa) {
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;
        Tarefas tarefa = new Tarefas();
        tarefa = null;
        try {
            String SQL = "SELECT * FROM tarefas WHERE idTarefa = ?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, idTarefa);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idProjeto = rs.getInt("idProjeto");
                String nomeTarefa = rs.getString("nomeTarefa");
                String descricaoTarefa = rs.getString("descricaoTarefa");
                Date dataCriacaoProjeto = rs.getDate("dataCriacaoTarefa");
                Date dataConslusaoTarefa = rs.getDate("dataConslusaoTarefa");
                String estadoTarefa= rs.getString("estadoTarefa");
                tarefa = new Tarefas(idTarefa, idProjeto, nomeTarefa, descricaoTarefa, dataCriacaoProjeto, dataConslusaoTarefa, estadoTarefa);
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao listar tarefas " + sqle);
        }
        return tarefa;
    }
    
    public void atualizar(Tarefas tarefa) {
        PreparedStatement ps = null;
        Connection connL = null;
        if (tarefa == null) {
            JOptionPane.showMessageDialog(null, "O objeto tarefa não pode ser nulo.");
        }
        try {
            String SQL = "UPDATE tarefas set idProjeto=?, nomeTarefa=?, descricaoTarefa=?, "
                    + "dataCriacaoTarefa=?, dataConslusaoTarefa=?, estadoTarefa=? WHERE idTarefa=?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, tarefa.getIdProjeto());
            ps.setString(2, tarefa.getNomeTarefa());
            ps.setString(3, tarefa.getDescricaoTarefa());
            java.util.Date dataJAVA = tarefa.getDataCriacaoTarefa();  // Data da classe Java Util
            java.sql.Date dataSQL = new java.sql.Date(dataJAVA.getTime()); // Data da classe SQL
            ps.setDate(4, dataSQL);
            dataJAVA = tarefa.getDataConslusaoTarefa();
            if (dataJAVA != null) {
                dataSQL = new java.sql.Date(dataJAVA.getTime()); // Data da classe SQL
                ps.setDate(5, dataSQL);
            }
            else{
                dataSQL = null ;
                ps.setDate(5, dataSQL );
            }
            ps.setString(6, tarefa.getEstadoTarefa());
            ps.setInt(7, tarefa.getIdTarefa());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao editar tarefa " + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
    }

    public void atualizarEstadoTarefa(int idTarefa, String estadoTarefa){
        PreparedStatement ps = null;
        Connection connL = null;
        try {
            String SQL = "UPDATE tarefas set estadoTarefa=? WHERE idTarefa=?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setString(1, estadoTarefa);
            ps.setInt(2, idTarefa);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao editar tarefa " + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
    }
    
    public void atualizarEstadoTarefa(int idTarefa, String estadoTarefa, java.util.Date dataJAVA){
        PreparedStatement ps = null;
        Connection connL = null;
        try {
            String SQL = "UPDATE tarefas set estadoTarefa=?, dataConslusaoTarefa=? WHERE idTarefa=?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setString(1, estadoTarefa);
            java.sql.Date dataSQL;
            System.out.println(dataJAVA);
            if (dataJAVA != null) {
                dataSQL = new java.sql.Date(dataJAVA.getTime()); // Data da classe SQL
                ps.setDate(2, dataSQL);
            }
            else{
                dataSQL = null;
                ps.setDate(2, dataSQL);
            }
            ps.setInt(3, idTarefa);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao editar tarefa " + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
    }
    
    public void excluir(Tarefas tarefa) {
        PreparedStatement ps = null;
        Connection connL = null;
        if (tarefa == null) {
            JOptionPane.showMessageDialog(null, "O objeto tarefa não pode ser nulo.");
        }
        try {
            String SQL = "DELETE FROM tarefas WHERE idTarefa=?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, tarefa.getIdTarefa());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir tarefa " + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
    }
}