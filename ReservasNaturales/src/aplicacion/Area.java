/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

/**
 *
 * @author davidavirn
 */
public class Area {
    private String nombreReserva;
    private double extension;
    private boolean acuatica;
    private boolean terrestre;

    //Clase Double para admitir null y no trbajar con 0
    private Double profundidad;   // Solo si es acuática

    private Double altitudBaja;    // Solo si es terrestre
    private Double altitudAlta;    // Solo si es terrestre

    // Constructor vacío
    public Area() {
    }

    // Constructor general 
    public Area(String nombreReserva, double extension, boolean terrestre, boolean acuatica) {
        this.nombreReserva = nombreReserva;
        this.extension = extension;
        this.terrestre = terrestre;
        this.acuatica = acuatica;
    }

    // Constructor para áreas acuáticas
    public Area(String nombreReserva, double extension, double profundidad) {
        this.nombreReserva = nombreReserva;
        this.extension = extension;
        this.acuatica = true;
        this.profundidad = profundidad;
    }

    // Constructor para áreas terrestres
    public Area(String nombreReserva, double extension, double altitudBaja, double altitudAlta) {
        this.nombreReserva = nombreReserva;
        this.extension = extension;
        this.terrestre = true;
        this.altitudBaja = altitudBaja;
        this.altitudAlta = altitudAlta;
    }

    // Getters y Setters
    public String getNombreReserva() {
        return nombreReserva;
    }

    public void setNombreReserva(String nombreReserva) {
        this.nombreReserva = nombreReserva;
    }

    public double getExtension() {
        return extension;
    }

    public void setExtension(double extension) {
        this.extension = extension;
    }

    public boolean isAcuatica() {
        return acuatica;
    }
    
    public void setAcuatica(boolean acuatica) {
        this.acuatica = acuatica;
    }
    
    public boolean isTerrestre() {
        return terrestre;
    }
    
    public void setTerrestre(boolean terrestre){
        this.terrestre = terrestre;
    }

    public Double getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(Double profundidad) {
        this.profundidad = profundidad;
    }

    public Double getAltitudBaja() {
        return altitudBaja;
    }

    public void setAltitudBaja(Double altitudBaja) {
        this.altitudBaja = altitudBaja;
    }

    public Double getAltitudAlta() {
        return altitudAlta;
    }

    public void setAltitudAlta(Double altitudAlta) {
        this.altitudAlta = altitudAlta;
    }
}
