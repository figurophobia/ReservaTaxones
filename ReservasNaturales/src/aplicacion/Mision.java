package aplicacion;

import java.sql.Date;

public class Mision {
    private Usuario trabajador;
    private String especie;
    private Date fechaInicio;
    private Date fechaFin;
    private String descripcion;

    public Mision(Usuario trabajador, String especie, Date fechaInicio, Date fechaFin, String descripcion) {
        this.trabajador = trabajador;
        this.especie = especie;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descripcion = descripcion;
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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
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

    // Devuelve "Completada" o "Incompleta" según si fechaFin es null
    public String getCompletada() {
        return fechaFin != null ? "Completada" : "Incompleta";
    }

    // Devuelve true si está completada (fechaFin != null), false en caso contrario
    public boolean getEstado() {
        return fechaFin != null;
    }

    // También devuelve true si está completada, false si no
    public boolean isCompletada() {
        return fechaFin != null;
    }

    // Alternativa textual
    public String estaCompletada() {
        return fechaFin != null ? "Completada" : "Incompleta";
    }

    public void setCompletada() {
        this.fechaFin = new Date(System.currentTimeMillis());
    }
    @Override
    public String toString() {
        return "Trabajador: " + trabajador.getNombre() + ", Especie: " + especie + ", Fecha de inicio: " + fechaInicio;
    }
    public Mision clone() {
    return new Mision(
        this.trabajador, // Clonar el trabajador si es necesario
        this.especie,
        this.fechaInicio,
        this.fechaFin,
        this.descripcion
    );
}


}
