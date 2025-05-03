/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import baseDatos.FachadaBaseDatos;
import gui.FachadaGui;
import java.sql.Date;
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
    GestionAlimentos gal;
    GestionEjemplares gej;
    GestionConsumirAlimentos gca;
    GestionRevision gr;
    GestionClinicas gc;
    GestionMisiones gm;

    public FachadaAplicacion() {
        fgui = new FachadaGui(this);
        fbd = new FachadaBaseDatos(this);
        gu = new GestionUsuarios(fgui, fbd);
        ge = new GestionEspecies(fgui,fbd);
        ga = new GestionAreas(fgui, fbd);
        gt = new GestionTaxones(fbd, fgui);
        gal = new GestionAlimentos(fgui, fbd);
        gej = new GestionEjemplares(fgui, fbd);
        gca = new GestionConsumirAlimentos(fgui, fbd);
        gr = new GestionRevision(fbd,fgui);
        gc = new GestionClinicas(fbd,fgui);
        gm=new GestionMisiones(fgui, fbd);
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


    public boolean nuevoTrabajador(String dni, String nombre, int horas, float sueldo,String nombre_reserva) {
        return gu.nuevoTrabajador(dni,nombre,horas,sueldo,nombre_reserva);
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
    public boolean actualizarAreaUsuario(Usuario trabajador, Area areaSeleccionada) {
        return gu.actualizarAreaUsuario(trabajador,areaSeleccionada);
    }
    public List<Taxon> obtenerTaxones() {
        return gt.obtenerTaxones();
    }

    public List<Alimento> obtenerAlimentos() {
        return gal.obtenerAlimentos();
    }

    public int anadirAlimento(String nome, String tipo) {
        return gal.anadirAlimento(nome, tipo);
    }

    public int borrarAlimento(String nome, String tipo) {
        return gal.borrarAlimento(nome, tipo);
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

    public int novoEjemplar(Ejemplar ej) {
        return gej.novoEjemplar(ej);
    }

    public List<Ejemplar> obterEjemplares() {
        return gej.obtenerEjemplares();
    }

    public int borrarEjemplar(int id, String nom_cient) {
        return gej.borrarEjemplar(id, nom_cient);
    }

    public List<ConsumirAlimento> obterConsumirAlimentos() {
        return gca.obterConsumirAlmentos();
    }

    public int anadirConsumirAlimentos(ConsumirAlimento consumirAlimento) {
        return gca.anadirConsumirAlimentos(consumirAlimento);
    }

    public int borrarConsumirAlimento(int idEjemplar, String nombreCientifico, int idAlimento) {
        return gca.borrarConsumirAlimento(idEjemplar, nombreCientifico, idAlimento);
    }

    public List<ConsumirAlimento> obterConsumirAlimentos(int idAlimento) {
        return gca.obterConsumirAlimentos(idAlimento);
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

    public void crearVClinica(Ejemplar ejem) {
        fgui.crearVClinicas(ejem);
    }
     public List<Mision> obtenerMisiones() {
        return gm.obtenerMisiones();
    }

    public List<Mision> obtenerMisionesTrabajador(String textoBusqueda) {
        return gm.obtenerMisionesTrabajador(textoBusqueda); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Mision> obtenerMisonesEstado(String textoBusqueda) {
        return gm.obtenerMisionesEstado(textoBusqueda);
    }

    public List<Mision> obtenerMisionesEspecie(String textoBusqueda) {
        return gm.obtenerMisionesEspecie(textoBusqueda);
    }


    public boolean eliminarMision(Mision trabajador) {
        return gm.eliminarMision(trabajador);
    }

    public boolean actualizarMision(Mision seleccionada, Mision misionOriginal) {
        return gm.actualizarMision(seleccionada, misionOriginal);    }

    public Usuario obtenerTrabajadorMasExperimentado(String especie) {
        return  gm.obtenerTrabajadorMasExperimentado(especie);
    }

    public Usuario obtenerTrabajadorMision() {
        return gm.obtenerTrabajadorMision();}

    public boolean verificarMisionExistente(Mision mision) {
        return gm.verificarMisionExistente(mision);
    }

    public void agregarNuevaMision(Mision misionActual) {
        gm.agregarNuevaMision(misionActual);
    }

    public void completarMision(Mision misionSeleccionada) {
        gm.completarMision(misionSeleccionada);
    }
     public void borrarClinica(ClinicaMedica clinicaSeleccionada) {
        gc.borrarClinica(clinicaSeleccionada);
    }

    public ClinicaMedica nuevaClinica(ClinicaMedica clinica) {
        return gc.nuevaClinica(clinica);
    }

    public void añadirRevsion(ClinicaMedica clinicaRevision, Ejemplar ejemplarRevision, String text) {
         gr.añadirRevsion(clinicaRevision,ejemplarRevision,text);

    }
}
