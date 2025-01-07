/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author 291021
 */
public class VinculoTarefasUsuario {
    private int idUsuario;
    private String nomeTarefa;
    private String estadoTarefa;
    private String nomeUsuario;
    private int idTarefa;
    private int idVinculoTarefa;

    public VinculoTarefasUsuario() {
    }

    public VinculoTarefasUsuario(int idUsuario, String nomeTarefa, String estadoTarefa, String nomeUsuario, int idTarefa, int idVinculoTarefa) {
        this.idUsuario = idUsuario;
        this.nomeTarefa = nomeTarefa;
        this.estadoTarefa = estadoTarefa;
        this.nomeUsuario = nomeUsuario;
        this.idTarefa = idTarefa;
        this.idVinculoTarefa = idVinculoTarefa;
    }
    
    public VinculoTarefasUsuario(String nomeTarefa, String estadoTarefa, int idTarefa, int idVinculoTarefa) {
        this.nomeTarefa = nomeTarefa;
        this.estadoTarefa = estadoTarefa;
        this.idTarefa = idTarefa;
        this.idVinculoTarefa = idVinculoTarefa;
    }

    public VinculoTarefasUsuario(int idUsuario, String nomeTarefa, String estadoTarefa, int idTarefa, int idVinculoTarefa) {
        this.idUsuario = idUsuario;
        this.nomeTarefa = nomeTarefa;
        this.estadoTarefa = estadoTarefa;
        this.idTarefa = idTarefa;
        this.idVinculoTarefa = idVinculoTarefa;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeTarefa() {
        return nomeTarefa;
    }

    public void setNomeTarefa(String nomeTarefa) {
        this.nomeTarefa = nomeTarefa;
    }

    public String getEstadoTarefa() {
        return estadoTarefa;
    }

    public void setEstadoTarefa(String estadoTarefa) {
        this.estadoTarefa = estadoTarefa;
    }

    public int getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(int idTarefa) {
        this.idTarefa = idTarefa;
    }

    public int getIdVinculoTarefa() {
        return idVinculoTarefa;
    }

    public void setIdVinculoTarefa(int idVinculoTarefa) {
        this.idVinculoTarefa = idVinculoTarefa;
    }
    
}
