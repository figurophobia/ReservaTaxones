/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

import java.util.Date;

/**
 *
 * @author davidavirn
 */
public class Ejemplar {
    private int id;
    private Especie especie;
    private String mote;
    private String fec_nac;
    private Area area;

    // Constructor vacío
    public Ejemplar() {
    }

    public Ejemplar(int id, Especie especie, String mote, String fec_nac, Area area) {
        this.id = id;
        this.especie = especie;
        this.mote = mote;
        this.fec_nac = fec_nac;
        this.area = area;
    }

    public Ejemplar(Especie especie, String mote, String fec_nac, Area area) {
        this.especie = especie;
        this.mote = mote;
        this.fec_nac = fec_nac;
        this.area = area;
    }

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

    public String getFec_nac() {
        return fec_nac;
    }

    public void setFec_nac(String fec_nac) {
        this.fec_nac = fec_nac;
    }

    

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    
}
