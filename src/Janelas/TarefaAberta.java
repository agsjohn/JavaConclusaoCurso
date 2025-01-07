/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Janelas;

import Classes.Tarefas;
import Classes.VinculoTarefasUsuario;
import DAO.TarefasDAO;
import DAO.VinculoTarefasUsuarioDAO;
import java.awt.CardLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author agsjohn
 */
public class TarefaAberta extends javax.swing.JPanel {

    /**
     * Creates new form TarefaAberta
     */
    
    Principal principal;
    Tarefas tarefa;
    
    public TarefaAberta() {
        initComponents();
    }
    
    public TarefaAberta(Principal principal, Tarefas tarefa) {
        initComponents();
        
        this.tarefa = tarefa;
        this.principal = principal;
        load();
    }
    
    public void load(){
        jLabelTit.setText(tarefa.getNomeTarefa());
        jTextDesc.setText(tarefa.getDescricaoTarefa());
        jLabelEstado.setText("Estado: "+tarefa.getEstadoTarefa());
        
        VinculoTarefasUsuarioDAO vtuDAO = new VinculoTarefasUsuarioDAO();
        ArrayList<VinculoTarefasUsuario> listaVtu = vtuDAO.listar();
        ArrayList<VinculoTarefasUsuario> atualVtu = new ArrayList<>();
        String nomes = "";
        for(int x=0; x<listaVtu.size(); x++){
            if(listaVtu.get(x).getIdTarefa() == tarefa.getIdTarefa()){
                atualVtu.add(listaVtu.get(x));
                nomes+= listaVtu.get(x).getNomeUsuario() + "\n";
            }
        }
        if(atualVtu.size() > 1){
            jLabelResp.setText("Responsáveis");
        }
        jTextListResp.setText(nomes);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataCriacao = "Tarefa criada em " + sdf.format(tarefa.getDataCriacaoTarefa());
        jLabelCriacao.setText(dataCriacao);
        
        if(tarefa.getDataConslusaoTarefa() != null){
            String dataConclusao = "Tarefa concluida em " + sdf.format(tarefa.getDataConslusaoTarefa());
            jLabelConclusao.setText(dataConclusao);
        }
    }
    
    public void reload(){
        TarefasDAO tareDAO = new TarefasDAO();
        Tarefas editado = tareDAO.procurar(tarefa.getIdTarefa());
        tarefa = editado;
        
        jLabelTit.setText(tarefa.getNomeTarefa());
        jTextDesc.setText(tarefa.getDescricaoTarefa());
        jLabelEstado.setText("Estado: "+tarefa.getEstadoTarefa());
        
        VinculoTarefasUsuarioDAO vtuDAO = new VinculoTarefasUsuarioDAO();
        ArrayList<VinculoTarefasUsuario> listaVtu = vtuDAO.listar();
        ArrayList<VinculoTarefasUsuario> atualVtu = new ArrayList<>();
        String nomes = "";
        for(int x=0; x<listaVtu.size(); x++){
            if(listaVtu.get(x).getIdTarefa() == tarefa.getIdTarefa()){
                atualVtu.add(listaVtu.get(x));
                nomes+= listaVtu.get(x).getNomeUsuario() + "\n";
            }
        }
        if(atualVtu.size() > 1){
            jLabelResp.setText("Responsáveis");
        }
        jTextListResp.setText(nomes);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataCriacao = "Tarefa criada em " + sdf.format(tarefa.getDataCriacaoTarefa());
        jLabelCriacao.setText(dataCriacao);
        
        if(tarefa.getDataConslusaoTarefa() != null){
            String dataConclusao = "Tarefa concluida em " + sdf.format(tarefa.getDataConslusaoTarefa());
            jLabelConclusao.setText(dataConclusao);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTit = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextDesc = new javax.swing.JTextArea();
        jLabelResp = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabelEstado = new javax.swing.JLabel();
        jLabelCriacao = new javax.swing.JLabel();
        jLabelConclusao = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextListResp = new javax.swing.JTextArea();

        jLabelTit.setText("Titulo");

        jTextDesc.setEditable(false);
        jTextDesc.setBackground(new java.awt.Color(255, 255, 255));
        jTextDesc.setColumns(20);
        jTextDesc.setLineWrap(true);
        jTextDesc.setRows(5);
        jTextDesc.setWrapStyleWord(true);
        jTextDesc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 4));
        jTextDesc.setFocusable(false);
        jScrollPane1.setViewportView(jTextDesc);

        jLabelResp.setText("Responsável");

        jButton1.setText("Voltar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Editar tarefa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabelEstado.setText("Estado");

        jLabelCriacao.setText("jLabel5");

        jTextListResp.setEditable(false);
        jTextListResp.setBackground(new java.awt.Color(255, 255, 255));
        jTextListResp.setRows(5);
        jTextListResp.setFocusable(false);
        jScrollPane3.setViewportView(jTextListResp);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelConclusao)
                            .addComponent(jLabelCriacao))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(65, 65, 65)
                                .addComponent(jButton1))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelTit)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelEstado)))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelResp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(76, 76, 76))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(222, 222, 222))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jLabelCriacao)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTit)
                    .addComponent(jLabelResp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelEstado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jLabelConclusao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(54, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CardLayout layout = (CardLayout) getParent().getLayout();
        principal.tarefasPane.desativarBotoes();
        principal.setLayoutAtual("tarefasWindow");
        layout.show(getParent(), "tarefasWindow");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        CardLayout layout = (CardLayout) getParent().getLayout();
        EditTarefa editTarefaPane = new EditTarefa(principal, tarefa);
        principal.getContentPane().add(editTarefaPane, "editTarefaWindow");
        layout.show(getParent(), "editTarefaWindow");
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabelConclusao;
    private javax.swing.JLabel jLabelCriacao;
    private javax.swing.JLabel jLabelEstado;
    private javax.swing.JLabel jLabelResp;
    private javax.swing.JLabel jLabelTit;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextDesc;
    private javax.swing.JTextArea jTextListResp;
    // End of variables declaration//GEN-END:variables
}
