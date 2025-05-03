/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;
import gui.FachadaGui;
import baseDatos.FachadaBaseDatos;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author alumnogreibd
 */
public class GestionMisiones {
    FachadaGui fgui;
    FachadaBaseDatos fbd;
    public GestionMisiones(FachadaGui fgui, FachadaBaseDatos fbd){
     this.fgui=fgui;
     this.fbd=fbd;
    }  
        public List<Mision> obtenerMisiones() {
        return fbd.obtenerMisiones();
    }

    public List<Mision> obtenerMisionesEstado(String textoBusqueda) {
        return fbd.obtenerMisionesEstado(textoBusqueda);    }

    public List<Mision> obtenerMisionesTrabajador(String textoBusqueda) {
        return fbd.obtenerMisionesTrabajador(textoBusqueda);
    }

    public List<Mision> obtenerMisionesEspecie(String textoBusqueda) {
        return fbd.obtenerMisionesEspecie(textoBusqueda);
    }

    public boolean eliminarMision(Usuario trabajador, Date fechaFin, String especie) {
        return fbd.eliminarMision(trabajador,fechaFin,especie);}

    public boolean actualizarMision(Mision seleccionada) {
        return fbd.actualizarMision(seleccionada);     
}

    public Usuario obtenerTrabajadorMasExperimentado(String especie) {
            return fbd.obtenerTrabajadorMasExperimentado(especie);    }

    public Usuario obtenerTrabajadorMision() {
        return fbd.obtenerTrabajadorMision();    }
}