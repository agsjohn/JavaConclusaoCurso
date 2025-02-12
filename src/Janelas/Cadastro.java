/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Janelas;

import Classes.Placeholder;
import Classes.Usuarios;
import Classes.limiteTexto;
import DAO.UsuariosDAO;
import java.awt.CardLayout;

/**
 *
 * @author agsjohn
 */
public class Cadastro extends javax.swing.JPanel {

    Principal principal;
    
    public Cadastro() {
        initComponents();
    }
    
    public Cadastro(Principal principal) {
        initComponents();
        
        this.principal = principal;
        
        emailUsuario.setDocument(new limiteTexto(50));
        senhaUsuario.setDocument(new limiteTexto(20));
        cargoUsuario.setDocument(new limiteTexto(50));
        nomeUsuario.setDocument(new limiteTexto(50));
        
        Placeholder.addPlaceholder(emailUsuario, "Digite seu e-mail");
        Placeholder.addPlaceholder(senhaUsuario, "Digite sua senha");
        Placeholder.addPlaceholder(cargoUsuario, "Digite seu cargo");
        Placeholder.addPlaceholder(nomeUsuario, "Digite seu nome");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        emailUsuario = new javax.swing.JTextField();
        jLabelEmail = new javax.swing.JLabel();
        jLabelSenha = new javax.swing.JLabel();
        jButtonRegistrar = new javax.swing.JButton();
        jButtonVoltar = new javax.swing.JButton();
        jLabelNome = new javax.swing.JLabel();
        nomeUsuario = new javax.swing.JTextField();
        jLabelCargo = new javax.swing.JLabel();
        cargoUsuario = new javax.swing.JTextField();
        senhaUsuario = new javax.swing.JPasswordField();

        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 5, 0, 5, 0};
        layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        layout.columnWeights = new double[] {0.0};
        setLayout(layout);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(emailUsuario, gridBagConstraints);

        jLabelEmail.setText("Email");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(jLabelEmail, gridBagConstraints);

        jLabelSenha.setText("Senha");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(jLabelSenha, gridBagConstraints);

        jButtonRegistrar.setText("Registrar");
        jButtonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 60);
        add(jButtonRegistrar, gridBagConstraints);

        jButtonVoltar.setText("Voltar");
        jButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.insets = new java.awt.Insets(20, 60, 0, 0);
        add(jButtonVoltar, gridBagConstraints);

        jLabelNome.setText("Nome");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(jLabelNome, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(nomeUsuario, gridBagConstraints);

        jLabelCargo.setText("Cargo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(jLabelCargo, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(cargoUsuario, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(senhaUsuario, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarActionPerformed
        principal.limparErro();
        CardLayout layout = (CardLayout) getParent().getLayout();
        layout.first(getParent());
    }//GEN-LAST:event_jButtonVoltarActionPerformed

    private void jButtonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrarActionPerformed
        UsuariosDAO userDAO = new UsuariosDAO();
        String nome = nomeUsuario.getText();
        String email = emailUsuario.getText();
        String cargo = cargoUsuario.getText();
        String senha = senhaUsuario.getText();
        principal.limparErro();
        if(userDAO.verificarEmail(email) == null){
            if(senha != null && !senha.equals("") && !senha.equals("Digite sua senha")&&
                email != null && !email.equals("") && !email.equals("Digite seu e-mail")&&
                cargo != null && !cargo.equals("") && !cargo.equals("Digite seu cargo")&&
                nome != null && !nome.equals("") && !nome.equals("Digite seu nome")){
                userDAO.inserir(new Usuarios(nome, email, senha, cargo));
                CardLayout layout = (CardLayout) getParent().getLayout();
                layout.first(getParent());
            }
        }
    }//GEN-LAST:event_jButtonRegistrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cargoUsuario;
    private javax.swing.JTextField emailUsuario;
    private javax.swing.JButton jButtonRegistrar;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JLabel jLabelCargo;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelSenha;
    private javax.swing.JTextField nomeUsuario;
    private javax.swing.JPasswordField senhaUsuario;
    // End of variables declaration//GEN-END:variables
}
