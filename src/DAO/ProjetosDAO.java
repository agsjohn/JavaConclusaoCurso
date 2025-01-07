package DAO;

import Classes.Projetos;
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
public class ProjetosDAO {
    private Connection conn;

    public ProjetosDAO() {
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
        ArrayList projetos = new ArrayList();
        try {
            String SQL = "SELECT * FROM projetos";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idProjeto = rs.getInt("idProjeto");
                String nomeProjeto = rs.getString("nomeProjeto");
                String descricaoProjeto = rs.getString("descricaoProjeto");
                Date dataCriacaoProjeto = rs.getDate("dataCriacaoProjeto");
                Date dataConslusaoProjeto = rs.getDate("dataConslusaoProjeto");
                Boolean finalizado = rs.getBoolean("finalizado");
                projetos.add(new Projetos(idProjeto, nomeProjeto, descricaoProjeto, dataCriacaoProjeto, dataConslusaoProjeto, finalizado));
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao listar projetos " + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
        return projetos;
    }

    public void inserir(Projetos projeto) {
        PreparedStatement ps = null;
        Connection connL = null;

        if (projeto == null) {
            JOptionPane.showMessageDialog(null, "O objeto projeto não pode ser nulo.");
        }
        try {
            String SQL = "INSERT INTO projetos (nomeProjeto, descricaoProjeto, dataCriacaoProjeto, dataConslusaoProjeto, finalizado)"
                    + " values (?,?,?,?,?)";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setString(1, projeto.getNomeProjeto());
            ps.setString(2, projeto.getDescricaoProjeto());
            java.util.Date dataJAVA; 
            dataJAVA = projeto.getDataCriacaoProjeto();
            java.sql.Date dataSQL = new java.sql.Date(dataJAVA.getTime()); // Data da classe SQL
            ps.setDate(3, dataSQL);
            dataJAVA = projeto.getDataConclusaoProjeto();
            if(dataJAVA == null){
                ps.setDate(4, null);
            } else{
                dataSQL = new java.sql.Date(dataJAVA.getTime());
                ps.setDate(4, dataSQL);
            }
            ps.setBoolean(5, projeto.getFinalizado());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir um novo projeto" + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
    }

    public Projetos procurar(int idProjeto) {
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;
        Projetos projeto = new Projetos();
        projeto = null;
        try {
            String SQL = "SELECT * FROM projetos WHERE idProjeto = ?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, idProjeto);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nomeProjeto = rs.getString("nomeProjeto");
                String descricaoProjeto = rs.getString("descricaoProjeto");
                Date dataCriacaoProjeto = rs.getDate("dataCriacaoProjeto");
                Date dataConslusaoProjeto = rs.getDate("dataConslusaoProjeto");
                Boolean finalizado = rs.getBoolean("finalizado");
                projeto = new Projetos(idProjeto, nomeProjeto, descricaoProjeto, dataCriacaoProjeto, dataConslusaoProjeto, finalizado);
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao listar projetos " + sqle);
        } finally {
           // conexaoAulaDAO.close(connL, ps);
        }
        return projeto;
    }

    public void atualizar(Projetos projeto) {
        PreparedStatement ps = null;
        Connection connL = null;
        if (projeto == null) {
            JOptionPane.showMessageDialog(null, "O objeto projeto não pode ser nulo.");
        }
        try {
            String SQL = "UPDATE projetos set nomeProjeto=?, descricaoProjeto=?, dataCriacaoProjeto=?, "
                    + "dataConslusaoProjeto=?, finalizado=? WHERE idProjeto=?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setString(1, projeto.getNomeProjeto());
            ps.setString(2, projeto.getDescricaoProjeto());
            java.util.Date dataJAVA = projeto.getDataCriacaoProjeto();  // Data da classe Java Util
            java.sql.Date dataSQL = new java.sql.Date(dataJAVA.getTime()); // Data da classe SQL
            ps.setDate(3, dataSQL);
            dataJAVA = projeto.getDataConclusaoProjeto();
            if (dataJAVA != null) {
                dataSQL = new java.sql.Date(dataJAVA.getTime()); // Data da classe SQL
                ps.setDate(4, dataSQL);
            }
            else{
                dataSQL = null ;
                ps.setDate(4, dataSQL );
            }
            ps.setBoolean(5, projeto.getFinalizado());
            ps.setInt(6, projeto.getIdProjeto());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao editar projeto " + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
    }

    public void excluir(Projetos projeto) {
        PreparedStatement ps = null;
        Connection connL = null;
        if (projeto == null) {
            JOptionPane.showMessageDialog(null, "O objeto projeto não pode ser nulo.");
        }
        try {
            String SQL = "DELETE FROM projetos WHERE idProjeto=?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, projeto.getIdProjeto());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir projeto " + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
    }
}