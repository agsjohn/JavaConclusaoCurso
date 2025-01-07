package DAO;

import Classes.Projetos;
import Classes.VinculoProjetos;
import Classes.VinculoTarefas;
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
public class VinculoTarefasDAO {
    private Connection conn;

    public VinculoTarefasDAO() {
        try {
            this.conn = Conexao.getConnection();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro de conexão: " + ":\n" + e.getMessage());
        }
    }
    
    public ArrayList listar() {
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;
        ArrayList<VinculoTarefas> vinculoProjetos = new ArrayList<>();
        try {
            String SQL = "SELECT * FROM vinculo_tarefas";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idVinculoTarefa = rs.getInt("idVinculoTarefa");
                int idTarefas = rs.getInt("idTarefa");
                int idUsuario = rs.getInt("idUsuario");
                vinculoProjetos.add(new VinculoTarefas(idVinculoTarefa, idTarefas, idUsuario));
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao listar vinculos " + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
        return vinculoProjetos;
    }
    
    public void inserir(VinculoTarefas vinculoTarefa) {
        PreparedStatement ps = null;
        Connection connL = null;

        if (vinculoTarefa == null) {
            JOptionPane.showMessageDialog(null, "O objeto vinculo não pode ser nulo.");
        }
        try {
            String SQL = "INSERT INTO vinculo_tarefas (idTarefa, idUsuario)"
                    + " values (?,?)";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, vinculoTarefa.getIdTarefa());
            ps.setInt(2, vinculoTarefa.getIdUsuario());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir um novo vinculo" + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
    }

    public VinculoTarefas procurar(int idVinculoTarefa) {
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;
        VinculoTarefas vinculoTarefas = new VinculoTarefas();
        vinculoTarefas = null;
        try {
            String SQL = "SELECT * FROM vinculo_tarefas WHERE idVinculoTarefa = ?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, idVinculoTarefa);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idTarefa = rs.getInt("idTarefa");
                int idUsuario = rs.getInt("idUsuario");
                vinculoTarefas = new VinculoTarefas(idVinculoTarefa, idTarefa, idUsuario);
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao listar vinculo " + sqle);
        } finally {
           // conexaoAulaDAO.close(connL, ps);
        }
        return vinculoTarefas;
    }

    public void atualizar(VinculoTarefas vinculoTarefas) {
        PreparedStatement ps = null;
        Connection connL = null;
        if (vinculoTarefas == null) {
            JOptionPane.showMessageDialog(null, "O objeto vinculo não pode ser nulo.");
        }
        try {
            String SQL = "UPDATE vinculo_tarefas set idTarefa=?, idUsuario=? WHERE idVinculoTarefa=?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, vinculoTarefas.getIdTarefa());
            ps.setInt(2, vinculoTarefas.getIdUsuario());
            ps.setInt(3, vinculoTarefas.getIdVinculoTarefa());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao editar vinculo " + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
    }

    public void excluir(VinculoTarefas vinculoTarefas) {
        PreparedStatement ps = null;
        Connection connL = null;
        if (vinculoTarefas == null) {
            JOptionPane.showMessageDialog(null, "O objeto vinculo não pode ser nulo.");
        }
        try {
            String SQL = "DELETE FROM vinculo_tarefas WHERE idVinculoTarefa=?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, vinculoTarefas.getIdVinculoTarefa());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir vinculo " + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
    }
    
    public void excluir(int idVinculoTarefa) {
        PreparedStatement ps = null;
        Connection connL = null;
        try {
            String SQL = "DELETE FROM vinculo_tarefas WHERE idVinculoTarefa=?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, idVinculoTarefa);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir vinculo " + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
    }
}