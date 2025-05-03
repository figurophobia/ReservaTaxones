/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

/**
 *
 * @author davidavirn
 */
public class Taxon {
    private String nombre;
    private String tipo;
    private Taxon taxonSuperior;
    
    public Taxon() {
    }
    
    public Taxon(String nombre, String tipo, Taxon taxonSuperior) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.taxonSuperior = taxonSuperior;
    }

    public Taxon(String nombre_taxon) {
        this.nombre = nombre_taxon;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Taxon getTaxonSuperior() {
        return taxonSuperior;
    }

    public void setTaxonSuperior(Taxon taxonSuperior) {
        this.taxonSuperior = taxonSuperior;
    }
}
