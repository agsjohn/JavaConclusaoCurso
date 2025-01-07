package DAO;

import Classes.Usuarios;
import Util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author 291021
 */
public class UsuariosDAO {
    private Connection conn;

    public UsuariosDAO() {
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
        ArrayList usuarios = new ArrayList();
        try {
            String SQL = "SELECT * FROM usuarios";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idUsuario = rs.getInt("idUsuario");
                String nomeUsuario = rs.getString("nomeUsuario");
                String emailUsuario = rs.getString("emailUsuario");
                String senhaUsuario = rs.getString("senhaUsuario");
                String cargoUsuario = rs.getString("cargoUsuario");
                usuarios.add(new Usuarios(idUsuario, nomeUsuario, emailUsuario, senhaUsuario, cargoUsuario));
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao listar usuarios " + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
        return usuarios;
    }

    public void inserir(Usuarios usuario) {
        PreparedStatement ps = null;
        Connection connL = null;

        if (usuario == null) {
            JOptionPane.showMessageDialog(null, "O objeto usuario não pode ser nulo.");
        }
        try {
            String SQL = "INSERT INTO usuarios (nomeUsuario, emailUsuario, senhaUsuario, cargoUsuario)"
                    + " values (?,?,?,?)";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setString(1, usuario.getNomeUsuario());
            ps.setString(2, usuario.getEmailUsuario());
            ps.setString(3, usuario.getSenhaUsuario());
            ps.setString(4, usuario.getCargoUsuario());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir um novo usuario " + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
    }

    public Usuarios procurar(int idUsuario) {
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;
        Usuarios usuario = new Usuarios();
        usuario = null;
        try {
            String SQL = "SELECT * FROM usuarios WHERE idUsuario = ?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nomeUsuario = rs.getString("nomeUsuario");
                String emailUsuario = rs.getString("emailUsuario");
                String senhaUsuario = rs.getString("senhaUsuario");
                String cargoUsuario = rs.getString("cargoUsuario");
                usuario = new Usuarios(idUsuario, nomeUsuario, emailUsuario, senhaUsuario, cargoUsuario);
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao listar usuario " + sqle);
        }
        return usuario;
    }
    
    public Usuarios verificarEmail(String emailUsuario) {
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;
        Usuarios usuario = new Usuarios();
        usuario = null;
        try {
            String SQL = "SELECT * FROM usuarios WHERE emailUsuario = ?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setString(1, emailUsuario);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idUsuario = rs.getInt("idUsuario");
                String nomeUsuario = rs.getString("nomeUsuario");
                String senhaUsuario = rs.getString("senhaUsuario");
                String cargoUsuario = rs.getString("cargoUsuario");
                usuario = new Usuarios(idUsuario, nomeUsuario, emailUsuario, senhaUsuario, cargoUsuario);
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao listar usuario " + sqle);
        }
        return usuario;
    }
    
    public Usuarios logar(String emailUsuario, String senhaUsuario) {
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;
        Usuarios usuario = new Usuarios();
        usuario = null;
        try {
            String SQL = "SELECT * FROM usuarios WHERE emailUsuario = ? AND senhaUsuario = ?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setString(1, emailUsuario);
            ps.setString(2, senhaUsuario);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idUsuario = rs.getInt("idUsuario");
                String nomeUsuario = rs.getString("nomeUsuario");
                String cargoUsuario = rs.getString("cargoUsuario");
                usuario = new Usuarios(idUsuario, nomeUsuario, emailUsuario, senhaUsuario, cargoUsuario);
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao listar usuario " + sqle);
        }
        return usuario;
    }

    public void atualizar(Usuarios usuario) {
        PreparedStatement ps = null;
        Connection connL = null;
        if (usuario == null) {
            JOptionPane.showMessageDialog(null, "O objeto usuario não pode ser nulo.");
        }
        try {
            String SQL = "UPDATE usuarios set nomeUsuario=?, emailUsuario=?, senhaUsuario=?, "
                    + "cargoUsuario=? WHERE idUsuario=?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setString(1, usuario.getNomeUsuario());
            ps.setString(2, usuario.getEmailUsuario());
            ps.setString(3, usuario.getSenhaUsuario());
            ps.setString(4, usuario.getCargoUsuario());
            ps.setInt(5, usuario.getIdUsuario());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao editar usuario " + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
    }

    public void excluir(Usuarios usuario) {
        PreparedStatement ps = null;
        Connection connL = null;
        if (usuario == null) {
            JOptionPane.showMessageDialog(null, "O objeto usuario não pode ser nulo.");
        }
        try {
            String SQL = "DELETE FROM usuarios WHERE idUsuario=?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, usuario.getIdUsuario());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir usuario " + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
    }
}