/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Janelas;

import Classes.Projetos;
import Classes.Tarefas;
import Classes.Transferir;
import Classes.VinculoTarefas;
import Classes.VinculoTarefasUsuario;
import DAO.ProjetosDAO;
import DAO.TarefasDAO;
import DAO.VinculoTarefasDAO;
import DAO.VinculoTarefasUsuarioDAO;
import java.awt.CardLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

/**
 *
 * @author agsjohn
 */
public class AbrirTarefas extends javax.swing.JPanel {

    /**
     * Creates new form Tarefas
     */
    
    Projetos projeto;
    int idProjeto;
    Geral geral;
    Principal principal;
    ArrayList<Tarefas> t1lista1 = new ArrayList<>();
    ArrayList<Tarefas> t1lista2 = new ArrayList<>();
    ArrayList<Tarefas> t1lista3 = new ArrayList<>();
    ArrayList<Tarefas> t2lista1 = new ArrayList<>();
    ArrayList<Tarefas> t2lista2 = new ArrayList<>();
    ArrayList<Tarefas> t2lista3 = new ArrayList<>();
    int idTarefa = -1;
    JList<?> sourceList;
    
    public AbrirTarefas() {
        initComponents();
    }
    
    public AbrirTarefas(Geral geral, int idProjeto) {
        initComponents();
        
        this.geral = geral;
        this.idProjeto = idProjeto;
        ProjetosDAO projDAO = new ProjetosDAO();
        this.principal = geral.getPrincipal();
        this.projeto = projDAO.procurar(idProjeto);
        
        modificarList(t1ListPendente);
        modificarList(t1ListProgresso);
        modificarList(t1ListConcluido);
        modificarList(t2ListPendente);
        modificarList(t2ListProgresso);
        modificarList(t2ListConcluido);

        load();
    }
    
    public void load(){
        //Minhas tarefas
        t1lista1 = new ArrayList<>();
        t1lista2 = new ArrayList<>();
        t1lista3 = new ArrayList<>();
        
        ArrayList<Tarefas> tarefas = new ArrayList<>();
        TarefasDAO tarefDAO = new TarefasDAO();
        tarefas = tarefDAO.listar();
        
        VinculoTarefasDAO vtDAO = new VinculoTarefasDAO();
        ArrayList<VinculoTarefas> vt = vtDAO.listar();
        
        ArrayList<Tarefas> minhasTarefas = new ArrayList<>();
        for(int x=0; x<tarefas.size(); x++){
            for(int y=0; y<vt.size(); y++){
                if(tarefas.get(x).getIdTarefa() == vt.get(y).getIdTarefa() && principal.getUsuarioLogado().getIdUsuario() == vt.get(y).getIdUsuario()){
                    minhasTarefas.add(tarefas.get(x));
                }
            }
        }
        
        ProjetosDAO projDAO = new ProjetosDAO();
        this.projeto = projDAO.procurar(idProjeto);
        
        ArrayList<Tarefas> t1tarefasProj = new ArrayList<>();
        for(int x=0; x < minhasTarefas.size(); x++){
            if(minhasTarefas.get(x).getIdProjeto() == projeto.getIdProjeto()){
                t1tarefasProj.add(minhasTarefas.get(x));
            }
        }
        
        DefaultListModel<String> t1listaPendente = new DefaultListModel<>();
        DefaultListModel<String> t1listaProgresso = new DefaultListModel<>();
        DefaultListModel<String> t1listaConcluido = new DefaultListModel<>();
        for(int x=0; x < t1tarefasProj.size(); x++){
            if(t1tarefasProj.get(x).getEstadoTarefa().equals("Pendente")){
                t1lista1.add(t1tarefasProj.get(x));
                t1listaPendente.addElement(t1tarefasProj.get(x).getNomeTarefa());
            } else if (t1tarefasProj.get(x).getEstadoTarefa().equals("Em progresso")){
                t1lista2.add(t1tarefasProj.get(x));
                t1listaProgresso.addElement(t1tarefasProj.get(x).getNomeTarefa());
            } else if (t1tarefasProj.get(x).getEstadoTarefa().equals("Concluída")){
                t1lista3.add(t1tarefasProj.get(x));
                t1listaConcluido.addElement(t1tarefasProj.get(x).getNomeTarefa());
            }
        }
        
        t1ListPendente.setModel(t1listaPendente);
        t1ListProgresso.setModel(t1listaProgresso);
        t1ListConcluido.setModel(t1listaConcluido);
        
        
        //Todas tarefas
        t2lista1 = new ArrayList<>();
        t2lista2 = new ArrayList<>();
        t2lista3 = new ArrayList<>();
        
        projDAO = new ProjetosDAO();
        this.projeto = projDAO.procurar(idProjeto);
        
        ArrayList<Tarefas> t2tarefasProj = new ArrayList<>();
        for(int x=0; x < tarefas.size(); x++){
            if(tarefas.get(x).getIdProjeto() == projeto.getIdProjeto()){
                t2tarefasProj.add(tarefas.get(x));
            }
        }
        
        DefaultListModel<String> t2listaPendente = new DefaultListModel<>();
        DefaultListModel<String> t2listaProgresso = new DefaultListModel<>();
        DefaultListModel<String> t2listaConcluido = new DefaultListModel<>();
        for(int x=0; x < t2tarefasProj.size(); x++){
            if(t2tarefasProj.get(x).getEstadoTarefa().equals("Pendente")){
                t2lista1.add(t2tarefasProj.get(x));
                t2listaPendente.addElement(t2tarefasProj.get(x).getNomeTarefa());
            } else if (t2tarefasProj.get(x).getEstadoTarefa().equals("Em progresso")){
                t2lista2.add(t2tarefasProj.get(x));
                t2listaProgresso.addElement(t2tarefasProj.get(x).getNomeTarefa());
            } else if (t2tarefasProj.get(x).getEstadoTarefa().equals("Concluída")){
                t2lista3.add(t2tarefasProj.get(x));
                t2listaConcluido.addElement(t2tarefasProj.get(x).getNomeTarefa());
            }
        }
        
        t2ListPendente.setModel(t2listaPendente);
        t2ListProgresso.setModel(t2listaProgresso);
        t2ListConcluido.setModel(t2listaConcluido);
    }
    
    public void ativarBotoes(){
        t1ButtonAbrirTarefa.setEnabled(true);
        t2ButtonAbrirTarefa.setEnabled(true);
    }
    
    public void desativarBotoes(){
        t1ButtonAbrirTarefa.setEnabled(false);
        t2ButtonAbrirTarefa.setEnabled(false);
    }
    
    public void modificarList(JList<String> lista) {
        lista.setDragEnabled(true);
        lista.setTransferHandler(new Transferir(this));
    }

    public JList<?> getSourceList() {
        return sourceList;
    }

    public void setSourceList(JList<?> sourceList) {
        this.sourceList = sourceList;
    }

    public JList<String> getT1ListConcluido() {
        return t1ListConcluido;
    }

    public JList<String> getT1ListPendente() {
        return t1ListPendente;
    }

    public JList<String> getT1ListProgresso() {
        return t1ListProgresso;
    }

    public JList<String> getT2ListConcluido() {
        return t2ListConcluido;
    }

    public JList<String> getT2ListPendente() {
        return t2ListPendente;
    }

    public JList<String> getT2ListProgresso() {
        return t2ListProgresso;
    }

    public ArrayList<Tarefas> getT1lista1() {
        return t1lista1;
    }

    public ArrayList<Tarefas> getT1lista2() {
        return t1lista2;
    }

    public ArrayList<Tarefas> getT1lista3() {
        return t1lista3;
    }

    public ArrayList<Tarefas> getT2lista1() {
        return t2lista1;
    }

    public ArrayList<Tarefas> getT2lista2() {
        return t2lista2;
    }

    public ArrayList<Tarefas> getT2lista3() {
        return t2lista3;
    }

    public int getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(int idTarefa) {
        this.idTarefa = idTarefa;
    }

    public JPanel getjPanelMtarefas() {
        return jPanelMtarefas;
    }

    public void setjPanelMtarefas(JPanel jPanelMtarefas) {
        this.jPanelMtarefas = jPanelMtarefas;
    }
    
    public void esconderMinhasTarefas(){
        int index = jTabbedPane1.indexOfComponent(jPanelMtarefas);
        if (index != -1) {
            jTabbedPane1.removeTabAt(index);
//            jTabbedPane1.addTab("Meus Projetos", jPanelMtarefas);
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelMtarefas = new javax.swing.JPanel();
        t1LabelPendente = new javax.swing.JLabel();
        t1LabelProgresso = new javax.swing.JLabel();
        t1LabelConcluido = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        t1ListPendente = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        t1ListProgresso = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        t1ListConcluido = new javax.swing.JList<>();
        t1ButtonAbrirTarefa = new javax.swing.JButton();
        t1ButtonVoltar = new javax.swing.JButton();
        jPanelTarefas = new javax.swing.JPanel();
        t2LabelPendente = new javax.swing.JLabel();
        t2LabelProgresso = new javax.swing.JLabel();
        t2LabelConcluido = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        t2ListPendente = new javax.swing.JList<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        t2ListProgresso = new javax.swing.JList<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        t2ListConcluido = new javax.swing.JList<>();
        t2ButtonAbrirTarefa = new javax.swing.JButton();
        t2ButtonVoltar = new javax.swing.JButton();

        jTabbedPane1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTabbedPane1FocusGained(evt);
            }
        });
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        t1LabelPendente.setText("Pendentes");

        t1LabelProgresso.setText("Em progresso");

        t1LabelConcluido.setText("Concluídos");

        t1ListPendente.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        t1ListPendente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                t1ListPendenteFocusGained(evt);
            }
        });
        t1ListPendente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t1ListPendenteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(t1ListPendente);

        t1ListProgresso.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        t1ListProgresso.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                t1ListProgressoFocusGained(evt);
            }
        });
        t1ListProgresso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t1ListProgressoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(t1ListProgresso);

        t1ListConcluido.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        t1ListConcluido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                t1ListConcluidoFocusGained(evt);
            }
        });
        t1ListConcluido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t1ListConcluidoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(t1ListConcluido);

        t1ButtonAbrirTarefa.setText("Abrir tarefa");
        t1ButtonAbrirTarefa.setEnabled(false);
        t1ButtonAbrirTarefa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t1ButtonAbrirTarefaActionPerformed(evt);
            }
        });

        t1ButtonVoltar.setText("Voltar");
        t1ButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t1ButtonVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelMtarefasLayout = new javax.swing.GroupLayout(jPanelMtarefas);
        jPanelMtarefas.setLayout(jPanelMtarefasLayout);
        jPanelMtarefasLayout.setHorizontalGroup(
            jPanelMtarefasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMtarefasLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanelMtarefasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelMtarefasLayout.createSequentialGroup()
                        .addGroup(jPanelMtarefasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t1LabelPendente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelMtarefasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t1LabelProgresso)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(58, 58, 58)
                        .addGroup(jPanelMtarefasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t1LabelConcluido)))
                    .addGroup(jPanelMtarefasLayout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(t1ButtonAbrirTarefa)
                        .addGap(18, 18, 18)
                        .addComponent(t1ButtonVoltar)
                        .addGap(176, 176, 176)))
                .addGap(70, 70, 70))
        );
        jPanelMtarefasLayout.setVerticalGroup(
            jPanelMtarefasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMtarefasLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanelMtarefasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t1LabelPendente)
                    .addComponent(t1LabelProgresso)
                    .addComponent(t1LabelConcluido))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelMtarefasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMtarefasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(jPanelMtarefasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t1ButtonAbrirTarefa)
                    .addComponent(t1ButtonVoltar))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Minhas tarefas", jPanelMtarefas);

        t2LabelPendente.setText("Pendentes");

        t2LabelProgresso.setText("Em progresso");

        t2LabelConcluido.setText("Concluídos");

        t2ListPendente.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        t2ListPendente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                t2ListPendenteFocusGained(evt);
            }
        });
        t2ListPendente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t2ListPendenteMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(t2ListPendente);

        t2ListProgresso.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        t2ListProgresso.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                t2ListProgressoFocusGained(evt);
            }
        });
        t2ListProgresso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t2ListProgressoMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(t2ListProgresso);

        t2ListConcluido.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        t2ListConcluido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                t2ListConcluidoFocusGained(evt);
            }
        });
        t2ListConcluido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t2ListConcluidoMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(t2ListConcluido);

        t2ButtonAbrirTarefa.setText("Abrir tarefa");
        t2ButtonAbrirTarefa.setEnabled(false);
        t2ButtonAbrirTarefa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t2ButtonAbrirTarefaActionPerformed(evt);
            }
        });

        t2ButtonVoltar.setText("Voltar");
        t2ButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t2ButtonVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelTarefasLayout = new javax.swing.GroupLayout(jPanelTarefas);
        jPanelTarefas.setLayout(jPanelTarefasLayout);
        jPanelTarefasLayout.setHorizontalGroup(
            jPanelTarefasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTarefasLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanelTarefasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelTarefasLayout.createSequentialGroup()
                        .addGroup(jPanelTarefasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t2LabelPendente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelTarefasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t2LabelProgresso)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(58, 58, 58)
                        .addGroup(jPanelTarefasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t2LabelConcluido))
                        .addGap(68, 68, 68))
                    .addGroup(jPanelTarefasLayout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(t2ButtonAbrirTarefa)
                        .addGap(18, 18, 18)
                        .addComponent(t2ButtonVoltar)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanelTarefasLayout.setVerticalGroup(
            jPanelTarefasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTarefasLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanelTarefasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t2LabelPendente)
                    .addComponent(t2LabelProgresso)
                    .addComponent(t2LabelConcluido))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelTarefasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTarefasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane5)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(jPanelTarefasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t2ButtonAbrirTarefa)
                    .addComponent(t2ButtonVoltar))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Todas tarefas", jPanelTarefas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void t1ButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t1ButtonVoltarActionPerformed
        CardLayout layout = (CardLayout) getParent().getLayout();
        geral.getPrincipal().setLayoutAtual("geralWindow");
        geral.limpar();
        geral.desativarBotoes();
        layout.show(getParent(), "geralWindow");
    }//GEN-LAST:event_t1ButtonVoltarActionPerformed

    private void t1ButtonAbrirTarefaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t1ButtonAbrirTarefaActionPerformed
        int selecao = -1;
        
        ArrayList<Tarefas> listaAtual = new ArrayList<>();
        
        if(t1ListPendente.getSelectedIndex() > -1){
            listaAtual = t1lista1;
            selecao = t1ListPendente.getSelectedIndex();
        } else if(t1ListProgresso.getSelectedIndex() > -1){
            listaAtual = t1lista2;
            selecao = t1ListProgresso.getSelectedIndex();
        } else if(t1ListConcluido.getSelectedIndex() > -1){
            listaAtual = t1lista3;
            selecao = t1ListConcluido.getSelectedIndex();
        }
        
        Principal principal = geral.getPrincipal();
        TarefaAberta tarefaAbertaPane = new TarefaAberta(principal, listaAtual.get(selecao));
        principal.setLayoutAtual("tarefaAbertaWindow");
        principal.getContentPane().add(tarefaAbertaPane, "tarefaAbertaWindow");
        CardLayout layout = (CardLayout) principal.getContentPane().getLayout();
        layout.show(principal.getContentPane(), "tarefaAbertaWindow");
    }//GEN-LAST:event_t1ButtonAbrirTarefaActionPerformed

    private void t1ListPendenteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t1ListPendenteFocusGained
        t1ListConcluido.clearSelection();
        t1ListProgresso.clearSelection();
    }//GEN-LAST:event_t1ListPendenteFocusGained

    private void t1ListProgressoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t1ListProgressoFocusGained
        t1ListConcluido.clearSelection();
        t1ListPendente.clearSelection();
    }//GEN-LAST:event_t1ListProgressoFocusGained

    private void t1ListConcluidoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t1ListConcluidoFocusGained
        t1ListPendente.clearSelection();
        t1ListProgresso.clearSelection();
    }//GEN-LAST:event_t1ListConcluidoFocusGained

    private void t1ListPendenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t1ListPendenteMouseClicked
        int cliques = evt.getClickCount();
        if(t1ListPendente.getModel().getSize() != 0){
            ativarBotoes();
        } else{
            desativarBotoes();
        }
        if(t1ListPendente.getSelectedIndex() != -1){
            if(cliques > 1){
                Object[] options = { "Sim", "Não"};
                int opcao = JOptionPane.showOptionDialog(null, "Deseja apagar a tarefa? Essa ação é irreversível", "Apagar tarefa", 1, 2, null, options, options[0]);
                if(opcao == 0){
                    TarefasDAO tareDAO = new TarefasDAO();
                    Tarefas tarefa = t1lista1.get(t1ListPendente.getSelectedIndex());
                    tareDAO.excluir(tarefa);
                    load();
                }
            }
        }
    }//GEN-LAST:event_t1ListPendenteMouseClicked

    private void t1ListProgressoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t1ListProgressoMouseClicked
        int cliques = evt.getClickCount();
        if(t1ListProgresso.getModel().getSize() != 0){
            ativarBotoes();
        } else{
            desativarBotoes();
        }
        if(t1ListProgresso.getSelectedIndex() != -1){
            ativarBotoes();
            if(cliques > 1){
                Object[] options = { "Sim", "Não"};
                int opcao = JOptionPane.showOptionDialog(null, "Deseja apagar a tarefa? Essa ação é irreversível", "Apagar tarefa", 1, 2, null, options, options[0]);
                if(opcao == 0){
                    TarefasDAO tareDAO = new TarefasDAO();
                    Tarefas tarefa = t1lista2.get(t1ListProgresso.getSelectedIndex());
                    tareDAO.excluir(tarefa);
                    load();
                }
            }
        }
    }//GEN-LAST:event_t1ListProgressoMouseClicked

    private void t1ListConcluidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t1ListConcluidoMouseClicked
        int cliques = evt.getClickCount();
        if(t1ListConcluido.getModel().getSize() != 0){
            ativarBotoes();
        } else{
            desativarBotoes();
        }
        if(t1ListConcluido.getSelectedIndex() != -1){
            if(cliques > 1){
                Object[] options = { "Sim", "Não"};
                int opcao = JOptionPane.showOptionDialog(null, "Deseja apagar a tarefa? Essa ação é irreversível", "Apagar tarefa", 1, 2, null, options, options[0]);
                if(opcao == 0){
                    TarefasDAO tareDAO = new TarefasDAO();
                    Tarefas tarefa = t1lista3.get(t1ListConcluido.getSelectedIndex());
                    tareDAO.excluir(tarefa);
                    load();
                }
            }
        }
    }//GEN-LAST:event_t1ListConcluidoMouseClicked

    private void t2ListPendenteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t2ListPendenteFocusGained
        t2ListConcluido.clearSelection();
        t2ListProgresso.clearSelection();
    }//GEN-LAST:event_t2ListPendenteFocusGained

    private void t2ListPendenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t2ListPendenteMouseClicked
        int cliques = evt.getClickCount();
        if(t2ListPendente.getModel().getSize() != 0){
            ativarBotoes();
        } else{
            desativarBotoes();
        }
        if(t2ListPendente.getSelectedIndex() != -1){
            if(cliques > 1){
                Object[] options = { "Sim", "Não"};
                int opcao = JOptionPane.showOptionDialog(null, "Deseja apagar a tarefa? Essa ação é irreversível", "Apagar tarefa", 1, 2, null, options, options[0]);
                if(opcao == 0){
                    TarefasDAO tareDAO = new TarefasDAO();
                    Tarefas tarefa = t2lista1.get(t2ListPendente.getSelectedIndex());
                    tareDAO.excluir(tarefa);
                    load();
                }
            }
        }
    }//GEN-LAST:event_t2ListPendenteMouseClicked

    private void t2ListProgressoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t2ListProgressoFocusGained
        t2ListConcluido.clearSelection();
        t2ListPendente.clearSelection();
    }//GEN-LAST:event_t2ListProgressoFocusGained

    private void t2ListProgressoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t2ListProgressoMouseClicked
        int cliques = evt.getClickCount();
        if(t2ListProgresso.getModel().getSize() != 0){
            ativarBotoes();
        } else{
            desativarBotoes();
        }
        if(t2ListProgresso.getSelectedIndex() != -1){
            if(cliques > 1){
                Object[] options = { "Sim", "Não"};
                int opcao = JOptionPane.showOptionDialog(null, "Deseja apagar a tarefa? Essa ação é irreversível", "Apagar tarefa", 1, 2, null, options, options[0]);
                if(opcao == 0){
                    TarefasDAO tareDAO = new TarefasDAO();
                    Tarefas tarefa = t2lista2.get(t2ListProgresso.getSelectedIndex());
                    tareDAO.excluir(tarefa);
                    load();
                }
            }
        }
    }//GEN-LAST:event_t2ListProgressoMouseClicked

    private void t2ListConcluidoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t2ListConcluidoFocusGained
        t2ListPendente.clearSelection();
        t2ListProgresso.clearSelection();
    }//GEN-LAST:event_t2ListConcluidoFocusGained

    private void t2ListConcluidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t2ListConcluidoMouseClicked
        int cliques = evt.getClickCount();
        if(t2ListConcluido.getModel().getSize() != 0){
            ativarBotoes();
        } else{
            desativarBotoes();
        }
        if(t2ListConcluido.getSelectedIndex() != -1){
            if(cliques > 1){
                Object[] options = { "Sim", "Não"};
                int opcao = JOptionPane.showOptionDialog(null, "Deseja apagar a tarefa? Essa ação é irreversível", "Apagar tarefa", 1, 2, null, options, options[0]);
                if(opcao == 0){
                    TarefasDAO tareDAO = new TarefasDAO();
                    Tarefas tarefa = t2lista3.get(t2ListConcluido.getSelectedIndex());
                    tareDAO.excluir(tarefa);
                    load();
                }
            }
        }
    }//GEN-LAST:event_t2ListConcluidoMouseClicked

    private void t2ButtonAbrirTarefaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t2ButtonAbrirTarefaActionPerformed
        int selecao = -1;
        
        ArrayList<Tarefas> listaAtual = new ArrayList<>();
        
        if(t2ListPendente.getSelectedIndex() > -1){
            listaAtual = t2lista1;
            selecao = t2ListPendente.getSelectedIndex();
        } else if(t2ListProgresso.getSelectedIndex() > -1){
            listaAtual = t2lista2;
            selecao = t2ListProgresso.getSelectedIndex();
        } else if(t2ListConcluido.getSelectedIndex() > -1){
            listaAtual = t2lista3;
            selecao = t2ListConcluido.getSelectedIndex();
        }
        
        Principal principal = geral.getPrincipal();
        TarefaAberta tarefaAbertaPane = new TarefaAberta(principal, listaAtual.get(selecao));
        principal.setLayoutAtual("tarefaAbertaWindow");
        principal.getContentPane().add(tarefaAbertaPane, "tarefaAbertaWindow");
        CardLayout layout = (CardLayout) principal.getContentPane().getLayout();
        layout.show(principal.getContentPane(), "tarefaAbertaWindow");
    }//GEN-LAST:event_t2ButtonAbrirTarefaActionPerformed

    private void t2ButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t2ButtonVoltarActionPerformed
        CardLayout layout = (CardLayout) getParent().getLayout();
        geral.getPrincipal().setLayoutAtual("geralWindow");
        geral.limpar();
        geral.desativarBotoes();
        layout.show(getParent(), "geralWindow");
    }//GEN-LAST:event_t2ButtonVoltarActionPerformed

    private void jTabbedPane1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTabbedPane1FocusGained
        load();
    }//GEN-LAST:event_jTabbedPane1FocusGained

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        desativarBotoes();
    }//GEN-LAST:event_jTabbedPane1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanelMtarefas;
    private javax.swing.JPanel jPanelTarefas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton t1ButtonAbrirTarefa;
    private javax.swing.JButton t1ButtonVoltar;
    private javax.swing.JLabel t1LabelConcluido;
    private javax.swing.JLabel t1LabelPendente;
    private javax.swing.JLabel t1LabelProgresso;
    private javax.swing.JList<String> t1ListConcluido;
    private javax.swing.JList<String> t1ListPendente;
    private javax.swing.JList<String> t1ListProgresso;
    private javax.swing.JButton t2ButtonAbrirTarefa;
    private javax.swing.JButton t2ButtonVoltar;
    private javax.swing.JLabel t2LabelConcluido;
    private javax.swing.JLabel t2LabelPendente;
    private javax.swing.JLabel t2LabelProgresso;
    private javax.swing.JList<String> t2ListConcluido;
    private javax.swing.JList<String> t2ListPendente;
    private javax.swing.JList<String> t2ListProgresso;
    // End of variables declaration//GEN-END:variables
}
