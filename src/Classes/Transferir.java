/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import DAO.TarefasDAO;
import Janelas.AbrirTarefas;
import java.util.Date;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.Calendar;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.TransferHandler;
import static javax.swing.TransferHandler.MOVE;

/**
 *
 * @author agsjohn
 */
public class Transferir extends TransferHandler {

    private JList<?> sourceList;
    Tarefas tarefa;
    AbrirTarefas abrirTarefas;
    String estadoNovo = "";
    
    public Transferir(AbrirTarefas abrirTarefas) {
        this.abrirTarefas = abrirTarefas;
    }
    
    public void verificar(){
        if(sourceList == abrirTarefas.getT1ListPendente()){
            tarefa = abrirTarefas.getT1lista1().get(sourceList.getSelectedIndex());
        }
        if(sourceList == abrirTarefas.getT1ListProgresso()){
            tarefa = abrirTarefas.getT1lista2().get(sourceList.getSelectedIndex());
        }
        if(sourceList == abrirTarefas.getT1ListConcluido()){
            tarefa = abrirTarefas.getT1lista3().get(sourceList.getSelectedIndex());
        }
        if(sourceList == abrirTarefas.getT2ListPendente()){
            tarefa = abrirTarefas.getT2lista1().get(sourceList.getSelectedIndex());
        }
        if(sourceList == abrirTarefas.getT2ListProgresso()){
            tarefa = abrirTarefas.getT2lista2().get(sourceList.getSelectedIndex());
        }
        if(sourceList == abrirTarefas.getT2ListConcluido()){
            tarefa = abrirTarefas.getT2lista3().get(sourceList.getSelectedIndex());
        }
        abrirTarefas.setIdTarefa(tarefa.getIdTarefa());
    }
    
    public void destino(JList<?> targetList){
        if(targetList == abrirTarefas.getT1ListPendente()){
            estadoNovo = "Pendente";
        }
        if(targetList == abrirTarefas.getT1ListProgresso()){
            estadoNovo = "Em progresso";
        }
        if(targetList == abrirTarefas.getT1ListConcluido()){
            estadoNovo = "Concluída";
        }
        if(targetList == abrirTarefas.getT2ListPendente()){
            estadoNovo = "Pendente";
        }
        if(targetList == abrirTarefas.getT2ListProgresso()){
            estadoNovo = "Em progresso";
        }
        if(targetList == abrirTarefas.getT2ListConcluido()){
            estadoNovo = "Concluída";
        }
        System.out.println(estadoNovo);
    }
    
    public Boolean destinoConcluido(JList<?> targetList){
        if(targetList == abrirTarefas.getT1ListConcluido()){
            return true;
        } else if(targetList == abrirTarefas.getT2ListConcluido()){
            return true;
        } else{
            return false;
        }
    }
    
    @Override
    public int getSourceActions(JComponent c) {
        return (TransferHandler.MOVE);
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        sourceList = (JList<?>) c;
        Object value = sourceList.getSelectedValue();
        abrirTarefas.setSourceList(sourceList);
        verificar();
        return new StringSelection(value.toString());
    }

    @Override
    public boolean canImport(TransferHandler.TransferSupport support) {
        return support.getComponent() instanceof JList && support.isDataFlavorSupported(DataFlavor.stringFlavor);
    }

    @Override
    public boolean importData(TransferHandler.TransferSupport support) {
        if (!canImport(support)) {
            return false;
        }

        try {
            JList<?> targetList = (JList<?>) support.getComponent();
            destino(targetList);
            sourceList = abrirTarefas.getSourceList();
            if(targetList == sourceList){
                return false;
            }
            System.out.println("sourceList é concluído? " + destinoConcluido(sourceList));
            System.out.println("targetList é concluído? " + destinoConcluido(targetList));
            if(destinoConcluido(targetList) == true && destinoConcluido(sourceList) == false){
                TarefasDAO tareDAO = new TarefasDAO();
                tareDAO.atualizarEstadoTarefa(abrirTarefas.getIdTarefa(), estadoNovo, (Calendar.getInstance()).getTime());
                System.out.println("True false");
            }
            if(destinoConcluido(targetList) == false && destinoConcluido(sourceList) == true){
                TarefasDAO tareDAO = new TarefasDAO();
                java.util.Date dataJAVA = null;
                tareDAO.atualizarEstadoTarefa(abrirTarefas.getIdTarefa(), estadoNovo, dataJAVA);
                System.out.println("False true");
            }
            if(destinoConcluido(targetList) == false && destinoConcluido(sourceList) == false){
                TarefasDAO tareDAO = new TarefasDAO();
                tareDAO.atualizarEstadoTarefa(abrirTarefas.getIdTarefa(), estadoNovo);
                System.out.println("False false");
            } 
            DefaultListModel<Object> model = (DefaultListModel<Object>) targetList.getModel();
            String data = (String) support.getTransferable().getTransferData(DataFlavor.stringFlavor);
            model.addElement(data);
            abrirTarefas.desativarBotoes();
            abrirTarefas.load();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
        if (action == MOVE) {
            JList<?> sourceList = (JList<?>) source;
//            DefaultListModel<?> model = (DefaultListModel<?>) sourceList.getModel();
//            model.remove(sourceList.getSelectedIndex());
        }
        sourceList = null;
        abrirTarefas.setSourceList(sourceList);
    }
}