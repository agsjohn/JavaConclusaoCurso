/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author 291021
 */
public class VinculoProjetosUsuario {
    private int idUsuario;
    private String nomeUsuario;
    private int idProjeto;
    private int idVinculoProjeto;

    public VinculoProjetosUsuario() {
    }

    public VinculoProjetosUsuario(int idUsuario, String nomeUsuario, int idProjeto, int idVinculoProjeto) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.idProjeto = idProjeto;
        this.idVinculoProjeto = idVinculoProjeto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
    
    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public int getIdVinculoProjeto() {
        return idVinculoProjeto;
    }

    public void setIdVinculoProjeto(int idVinculoProjeto) {
        this.idVinculoProjeto = idVinculoProjeto;
    }
    
}
