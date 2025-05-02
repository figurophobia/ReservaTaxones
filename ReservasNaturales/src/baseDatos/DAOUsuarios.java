/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDatos;

import aplicacion.FachadaAplicacion;
import aplicacion.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author basesdatos
 */
public class DAOUsuarios extends AbstractDAO {

    
    

    public DAOUsuarios(Connection conexion, FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public Usuario validarUsuario(String nombre, String dni) {
        Usuario resultado = null;
        Connection con;
        PreparedStatement stmUsuario = null;
        ResultSet rsUsuario;

        con = this.getConexion();

        try {
            stmUsuario = con.prepareStatement("select dni, nombre, sueldo, horas "
                    + "from trabajadores "
                    + "where dni = ? and nombre = ?");
            stmUsuario.setString(1, dni);
            stmUsuario.setString(2, nombre);
            rsUsuario = stmUsuario.executeQuery();
            if (rsUsuario.next()) {
                resultado = new Usuario(rsUsuario.getString("dni"),
                                        rsUsuario.getString("nombre"),
                                        rsUsuario.getFloat("sueldo"),
                                        rsUsuario.getInt("horas"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmUsuario.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return resultado;
    }
    
    
    public List<Usuario> obtenerTrabajadoresDni(String textoBusqueda) {
        List<Usuario> resultado=new ArrayList<>();
        Connection con;
        PreparedStatement stmTrabajador=null;
        ResultSet rsTrabajador;
        con=this.getConexion();
        
        try {
        stmTrabajador = con.prepareStatement(
            "SELECT dni, nombre, sueldo, horas FROM trabajadores WHERE dni = ?"
        );
        stmTrabajador.setString(1, textoBusqueda);
        rsTrabajador = stmTrabajador.executeQuery();

        while (rsTrabajador.next()) {
            Usuario u = new Usuario(
                rsTrabajador.getString("dni"),
                rsTrabajador.getString("nombre"),
                rsTrabajador.getFloat("sueldo"),
                rsTrabajador.getInt("horas")
            );
            resultado.add(u);
        }

    } catch (SQLException e) {
        System.out.println(e.getMessage());
        this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
    } finally {
        try {
            if (stmTrabajador != null) stmTrabajador.close();
        } catch (SQLException e) {
            System.out.println("Imposible cerrar cursores");
        }
    }

    return resultado;
    
    
    
    
    
    
    
    }

public  List<Usuario> obtenerTrabajadoresNombre(String textoBusqueda) {
        List<Usuario> resultado=new ArrayList<>();
        Connection con;
        PreparedStatement stmTrabajador=null;
        ResultSet rsTrabajador;
        con=this.getConexion();
        
        try {
        stmTrabajador = con.prepareStatement(
            "SELECT dni, nombre, sueldo, horas FROM trabajadores WHERE nombre = ?"
        );
        stmTrabajador.setString(1, textoBusqueda);
        rsTrabajador = stmTrabajador.executeQuery();

        while (rsTrabajador.next()) {
            Usuario u = new Usuario(
                rsTrabajador.getString("dni"),
                rsTrabajador.getString("nombre"),
                rsTrabajador.getFloat("sueldo"),
                rsTrabajador.getInt("horas")
            );
            resultado.add(u);
        }

    } catch (SQLException e) {
        System.out.println(e.getMessage());
        this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
    } finally {
        try {
            if (stmTrabajador != null) stmTrabajador.close();
        } catch (SQLException e) {
            System.out.println("Imposible cerrar cursores");
        }
    }

    return resultado;
    }

    public List<Usuario> obtenerTodosLosTrabajadores() {
        List<Usuario> resultado = new ArrayList<>();
    Connection con;
    PreparedStatement stmTrabajador = null;
    ResultSet rsTrabajador;
    con = this.getConexion();

    try {
        stmTrabajador = con.prepareStatement(
            "SELECT dni, nombre, sueldo, horas FROM trabajadores"
        );
        rsTrabajador = stmTrabajador.executeQuery();

        while (rsTrabajador.next()) {
            Usuario u = new Usuario(
                rsTrabajador.getString("dni"),
                rsTrabajador.getString("nombre"),
                rsTrabajador.getFloat("sueldo"),
                rsTrabajador.getInt("horas")
            );
            resultado.add(u);
        }

    } catch (SQLException e) {
        System.out.println(e.getMessage());
        this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
    } finally {
        try {
            if (stmTrabajador != null) stmTrabajador.close();
        } catch (SQLException e) {
            System.out.println("Imposible cerrar cursores");
        }
    }

    return resultado;

    }

    public boolean nuevoTrabajador(String dni, String nombre, int horas, float sueldo) {
    Connection con;
    PreparedStatement stmTrabajador = null;
    boolean exito = true;

    con = this.getConexion();

    try {
        stmTrabajador = con.prepareStatement(
            "INSERT INTO trabajadores (dni, nombre, sueldo, horas) VALUES (?, ?, ?, ?)"
        );
        stmTrabajador.setString(1, dni);
        stmTrabajador.setString(2, nombre);
        stmTrabajador.setFloat(3, sueldo);
        stmTrabajador.setInt(4,horas);

        stmTrabajador.executeUpdate();

    } catch (SQLException e) {
        exito = false;
        System.out.println("Error al insertar trabajador: " + e.getMessage());
        this.getFachadaAplicacion().muestraExcepcion("Error al insertar trabajador: " + e.getMessage());
    } catch (NumberFormatException e) {
        exito = false;
        System.out.println("Formato incorrecto de sueldo u horas");
        this.getFachadaAplicacion().muestraExcepcion("Formato incorrecto de sueldo u horas");
    } finally {
        try {
            if (stmTrabajador != null) stmTrabajador.close();
        } catch (SQLException e) {
            System.out.println("Imposible cerrar cursores");
        }
    }

    return exito;
}

    boolean actualizarTrabajador(Usuario t) {
    Connection con;
    PreparedStatement stmTrabajador = null;
    boolean exito = true;

    con = this.getConexion();

    try {
        stmTrabajador = con.prepareStatement(
            "UPDATE trabajadores SET nombre = ?, sueldo = ?, horas = ? WHERE dni = ?"
        );
        stmTrabajador.setString(1, t.getNombre());
        stmTrabajador.setFloat(2, t.getSueldo());
        stmTrabajador.setInt(3, t.getHoras());
        stmTrabajador.setString(4, t.getDni());

        int filas = stmTrabajador.executeUpdate();
        if (filas == 0) {
            exito = false; // No se actualizó ninguna fila
        }

    } catch (SQLException e) {
        exito = false;
        System.out.println("Error al actualizar trabajador: " + e.getMessage());
        this.getFachadaAplicacion().muestraExcepcion("Error al actualizar trabajador: " + e.getMessage());
    } finally {
        try {
            if (stmTrabajador != null) stmTrabajador.close();
        } catch (SQLException e) {
            System.out.println("Imposible cerrar cursores");
        }
    }

    return exito;    }

    boolean eliminarTrabajador(String dni) {
    Connection con;
    PreparedStatement stmTrabajador = null;
    boolean exito = true;

    con = this.getConexion();

    try {
        stmTrabajador = con.prepareStatement(
            "DELETE FROM trabajadores WHERE dni = ?"
        );

        stmTrabajador.setString(1, dni);

        // Aquí es donde faltaba:
        int filas = stmTrabajador.executeUpdate();

        if (filas == 0) {
            exito = false; // No se eliminó ninguna fila (dni no encontrado)
        }

    } catch (SQLException e) {
        exito = false;
        System.out.println("Error al eliminar el trabajador: " + e.getMessage());
        this.getFachadaAplicacion().muestraExcepcion("Error al eliminar el trabajador: " + e.getMessage());
    } finally {
        try {
            if (stmTrabajador != null) stmTrabajador.close();
        } catch (SQLException e) {
            System.out.println("Imposible cerrar cursores");
        }
    }

    return exito;    }

    
    
    
    
}
