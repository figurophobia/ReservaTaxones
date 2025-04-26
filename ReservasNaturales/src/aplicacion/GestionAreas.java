/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

import gui.FachadaGui;
import baseDatos.FachadaBaseDatos;
import java.util.List;

/**
 *
 * @author davidavirn
 */
public class GestionAreas {
     
    private FachadaGui fgui;
    private FachadaBaseDatos fbd;
    
    public GestionAreas(FachadaGui fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }

    public List<Area> obtenerAreas() {
        return fbd.obtenerAreas();
    }

}
