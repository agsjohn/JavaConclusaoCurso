package Classes;

import java.util.Date;

/**
 *
 * @author 291021
 */
public class Projetos{
    private int idProjeto;
    private String nomeProjeto;
    private String descricaoProjeto;
    private Date dataCriacaoProjeto;
    private Date dataConclusaoProjeto;
    private Boolean finalizado;

    public Projetos() {
    }

    public Projetos(String nomeProjeto, String descricaoProjeto, Date dataCriacaoProjeto, Boolean finalizado) {
        this.nomeProjeto = nomeProjeto;
        this.descricaoProjeto = descricaoProjeto;
        this.dataCriacaoProjeto = dataCriacaoProjeto;
        this.finalizado = finalizado;
    }

    public Projetos(String nomeProjeto, String descricaoProjeto, Date dataCriacaoProjeto, Date dataConclusaoProjeto, Boolean finalizado) {
        this.nomeProjeto = nomeProjeto;
        this.descricaoProjeto = descricaoProjeto;
        this.dataCriacaoProjeto = dataCriacaoProjeto;
        this.dataConclusaoProjeto = dataConclusaoProjeto;
        this.finalizado = finalizado;
    }

    public Projetos(int idProjeto, String nomeProjeto, String descricaoProjeto, Date dataCriacaoProjeto, Date dataConclusaoProjeto, Boolean finalizado) {
        this.idProjeto = idProjeto;
        this.nomeProjeto = nomeProjeto;
        this.descricaoProjeto = descricaoProjeto;
        this.dataCriacaoProjeto = dataCriacaoProjeto;
        this.dataConclusaoProjeto = dataConclusaoProjeto;
        this.finalizado = finalizado;
    }

    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }

    public String getDescricaoProjeto() {
        return descricaoProjeto;
    }

    public void setDescricaoProjeto(String descricaoProjeto) {
        this.descricaoProjeto = descricaoProjeto;
    }

    public Date getDataCriacaoProjeto() {
        return dataCriacaoProjeto;
    }

    public void setDataCriacaoProjeto(Date dataCriacaoProjeto) {
        this.dataCriacaoProjeto = dataCriacaoProjeto;
    }

    public Date getDataConclusaoProjeto() {
        return dataConclusaoProjeto;
    }

    public void setDataConclusaoProjeto(Date dataConclusaoProjeto) {
        this.dataConclusaoProjeto = dataConclusaoProjeto;
    }

    public Boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }
    
    
}