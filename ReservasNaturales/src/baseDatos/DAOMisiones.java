/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseDatos;


import aplicacion.FachadaAplicacion;
import aplicacion.Mision;
import aplicacion.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 *
 * @author alumnogreibd
 */
public class DAOMisiones extends AbstractDAO{
    
    public DAOMisiones(Connection conexion, FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
 
    public List<Mision> obtenerMisiones() {
    List<Mision> resultado = new ArrayList<>();
    String sql = "SELECT dni_trabajador, fecha_inicio, fecha_fin, descripcion, nombre_cientifico_especie, completada FROM misiones";
    Connection con;
    con=this.getConexion();
    // Usar la conexión obtenida desde el constructor
    try ( 
         PreparedStatement stmMision = con.prepareStatement(sql);
         ResultSet rsMision = stmMision.executeQuery()) {

        while (rsMision.next()) {
            String dni = rsMision.getString("dni_trabajador");
            Usuario trabajador = obtenerUsuarioDni(dni);

            if (trabajador != null) {
                Mision m = new Mision(
                    trabajador,
                    rsMision.getString("nombre_cientifico_especie"),
                    rsMision.getDate("fecha_inicio"),
                    rsMision.getDate("fecha_fin"),
                    rsMision.getString("descripcion"),
                    "completada".equalsIgnoreCase(rsMision.getString("completada"))
                );
                resultado.add(m);
            }
        }

    } catch (SQLException e) {
        System.out.println("Error al obtener las misiones: " + e.getMessage());
        this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
    }

    return resultado;
}


    private Usuario obtenerUsuarioDni(String dni) {
        Usuario resultado = null;
        String sql = "SELECT dni, nombre, sueldo, horas, nombre_reserva FROM trabajadores WHERE dni = ?";

        try (PreparedStatement stmUsuario = this.getConexion().prepareStatement(sql)) {
            stmUsuario.setString(1, dni);

            try (ResultSet rsUsuario = stmUsuario.executeQuery()) {
                if (rsUsuario.next()) {
                    resultado = new Usuario(
                        rsUsuario.getString("dni"),
                        rsUsuario.getString("nombre"),
                        rsUsuario.getFloat("sueldo"),
                        rsUsuario.getInt("horas"),
                        rsUsuario.getString("nombre_reserva")
                         
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener el usuario por DNI: " + e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }

        return resultado;
    }

    public List<Mision> obtenerMisionesEstado(String textoBusqueda) {
    List<Mision> resultado = new ArrayList<>();
    // Modificar la consulta para trabajar con valores booleanos
    String sql = "SELECT dni_trabajador, fecha_inicio, fecha_fin, descripcion, nombre_cientifico_especie, completada " +
                 "FROM misiones WHERE completada = ?";

    try (PreparedStatement stm = this.getConexion().prepareStatement(sql)) {
        // Convertir el texto de búsqueda en un valor booleano
        boolean estadoBusqueda = "completada".equalsIgnoreCase(textoBusqueda);
        stm.setBoolean(1, estadoBusqueda); // Usar setBoolean para enviar el valor true o false

        try (ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                String dni = rs.getString("dni_trabajador");
                Usuario trabajador = obtenerUsuarioDni(dni);

                if (trabajador != null) {
                    Mision m = new Mision(
                        trabajador,
                        rs.getString("nombre_cientifico_especie"),
                        rs.getDate("fecha_inicio"),
                        rs.getDate("fecha_fin"),
                        rs.getString("descripcion"),
                        rs.getBoolean("completada") // Usar getBoolean para obtener el valor booleano
                    );
                    resultado.add(m);
                }
            }
        }

    } catch (SQLException e) {
        System.out.println("Error al obtener misiones por estado: " + e.getMessage());
        this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
    }

    return resultado;
    }

    public List<Mision> obtenerMisionesTrabajador(String textoBusqueda) {
        List<Mision> resultado = new ArrayList<>();
        String sql = "SELECT dni_trabajador, fecha_inicio, fecha_fin, descripcion, nombre_cientifico_especie, completada " +
                     "FROM misiones m JOIN trabajadores u ON m.dni_trabajador = u.dni " +
                     "WHERE u.nombre ILIKE ?";

        try (PreparedStatement stm = this.getConexion().prepareStatement(sql)) {
            stm.setString(1, "%" + textoBusqueda + "%");

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    String dni = rs.getString("dni_trabajador");
                    Usuario trabajador = obtenerUsuarioDni(dni);

                    if (trabajador != null) {
                        Mision m = new Mision(
                            trabajador,
                            rs.getString("nombre_cientifico_especie"),
                            rs.getDate("fecha_inicio"),
                            rs.getDate("fecha_fin"),
                            rs.getString("descripcion"),
                            "completada".equalsIgnoreCase(rs.getString("completada"))
                        );
                        resultado.add(m);
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener misiones por trabajador: " + e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }

        return resultado;
    }

    public List<Mision> obtenerMisionesEspecie(String textoBusqueda) {
        List<Mision> resultado = new ArrayList<>();
        String sql = "SELECT dni_trabajador, fecha_inicio, fecha_fin, descripcion, nombre_cientifico_especie, completada " +
                     "FROM misiones WHERE nombre_cientifico_especie ILIKE ?";

        try (PreparedStatement stm = this.getConexion().prepareStatement(sql)) {
            stm.setString(1, "%" + textoBusqueda + "%");

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    String dni = rs.getString("dni_trabajador");
                    Usuario trabajador = obtenerUsuarioDni(dni);

                    if (trabajador != null) {
                        Mision m = new Mision(
                            trabajador,
                            rs.getString("nombre_cientifico_especie"),
                            rs.getDate("fecha_inicio"),
                            rs.getDate("fecha_fin"),
                            rs.getString("descripcion"),
                            "completada".equalsIgnoreCase(rs.getString("completada"))
                        );
                        resultado.add(m);
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener misiones por especie: " + e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }

        return resultado;
    }

    public boolean eliminarMision(Usuario trabajador, Date fechaFin, String especie) {
    String sql = "DELETE FROM misiones WHERE dni_trabajador = ? AND fecha_fin = ? AND nombre_cientifico_especie = ?";
    boolean exito = false;

    try (PreparedStatement stm = this.getConexion().prepareStatement(sql)) {
        stm.setString(1, trabajador.getDni());
        stm.setDate(2, fechaFin);
        stm.setString(3, especie);

        int filasAfectadas = stm.executeUpdate();
        exito = filasAfectadas > 0;

    } catch (SQLException e) {
        System.out.println("Error al eliminar la misión: " + e.getMessage());
        this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
    }

    return exito;
}

 
public boolean actualizarMision(Mision seleccionada) {
    String sql = "UPDATE misiones SET completada = true " +
                 "WHERE dni_trabajador = ? AND fecha_inicio = ? AND nombre_cientifico_especie = ?";
    boolean exito = false;

    try (PreparedStatement stm = this.getConexion().prepareStatement(sql)) {
        

        stm.setString(1, seleccionada.getTrabajador().getDni());
        stm.setDate(2, new java.sql.Date(seleccionada.getFechaInicio().getTime()));
        stm.setString(3, seleccionada.getEspecie());
        
        System.out.println(stm);

        int filasAfectadas = stm.executeUpdate();
        
        exito = filasAfectadas > 0;
        if(exito){
            seleccionada.setCompletada(true);
        }
        if (filasAfectadas == 0) {
        System.out.println("No se actualizó ninguna misión. Verifica los datos:");
        System.out.println("DNI: " + seleccionada.getTrabajador().getDni());
        System.out.println("Fecha inicio: " + seleccionada.getFechaInicio());
        System.out.println("Especie: " + seleccionada.getEspecie());
}

    } catch (SQLException e) {
        System.out.println("Error al actualizar la misión: " + e.getMessage());
        this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
    }

    return exito;
}

    public Usuario obtenerTrabajadorMasExperimentado(String especie) {
    Usuario resultado = null;

    String sql = 
        "SELECT t.dni, t.nombre, t.sueldo, t.horas, t.nombre_reserva " +
        "FROM trabajadores t " +
        "JOIN misiones m ON t.dni = m.dni_trabajador " +
        "WHERE m.nombre_cientifico_especie = ? " +
        "AND t.dni NOT IN ( " +
        "    SELECT dni_trabajador FROM misiones WHERE completada = false " +
        ") " +
        "GROUP BY t.dni, t.nombre, t.sueldo, t.horas, t.nombre_reserva " +
        "ORDER BY COUNT(*) DESC " +
        "LIMIT 1";

    try (PreparedStatement stm = this.getConexion().prepareStatement(sql)) {
        stm.setString(1, especie);

        try (ResultSet rs = stm.executeQuery()) {
            if (rs.next()) {
                resultado = new Usuario(
                    rs.getString("dni"),
                    rs.getString("nombre"),
                    rs.getFloat("sueldo"),
                    rs.getInt("horas"),
                    rs.getString("nombre_reserva")
                );
            }
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener el trabajador más experimentado: " + e.getMessage());
        this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
    }

    return resultado;
}

    public Usuario obtenerTrabajadorMision() {
        Usuario resultado = null;
    String sql = 
        "SELECT t.dni, t.nombre, t.sueldo, t.horas, t.nombre_reserva " +
        "FROM trabajadores t " +
        "LEFT JOIN misiones m ON t.dni = m.dni_trabajador " +
        "GROUP BY t.dni, t.nombre, t.sueldo, t.horas, t.nombre_reserva " +
        "HAVING COUNT(CASE WHEN m.fecha_fin > CURRENT_DATE THEN 1 ELSE NULL END) = 0 " +
        "ORDER BY COUNT(m.dni_trabajador) ASC " +
        "LIMIT 1";

    try (PreparedStatement stm = this.getConexion().prepareStatement(sql)) {
        try (ResultSet rs = stm.executeQuery()) {
            if (rs.next()) {
                resultado = new Usuario(
                    rs.getString("dni"),
                    rs.getString("nombre"),
                    rs.getFloat("sueldo"),
                    rs.getInt("horas"),
                    rs.getString("nombre_reserva")
                );
            }
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener un trabajador sin misiones activas: " + e.getMessage());
        this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
    }

    return resultado;
    }
    
}
