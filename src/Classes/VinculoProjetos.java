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
public class VinculoProjetos {
    private int idVinculoProjeto;
    private int idProjeto;
    private int idUsuario;

    public VinculoProjetos() {
    }
    
    public VinculoProjetos(int idProjeto, int idUsuario) {
        this.idProjeto = idProjeto;
        this.idUsuario = idUsuario;
    }

    public VinculoProjetos(int idVinculoProjeto, int idProjeto, int idUsuario) {
        this.idVinculoProjeto = idVinculoProjeto;
        this.idProjeto = idProjeto;
        this.idUsuario = idUsuario;
    }

    public int getIdVinculoProjeto() {
        return idVinculoProjeto;
    }

    public void setIdVinculoProjeto(int idVinculoProjeto) {
        this.idVinculoProjeto = idVinculoProjeto;
    }
    
    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
