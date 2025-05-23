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
import java.util.logging.Level;
import java.util.logging.Logger;

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
                if (stmTrabajador != null) {
                    stmTrabajador.close();
                }
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
                if (stmTrabajador != null) {
                    stmTrabajador.close();
                }
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
                if (stmTrabajador != null) {
                    stmTrabajador.close();
                }
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
            stmTrabajador.setInt(4, horas);
            stmTrabajador.setString(5, nombre_reserva);

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
                if (stmTrabajador != null) {
                    stmTrabajador.close();
                }
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
                exito = false;
            }

        } catch (SQLException e) {
            exito = false;
            System.out.println("Error al actualizar trabajador: " + e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion("Error al actualizar trabajador: " + e.getMessage());
        } finally {
            try {
                if (stmTrabajador != null) {
                    stmTrabajador.close();
                }
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return exito;
    }

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
                if (stmTrabajador != null) {
                    stmTrabajador.close();
                }
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return exito;
    }

    boolean actualizarAreaUsuario(Usuario trabajador, Area areaSeleccionada) {
        Connection con;
        PreparedStatement stm = null, stmMisions = null;
        boolean exito = true;

        con = this.getConexion();

        try {
            stm = con.prepareStatement(
                    "UPDATE trabajadores SET nombre_reserva = ?, sueldo = sueldo + sueldo * 0.1 WHERE dni = ?"
            );

            stmMisions = con.prepareStatement("UPDATE misiones SET fecha_inicio = NOW() WHERE dni_trabajador = ? and fecha_fin IS NULL");

            if (areaSeleccionada != null) {
                stm.setString(1, areaSeleccionada.getNombreReserva());
            } else {
                stm.setNull(1, java.sql.Types.VARCHAR);
            }

            stm.setString(2, trabajador.getDni());

            con.setAutoCommit(false);

            int filas = stm.executeUpdate();

            stmMisions.setString(1, trabajador.getDni());

            int res = stmMisions.executeUpdate();

            if (filas != -1 || res != -1) {
                exito = true;
            }

            con.setAutoCommit(true);

        } catch (SQLException e) {
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(DAOUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
            exito = false;
            System.out.println("Error al actualizar área del usuario: " + e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion("Error al actualizar área del usuario: " + e.getMessage());
        }

        return exito;
    }

    public List<Usuario> obtenerTrabajadoresPorArea(String area) {
        List<Usuario> resultado = new ArrayList<>();
        Connection con = this.getConexion();
        PreparedStatement stmTrabajadores = null;
        ResultSet rsTrabajadores = null;

        try {
            // Consulta SQL para obtener trabajadores con misiones en el área especificada
            String consulta = "SELECT DISTINCT t.dni, t.nombre, t.sueldo, t.horas, t.nombre_reserva "
                    + "FROM trabajadores t "
                    + "JOIN misiones m ON t.dni = m.dni_trabajador "
                    + "JOIN ejemplar e ON m.nombre_cientifico_especie = e.nombre_cientifico_especie "
                    + "WHERE e.area_geografica = ? "
                    + "ORDER BY t.nombre";

            stmTrabajadores = con.prepareStatement(consulta);
            stmTrabajadores.setString(1, area);
            rsTrabajadores = stmTrabajadores.executeQuery();

            while (rsTrabajadores.next()) {
                String nombreReserva = rsTrabajadores.getString("nombre_reserva");
                Area area_2 = new Area(nombreReserva);
                Usuario usuario = new Usuario(
                        rsTrabajadores.getString("dni"),
                        rsTrabajadores.getString("nombre"),
                        rsTrabajadores.getFloat("sueldo"),
                        rsTrabajadores.getInt("horas"),
                        area_2
                );
                resultado.add(usuario);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener trabajadores por área: " + e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                if (rsTrabajadores != null) {
                    rsTrabajadores.close();
                }
                if (stmTrabajadores != null) {
                    stmTrabajadores.close();
                }
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
    }

    int reducirXornadaAreaMaisSaturada(int porcentaxeReduc) {
        Connection con;
        PreparedStatement stmUsuario = null;
        int res = -1;
        con = super.getConexion();

        try {
            float porcentaxe = porcentaxeReduc / 100f;
            stmUsuario = con.prepareStatement("update trabajadores set horas = horas - horas * ? where nombre_reserva = (SELECT nombre_reserva FROM trabajadores group by nombre_reserva order by count (*) DESC LIMIT 1)");
            stmUsuario.setFloat(1, porcentaxe);

            res = stmUsuario.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }

        return res;
    }

}
