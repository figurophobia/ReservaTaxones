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

    public List<Mision> obtenerMisionesGeneral(String textoBusqueda){ return fbd.obtenerMisionesGeneral(textoBusqueda); }

    public List<Mision> obtenerMisionesEstado(String textoBusqueda) {
        return fbd.obtenerMisionesEstado(textoBusqueda);    }

    public List<Mision> obtenerMisionesTrabajador(String textoBusqueda) {
        return fbd.obtenerMisionesTrabajador(textoBusqueda);
    }

    public List<Mision> obtenerMisionesEspecie(String textoBusqueda) {
        return fbd.obtenerMisionesEspecie(textoBusqueda);
    }

    public boolean eliminarMision(Mision trabajador) {
        return fbd.eliminarMision(trabajador);}

    public boolean actualizarMision(Mision seleccionada, Mision misionOriginal) {
        return fbd.actualizarMision(seleccionada, misionOriginal);     
}

    public Usuario obtenerTrabajadorMision() {
        return fbd.obtenerTrabajadorMision();    }

    boolean verificarMisionExistente(Mision mision) {
        return fbd.verificarMisionExistente(mision);
    }

    void agregarNuevaMision(Mision misionActual) {
        fbd.agregarNuevaMision(misionActual);
    }

    void completarMision(Mision misionSeleccionada) {
        fbd.completarMision(misionSeleccionada);
    }

    public Usuario obtenerTrabajadorMasExperimentado(List<Usuario> trabajadoresDisponibles, String especie){return fbd.obtenerTrabajadorMasExperimentado(trabajadoresDisponibles, especie);}

    public Mision obtenerMisionMasAntigua(String dni) { return fbd.obtenerMisionMasAntigua(dni); }

    public int contarMisionesActivas(String dni) { return fbd.contarMisionesActivas(dni); }

}