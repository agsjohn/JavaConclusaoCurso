/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Janelas;

import Classes.Projetos;
import Classes.Tarefas;
import Classes.VinculoProjetos;
import Classes.VinculoProjetosUsuario;
import DAO.ProjetosDAO;
import DAO.TarefasDAO;
import DAO.VinculoProjetosDAO;
import DAO.VinculoProjetosUsuarioDAO;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author agsjohn
 */
public class Geral extends javax.swing.JPanel {
    ArrayList<Projetos> projetos = new ArrayList<Projetos>();
    ArrayList<Tarefas> tarefas = new ArrayList<Tarefas>();
    ArrayList<Projetos> meusProjetos = new ArrayList<Projetos>();
    String [] meusProjetosNomes;
    
    Principal principal;
    
    /**
     * Creates new form Geral
     */
    public Geral() {
        initComponents();
    }
    
    public Geral(Principal principal) {
        initComponents();
        this.principal = principal;
        reload();
    }
    
    public void reload(){
        //Projetos
        ProjetosDAO projetosDAO = new ProjetosDAO();
        projetos = null;
        projetos = new ArrayList<Projetos>();
        projetos = projetosDAO.listar();
        if(!projetos.isEmpty()){
            t2ListProjetos.setEnabled(true);
        } else{
            t2ListProjetos.setEnabled(false);
        }
        String [] string = new String[projetos.size()];
        for(int x=0; x < projetos.size(); x++){
            string[x] = projetos.get(x).getNomeProjeto();
        }
        t2ListProjetos.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return string.length; }
            public String getElementAt(int i) { return string[i]; }
        });
        
        
        //Meus projetos
        meusProjetos = null;
        meusProjetos = new ArrayList<>();
        VinculoProjetosDAO vpuDAO = new VinculoProjetosDAO();
        ArrayList<VinculoProjetos> vinculoProjetos = vinculoProjetos = vpuDAO.listar();
        for(int x=0; x<vinculoProjetos.size();x++){
            if(vinculoProjetos.get(x).getIdUsuario() == principal.getUsuarioLogado().getIdUsuario()){
                ProjetosDAO projDAO = new ProjetosDAO();
                meusProjetos.add(projDAO.procurar(vinculoProjetos.get(x).getIdProjeto()));
            }
        }
        
        if(!meusProjetos.isEmpty()){
            t1ListProjetos.setEnabled(true);
        } else{
            t1ListProjetos.setEnabled(false);
        }
        meusProjetosNomes = null;
        meusProjetosNomes = new String[meusProjetos.size()];
        for(int x=0; x < meusProjetos.size(); x++){
            meusProjetosNomes[x] = meusProjetos.get(x).getNomeProjeto();
        }
        t1ListProjetos.setModel(new javax.swing.AbstractListModel<String>() {
            @Override
            public int getSize() { return meusProjetosNomes.length; }
            @Override
            public String getElementAt(int i) { return meusProjetosNomes[i]; }
        });
        
        //Depois
        if(projetos.isEmpty()){
            principal.getjMenuItemNovaTarefa().setEnabled(false);
        } else{
            principal.getjMenuItemNovaTarefa().setEnabled(true);
        }
    }
    
    public Principal getPrincipal(){
        return principal;
    }
    
    public void desativarBotoes(){
        t2ButtonEdit.setEnabled(false);
        t1ButtonEdit.setEnabled(false);
        t1ButtonAbrirProjeto.setEnabled(false);
        t2ButtonAbrirProjeto.setEnabled(false);
    }
    
    public void limpar(){
        t1LabelDataCriacao.setText("");
        t1DescricaoProjeto.setText("");
        t1jResponsaveisList.setText("");
        t1LabelConcluido.setText("");
        t1ProgressBar.setMaximum(0);
        t2LabelDataCriacao.setText("");
        t2DescricaoProjeto.setText("");
        t2jResponsaveisList.setText("");
        t2LabelConcluido.setText("");
        t2ProgressBar.setMaximum(0);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPaneProjetos = new javax.swing.JTabbedPane();
        jMeusProjetos = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        t1ListProjetos = new javax.swing.JList<>();
        t1LabelTextDataCriacao = new javax.swing.JLabel();
        t1LabelDataCriacao = new javax.swing.JLabel();
        t1LabelDesc = new javax.swing.JLabel();
        t1ButtonEdit = new javax.swing.JButton();
        t1LabelConcluido = new javax.swing.JLabel();
        t1ProgressBar = new javax.swing.JProgressBar();
        t1jResponsaveis = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        t1jResponsaveisList = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        t1DescricaoProjeto = new javax.swing.JTextArea();
        t1ButtonAbrirProjeto = new javax.swing.JButton();
        jProjetos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        t2ListProjetos = new javax.swing.JList<>();
        t2LabelTextDataCriacao = new javax.swing.JLabel();
        t2LabelDataCriacao = new javax.swing.JLabel();
        t2LabelDesc = new javax.swing.JLabel();
        t2ButtonEdit = new javax.swing.JButton();
        t2LabelConcluido = new javax.swing.JLabel();
        t2ProgressBar = new javax.swing.JProgressBar();
        jScrollPane6 = new javax.swing.JScrollPane();
        t2DescricaoProjeto = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        t2jResponsaveisList = new javax.swing.JTextArea();
        t2jResponsaveis = new javax.swing.JLabel();
        t2ButtonAbrirProjeto = new javax.swing.JButton();

        setToolTipText("");
        setPreferredSize(new java.awt.Dimension(640, 360));

        jTabbedPaneProjetos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTabbedPaneProjetosFocusGained(evt);
            }
        });

        jScrollPane2.setMaximumSize(new java.awt.Dimension(102, 146));

        t1ListProjetos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        t1ListProjetos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t1ListProjetosMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                t1ListProjetosMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(t1ListProjetos);

        t1LabelTextDataCriacao.setForeground(new java.awt.Color(102, 102, 102));
        t1LabelTextDataCriacao.setText("Data de criação do projeto: ");

        t1LabelDataCriacao.setBackground(new java.awt.Color(255, 255, 255));
        t1LabelDataCriacao.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
        t1LabelDataCriacao.setOpaque(true);

        t1LabelDesc.setForeground(new java.awt.Color(102, 102, 102));
        t1LabelDesc.setText("Descrição projeto");

        t1ButtonEdit.setText("Editar projeto");
        t1ButtonEdit.setEnabled(false);
        t1ButtonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t1ButtonEditActionPerformed(evt);
            }
        });

        t1ProgressBar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        t1jResponsaveis.setForeground(new java.awt.Color(102, 102, 102));
        t1jResponsaveis.setText("Responsáveis");

        jScrollPane4.setBorder(null);

        t1jResponsaveisList.setEditable(false);
        t1jResponsaveisList.setBackground(new java.awt.Color(255, 255, 255));
        t1jResponsaveisList.setColumns(5);
        t1jResponsaveisList.setRows(5);
        t1jResponsaveisList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 4));
        jScrollPane4.setViewportView(t1jResponsaveisList);

        jScrollPane5.setBorder(null);

        t1DescricaoProjeto.setEditable(false);
        t1DescricaoProjeto.setBackground(new java.awt.Color(255, 255, 255));
        t1DescricaoProjeto.setColumns(20);
        t1DescricaoProjeto.setLineWrap(true);
        t1DescricaoProjeto.setRows(5);
        t1DescricaoProjeto.setWrapStyleWord(true);
        t1DescricaoProjeto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 4));
        jScrollPane5.setViewportView(t1DescricaoProjeto);

        t1ButtonAbrirProjeto.setText("Abrir projeto");
        t1ButtonAbrirProjeto.setEnabled(false);
        t1ButtonAbrirProjeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t1ButtonAbrirProjetoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jMeusProjetosLayout = new javax.swing.GroupLayout(jMeusProjetos);
        jMeusProjetos.setLayout(jMeusProjetosLayout);
        jMeusProjetosLayout.setHorizontalGroup(
            jMeusProjetosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMeusProjetosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jMeusProjetosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jMeusProjetosLayout.createSequentialGroup()
                        .addComponent(t1ButtonEdit)
                        .addGap(18, 18, 18)
                        .addComponent(t1ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(t1ButtonAbrirProjeto)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jMeusProjetosLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jMeusProjetosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t1LabelDesc)
                            .addGroup(jMeusProjetosLayout.createSequentialGroup()
                                .addGroup(jMeusProjetosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jMeusProjetosLayout.createSequentialGroup()
                                        .addComponent(t1LabelTextDataCriacao)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(t1LabelDataCriacao, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t1LabelConcluido, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(jMeusProjetosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(t1jResponsaveis)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 139, Short.MAX_VALUE))))
        );
        jMeusProjetosLayout.setVerticalGroup(
            jMeusProjetosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMeusProjetosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jMeusProjetosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jMeusProjetosLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jMeusProjetosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(t1LabelDataCriacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(t1LabelTextDataCriacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jMeusProjetosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(t1LabelDesc)
                            .addComponent(t1jResponsaveis))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jMeusProjetosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(t1LabelConcluido, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addGroup(jMeusProjetosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jMeusProjetosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(t1ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(t1ButtonEdit))
                    .addComponent(t1ButtonAbrirProjeto))
                .addGap(60, 60, 60))
        );

        jTabbedPaneProjetos.addTab("Meus Projetos", jMeusProjetos);

        jScrollPane1.setMaximumSize(new java.awt.Dimension(102, 146));

        t2ListProjetos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        t2ListProjetos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t2ListProjetosMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                t2ListProjetosMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(t2ListProjetos);

        t2LabelTextDataCriacao.setForeground(new java.awt.Color(102, 102, 102));
        t2LabelTextDataCriacao.setText("Data de criação do projeto: ");

        t2LabelDataCriacao.setBackground(new java.awt.Color(255, 255, 255));
        t2LabelDataCriacao.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
        t2LabelDataCriacao.setOpaque(true);

        t2LabelDesc.setForeground(new java.awt.Color(102, 102, 102));
        t2LabelDesc.setText("Descrição projeto");

        t2ButtonEdit.setText("Editar projeto");
        t2ButtonEdit.setEnabled(false);
        t2ButtonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t2ButtonEditActionPerformed(evt);
            }
        });

        t2ProgressBar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jScrollPane6.setBorder(null);

        t2DescricaoProjeto.setEditable(false);
        t2DescricaoProjeto.setBackground(new java.awt.Color(255, 255, 255));
        t2DescricaoProjeto.setColumns(20);
        t2DescricaoProjeto.setLineWrap(true);
        t2DescricaoProjeto.setRows(5);
        t2DescricaoProjeto.setWrapStyleWord(true);
        t2DescricaoProjeto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 4));
        jScrollPane6.setViewportView(t2DescricaoProjeto);

        jScrollPane7.setBorder(null);

        t2jResponsaveisList.setEditable(false);
        t2jResponsaveisList.setBackground(new java.awt.Color(255, 255, 255));
        t2jResponsaveisList.setColumns(5);
        t2jResponsaveisList.setRows(5);
        t2jResponsaveisList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 4));
        jScrollPane7.setViewportView(t2jResponsaveisList);

        t2jResponsaveis.setForeground(new java.awt.Color(102, 102, 102));
        t2jResponsaveis.setText("Responsáveis");

        t2ButtonAbrirProjeto.setText("Abrir projeto");
        t2ButtonAbrirProjeto.setEnabled(false);
        t2ButtonAbrirProjeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t2ButtonAbrirProjetoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jProjetosLayout = new javax.swing.GroupLayout(jProjetos);
        jProjetos.setLayout(jProjetosLayout);
        jProjetosLayout.setHorizontalGroup(
            jProjetosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jProjetosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jProjetosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t2ButtonEdit)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jProjetosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jProjetosLayout.createSequentialGroup()
                        .addComponent(t2ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(t2ButtonAbrirProjeto))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jProjetosLayout.createSequentialGroup()
                        .addComponent(t2LabelTextDataCriacao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(t2LabelDataCriacao, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(t2LabelConcluido, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jProjetosLayout.createSequentialGroup()
                        .addGroup(jProjetosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t2LabelDesc))
                        .addGap(25, 25, 25)
                        .addGroup(jProjetosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t2jResponsaveis)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(139, Short.MAX_VALUE))
        );
        jProjetosLayout.setVerticalGroup(
            jProjetosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jProjetosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jProjetosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jProjetosLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jProjetosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(t2LabelDataCriacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(t2LabelTextDataCriacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jProjetosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(t2jResponsaveis)
                            .addComponent(t2LabelDesc))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jProjetosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(t2LabelConcluido, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jProjetosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t2ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t2ButtonEdit)
                    .addComponent(t2ButtonAbrirProjeto))
                .addGap(60, 60, 60))
        );

        jTabbedPaneProjetos.addTab("Todos Projetos", jProjetos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jTabbedPaneProjetos)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneProjetos, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void t2ButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t2ButtonEditActionPerformed
        int selecao = t2ListProjetos.getSelectedIndex();
        ProjetosDAO projDAO = new ProjetosDAO();
        projetos = projDAO.listar();
        
        int idProjeto = projetos.get(selecao).getIdProjeto();
        
        CardLayout layout = (CardLayout) getParent().getLayout();
        EditProj editProjPane = new EditProj(this, idProjeto, "geralWindow");
        getParent().add(editProjPane, "editProjWindow");
        layout.show(getParent(), "editProjWindow");
        reload();
    }//GEN-LAST:event_t2ButtonEditActionPerformed

    private void t2ListProjetosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t2ListProjetosMouseReleased
        if(t2ListProjetos.getModel().getSize() != 0){
            int selecao = t2ListProjetos.getSelectedIndex();
            TarefasDAO tareDAO = new TarefasDAO();
            ProjetosDAO projDAO = new ProjetosDAO();
            projetos = projDAO.listar();

            t2ButtonEdit.setEnabled(true);
            t2ButtonAbrirProjeto.setEnabled(true);

            t2DescricaoProjeto.setText(projetos.get(selecao).getDescricaoProjeto());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            t2LabelDataCriacao.setText(sdf.format(projetos.get(selecao).getDataCriacaoProjeto()));
            if(projetos.get(selecao).getDataConclusaoProjeto() == null){
                t2LabelConcluido.setText("");
            } else{
                t2LabelConcluido.setText("Projeto concluído em " + sdf.format(projetos.get(selecao).getDataConclusaoProjeto()));
            }

            int idProjeto = projetos.get(selecao).getIdProjeto();
            tarefas = tareDAO.procurarIdProjeto(idProjeto);
            int feitas=0;
            for(int x=0; x < tarefas.size(); x++){
                if(tarefas.get(x).getEstadoTarefa().equals("Concluída")){
                    feitas++;
                }
            }
            t2ProgressBar.setMaximum(tarefas.size());
            t2ProgressBar.setValue(feitas);

            ArrayList<VinculoProjetosUsuario> vup = new ArrayList<VinculoProjetosUsuario>();
            VinculoProjetosUsuarioDAO vupDAO = new VinculoProjetosUsuarioDAO();
            ArrayList<VinculoProjetosUsuario> vinculoUsuariosProjeto = new ArrayList<VinculoProjetosUsuario>();
            vup = vupDAO.listar();
            for(int x=0; x<vup.size(); x++){
                if(vup.get(x).getIdProjeto() == idProjeto){
                    vinculoUsuariosProjeto.add(vup.get(x));
                }
            }
            String usuariosProjeto = "";
            for(int x=0; x<vinculoUsuariosProjeto.size(); x++){
                usuariosProjeto+= vinculoUsuariosProjeto.get(x).getNomeUsuario() + "\n";
            }
            t2jResponsaveisList.setText(usuariosProjeto);
        }
    }//GEN-LAST:event_t2ListProjetosMouseReleased

    private void t2ListProjetosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t2ListProjetosMouseClicked
        int cliques = evt.getClickCount();
        if(t2ListProjetos.getSelectedIndex() != -1){
            if(cliques > 1){
                Object[] options = { "Sim", "Não"};
                int opcao = JOptionPane.showOptionDialog(null, "Deseja apagar o projeto? Essa ação é irreversível", "Apagar projeto", 1, 2, null, options, options[0]);
                if(opcao == 0){
                    ProjetosDAO projDAO = new ProjetosDAO();
                    Projetos projeto = projetos.get(t2ListProjetos.getSelectedIndex());
                    projDAO.excluir(projeto);
                    limpar();
                    desativarBotoes();
                    reload();
                }
            }
        }
    }//GEN-LAST:event_t2ListProjetosMouseClicked

    private void jTabbedPaneProjetosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTabbedPaneProjetosFocusGained
        reload();
        desativarBotoes();
    }//GEN-LAST:event_jTabbedPaneProjetosFocusGained

    private void t1ButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t1ButtonEditActionPerformed
        int selecao = t1ListProjetos.getSelectedIndex();
        ProjetosDAO projDAO = new ProjetosDAO();

        int idProjeto = meusProjetos.get(selecao).getIdProjeto();

        CardLayout layout = (CardLayout) getParent().getLayout();
        EditProj editProjPane = new EditProj(this, idProjeto, "geralWindow");
        getParent().add(editProjPane, "editProjWindow");
        layout.show(getParent(), "editProjWindow");
        reload();
    }//GEN-LAST:event_t1ButtonEditActionPerformed

    private void t1ListProjetosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t1ListProjetosMouseReleased
        if(t1ListProjetos.getModel().getSize() != 0){
            int selecao = t1ListProjetos.getSelectedIndex();
            TarefasDAO tareDAO = new TarefasDAO();

            t1ButtonEdit.setEnabled(true);
            t1ButtonAbrirProjeto.setEnabled(true);

            t1DescricaoProjeto.setText(meusProjetos.get(selecao).getDescricaoProjeto());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            t1LabelDataCriacao.setText(sdf.format(meusProjetos.get(selecao).getDataCriacaoProjeto()));
            if(meusProjetos.get(selecao).getDataConclusaoProjeto() == null){
                t1LabelConcluido.setText("");
            } else{
                t1LabelConcluido.setText("Projeto concluído em " + sdf.format(meusProjetos.get(selecao).getDataConclusaoProjeto()));
            }

            int idProjeto = meusProjetos.get(selecao).getIdProjeto();
            tarefas = tareDAO.procurarIdProjeto(idProjeto);
            int feitas=0;
            for(int x=0; x < tarefas.size(); x++){
                if(tarefas.get(x).getEstadoTarefa().equals("Concluída")){
                    feitas++;
                }
            }
            t1ProgressBar.setMaximum(tarefas.size());
            t1ProgressBar.setValue(feitas);

            ArrayList<VinculoProjetosUsuario> vup = new ArrayList<VinculoProjetosUsuario>();
            VinculoProjetosUsuarioDAO vupDAO = new VinculoProjetosUsuarioDAO();
            ArrayList<VinculoProjetosUsuario> vinculoUsuariosProjeto = new ArrayList<VinculoProjetosUsuario>();
            vup = vupDAO.listar();
            for(int x=0; x<vup.size(); x++){
                if(vup.get(x).getIdProjeto() == idProjeto){
                    vinculoUsuariosProjeto.add(vup.get(x));
                }
            }
            String usuariosProjeto = "";
            for(int x=0; x<vinculoUsuariosProjeto.size(); x++){
                usuariosProjeto+= vinculoUsuariosProjeto.get(x).getNomeUsuario() + "\n";
            }
            t1jResponsaveisList.setText(usuariosProjeto);
        }
    }//GEN-LAST:event_t1ListProjetosMouseReleased

    private void t1ListProjetosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t1ListProjetosMouseClicked
        int cliques = evt.getClickCount();
        if(t1ListProjetos.getSelectedIndex() != -1){
            if(cliques > 1){
                Object[] options = { "Sim", "Não"};
                int opcao = JOptionPane.showOptionDialog(null, "Deseja apagar o projeto? Essa ação é irreversível", "Apagar projeto", 1, 2, null, options, options[0]);
                if(opcao == 0){
                    ProjetosDAO projDAO = new ProjetosDAO();
                    Projetos projeto = meusProjetos.get(t1ListProjetos.getSelectedIndex());
                    projDAO.excluir(projeto);
                    limpar();
                    desativarBotoes();
                    reload();
                }
            }
        }
    }//GEN-LAST:event_t1ListProjetosMouseClicked

    private void t1ButtonAbrirProjetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t1ButtonAbrirProjetoActionPerformed
        int atual = t1ListProjetos.getSelectedIndex();
        int idProjeto = meusProjetos.get(atual).getIdProjeto();
        CardLayout layout = (CardLayout) getParent().getLayout();
        principal.setTarefasPane(new AbrirTarefas(this, idProjeto));
        principal.setIdProjetoAberto(idProjeto);
        principal.setLayoutAtual("tarefasWindow");
        getParent().add(principal.getTarefasPane(), "tarefasWindow");
        layout.show(getParent(), "tarefasWindow");
    }//GEN-LAST:event_t1ButtonAbrirProjetoActionPerformed

    private void t2ButtonAbrirProjetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t2ButtonAbrirProjetoActionPerformed
        int atual = t2ListProjetos.getSelectedIndex();
        int idProjeto = projetos.get(atual).getIdProjeto();
        CardLayout layout = (CardLayout) getParent().getLayout();
        
        Boolean meuProjeto = false;
        for(Projetos projeto : meusProjetos){
            if(projeto.getIdProjeto() == idProjeto){
                meuProjeto = true;
            }
        }
        AbrirTarefas abrirTarefas = new AbrirTarefas(this, idProjeto);
        if(meuProjeto == false){
            abrirTarefas.esconderMinhasTarefas();
        }
        principal.setTarefasPane(abrirTarefas);
        principal.setIdProjetoAberto(idProjeto);
        principal.setLayoutAtual("tarefasWindow");
        getParent().add(principal.getTarefasPane(), "tarefasWindow");
        layout.show(getParent(), "tarefasWindow");
    }//GEN-LAST:event_t2ButtonAbrirProjetoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jMeusProjetos;
    private javax.swing.JPanel jProjetos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPaneProjetos;
    private javax.swing.JButton t1ButtonAbrirProjeto;
    private javax.swing.JButton t1ButtonEdit;
    private javax.swing.JTextArea t1DescricaoProjeto;
    private javax.swing.JLabel t1LabelConcluido;
    private javax.swing.JLabel t1LabelDataCriacao;
    private javax.swing.JLabel t1LabelDesc;
    private javax.swing.JLabel t1LabelTextDataCriacao;
    private javax.swing.JList<String> t1ListProjetos;
    private javax.swing.JProgressBar t1ProgressBar;
    private javax.swing.JLabel t1jResponsaveis;
    private javax.swing.JTextArea t1jResponsaveisList;
    private javax.swing.JButton t2ButtonAbrirProjeto;
    private javax.swing.JButton t2ButtonEdit;
    private javax.swing.JTextArea t2DescricaoProjeto;
    private javax.swing.JLabel t2LabelConcluido;
    private javax.swing.JLabel t2LabelDataCriacao;
    private javax.swing.JLabel t2LabelDesc;
    private javax.swing.JLabel t2LabelTextDataCriacao;
    private javax.swing.JList<String> t2ListProjetos;
    private javax.swing.JProgressBar t2ProgressBar;
    private javax.swing.JLabel t2jResponsaveis;
    private javax.swing.JTextArea t2jResponsaveisList;
    // End of variables declaration//GEN-END:variables
}
