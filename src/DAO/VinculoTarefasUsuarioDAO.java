package DAO;

import Classes.Projetos;
import Classes.VinculoProjetosUsuario;
import Classes.VinculoTarefasUsuario;
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
public class VinculoTarefasUsuarioDAO {
    private Connection conn;

    public VinculoTarefasUsuarioDAO() {
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
            String SQL = "select u.idUsuario, u.nomeUsuario, t.nomeTarefa, t.estadoTarefa, t.idTarefa, v.idVinculoTarefa from usuarios as u, tarefas as t, vinculo_tarefas as v where u.idUsuario=v.idUsuario AND t.idTarefa=v.idTarefa;";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idUsuario = rs.getInt("idUsuario");
                String nomeUsuario = rs.getString("nomeUsuario");
                String nomeTarefa = rs.getString("nomeTarefa");
                String estadoTarefa = rs.getString("estadoTarefa");
                int idTarefa = rs.getInt("idTarefa");
                int idVinculoTarefa = rs.getInt("idVinculoTarefa");
                vinculos.add(new VinculoTarefasUsuario(idUsuario, nomeTarefa, estadoTarefa, nomeUsuario, idTarefa, idVinculoTarefa));
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao listar vinculo " + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
        return vinculos;
    }
}