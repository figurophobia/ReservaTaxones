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
 
    public List<Mision> obtenerMisionesGeneral() {
        List<Mision> listaMisiones = new ArrayList<>();
        String consulta = "SELECT dni_trabajador, fecha_inicio, fecha_fin, descripcion, nombre_cientifico_especie FROM misiones";
        Connection conexion = this.getConexion();

        try (
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultadoConsulta = sentencia.executeQuery()
        ) {
            while (resultadoConsulta.next()) {
                String dniTrabajador = resultadoConsulta.getString("dni_trabajador");
                Usuario persona = obtenerUsuarioPorDni(dniTrabajador);

                if (persona != null) {
                    Mision nueva = new Mision(
                        persona,
                        resultadoConsulta.getString("nombre_cientifico_especie"),
                        resultadoConsulta.getDate("fecha_inicio"),
                        resultadoConsulta.getDate("fecha_fin"),
                        resultadoConsulta.getString("descripcion")
                    );
                    listaMisiones.add(nueva);
                }
            }
        } catch (SQLException ex) {
            System.out.println("No se pudieron recuperar las misiones: " + ex.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(ex.getMessage());
        }

        return listaMisiones;
}


    private Usuario obtenerUsuarioPorDni(String dni) {
        Usuario usuarioEncontrado = null;
        String consulta = "SELECT dni, nombre, sueldo, horas, nombre_reserva FROM trabajadores WHERE dni = ?";

        try (PreparedStatement sentencia = this.getConexion().prepareStatement(consulta)) {
            sentencia.setString(1, dni);

            try (ResultSet resultado = sentencia.executeQuery()) {
                if (resultado.next()) {
                    String reserva = resultado.getString("nombre_reserva");
                    Area zona = new Area(reserva);

                    usuarioEncontrado = new Usuario(
                        resultado.getString("dni"),
                        resultado.getString("nombre"),
                        resultado.getFloat("sueldo"),
                        resultado.getInt("horas"),
                        zona
                    );
                }
            }

        } catch (SQLException ex) {
            System.out.println("Fallo al recuperar al trabajador con ese DNI: " + ex.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(ex.getMessage());
        }

        return usuarioEncontrado;
        }

public List<Mision> buscarMisionesPorEstado(String filtroEstado) {
    List<Mision> listaMisiones = new ArrayList<>();
    String consulta;

    if ("completada".equalsIgnoreCase(filtroEstado)) {
        consulta = "SELECT dni_trabajador, fecha_inicio, fecha_fin, descripcion, nombre_cientifico_especie " +
                   "FROM misiones WHERE fecha_fin IS NOT NULL";
    } else if ("incompleta".equalsIgnoreCase(filtroEstado)) {
        consulta = "SELECT dni_trabajador, fecha_inicio, fecha_fin, descripcion, nombre_cientifico_especie " +
                   "FROM misiones WHERE fecha_fin IS NULL";
    } else {
        return listaMisiones;
    }

    try (PreparedStatement instruccion = this.getConexion().prepareStatement(consulta);
         ResultSet datos = instruccion.executeQuery()) {

        while (datos.next()) {
            String identificador = datos.getString("dni_trabajador");
            Usuario empleado = obtenerUsuarioPorDni(identificador);

            if (empleado != null) {
                Mision nueva = new Mision(
                    empleado,
                    datos.getString("nombre_cientifico_especie"),
                    datos.getDate("fecha_inicio"),
                    datos.getDate("fecha_fin"),
                    datos.getString("descripcion")
                );
                listaMisiones.add(nueva);
            }
        }

    } catch (SQLException ex) {
        System.out.println("Fallo al recuperar misiones según el estado: " + ex.getMessage());
        this.getFachadaAplicacion().muestraExcepcion(ex.getMessage());
    }

    return listaMisiones;
}

    public List<Mision> buscarMisionesPorNombre(String nombreBuscado) {
        List<Mision> listaMisiones = new ArrayList<>();
        String consulta = "SELECT dni_trabajador, fecha_inicio, fecha_fin, descripcion, nombre_cientifico_especie " +
                          "FROM misiones m JOIN trabajadores u ON m.dni_trabajador = u.dni " +
                          "WHERE u.nombre ILIKE ?";

        try (PreparedStatement instruccion = this.getConexion().prepareStatement(consulta)) {
            instruccion.setString(1, "%" + nombreBuscado + "%");

            try (ResultSet resultados = instruccion.executeQuery()) {
                while (resultados.next()) {
                    String identificador = resultados.getString("dni_trabajador");
                    Usuario empleado = obtenerUsuarioPorDni(identificador);

                    if (empleado != null) {
                        Mision nueva = new Mision(
                            empleado,
                            resultados.getString("nombre_cientifico_especie"),
                            resultados.getDate("fecha_inicio"),
                            resultados.getDate("fecha_fin"),
                            resultados.getString("descripcion")
                        );
                        listaMisiones.add(nueva);
                    }
                }
            }

        } catch (SQLException ex) {
            System.out.println("Fallo al buscar misiones por nombre del trabajador: " + ex.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(ex.getMessage());
        }

        return listaMisiones;
    }

    public List<Mision> buscarMisionesPorEspecie(String especieBuscada) {
        List<Mision> listaMisiones = new ArrayList<>();
        String consulta = "SELECT dni_trabajador, fecha_inicio, fecha_fin, descripcion, nombre_cientifico_especie " +
                          "FROM misiones WHERE nombre_cientifico_especie ILIKE ?";

        try (PreparedStatement instruccion = this.getConexion().prepareStatement(consulta)) {
            instruccion.setString(1, "%" + especieBuscada + "%");

            try (ResultSet resultados = instruccion.executeQuery()) {
                while (resultados.next()) {
                    String identificador = resultados.getString("dni_trabajador");
                    Usuario empleado = obtenerUsuarioPorDni(identificador);

                    if (empleado != null) {
                        Mision nueva = new Mision(
                            empleado,
                            resultados.getString("nombre_cientifico_especie"),
                            resultados.getDate("fecha_inicio"),
                            resultados.getDate("fecha_fin"),
                            resultados.getString("descripcion")
                        );
                        listaMisiones.add(nueva);
                    }
                }
            }

        } catch (SQLException ex) {
            System.out.println("Fallo al buscar misiones por especie: " + ex.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(ex.getMessage());
        }

        return listaMisiones;
    }

public boolean borrarMision(Mision mision) {
    String consulta = "DELETE FROM misiones WHERE dni_trabajador = ? AND fecha_inicio = ? AND nombre_cientifico_especie = ?";
    boolean resultadoOperacion = false;

    try (PreparedStatement instruccion = this.getConexion().prepareStatement(consulta)) {
        // Obtener el dni, fecha de inicio y especie de la misión
        instruccion.setString(1, mision.getTrabajador().getDni());
        instruccion.setDate(2, new java.sql.Date(mision.getFechaInicio().getTime()));  // Convertir a java.sql.Date
        instruccion.setString(3, mision.getEspecie());

        int filasAfectadas = instruccion.executeUpdate();
        resultadoOperacion = filasAfectadas > 0;

    } catch (SQLException ex) {
        System.out.println("Error al borrar la misión: " + ex.getMessage());
        this.getFachadaAplicacion().muestraExcepcion(ex.getMessage());
    }

    return resultadoOperacion;
}

public boolean modificarMision(Mision misionSeleccionada, Mision misionInicial) {
    String consulta = "UPDATE misiones SET " +
                      "dni_trabajador = ?, nombre_cientifico_especie = ?, fecha_inicio = ?, fecha_fin = ?, descripcion = ? " +
                      "WHERE dni_trabajador = ? AND nombre_cientifico_especie = ? AND fecha_inicio = ?";
    boolean resultadoOperacion = false;
    Connection conexion = this.getConexion();
    PreparedStatement instruccion = null;

    try {
        instruccion = conexion.prepareStatement(consulta);

        // Nuevos valores
        instruccion.setString(1, misionSeleccionada.getTrabajador().getDni());
        instruccion.setString(2, misionSeleccionada.getEspecie());
        instruccion.setDate(3, misionSeleccionada.getFechaInicio());
        instruccion.setDate(4, misionSeleccionada.getFechaFin());
        instruccion.setString(5, misionSeleccionada.getDescripcion());

        // Clave primaria para identificar la misión inicial
        instruccion.setString(6, misionInicial.getTrabajador().getDni());
        instruccion.setString(7, misionInicial.getEspecie());
        instruccion.setDate(8, misionInicial.getFechaInicio());

        int filasAfectadas = instruccion.executeUpdate();
        resultadoOperacion = filasAfectadas > 0;

        if (!resultadoOperacion) {
            System.out.println("No se actualizó ninguna misión. Verifica los datos:");
            System.out.println("DNI inicial: " + misionInicial.getTrabajador().getDni());
            System.out.println("Fecha inicio inicial: " + misionInicial.getFechaInicio());
            System.out.println("Especie inicial: " + misionInicial.getEspecie());
        }

    } catch (SQLException ex) {
        System.out.println("Error al modificar la misión: " + ex.getMessage());
        this.getFachadaAplicacion().muestraExcepcion(ex.getMessage());
    }

    return resultadoOperacion;
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
                String nombreReserva = rs.getString("nombre_reserva");
                Area area = new Area(nombreReserva);
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

    boolean comprobarMisionExiste(Mision mision) {
        boolean encontrada = false;
        Connection conexion = this.getConexion();
        PreparedStatement instruccion = null;
        ResultSet resultados = null;

        try {
            String consulta = "SELECT 1 FROM misiones WHERE dni_trabajador = ? AND nombre_cientifico_especie = ? AND fecha_inicio = ?";
            instruccion = conexion.prepareStatement(consulta);
            instruccion.setString(1, mision.getTrabajador().getDni());
            instruccion.setString(2, mision.getEspecie());
            instruccion.setDate(3, mision.getFechaInicio());
            resultados = instruccion.executeQuery();
            encontrada = resultados.next(); // Si hay resultados, la misión existe
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (resultados != null) resultados.close();
                if (instruccion != null) instruccion.close();
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return encontrada;
    }

void insertarMision(Mision misionActual) {
    String sql = "INSERT INTO misiones (dni_trabajador, nombre_cientifico_especie, fecha_inicio, fecha_fin, descripcion) " +
                 "VALUES (?, ?, ?, ?, ?)";
    Connection conexion = this.getConexion();
    PreparedStatement instruccion = null;

    try {
        instruccion = conexion.prepareStatement(sql);

        instruccion.setString(1, misionActual.getTrabajador().getDni());
        instruccion.setString(2, misionActual.getEspecie());
        instruccion.setDate(3, misionActual.getFechaInicio());
        instruccion.setDate(4, misionActual.getFechaFin());
        instruccion.setString(5, misionActual.getDescripcion());

        instruccion.executeUpdate();

    } catch (SQLException ex) {
        System.out.println("Error al insertar la misión: " + ex.getMessage());
        this.getFachadaAplicacion().muestraExcepcion(ex.getMessage());
    }

}

    void finalizarMision(Mision misionSeleccionada) {
        String consulta = "UPDATE misiones SET fecha_fin = CURRENT_DATE " +
                          "WHERE dni_trabajador = ? AND nombre_cientifico_especie = ? AND fecha_inicio = ?";

        try (PreparedStatement instruccion = this.getConexion().prepareStatement(consulta)) {
            instruccion.setString(1, misionSeleccionada.getTrabajador().getDni());
            instruccion.setString(2, misionSeleccionada.getEspecie());
            instruccion.setDate(3, misionSeleccionada.getFechaInicio());

            int filasAfectadas = instruccion.executeUpdate();

            if (filasAfectadas == 0) {
                System.out.println("No se encontró la misión para marcar como completada.");
            } else {
                System.out.println("Misión marcada como completada.");
                misionSeleccionada.setFechaFin(Date.valueOf(LocalDate.now())); // actualiza el objeto en memoria
            }

        } catch (SQLException ex) {
            System.out.println("Error al completar la misión: " + ex.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(ex.getMessage());
        }
    }



    
}
