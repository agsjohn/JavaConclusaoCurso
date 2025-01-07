package Classes;

import java.util.Date;

/**
 *
 * @author 291021
 */
public class Tarefas{
    private int idTarefa;
    private int idProjeto;
    private String nomeTarefa;
    private String descricaoTarefa;
    private Date dataCriacaoTarefa;
    private Date dataConclusaoTarefa;
    private String estadoTarefa;

    public Tarefas() {
    }

    public Tarefas(int idProjeto, String nomeTarefa, String descricaoTarefa, Date dataCriacaoTarefa, String estadoTarefa) {
        this.idProjeto = idProjeto;
        this.nomeTarefa = nomeTarefa;
        this.descricaoTarefa = descricaoTarefa;
        this.dataCriacaoTarefa = dataCriacaoTarefa;
        this.estadoTarefa = estadoTarefa;
    }

    public Tarefas(int idProjeto, String nomeTarefa, String descricaoTarefa, Date dataCriacaoTarefa, Date dataConslusaoTarefa, String estadoTarefa) {
        this.idProjeto = idProjeto;
        this.nomeTarefa = nomeTarefa;
        this.descricaoTarefa = descricaoTarefa;
        this.dataCriacaoTarefa = dataCriacaoTarefa;
        this.dataConclusaoTarefa = dataConslusaoTarefa;
        this.estadoTarefa = estadoTarefa;
    }

    public Tarefas(int idTarefa, int idProjeto, String nomeTarefa, String descricaoTarefa, Date dataCriacaoTarefa, Date dataConslusaoTarefa, String estadoTarefa) {
        this.idTarefa = idTarefa;
        this.idProjeto = idProjeto;
        this.nomeTarefa = nomeTarefa;
        this.descricaoTarefa = descricaoTarefa;
        this.dataCriacaoTarefa = dataCriacaoTarefa;
        this.dataConclusaoTarefa = dataConslusaoTarefa;
        this.estadoTarefa = estadoTarefa;
    }

    public int getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(int idTarefa) {
        this.idTarefa = idTarefa;
    }

    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public String getNomeTarefa() {
        return nomeTarefa;
    }

    public void setNomeTarefa(String nomeTarefa) {
        this.nomeTarefa = nomeTarefa;
    }

    public String getDescricaoTarefa() {
        return descricaoTarefa;
    }

    public void setDescricaoTarefa(String descricaoTarefa) {
        this.descricaoTarefa = descricaoTarefa;
    }

    public Date getDataCriacaoTarefa() {
        return dataCriacaoTarefa;
    }

    public void setDataCriacaoTarefa(Date dataCriacaoTarefa) {
        this.dataCriacaoTarefa = dataCriacaoTarefa;
    }

    public Date getDataConslusaoTarefa() {
        return dataConclusaoTarefa;
    }

    public void setDataConslusaoTarefa(Date dataConslusaoTarefa) {
        this.dataConclusaoTarefa = dataConslusaoTarefa;
    }

    public String getEstadoTarefa() {
        return estadoTarefa;
    }

    public void setEstadoTarefa(String estadoTarefa) {
        this.estadoTarefa = estadoTarefa;
    }
    
    
}