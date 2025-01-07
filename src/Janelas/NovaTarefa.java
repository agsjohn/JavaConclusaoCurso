package Janelas;

import Classes.Projetos;
import Classes.Tarefas;
import Classes.Usuarios;
import Classes.VinculoProjetos;
import Classes.VinculoProjetosUsuario;
import Classes.VinculoTarefas;
import Classes.VinculoTarefasUsuario;
import Classes.limiteTexto;
import DAO.ProjetosDAO;
import DAO.TarefasDAO;
import DAO.UsuariosDAO;
import DAO.VinculoProjetosDAO;
import DAO.VinculoProjetosUsuarioDAO;
import DAO.VinculoTarefasDAO;
import java.awt.CardLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author agsjohn
 */
public class NovaTarefa extends javax.swing.JPanel {

    /**
     * Creates new form EditForm
     */
    int idProjetoSelecionado;
    String layoutAnterior;
    ArrayList<Usuarios> usuariosProjeto = new ArrayList<>();
    ArrayList<Usuarios> todosUsuarios = new ArrayList<>();
    ArrayList<Usuarios> usuariosTarefa = new ArrayList<>();
    ArrayList<Projetos> todosProjetos = new ArrayList<>();
    ArrayList<VinculoProjetosUsuario> vinculoUsuariosProjeto = new ArrayList<>();
    Principal principal;
    Date dataCriacao = null;
    Date dataConclusao = null;
    String estadoTarefa = "Pendente";
    
    public NovaTarefa() {
        initComponents();
    }
    
    public NovaTarefa(Principal principal) {
        initComponents();
        
        jTextField1.setDocument(new limiteTexto(50));
        jTextArea1.setDocument(new limiteTexto(500));
        
        this.layoutAnterior = principal.getLayoutAtual();
        this.principal = principal;
        load();
        dateChooserPanel1.setSelectedDate(null);
    }

    public void load(){
        dateChooserPanel1.setVisible(false);
        jLabel5.setVisible(false);
        
        //Adiciona os projetos ao comboBox de projetos
        ProjetosDAO projDAO = new ProjetosDAO();
        todosProjetos = projDAO.listar();
        String [] nomesTodosProjetos = new String[todosProjetos.size()];
        for(int x=0; x<todosProjetos.size(); x++){
            nomesTodosProjetos[x] = todosProjetos.get(x).getNomeProjeto();
        }
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(nomesTodosProjetos));
        
        //Pega os vinculos dos usuarios e o projeto selecionado
        this.idProjetoSelecionado = todosProjetos.get(jComboBox2.getSelectedIndex()).getIdProjeto();
        ArrayList<VinculoProjetosUsuario> vup = new ArrayList<>();
        VinculoProjetosUsuarioDAO vupDAO = new VinculoProjetosUsuarioDAO();
        vup = vupDAO.listar();
        for(int x=0; x<vup.size(); x++){
            if(vup.get(x).getIdProjeto() == idProjetoSelecionado){
                vinculoUsuariosProjeto.add(vup.get(x));
            }
        }
        
        //Adicionar os usuarios a partir do vinculo
        UsuariosDAO usuariosDAO = new UsuariosDAO();
        todosUsuarios = usuariosDAO.listar();
        for(int x=0; x<todosUsuarios.size(); x++){
            for(int y=0; y<vinculoUsuariosProjeto.size();y++){
                if(vinculoUsuariosProjeto.get(y).getIdUsuario() == todosUsuarios.get(x).getIdUsuario()){
                    usuariosProjeto.add(todosUsuarios.get(x));
                }
            }
        }
        String [] nomesUsuariosDisponiveis = new String[usuariosProjeto.size()];
        for(int x=0; x<usuariosProjeto.size(); x++){
            nomesUsuariosDisponiveis[x] = usuariosProjeto.get(x).getNomeUsuario();
        }
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(nomesUsuariosDisponiveis));
    }
    
    public void reloadNomes(){
        //Limpa
        usuariosProjeto = new ArrayList<>();
        todosUsuarios = new ArrayList<>();
        usuariosTarefa = new ArrayList<>();
        vinculoUsuariosProjeto = new ArrayList<>();
        jComboBox1.removeAllItems();
        DefaultListModel model = new DefaultListModel();
        model.clear();
        jList1.setModel(model);
        
        //Pega os vinculos dos usuarios e o projeto selecionado
        this.idProjetoSelecionado = todosProjetos.get(jComboBox2.getSelectedIndex()).getIdProjeto();
        ArrayList<VinculoProjetosUsuario> vup = new ArrayList<>();
        VinculoProjetosUsuarioDAO vupDAO = new VinculoProjetosUsuarioDAO();
        vup = vupDAO.listar();
        for(int x=0; x<vup.size(); x++){
            if(vup.get(x).getIdProjeto() == idProjetoSelecionado){
                vinculoUsuariosProjeto.add(vup.get(x));
            }
        }
        
        //Adicionar os usuarios a partir do vinculo
        UsuariosDAO usuariosDAO = new UsuariosDAO();
        todosUsuarios = usuariosDAO.listar();
        for(int x=0; x<todosUsuarios.size(); x++){
            for(int y=0; y<vinculoUsuariosProjeto.size();y++){
                if(vinculoUsuariosProjeto.get(y).getIdUsuario() == todosUsuarios.get(x).getIdUsuario()){
                    usuariosProjeto.add(todosUsuarios.get(x));
                }
            }
        }
        String [] nomesUsuariosDisponiveis = new String[usuariosProjeto.size()];
        for(int x=0; x<usuariosProjeto.size(); x++){
            nomesUsuariosDisponiveis[x] = usuariosProjeto.get(x).getNomeUsuario();
        }
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(nomesUsuariosDisponiveis));
    }
    
    public void reload(){
        String [] nomesUsuariosProjeto = new String[usuariosProjeto.size()];
        for(int x=0; x<usuariosProjeto.size(); x++){
            nomesUsuariosProjeto[x] = usuariosProjeto.get(x).getNomeUsuario();
        }
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(nomesUsuariosProjeto));
        
        String [] string = new String[usuariosTarefa.size()];
        for(int x=0; x < usuariosTarefa.size(); x++){
            string[x] = usuariosTarefa.get(x).getNomeUsuario();
        }
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return string.length; }
            public String getElementAt(int i) { return string[i]; }
        });
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooserPanel1 = new datechooser.beans.DateChooserPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();
        dateChooserPanel2 = new datechooser.beans.DateChooserPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();

        dateChooserPanel1.setBehavior(datechooser.model.multiple.MultyModelBehavior.SELECT_SINGLE);

        jLabel5.setText("Data de conclusão da tarefa");

        setMinimumSize(new java.awt.Dimension(640, 504));
        setPreferredSize(new java.awt.Dimension(640, 360));

        jScrollPane3.setBorder(null);

        jLabel1.setText("Nome tarefa");

        jLabel2.setText("Descrição tarefa");

        jLabel3.setText("Data de criação da tarefa");

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jTextArea1);

        dateChooserPanel2.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
            new datechooser.view.appearance.ViewAppearance("custom",
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    true,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(0, 0, 255),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(128, 128, 128),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(255, 0, 0),
                    false,
                    false,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                (datechooser.view.BackRenderer)null,
                false,
                true)));
    dateChooserPanel2.setNothingAllowed(false);
    dateChooserPanel2.setBehavior(datechooser.model.multiple.MultyModelBehavior.SELECT_SINGLE);

    jLabel4.setText("Usuários ligados à tarefa");

    jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    jList1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jList1MouseClicked(evt);
        }
    });
    jScrollPane2.setViewportView(jList1);

    jButton1.setText("Cancelar");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
        }
    });

    jButton2.setText("Salvar");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton2ActionPerformed(evt);
        }
    });

    jButton3.setText("Adicionar");
    jButton3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton3ActionPerformed(evt);
        }
    });

    jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vazio" }));

    jLabel6.setText("Vincular usuário");

    jComboBox2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBox2ActionPerformed(evt);
        }
    });

    jLabel7.setText("Projeto ao qual a tarefa pertence");

    jLabel8.setText("Estado da tarefa");

    jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pendente", "Em progresso", "Concluída" }));
    jComboBox3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBox3ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(12, 12, 12)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel7)
                .addComponent(jLabel4)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(44, 44, 44)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))
                            .addGap(0, 0, Short.MAX_VALUE)))))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel3)
                .addComponent(dateChooserPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(71, 71, 71)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(135, 135, 135))))
            .addContainerGap(60, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(7, 7, 7)
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel2)
                    .addGap(3, 3, 3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(4, 4, 4)
                    .addComponent(jLabel7)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(jLabel4))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(dateChooserPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jLabel8)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(jButton1)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3))))
            .addContainerGap(18, Short.MAX_VALUE))
    );

    jScrollPane3.setViewportView(jPanel1);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane3)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane3)
    );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CardLayout layout = (CardLayout) getParent().getLayout();
        layout.show(getParent(), layoutAnterior);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(jTextField1.getText().equals("") || jTextArea1.getText().equals("") || jList1.getModel().getSize()==0){
            String mensagem = "Preencha os campos de nome, descrição e usuários ligado à tarefa";
            JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String nomeTarefa = jTextField1.getText();
        String descTarefa = jTextArea1.getText();
        int idProjeto = todosProjetos.get(jComboBox2.getSelectedIndex()).getIdProjeto();
        
        Calendar cal = Calendar.getInstance();
        cal = dateChooserPanel2.getSelectedDate();
        dataCriacao = cal.getTime();
        
        cal = dateChooserPanel1.getSelectedDate();
        if(cal != null){
            dataConclusao = cal.getTime();
            estadoTarefa = "Concluída";
        }
        if(estadoTarefa.equals("Concluída") && dataConclusao == null){
            dataConclusao = (Calendar.getInstance()).getTime();
        }
        
        System.out.println(dataConclusao);
        
        Tarefas tarefa = new Tarefas(idProjeto, nomeTarefa, descTarefa, dataCriacao, dataConclusao, estadoTarefa);
        TarefasDAO tareDAO = new TarefasDAO();
        tarefa.setIdTarefa(tareDAO.inserir(tarefa));

        VinculoTarefasDAO vtDAO = new VinculoTarefasDAO();
        VinculoTarefas vt = null;
        for(int x=0; x<usuariosTarefa.size(); x++){
            vtDAO = new VinculoTarefasDAO();
            vt = new VinculoTarefas(tarefa.getIdTarefa(), usuariosTarefa.get(x).getIdUsuario());
            vtDAO.inserir(vt);
        }

        CardLayout layout = (CardLayout) getParent().getLayout();
        try {
            principal.getTarefasPane().load();
        } catch (Exception e) {
        }
        layout.show(getParent(), layoutAnterior);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        int cliques = evt.getClickCount();
        if(jList1.getSelectedIndex() != -1){
            if(cliques > 1){
                Object[] options = { "Sim", "Não"};
                int opcao = JOptionPane.showOptionDialog(null, "Deseja desvincular usuário da tarefa?", "Remover vínculo", 1, 2, null, options, options[0]);
                if(opcao == 0){
                    usuariosProjeto.add(usuariosTarefa.get(jList1.getSelectedIndex()));
                    usuariosTarefa.remove(jList1.getSelectedIndex());
                    this.reload();
                    if(jComboBox1.getItemCount() != 0){
                        jButton3.setEnabled(true);
                    }
                }
            }
        }
    }//GEN-LAST:event_jList1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        usuariosTarefa.add(usuariosProjeto.get(jComboBox1.getSelectedIndex()));
        usuariosProjeto.remove(jComboBox1.getSelectedIndex());
        this.reload();
        if(jComboBox1.getItemCount() == 0){
            jButton3.setEnabled(false);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        reloadNomes();
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        estadoTarefa = jComboBox3.getSelectedItem().toString();
    }//GEN-LAST:event_jComboBox3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserPanel dateChooserPanel1;
    private datechooser.beans.DateChooserPanel dateChooserPanel2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
