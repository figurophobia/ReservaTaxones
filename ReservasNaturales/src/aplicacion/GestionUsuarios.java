/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion;
import gui.FachadaGui;
import baseDatos.FachadaBaseDatos;
import java.util.List;
/**
 *
 * @author basesdatos
 */
public class GestionUsuarios {
     
    FachadaGui fgui;
    FachadaBaseDatos fbd;
    
   
    public GestionUsuarios(FachadaGui fgui, FachadaBaseDatos fbd){
     this.fgui=fgui;
     this.fbd=fbd;
    }  
    
    public Boolean comprobarAutentificacion(String idUsuario, String clave){
      Usuario u;

      u=fbd.validarUsuario(idUsuario, clave);
      if (u!=null){
          //return u.getTipoUsuario()==TipoUsuario.Administrador;
          return true;
      } else return false;
  }

    public List<Usuario> obtenerTrabajdoresDni(String textoBusqueda) {
        return fbd.obtenerTrabajadoresDni(textoBusqueda);
    }

    public List<Usuario> obtenerTrabajdoresNombre(String textoBusqueda) {
        return fbd.obtenerTrabajadoresNombre(textoBusqueda);
    }

    public boolean actualizarAreaUsuario(Usuario trabajador, Area areaSeleccionada) {
        return fbd.actualizarAreaUsuario(trabajador, areaSeleccionada);
    }

    public List<Usuario> obtenerTodosLosTrabajadores() {
        return fbd.obtenerTodosLosTrabajadores(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    boolean nuevoTrabajador(String dni, String nombre, int horas, float sueldo, String nombre_reserva) {
        return fbd.nuevoTrabajador(dni,nombre,horas,sueldo,nombre_reserva);
    }

    public boolean actualizarTrabajador(Usuario t) {
        return fbd.actualizarTrabajador(t);
    }

    public boolean eliminarTrabajador(String dni) {
        return fbd.eliminarTrabajador(dni); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Usuario> obtenerTrabajadoresPorArea(String area) {return fbd.obtenerTrabajadoresPorArea(area);}
  
}
