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
    private String tipo; // "Acuática" o "Terrestre"

    //Clase Double para admitir null y no trbajar con 0
    private Double profundidad;   // Solo si es acuática

    private Double altitudBaja;    // Solo si es terrestre
    private Double altitudAlta;    // Solo si es terrestre

    // Constructor vacío
    public Area() {
    }

    // Constructor general 
    public Area(String nombreReserva, double extension, String tipo) {
        this.nombreReserva = nombreReserva;
        this.extension = extension;
        this.tipo = tipo;
    }

    // Constructor para áreas acuáticas
    public Area(String nombreReserva, double extension, double profundidad) {
        this.nombreReserva = nombreReserva;
        this.extension = extension;
        this.tipo = "Acuática";
        this.profundidad = profundidad;
    }

    // Constructor para áreas terrestres
    public Area(String nombreReserva, double extension, double altitudBaja, double altitudAlta) {
        this.nombreReserva = nombreReserva;
        this.extension = extension;
        this.tipo = "Terrestre";
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
