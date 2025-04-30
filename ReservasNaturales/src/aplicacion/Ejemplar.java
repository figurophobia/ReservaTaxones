/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

/**
 *
 * @author davidavirn
 */
public class Ejemplar {
    private int id;
    private Especie especie;
    private String mote;
    private int edad;

    // Constructor vacío
    public Ejemplar() {
    }

    // Constructor con parámetros
    public Ejemplar(int id, Especie especie, String mote, int edad) {
        this.id = id;
        this.especie = especie;
        this.mote = mote;
        this.edad = edad;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public String getMote() {
        return mote;
    }

    public void setMote(String mote) {
        this.mote = mote;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
