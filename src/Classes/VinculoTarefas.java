/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.util.Date;

/**
 *
 * @author 291021
 */
public class VinculoTarefas {
    private int idVinculoTarefa;
    private int idTarefa;
    private int idUsuario;

    public VinculoTarefas() {
    }
    
    public VinculoTarefas(int idTarefa, int idUsuario) {
        this.idTarefa = idTarefa;
        this.idUsuario = idUsuario;
    }

    public VinculoTarefas(int idVinculoTarefa, int idTarefa, int idUsuario) {
        this.idVinculoTarefa = idVinculoTarefa;
        this.idTarefa = idTarefa;
        this.idUsuario = idUsuario;
    }

    public int getIdVinculoTarefa() {
        return idVinculoTarefa;
    }

    public void setIdVinculoTarefa(int idVinculoTarefa) {
        this.idVinculoTarefa = idVinculoTarefa;
    }

    public int getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(int idTarefa) {
        this.idTarefa = idTarefa;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
}
