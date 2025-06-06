/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseDatos;

import aplicacion.Area;
import aplicacion.FachadaAplicacion;
import aplicacion.Mision;
import aplicacion.Usuario;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author alumnogreibd
 */
public class DAOMisiones extends AbstractDAO {

    public DAOMisiones(Connection conexion, FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public List<Mision> obtenerMisiones() {
        List<Mision> resultado = new ArrayList<>();
        String sql = "SELECT dni_trabajador, fecha_inicio, fecha_fin, descripcion, nombre_cientifico_especie FROM misiones";
        Connection con;
        con = this.getConexion();
        // Usar la conexión obtenida desde el constructor
        try (
                PreparedStatement stmMision = con.prepareStatement(sql); ResultSet rsMision = stmMision.executeQuery()) {

            while (rsMision.next()) {
                String dni = rsMision.getString("dni_trabajador");
                Usuario trabajador = obtenerUsuarioDni(dni);

                if (trabajador != null) {
                    Mision m = new Mision(
                            trabajador,
                            rsMision.getString("nombre_cientifico_especie"),
                            rsMision.getDate("fecha_inicio"),
                            rsMision.getDate("fecha_fin"),
                            rsMision.getString("descripcion")
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
                    String nombreReserva = rsUsuario.getString("nombre_reserva");
                    Area area = new Area(nombreReserva);
                    resultado = new Usuario(
                            rsUsuario.getString("dni"),
                            rsUsuario.getString("nombre"),
                            rsUsuario.getFloat("sueldo"),
                            rsUsuario.getInt("horas"),
                            area);
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
        String sql;

        if ("completada".equalsIgnoreCase(textoBusqueda)) {
            sql = "SELECT dni_trabajador, fecha_inicio, fecha_fin, descripcion, nombre_cientifico_especie "
                    + "FROM misiones WHERE fecha_fin IS NOT NULL";
        } else if ("incompleta".equalsIgnoreCase(textoBusqueda)) {
            sql = "SELECT dni_trabajador, fecha_inicio, fecha_fin, descripcion, nombre_cientifico_especie "
                    + "FROM misiones WHERE fecha_fin IS NULL";
        } else {
            return resultado;
        }

        try (PreparedStatement stm = this.getConexion().prepareStatement(sql); ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                String dni = rs.getString("dni_trabajador");
                Usuario trabajador = obtenerUsuarioDni(dni);

                if (trabajador != null) {
                    Mision m = new Mision(
                            trabajador,
                            rs.getString("nombre_cientifico_especie"),
                            rs.getDate("fecha_inicio"),
                            rs.getDate("fecha_fin"),
                            rs.getString("descripcion")
                    );
                    resultado.add(m);
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
        String sql = "SELECT dni_trabajador, fecha_inicio, fecha_fin, descripcion, nombre_cientifico_especie "
                + "FROM misiones m JOIN trabajadores u ON m.dni_trabajador = u.dni "
                + "WHERE u.nombre ILIKE ?";

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
                                rs.getString("descripcion")
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
        String sql = "SELECT dni_trabajador, fecha_inicio, fecha_fin, descripcion, nombre_cientifico_especie "
                + "FROM misiones WHERE nombre_cientifico_especie ILIKE ?";

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
                                rs.getString("descripcion")
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

    public boolean eliminarMision(Mision mision) {
        String sql = "DELETE FROM misiones WHERE dni_trabajador = ? AND fecha_inicio = ? AND nombre_cientifico_especie = ?";
        boolean exito = false;

        try (PreparedStatement stm = this.getConexion().prepareStatement(sql)) {
            // Obtener el dni, fecha de inicio y especie de la misión
            stm.setString(1, mision.getTrabajador().getDni());
            stm.setDate(2, new java.sql.Date(mision.getFechaInicio().getTime()));  // Convertir a java.sql.Date
            stm.setString(3, mision.getEspecie());

            int filasAfectadas = stm.executeUpdate();
            exito = filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar la misión: " + e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }

        return exito;
    }

    public boolean actualizarMision(Mision seleccionada, Mision misionOriginal) {
        String sql = "UPDATE misiones SET "
                + "dni_trabajador = ?, nombre_cientifico_especie = ?, fecha_inicio = ?, fecha_fin = NOW(), descripcion = ? "
                + "WHERE dni_trabajador = ? AND nombre_cientifico_especie = ? AND fecha_inicio = ?";
        boolean exito = false;
        Connection con = this.getConexion();
        PreparedStatement stm = null;

        try {
            stm = con.prepareStatement(sql);

            // Nuevos valores
            stm.setString(1, seleccionada.getTrabajador().getDni());
            stm.setString(2, seleccionada.getEspecie());
            stm.setDate(3, seleccionada.getFechaInicio());
            stm.setString(4, seleccionada.getDescripcion());

            // Clave primaria para identificar la misión original
            stm.setString(5, misionOriginal.getTrabajador().getDni());
            stm.setString(6, misionOriginal.getEspecie());
            stm.setDate(7, misionOriginal.getFechaInicio());

            int filasAfectadas = stm.executeUpdate();
            exito = filasAfectadas > 0;

            if (!exito) {
                System.out.println("No se actualizó ninguna misión. Verifica los datos:");
                System.out.println("DNI original: " + misionOriginal.getTrabajador().getDni());
                System.out.println("Fecha inicio original: " + misionOriginal.getFechaInicio());
                System.out.println("Especie original: " + misionOriginal.getEspecie());
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar la misión: " + e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }

        return exito;
    }

    public Usuario obtenerTrabajadorMasExperimentado(List<Usuario> trabajadoresDisponibles, String especie) {
        if (trabajadoresDisponibles == null || trabajadoresDisponibles.isEmpty() || especie == null) {
            return null;
        }

        Usuario resultado = null;
        Connection con = this.getConexion();
        PreparedStatement stmTrabajador = null;
        ResultSet rsTrabajador = null;

        try {
            // Construimos una consulta parametrizada con los DNIs disponibles
            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append(
                    "SELECT t.dni, t.nombre, t.sueldo, t.horas, t.nombre_reserva, COUNT(*) as experiencia "
                    + "FROM trabajadores t "
                    + "JOIN misiones m ON t.dni = m.dni_trabajador "
                    + "WHERE t.dni IN (");

            // Añadimos un placeholder ? por cada trabajador disponible
            String placeholders = trabajadoresDisponibles.stream()
                    .map(u -> "?")
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("");

            sqlBuilder.append(placeholders);
            sqlBuilder.append(") AND m.nombre_cientifico_especie = ? "
                    + // Filtrar por especie
                    "GROUP BY t.dni, t.nombre, t.sueldo, t.horas, t.nombre_reserva "
                    + "ORDER BY experiencia DESC "
                    + "LIMIT 1");

            stmTrabajador = con.prepareStatement(sqlBuilder.toString());

            // Establecemos los parámetros para los DNIs de trabajadores disponibles
            int paramIndex = 1;
            for (Usuario trabajador : trabajadoresDisponibles) {
                stmTrabajador.setString(paramIndex++, trabajador.getDni());
            }

            // Añadimos el parámetro de la especie
            stmTrabajador.setString(paramIndex, especie);

            rsTrabajador = stmTrabajador.executeQuery();

            if (rsTrabajador.next()) {
                String nombreReserva = rsTrabajador.getString("nombre_reserva");
                Area area = new Area(nombreReserva);
                resultado = new Usuario(
                        rsTrabajador.getString("dni"),
                        rsTrabajador.getString("nombre"),
                        rsTrabajador.getFloat("sueldo"),
                        rsTrabajador.getInt("horas"),
                        area
                );
            } else {
                // Si no hay resultados, devolvemos el primer trabajador de la lista
                resultado = trabajadoresDisponibles.get(0);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener el trabajador más experimentado para la especie: " + e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                if (rsTrabajador != null) {
                    rsTrabajador.close();
                }
                if (stmTrabajador != null) {
                    stmTrabajador.close();
                }
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
    }

    public Usuario obtenerTrabajadorMision() {
        Usuario resultado = null;
        String sql
                = "SELECT t.dni, t.nombre, t.sueldo, t.horas, t.nombre_reserva "
                + "FROM trabajadores t "
                + "LEFT JOIN misiones m ON t.dni = m.dni_trabajador "
                + "GROUP BY t.dni, t.nombre, t.sueldo, t.horas, t.nombre_reserva "
                + "HAVING COUNT(CASE WHEN m.fecha_fin > CURRENT_DATE THEN 1 ELSE NULL END) = 0 "
                + "ORDER BY COUNT(m.dni_trabajador) ASC "
                + "LIMIT 1";

        try (PreparedStatement stm = this.getConexion().prepareStatement(sql)) {
            try (ResultSet rs = stm.executeQuery()) {
                String nombreReserva = rs.getString("nombre_reserva");
                Area area = new Area(nombreReserva);
                if (rs.next()) {
                    resultado = new Usuario(
                            rs.getString("dni"),
                            rs.getString("nombre"),
                            rs.getFloat("sueldo"),
                            rs.getInt("horas"),
                            area
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener un trabajador sin misiones activas: " + e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }

        return resultado;
    }

    boolean verificarMisionExistente(Mision mision) {
        boolean existe = false;
        Connection con = this.getConexion();
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            String consulta = "SELECT 1 FROM misiones WHERE dni_trabajador = ? AND nombre_cientifico_especie = ? AND fecha_inicio = ?";
            stm = con.prepareStatement(consulta);
            stm.setString(1, mision.getTrabajador().getDni());
            stm.setString(2, mision.getEspecie());
            stm.setDate(3, mision.getFechaInicio());
            rs = stm.executeQuery();
            existe = rs.next(); // Si hay resultados, existe
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return existe;
    }

    void agregarNuevaMision(Mision misionActual) {
        String sql = "INSERT INTO misiones (dni_trabajador, nombre_cientifico_especie, fecha_inicio, fecha_fin, descripcion) "
                + "VALUES (?, ?, ?, ?, ?)";
        Connection con = this.getConexion();
        PreparedStatement stm = null;

        try {
            stm = con.prepareStatement(sql);

            stm.setString(1, misionActual.getTrabajador().getDni());
            stm.setString(2, misionActual.getEspecie());
            stm.setDate(3, misionActual.getFechaInicio());
            stm.setDate(4, misionActual.getFechaFin());
            stm.setString(5, misionActual.getDescripcion());

            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al insertar la misión: " + e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }
    }

    void completarMision(Mision misionSeleccionada) {
        String sql = "UPDATE misiones SET fecha_fin = CURRENT_DATE "
                + "WHERE dni_trabajador = ? AND nombre_cientifico_especie = ? AND fecha_inicio = ?";

        try (PreparedStatement stm = this.getConexion().prepareStatement(sql)) {
            stm.setString(1, misionSeleccionada.getTrabajador().getDni());
            stm.setString(2, misionSeleccionada.getEspecie());
            stm.setDate(3, misionSeleccionada.getFechaInicio());

            int filas = stm.executeUpdate();

            if (filas == 0) {
                System.out.println("No se encontró la misión para marcar como completada.");
            } else {
                System.out.println("Misión marcada como completada.");
                misionSeleccionada.setFechaFin(Date.valueOf(LocalDate.now())); // actualiza el objeto en memoria
            }

        } catch (SQLException e) {
            System.out.println("Error al completar la misión: " + e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }
    }

    public List<Mision> obtenerMisionesGeneral(String textoBusqueda) {
        List<Mision> resultado = new ArrayList<>();

        if (textoBusqueda == null || textoBusqueda.trim().isEmpty()) {
            return resultado;
        }

        // Normalizar la cadena de búsqueda
        String busqueda = textoBusqueda.trim();

        // Consulta base
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT DISTINCT m.dni_trabajador, m.fecha_inicio, m.fecha_fin, m.descripcion, m.nombre_cientifico_especie ");
        sqlBuilder.append("FROM misiones m ");
        sqlBuilder.append("JOIN trabajadores t ON m.dni_trabajador = t.dni ");
        sqlBuilder.append("WHERE ");

        // Lista para almacenar los parámetros
        List<String> parametros = new ArrayList<>();

        // Caso especial para "completada" o "incompleta"
        boolean esBusquedaEspecial = false;
        if ("completada".equalsIgnoreCase(busqueda)) {
            sqlBuilder.append("m.fecha_fin IS NOT NULL");
            esBusquedaEspecial = true;
        } else if ("incompleta".equalsIgnoreCase(busqueda)) {
            sqlBuilder.append("m.fecha_fin IS NULL");
            esBusquedaEspecial = true;
        }

        // Para búsquedas generales
        if (!esBusquedaEspecial) {
            sqlBuilder.append("t.nombre ILIKE ? OR ");
            parametros.add("%" + busqueda + "%");

            sqlBuilder.append("m.nombre_cientifico_especie ILIKE ? OR ");
            parametros.add("%" + busqueda + "%");

            sqlBuilder.append("m.descripcion ILIKE ?");
            parametros.add("%" + busqueda + "%");
        }

        try (PreparedStatement stm = this.getConexion().prepareStatement(sqlBuilder.toString())) {
            // Configurar los parámetros
            for (int i = 0; i < parametros.size(); i++) {
                stm.setString(i + 1, parametros.get(i));
            }

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
                                rs.getString("descripcion")
                        );
                        resultado.add(m);
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al realizar búsqueda general de misiones: " + e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }

        return resultado;
    }

    public int contarMisionesActivas(String dni) {
        int contador = 0;
        Connection con = this.getConexion();
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            String consulta = "SELECT COUNT(*) AS total FROM misiones WHERE fecha_fin IS NULL and dni_trabajador = ?";
            stm = con.prepareStatement(consulta);
            stm.setString(1, dni);
            rs = stm.executeQuery();

            if (rs.next()) {
                contador = rs.getInt("total");
            }
        } catch (SQLException e) {
            System.out.println("Error al contar misiones activas: " + e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return contador;
    }

    public Mision obtenerMisionMasAntigua(String dniBuscar) {
        Mision resultado = null;
        Connection con = this.getConexion();
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT dni_trabajador, fecha_inicio, fecha_fin, descripcion, nombre_cientifico_especie "
                    + "FROM misiones WHERE fecha_fin IS NULL and dni_trabajador = ?"
                    + "ORDER BY fecha_inicio ASC "
                    + "LIMIT 1";

            stm = con.prepareStatement(sql);
            stm.setString(1, dniBuscar);
            rs = stm.executeQuery();

            if (rs.next()) {
                String dni = rs.getString("dni_trabajador");
                Usuario trabajador = obtenerUsuarioDni(dni);

                if (trabajador != null) {
                    resultado = new Mision(
                            trabajador,
                            rs.getString("nombre_cientifico_especie"),
                            rs.getDate("fecha_inicio"),
                            rs.getDate("fecha_fin"),
                            rs.getString("descripcion")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la misión más antigua: " + e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
    }

}
