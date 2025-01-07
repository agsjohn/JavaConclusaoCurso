package DAO;

import Classes.Projetos;
import Classes.VinculoProjetos;
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
public class VinculoProjetosDAO {
    private Connection conn;

    public VinculoProjetosDAO() {
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
        ArrayList vinculoProjetos = new ArrayList();
        try {
            String SQL = "SELECT * FROM vinculo_projetos";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idVinculoProjeto = rs.getInt("idVinculoProjeto");
                int idProjeto = rs.getInt("idProjeto");
                int idUsuario = rs.getInt("idUsuario");
                vinculoProjetos.add(new VinculoProjetos(idVinculoProjeto, idProjeto, idUsuario));
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao listar vinculos " + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
        return vinculoProjetos;
    }
    
    public void inserir(VinculoProjetos vinculoProjetos) {
        PreparedStatement ps = null;
        Connection connL = null;

        if (vinculoProjetos == null) {
            JOptionPane.showMessageDialog(null, "O objeto vinculo não pode ser nulo.");
        }
        try {
            String SQL = "INSERT INTO vinculo_projetos (idProjeto, idUsuario)"
                    + " values (?,?)";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, vinculoProjetos.getIdProjeto());
            ps.setInt(2, vinculoProjetos.getIdUsuario());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir um novo vinculo" + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
    }

    public VinculoProjetos procurar(int idVinculoProjeto) {
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;
        VinculoProjetos vinculoProjetos = new VinculoProjetos();
        vinculoProjetos = null;
        try {
            String SQL = "SELECT * FROM vinculo_projetos WHERE idVinculoProjeto = ?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, idVinculoProjeto);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idProjeto = rs.getInt("idProjeto");
                int idUsuario = rs.getInt("idUsuario");
                vinculoProjetos = new VinculoProjetos(idVinculoProjeto, idProjeto, idUsuario);
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao listar vinculo " + sqle);
        } finally {
           // conexaoAulaDAO.close(connL, ps);
        }
        return vinculoProjetos;
    }

    public void atualizar(VinculoProjetos vinculoProjetos) {
        PreparedStatement ps = null;
        Connection connL = null;
        if (vinculoProjetos == null) {
            JOptionPane.showMessageDialog(null, "O objeto vinculo não pode ser nulo.");
        }
        try {
            String SQL = "UPDATE vinculo_projetos set idProjeto=?, idUsuario=? WHERE idVinculoProjeto=?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, vinculoProjetos.getIdProjeto());
            ps.setInt(2, vinculoProjetos.getIdUsuario());
            ps.setInt(3, vinculoProjetos.getIdVinculoProjeto());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao editar vinculo " + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
    }

    public void excluir(VinculoProjetos vinculoProjeto) {
        PreparedStatement ps = null;
        Connection connL = null;
        if (vinculoProjeto == null) {
            JOptionPane.showMessageDialog(null, "O objeto vinculo não pode ser nulo.");
        }
        try {
            String SQL = "DELETE FROM vinculo_projetos WHERE idVinculoProjeto=?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, vinculoProjeto.getIdVinculoProjeto());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir vinculo " + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
    }
    
    public void excluir(int idVinculoProjeto) {
        PreparedStatement ps = null;
        Connection connL = null;
        try {
            String SQL = "DELETE FROM vinculo_projetos WHERE idVinculoProjeto=?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, idVinculoProjeto);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir vinculo " + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
    }
}