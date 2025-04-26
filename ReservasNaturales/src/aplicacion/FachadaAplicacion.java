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

    public FachadaAplicacion() {
        fgui = new FachadaGui(this);
        fbd = new FachadaBaseDatos(this);
        gu = new GestionUsuarios(fgui, fbd);
        ge = new GestionEspecies(fgui,fbd);
        ga = new GestionAreas(fgui, fbd);
        gt = new GestionTaxones(fbd, fgui);
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

    public List<Taxon> obtenerTaxones() {
        return gt.obtenerTaxones();
    }
}
