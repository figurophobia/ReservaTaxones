/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDatos;

import aplicacion.Area;
import aplicacion.FachadaAplicacion;
import aplicacion.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

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
            stmUsuario = con.prepareStatement("select dni, nombre, sueldo, horas,nombre_reserva "
                    + "from trabajadores "
                    + "where dni = ? and nombre = ?");
            stmUsuario.setString(1, dni);
            stmUsuario.setString(2, nombre);
            rsUsuario = stmUsuario.executeQuery();
            if (rsUsuario.next()) {
                //Area area = new Area(rsUsuario.getString("nombre_reserva"));
                String nombreReserva = rsUsuario.getString("nombre_reserva");
                Area area = new Area(nombreReserva);
                resultado = new Usuario(rsUsuario.getString("dni"),
                                        rsUsuario.getString("nombre"),
                                        rsUsuario.getFloat("sueldo"),
                                        rsUsuario.getInt("horas"),
                                        area);
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
    List<Usuario> resultado = new ArrayList<>();
    Connection con;
    PreparedStatement stmTrabajador = null;
    ResultSet rsTrabajador;
    con = this.getConexion();

    try {
        stmTrabajador = con.prepareStatement(
            "SELECT dni, nombre, sueldo, horas, nombre_reserva FROM trabajadores WHERE LOWER(dni) LIKE LOWER(?)"
        );
        stmTrabajador.setString(1, "%" + textoBusqueda + "%");
        rsTrabajador = stmTrabajador.executeQuery();

        while (rsTrabajador.next()) {
            Area area = new Area(rsTrabajador.getString("nombre_reserva"));
            Usuario u = new Usuario(
                rsTrabajador.getString("dni"),
                rsTrabajador.getString("nombre"),
                rsTrabajador.getFloat("sueldo"),
                rsTrabajador.getInt("horas"),
                area
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

public List<Usuario> obtenerTrabajadoresNombre(String textoBusqueda) {
    List<Usuario> resultado = new ArrayList<>();
    Connection con;
    PreparedStatement stmTrabajador = null;
    ResultSet rsTrabajador;
    con = this.getConexion();

    try {
        stmTrabajador = con.prepareStatement(
            "SELECT dni, nombre, sueldo, horas, nombre_reserva FROM trabajadores WHERE LOWER(nombre) LIKE LOWER(?)"
        );
        stmTrabajador.setString(1, "%" + textoBusqueda + "%");
        rsTrabajador = stmTrabajador.executeQuery();

        while (rsTrabajador.next()) {
            Area area = new Area(rsTrabajador.getString("nombre_reserva"));
            Usuario u = new Usuario(
                rsTrabajador.getString("dni"),
                rsTrabajador.getString("nombre"),
                rsTrabajador.getFloat("sueldo"),
                rsTrabajador.getInt("horas"),
                area
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
            "SELECT dni, nombre, sueldo, horas,nombre_reserva FROM trabajadores"
        );
        rsTrabajador = stmTrabajador.executeQuery();

        while (rsTrabajador.next()) {
            //Area area = new Area(rsTrabajador.getString("nombre_reserva"));
            String nombreReserva = rsTrabajador.getString("nombre_reserva");
            Area area = new Area(nombreReserva);
            Usuario u = new Usuario(
                rsTrabajador.getString("dni"),
                rsTrabajador.getString("nombre"),
                rsTrabajador.getFloat("sueldo"),
                rsTrabajador.getInt("horas"),
                area
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

    public boolean nuevoTrabajador(String dni, String nombre, int horas, float sueldo, String nombre_reserva) {
    Connection con;
    PreparedStatement stmTrabajador = null;
    boolean exito = true;

    con = this.getConexion();

    try {
        stmTrabajador = con.prepareStatement(
            "INSERT INTO trabajadores (dni, nombre, sueldo, horas,nombre_reserva) VALUES (?, ?, ?, ?,?)"
        );
        stmTrabajador.setString(1, dni);
        stmTrabajador.setString(2, nombre);
        stmTrabajador.setFloat(3, sueldo);
        stmTrabajador.setInt(4,horas);
        stmTrabajador.setString(5,nombre_reserva);

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

    boolean actualizarAreaUsuario(Usuario trabajador, Area areaSeleccionada) {
     Connection con;
    PreparedStatement stm = null;
    boolean exito = true;

    con = this.getConexion();

    try {
        stm = con.prepareStatement(
            "UPDATE trabajadores SET nombre_reserva = ? WHERE dni = ?"
        );
        
        if (areaSeleccionada != null) {
            stm.setString(1, areaSeleccionada.getNombreReserva());
        } else {
            stm.setNull(1, java.sql.Types.VARCHAR);
        }

        stm.setString(2, trabajador.getDni());

        int filas = stm.executeUpdate();
        if (filas == 0) {
            exito = false; 
        } else {
            trabajador.setArea(areaSeleccionada); // puede ser null, y está bien
        }

    } catch (SQLException e) {
        exito = false;
        System.out.println("Error al actualizar área del usuario: " + e.getMessage());
        this.getFachadaAplicacion().muestraExcepcion("Error al actualizar área del usuario: " + e.getMessage());
    } finally {
        try {
            if (stm != null) stm.close();
        } catch (SQLException e) {
            System.out.println("Imposible cerrar cursores");
        }
    }

    return exito;

    }
    
    void despedirInactivos() {
        String sql = "DELETE FROM trabajadores " +
                     "WHERE dni NOT IN ( " +
                     "    SELECT DISTINCT dni_trabajador " +
                     "    FROM misiones " +
                     "    WHERE fecha_inicio >= current_date - INTERVAL '1 month' " +
                     "    OR fecha_fin IS NULL " +
                     "    OR fecha_fin >= current_date " +
                     ")";

        Connection con = this.getConexion();
        try (
             PreparedStatement stm = con.prepareStatement(sql)) {

            int filas = stm.executeUpdate();

            if (filas > 0) {
                // Mensaje usando JOptionPane
                JOptionPane.showMessageDialog(null, "Se han despedido " + filas + " trabajadores inactivos.");
            } else {
                // Mensaje usando JOptionPane
                JOptionPane.showMessageDialog(null, "No hay trabajadores inactivos para despedir.");
            }

        } catch (SQLException e) {
            // Manejar si algún trabajador no puede eliminarse por la restricción
            System.out.println("Error al despedir inactivos: " + e.getMessage());
            // Mensaje de error
            JOptionPane.showMessageDialog(null, "No se han podido despedir algunos trabajadores porque aún tienen misiones asignadas.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void reducirPersonal() {
        String sqlMediaSueldo = "SELECT AVG(sueldo) FROM trabajadores";  // Calcula la media de sueldo
        String sqlEliminarTrabajadores = "DELETE FROM trabajadores WHERE sueldo > (SELECT AVG(sueldo) FROM trabajadores)";  
        // Elimina a los trabajadores con sueldo superior a la media
        Connection con = this.getConexion();

        try (PreparedStatement stmMediaSueldo = con.prepareStatement(sqlMediaSueldo);
             PreparedStatement stmEliminar = con.prepareStatement(sqlEliminarTrabajadores)) {

            // Ejecutamos la consulta para obtener la media del sueldo
            ResultSet rs = stmMediaSueldo.executeQuery();

            if (rs.next()) {
                double mediaSueldo = rs.getDouble(1);  // Extraemos la media de sueldo

                // Ahora ejecutamos la consulta para eliminar a los trabajadores con sueldo superior a la media
                int filasEliminadas = stmEliminar.executeUpdate();

                if (filasEliminadas > 0) {
                    JOptionPane.showMessageDialog(null, "Se han despedido " + filasEliminadas + " trabajadores por tener sueldo superior a la media.", "Reducción de Personal", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No hay trabajadores por despedir, todos están por debajo de la media.", "Reducción de Personal", JOptionPane.INFORMATION_MESSAGE);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al reducir personal: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo reducir el personal debido a un error en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }




    
    
}
