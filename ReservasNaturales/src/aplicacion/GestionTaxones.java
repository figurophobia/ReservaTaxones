/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

import baseDatos.FachadaBaseDatos;
import gui.FachadaGui;
import java.util.List;

public class GestionTaxones {
    
    private FachadaBaseDatos fbd;
    private FachadaGui fgui;
    
    // Constructor que recibe una instancia de FachadaBaseDatos y FachadaGui
    public GestionTaxones(FachadaBaseDatos fbd, FachadaGui fgui) {
        this.fbd = fbd;
        this.fgui = fgui;
    }
    
    // Método que obtiene todos los taxones
    public List<Taxon> obtenerTaxones() {
        return fbd.obtenerTaxones();  // Llama a la fachada de la base de datos para obtener los taxones
    }
    
}
