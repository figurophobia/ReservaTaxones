/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import baseDatos.FachadaBaseDatos;
import gui.FachadaGui;
import java.util.List;


/**
 *
 * @author basesdatos
 */
public class FachadaAplicacion {

    FachadaGui fgui;
    FachadaBaseDatos fbd;
    GestionUsuarios gu;
    GestionEspecies ge;
    GestionAreas ga;
    GestionTaxones gt;
    GestionRevision gr;
    GestionClinicas gc;

    public FachadaAplicacion() {
        fgui = new FachadaGui(this);
        fbd = new FachadaBaseDatos(this);
        gu = new GestionUsuarios(fgui, fbd);
        ge = new GestionEspecies(fgui,fbd);
        ga = new GestionAreas(fgui, fbd);
        gt = new GestionTaxones(fbd, fgui);
        gr = new GestionRevision(fbd,fgui);
        gc = new GestionClinicas(fbd,fgui);
    }

    public static void main(String args[]) {
        FachadaAplicacion fa;

        fa = new FachadaAplicacion();
        fa.iniciaInterfazUsuario();
    }

    public void iniciaInterfazUsuario() {
        fgui.iniciaVista();
    }

    public void muestraExcepcion(String e) {
        fgui.muestraExcepcion(e);
    }
    
     public Boolean comprobarAutentificacion(String idUsuario, String clave) {
        return gu.comprobarAutentificacion(idUsuario, clave);
    }

    public List<Usuario> obtenerTrabajadoresDni(String textoBusqueda) {
        return gu.obtenerTrabajdoresDni(textoBusqueda);
    }

    public List<Usuario> obtenerTrabajadoresNombre(String textoBusqueda) {
        return gu.obtenerTrabajdoresNombre(textoBusqueda);
    }

    public List<Usuario> obtenerTodosLosTrabajadores() {
        return gu.obtenerTodosLosTrabajadores();// Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean nuevoTrabajador(String dni, String nombre, int horas, float sueldo) {
        return gu.nuevoTrabajador(dni,nombre,horas,sueldo);
    }

    public boolean actualizarTrabajador(Usuario t) {
        return gu.actualizarTrabajador(t);
    }

    public boolean eliminarTrabajador(String dni) {
        return gu.eliminarTrabajador(dni);// Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Especie> obtenerEspecies(String text) {
        return ge.obtenerEspecies(text);
    }

    public void nuevaEspecie() {
        ge.nuevaEspecie();
    }

    public List<Area> obtenerAreas() {
        return ga.obtenerAreas();
    }

    public List<Area> buscarAreas(String textoBusqueda){ return ga.buscarAreas(textoBusqueda); }

    public boolean actualizarArea(Area area) { return ga.actualizarArea(area); }

    public boolean eliminarArea(String nombreReserva) { return ga.eliminarArea(nombreReserva); }

    public boolean crearArea(Area area) { return ga.crearArea(area); }

    public List<Taxon> obtenerTaxones() {
        return gt.obtenerTaxones();
    }

    public void editarEspecie(Especie e) {
        ge.editarEspecie(e);
    }

    public void anhadirEspecie(Especie e) {
        ge.anhadirEspecie(e);
    }

    public void actualizarEspecie(Especie e, Especie eNueva) {
        ge.actualizarEspecie(e,eNueva);
    }

    public void borrarEspecie(Especie e) {
        ge.borrarEspecie(e);
    }
    public void crearRevision(ClinicaMedica clinicaSeleccionada,Ejemplar ejemplar) {
       fgui.crearRevision(clinicaSeleccionada,ejemplar);
    }

    public List<Revision> obtenerRevisiones(String nClinica, int Id) {
        return gr.obtenerRevisiones(nClinica, Id);
    }

    public List<ClinicaMedica> obtenerClinicas(String textoBusqueda) {
          return gc.obtenerClinicas(textoBusqueda);
    }
}
