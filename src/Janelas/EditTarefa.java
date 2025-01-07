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
import DAO.VinculoTarefasUsuarioDAO;
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
public class EditTarefa extends javax.swing.JPanel {

    /**
     * Creates new form EditForm
     */
    
    int idProjetoSelecionado;
    String layoutAnterior;
    Tarefas tarefa;
    ArrayList<Usuarios> usuariosProjeto = new ArrayList<>();
    ArrayList<Usuarios> todosUsuarios = new ArrayList<>();
    ArrayList<Usuarios> usuariosTarefa = new ArrayList<>();
    ArrayList<Projetos> todosProjetos = new ArrayList<>();
    ArrayList<Usuarios> usuariosDisponiveis = new ArrayList<>();
    ArrayList<VinculoTarefasUsuario> vinculoTarefasUsuario = new ArrayList<>();
    ArrayList<VinculoProjetosUsuario> vinculoProjetosUsuario = new ArrayList<>();
    Principal principal;
    int idTarefa;
    
    public EditTarefa() {
        initComponents();
    }
    
    public EditTarefa(Principal principal, Tarefas tarefa) {
        initComponents();
        
        jTextField1.setDocument(new limiteTexto(50));
        jTextArea1.setDocument(new limiteTexto(500));
        
        this.layoutAnterior = principal.getLayoutAtual();
        this.principal = principal;
        this.tarefa = tarefa;
        this.idTarefa = tarefa.getIdTarefa();
        load();
        System.out.println("Todos usuários: " + todosUsuarios.size());
        System.out.println("Usuários no projeto: " + vinculoProjetosUsuario.size());
        System.out.println("Usuários disponíveis: " + usuariosDisponiveis.size());
    }

    public void load(){
        jTextField1.setText(tarefa.getNomeTarefa());
        jTextArea1.setText(tarefa.getDescricaoTarefa());
        
        switch (tarefa.getEstadoTarefa()){
            case "Pendente":
                jComboBox3.setSelectedIndex(0);
                break;
            case "Em progresso":
                jComboBox3.setSelectedIndex(1);
                break;
            case "Concluída":
                jComboBox3.setSelectedIndex(2);
                break;
        }
        
        //Caldendario inicio
        Date dataCriacao = tarefa.getDataCriacaoTarefa();
        java.util.Date date = null;
        try {
            String texto = dataCriacao.toString();
            date = new SimpleDateFormat("yyyy-MM-dd").parse(texto);
        } catch (ParseException ex) {
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        dateChooserPanel2.setSelectedDate(cal);
        
        //Calendario conclusao
        Date dataConclusao = tarefa.getDataConslusaoTarefa();
        if(dataConclusao == null){
            dateChooserPanel1.setSelectedDate(null);
        } else{
            date = null;
            try {
                String texto = dataConclusao.toString();
                date = new SimpleDateFormat("yyyy-MM-dd").parse(texto);
            } catch (ParseException ex) {
            }
            cal = Calendar.getInstance();
            cal.setTime(date);
            dateChooserPanel1.setSelectedDate(cal);
        }
        
        // Limpa as listas antes de começar
        usuariosDisponiveis.clear();
        usuariosTarefa.clear();
        usuariosProjeto.clear();
        vinculoTarefasUsuario.clear();
        vinculoProjetosUsuario.clear();
        
        //Adiciona os projetos ao comboBox de projetos
        ProjetosDAO projDAO = new ProjetosDAO();
        todosProjetos = projDAO.listar();
        String [] nomesTodosProjetos = new String[todosProjetos.size()];
        for(int x=0; x<todosProjetos.size(); x++){
            nomesTodosProjetos[x] = todosProjetos.get(x).getNomeProjeto();
        }
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(nomesTodosProjetos));
        int posComboBoxProj = -1;
        for(int x =0; x < todosProjetos.size(); x++){
            if(todosProjetos.get(x).getIdProjeto() == tarefa.getIdProjeto()){
                posComboBoxProj = x;
            }
        }
        jComboBox2.setSelectedIndex(posComboBoxProj);
        
        //Carrega todos usuarios
        UsuariosDAO userDAO = new UsuariosDAO();
        todosUsuarios = userDAO.listar();
        
        //Carrega Usuarios na tarefa
        ArrayList<VinculoTarefasUsuario> vtu = new ArrayList<>();
        VinculoTarefasUsuarioDAO vtuDAO = new VinculoTarefasUsuarioDAO();
        vtu = vtuDAO.listar();
        for(int x=0; x<vtu.size(); x++){
            if(vtu.get(x).getIdTarefa()== tarefa.getIdTarefa()){
                vinculoTarefasUsuario.add(vtu.get(x));
            }
        }
        
        //Carrega Usuarios no projeto
        ArrayList<VinculoProjetosUsuario> vpu = new ArrayList<>();
        VinculoProjetosUsuarioDAO vpuDAO = new VinculoProjetosUsuarioDAO();
        vpu = vpuDAO.listar();
        for(int x=0; x<vpu.size(); x++){
            if(vpu.get(x).getIdProjeto() == tarefa.getIdProjeto()){
                vinculoProjetosUsuario.add(vpu.get(x));
            }
        }
        
        // Determinar os usuários disponíveis e os usuários vinculados à tarefa
        for (int x = 0; x < todosUsuarios.size(); x++) {
            Usuarios usuario = todosUsuarios.get(x);
            boolean estaNoProjeto = false;
            boolean estaNaTarefa = false;
            for (int y = 0; y < vinculoProjetosUsuario.size(); y++) {
                if (vinculoProjetosUsuario.get(y).getIdUsuario() == usuario.getIdUsuario()) {
                    estaNoProjeto = true;
                    break;
                }
            }
            for (int y = 0; y < vinculoTarefasUsuario.size(); y++) {
                if (vinculoTarefasUsuario.get(y).getIdUsuario() == usuario.getIdUsuario()) {
                    estaNaTarefa = true;
                    break;
                }
            }
            if (estaNoProjeto && !estaNaTarefa) {
                usuariosDisponiveis.add(usuario);
            }
        }
        
        //Adiciona na combobox usuarios disponiveis
        String [] nomesUsuariosDisponiveis = new String[usuariosDisponiveis.size()];
        for(int x=0; x<usuariosDisponiveis.size(); x++){
            nomesUsuariosDisponiveis[x] = usuariosDisponiveis.get(x).getNomeUsuario();
        }
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(nomesUsuariosDisponiveis));
        
        //Mostra na lista usuarios na tarefa
        for (int x = 0; x < vinculoTarefasUsuario.size(); x++) {
            for(int y=0; y<todosUsuarios.size();y++){
                if(vinculoTarefasUsuario.get(x).getIdUsuario() == todosUsuarios.get(y).getIdUsuario()){
                    usuariosTarefa.add(todosUsuarios.get(y));
                }
            }
        }
        String [] string = new String[usuariosTarefa.size()];
        for(int x=0; x < usuariosTarefa.size(); x++){
            string[x] = usuariosTarefa.get(x).getNomeUsuario();
        }
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return string.length; }
            public String getElementAt(int i) { return string[i]; }
        });
        
        //Limpar
        if(usuariosDisponiveis.isEmpty()){
            jButton3.setEnabled(false);
        } else{
            jButton3.setEnabled(true);
        }
    }
    
    public void reloadNomes() {
        usuariosDisponiveis.clear();
        usuariosTarefa.clear();
        vinculoProjetosUsuario.clear();
        jComboBox1.removeAllItems();
        DefaultListModel<String> model = new DefaultListModel<>();
        jList1.setModel(model);

        int projetoSelecionado = jComboBox2.getSelectedIndex();
        if (projetoSelecionado < 0) {
            return;
        }
        idProjetoSelecionado = todosProjetos.get(projetoSelecionado).getIdProjeto();

        // Carrega os vínculos dos usuários no projeto selecionado
        VinculoProjetosUsuarioDAO vpuDAO = new VinculoProjetosUsuarioDAO();
        ArrayList<VinculoProjetosUsuario> vpu = vpuDAO.listar();
        for (int x=0; x < vpu.size(); x++){
            if (vpu.get(x).getIdProjeto() == idProjetoSelecionado) {
                vinculoProjetosUsuario.add(vpu.get(x));
            }
        }

        // Identifica os usuários disponíveis com base nos vínculos do projeto
        for (Usuarios usuario : todosUsuarios) {
            for (VinculoProjetosUsuario vinculo : vinculoProjetosUsuario) {
                if (vinculo.getIdUsuario() == usuario.getIdUsuario()) {
                    usuariosDisponiveis.add(usuario);
                }
            }
        }

        // Preenche o comboBox de usuários disponíveis
        String [] nomesUsuariosDisponiveis = new String[usuariosDisponiveis.size()];
        for(int x=0; x<usuariosDisponiveis.size(); x++){
            nomesUsuariosDisponiveis[x] = usuariosDisponiveis.get(x).getNomeUsuario();
        }
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(nomesUsuariosDisponiveis));

        // Garante que o botão "Adicionar" seja desativado se não houver usuários disponíveis
        jButton3.setEnabled(!usuariosDisponiveis.isEmpty());
    }
    
    public void reload(){
        String [] nomesUsuariosDisponiveis = new String[usuariosDisponiveis.size()];
        for(int x=0; x<usuariosDisponiveis.size(); x++){
            nomesUsuariosDisponiveis[x] = usuariosDisponiveis.get(x).getNomeUsuario();
        }
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(nomesUsuariosDisponiveis));
        
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
        jLabel5 = new javax.swing.JLabel();
        dateChooserPanel1 = new datechooser.beans.DateChooserPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();

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

    jLabel5.setText("Data de conclusão da tarefa");

    dateChooserPanel1.setBehavior(datechooser.model.multiple.MultyModelBehavior.SELECT_SINGLE);

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

    jLabel6.setText("Vincular usuário");

    jComboBox2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBox2ActionPerformed(evt);
        }
    });

    jLabel7.setText("Projeto ao qual a tarefa pertence");

    jLabel8.setText("Estado da tarefa");

    jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pendente", "Em progresso", "Concluída" }));

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(jLabel6)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(44, 44, 44)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jButton1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel3)
                .addComponent(dateChooserPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel5)
                .addComponent(dateChooserPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(43, Short.MAX_VALUE))
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
                    .addGap(8, 8, 8)
                    .addComponent(jLabel7)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(22, 22, 22)
                    .addComponent(jLabel4)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton3)))
                    .addGap(18, 18, 18)
                    .addComponent(jLabel8)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(dateChooserPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel5)
                    .addGap(2, 2, 2)
                    .addComponent(dateChooserPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton2)
                .addComponent(jButton1))
            .addGap(40, 40, 40))
    );

    jScrollPane3.setViewportView(jPanel1);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
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
        Date dataCriacao = cal.getTime();
        
        Date dataConclusao = null;
        cal = dateChooserPanel1.getSelectedDate();
        if(cal != null){
            dataConclusao = cal.getTime();
        }
        
        String estadoTarefa = jComboBox3.getSelectedItem().toString();
        
        if(dataConclusao != null){
            estadoTarefa = "Concluída";
        }
        
        if(estadoTarefa.equals("Concluída") && dataConclusao == null){
            dataConclusao = (Calendar.getInstance()).getTime();
        }
        
        tarefa = new Tarefas(idTarefa, idProjeto, nomeTarefa, descTarefa, dataCriacao, dataConclusao, estadoTarefa);
        TarefasDAO tareDAO = new TarefasDAO();
        tareDAO.atualizar(tarefa);
        
        VinculoTarefasDAO vpDAO = new VinculoTarefasDAO();
        ArrayList<VinculoTarefas> todosVinculos = new ArrayList<VinculoTarefas>();
        ArrayList<VinculoTarefas> filtroVinculos = new ArrayList<VinculoTarefas>();
        todosVinculos = vpDAO.listar();
        vpDAO = new VinculoTarefasDAO();
        for(int x=0; x<todosVinculos.size();x++){
            if(todosVinculos.get(x).getIdTarefa() == idTarefa){
                filtroVinculos.add((VinculoTarefas) todosVinculos.get(x));
            }
        }
        for(int x=0; x<filtroVinculos.size();x++){
            boolean achou = false;
            for(int y=0; y<usuariosTarefa.size();y++){
                if(filtroVinculos.get(x).getIdUsuario() == usuariosTarefa.get(y).getIdUsuario()){
                    achou = true;
                }
            }
            if(achou == false){
                vpDAO.excluir(filtroVinculos.get(x));
                vpDAO = new VinculoTarefasDAO();
            }
        }
        for(int x=0; x<usuariosTarefa.size();x++){
            boolean achou = false;
            for(int y=0; y<filtroVinculos.size();y++){
                if(usuariosTarefa.get(x).getIdUsuario() == filtroVinculos.get(y).getIdUsuario()){
                    achou = true;
                }
            }
            if(achou == false){
                vpDAO.inserir(new VinculoTarefas(idTarefa, usuariosTarefa.get(x).getIdUsuario()));
                vpDAO = new VinculoTarefasDAO();
            }
        }
        
        CardLayout layout = (CardLayout) getParent().getLayout();
        try {
            principal.getTarefasPane().load();
        } catch (Exception e) {
        }
        TarefaAberta tarefaAbertaPane = new TarefaAberta(principal, tarefa);
        tarefaAbertaPane.reload();
        principal.getContentPane().add(tarefaAbertaPane, "tarefaAbertaWindow");
        layout.show(getParent(), layoutAnterior);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        int cliques = evt.getClickCount();
        if(jList1.getSelectedIndex() != -1){
            if(cliques > 1){
                Object[] options = { "Sim", "Não"};
                int opcao = JOptionPane.showOptionDialog(null, "Deseja desvincular usuário da tarefa?", "Remover vínculo", 1, 2, null, options, options[0]);
                if(opcao == 0){
                    usuariosDisponiveis.add(usuariosTarefa.get(jList1.getSelectedIndex()));
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
        usuariosTarefa.add(usuariosDisponiveis.get(jComboBox1.getSelectedIndex()));
        usuariosDisponiveis.remove(jComboBox1.getSelectedIndex());
        this.reload();
        if(jComboBox1.getItemCount() == 0){
            jButton3.setEnabled(false);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        reloadNomes();
    }//GEN-LAST:event_jComboBox2ActionPerformed


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
