/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package baseDatos;


import aplicacion.Alimento;
import aplicacion.Area;
import aplicacion.ClinicaMedica;
import aplicacion.Revision;
import aplicacion.Ejemplar;
import aplicacion.Area;
import aplicacion.ConsumirAlimento;
import aplicacion.Area;
import aplicacion.Especie;
import aplicacion.FachadaAplicacion;
import aplicacion.Taxon;
import aplicacion.Usuario;
import java.sql.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author basesdatos
 */
public class FachadaBaseDatos {
    private FachadaAplicacion fa;
    private Connection conexion;
    private DAOUsuarios daoUsuarios;
    private DAOEspecies daoEspecies;
    private DAOAreas daoAreas;
    private DAOTaxones daoTaxones;
    private DAOAlimentos daoAlimentos;
    private DAOEjemplares daoEjemplares;
    private DAOConsumirAlimentos daoConsumirAlimentos;
    private DAORevisiones daoRevisiones;
    private DAOClinicas daoClinicas;

    public FachadaBaseDatos (FachadaAplicacion fa){
        
        Properties configuracion = new Properties();
        this.fa=fa;
        FileInputStream arqConfiguracion;

        try {
            arqConfiguracion = new FileInputStream("baseDatos.properties");
            configuracion.load(arqConfiguracion);
            arqConfiguracion.close();

            Properties usuario = new Properties();
     

            String gestor = configuracion.getProperty("gestor");

            usuario.setProperty("user", configuracion.getProperty("usuario"));
            usuario.setProperty("password", configuracion.getProperty("clave"));
            
            System.out.println("jdbc:"+gestor+"://"+
                    configuracion.getProperty("servidor")+":"+
                    configuracion.getProperty("puerto")+"/"+
                    configuracion.getProperty("baseDatos"));
            
            System.out.println(usuario);
            
            this.conexion= DriverManager.getConnection("jdbc:"+gestor+"://"+
                    configuracion.getProperty("servidor")+":"+
                    configuracion.getProperty("puerto")+"/"+
                    configuracion.getProperty("baseDatos"),
                    usuario);
            
            daoUsuarios = new DAOUsuarios(conexion, fa);
            daoEspecies = new DAOEspecies(conexion, fa);
            daoAreas = new DAOAreas(conexion, fa);
            daoTaxones = new DAOTaxones(conexion, fa);
            daoAlimentos = new DAOAlimentos(conexion, fa);
            daoEjemplares = new DAOEjemplares(conexion, fa);
            daoConsumirAlimentos = new DAOConsumirAlimentos(conexion, fa);
            daoRevisiones = new DAORevisiones(conexion,fa);
            daoClinicas = new DAOClinicas(conexion,fa);
          


        } catch (FileNotFoundException f){
            System.out.println(f.getMessage());
            fa.muestraExcepcion(f.getMessage());
        } catch (IOException i){
            System.out.println(i.getMessage());
            fa.muestraExcepcion(i.getMessage());
        } 
        catch (java.sql.SQLException e){
            System.out.println(e.getMessage());
            fa.muestraExcepcion(e.getMessage());
            System.out.println("ERROR");
            System.out.flush();
        }
        
        
        
    }

    public Usuario validarUsuario(String idUsuario, String clave){
        return daoUsuarios.validarUsuario(idUsuario, clave);
    }

    public List<Usuario> obtenerTrabajadoresDni(String textoBusqueda) {
        return daoUsuarios.obtenerTrabajadoresDni(textoBusqueda);
    }

    public List<Usuario> obtenerTrabajadoresNombre(String textoBusqueda) {
        return daoUsuarios.obtenerTrabajadoresNombre(textoBusqueda);
    }

    public List<Usuario> obtenerTodosLosTrabajadores() {
        return daoUsuarios.obtenerTodosLosTrabajadores(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean nuevoTrabajador(String dni, String nombre, int horas, float sueldo) {
        return daoUsuarios.nuevoTrabajador(dni,nombre,horas,sueldo);
    }

    public boolean actualizarTrabajador(Usuario t) {
        return daoUsuarios.actualizarTrabajador(t);
    }

    public boolean eliminarTrabajador(String dni) {
        return daoUsuarios.eliminarTrabajador(dni);
    }

    public List<Especie> obtenerEspecies(String nombreCientifico) {
        return daoEspecies.obtenerEspecies(nombreCientifico);
    }

    public List<Area> obtenerAreas() {
        return daoAreas.obtenerAreas();
    }

    public List<Area> buscarAreas(String textoBusqueda){ return daoAreas.buscarAreas(textoBusqueda); }

    public boolean actualizarArea(Area area) { return daoAreas.actualizarArea(area); }

    public boolean eliminarArea(String nombreReserva) { return daoAreas.eliminarArea(nombreReserva); }

    public boolean crearArea(Area area) { return daoAreas.crearArea(area); }

    public List<Taxon> obtenerTaxones() {
        return daoTaxones.obtenerTaxones();
    }

    public List<Alimento> obtenerAlimentos() {
        return daoAlimentos.obtenerAlimentos();
    }

    public int anadirAlimento(String nome, String tipo) {
        return daoAlimentos.anadirAlimento(nome, tipo);
    }

    public int borrarAlimento(String nome, String tipo) {
        return daoAlimentos.borrarAlimento(nome, tipo);
    }

    public List<Ejemplar> obtenerEjemplares() {
        return daoEjemplares.obtenerEjemplares();
    }
    
  

    public void anhadirEspecie(Especie e) {
        daoEspecies.anhadirEspecie(e);
    }

    public void actualizarEspecie(Especie e, Especie eNueva) {
        daoEspecies.actualizarEspecie(e,eNueva);
    }

    public void borrarEspecie(Especie e) {
        daoEspecies.borrarEspecie(e);
    }

    public int novoEjemplar(Ejemplar ej) {
        return daoEjemplares.novoEjemplar(ej);
    }

    public int borrarEjemplar(int id, String nom_cient) {
        return daoEjemplares.borrarEjemplar(id, nom_cient);
    }

    public List<ConsumirAlimento> obterConsumirAlimentos() {
        return daoConsumirAlimentos.obterConsumirAlimentos();
    }

    public int anadirConsumirAlimentos(ConsumirAlimento consumirAlimento) {
        return daoConsumirAlimentos.anadirConsumirAlimento(consumirAlimento);
    }

    public int borrarConsumirAlimento(int idEjemplar, String nombreCientifico, int idAlimento) {
        return daoConsumirAlimentos.borrarConsumirAlimento(idEjemplar, nombreCientifico, idAlimento);
    }

    public List<ConsumirAlimento> obterConsumirAlimentos(int idAlimento) {
         return daoConsumirAlimentos.obterConsumirAlimentos(idAlimento);
    }
    public List<Revision> obtenerRevisiones(String nClinica, int Id) {
        return daoRevisiones.obtenerRevisiones(nClinica,Id);
    }

    public List<ClinicaMedica> obtenerClinicas(String textoBusqueda) {
        return daoClinicas.obtenerClinicas(textoBusqueda);
    }
}
