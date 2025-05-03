package aplicacion;

import java.sql.Date;

public class Mision {
    private Usuario trabajador;
    private String especie;
    private Date fechaInicio;
    private Date fechaFin;
    private String descripcion;
    private boolean completada;

    public Mision(Usuario trabajador, String especie, Date fechaInicio, Date fechaFin, String descripcion, boolean completada) {
        this.trabajador = trabajador;
        this.especie = especie;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descripcion = descripcion;
        this.completada = completada;
    }

    public Usuario getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Usuario trabajador) {
        this.trabajador = trabajador;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public java.sql.Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }
    public String getCompletada(){
        if (completada) {
            return "Completada";
        }else{
            return "Incompleta";
        
        }
    }
    public boolean getEstado(){
        return completada;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }
    public String estaCompletada() {
        if (completada) {
            return "Completada";
        } else {
            return "Incompleta";
        }
    }
    

  
    
}
