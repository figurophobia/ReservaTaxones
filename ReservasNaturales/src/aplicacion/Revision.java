package aplicacion;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author davidavirn
 */
public class Revision {
    private String clinica;           // FK a Clínica_medica.nombre
    private String ejemplar;            // FK a Ejemplar.id
    private int especieAsociada;  // FK a Especie.nombre_cientifico
    private Date fechaRevision; // Parte de la clave primaria
    private String informe;

    // Constructor vacío
    public Revision() {
    }

    // Constructor con parámetros
    public Revision(String clinica, String ejemplar, int especieAsociada, Date fechaRevision, String informe) {
        this.clinica = clinica;
        this.ejemplar = ejemplar;
        this.especieAsociada = especieAsociada;
        this.fechaRevision = fechaRevision;
        this.informe = informe;
    }

    // Getters y Setters
    public String getClinica() {
        return clinica;
    }

    public void setClinica(String clinica) {
        this.clinica = clinica;
    }

    public String getEjemplar() {
        return ejemplar;
    }

    public void setEjemplar(String ejemplar) {
        this.ejemplar = ejemplar;
    }

    public int getEspecieAsociada() {
        return especieAsociada;
    }

    public void setEspecieAsociada(int especieAsociada) {
        this.especieAsociada = especieAsociada;
    }

    public Date getFechaRevision() {
        return fechaRevision;
    }

    public void setFechaRevision(Date fechaRevision) {
        this.fechaRevision = fechaRevision;
    }

    public String getInforme() {
        return informe;
    }

    public void setInforme(String informe) {
        this.informe = informe;
    }
}
