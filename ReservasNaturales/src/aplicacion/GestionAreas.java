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

    public List<Area> buscarAreas(String textoBusqueda){ return fbd.buscarAreas(textoBusqueda); }

    public boolean actualizarArea(Area area) { return fbd.actualizarArea(area); }

    public boolean eliminarArea(String nombreReserva) { return fbd.eliminarArea(nombreReserva); }

    public boolean crearArea(Area area) { return fbd.crearArea(area); }

    public List<String> obtenerAreasPorEspecie(String nombreCientifico) {return fbd.obtenerAreasPorEspecie(nombreCientifico);}
}
