    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
package aplicacion;

/**
 *
 * @author davidavirn
 */
public class Especie {
    private String nombreCientifico;
    private String nombreComun;
    private String descripcion;
    private Taxon taxon;  // Taxon al que pertenece la especie

    // Constructor vacío
    public Especie() {
    }

    // Constructor con parámetros
    public Especie(String nombreCientifico, String nombreComun, String descripcion, Taxon taxon) {
        this.nombreCientifico = nombreCientifico;
        this.nombreComun = nombreComun;
        this.descripcion = descripcion;
        this.taxon = taxon;
    }

    public Especie(String nombreCientifico) {
        this.nombreCientifico = nombreCientifico;
    }

    // Getters y Setters
    public String getNombreCientifico() {
        return nombreCientifico;
    }

    public void setNombreCientifico(String nombreCientifico) {
        this.nombreCientifico = nombreCientifico;
    }

    public String getNombreComun() {
        return nombreComun;
    }

    public void setNombreComun(String nombreComun) {
        this.nombreComun = nombreComun;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Taxon getTaxon() {
        return taxon;
    }

    public void setTaxon(Taxon taxon) {
        this.taxon = taxon;
    }
}
