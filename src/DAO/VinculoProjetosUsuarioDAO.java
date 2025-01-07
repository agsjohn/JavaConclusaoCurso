package DAO;

import Classes.Projetos;
import Classes.VinculoProjetosUsuario;
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
public class VinculoProjetosUsuarioDAO {
    private Connection conn;

    public VinculoProjetosUsuarioDAO() {
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
        ArrayList vinculos = new ArrayList();
        try {
            String SQL = "select u.idUsuario, u.nomeUsuario, p.idProjeto, v.idVinculoProjeto from usuarios as u, projetos as p, vinculo_projetos as v where u.idUsuario=v.idUsuario AND p.idProjeto=v.idProjeto;";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idUsuario = rs.getInt("idUsuario");
                String nomeUsuario = rs.getString("nomeUsuario");
                int idProjeto = rs.getInt("idProjeto");
                int idVinculoProjeto = rs.getInt("idVinculoProjeto");
                vinculos.add(new VinculoProjetosUsuario(idUsuario, nomeUsuario, idProjeto, idVinculoProjeto));
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao listar vinculo " + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
        return vinculos;
    }
}