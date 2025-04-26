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
    private Area area;        // Clase Area que deberías tener creada
    private Taxon taxon;      // Taxon al que pertenece la especie

    // Constructor vacío
    public Especie() {
    }

    // Constructor con parámetros
    public Especie(String nombreCientifico, String nombreComun, String descripcion, Area area, Taxon taxon) {
        this.nombreCientifico = nombreCientifico;
        this.nombreComun = nombreComun;
        this.descripcion = descripcion;
        this.area = area;
        this.taxon = taxon;
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

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Taxon getTaxon() {
        return taxon;
    }

    public void setTaxon(Taxon taxon) {
        this.taxon = taxon;
    }
}
