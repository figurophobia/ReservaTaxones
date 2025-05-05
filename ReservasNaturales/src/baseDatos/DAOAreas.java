/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseDatos;

import aplicacion.Area;
import aplicacion.FachadaAplicacion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOAreas extends AbstractDAO {

    public DAOAreas(Connection conexion, FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public List<Area> obtenerAreasGeneric() {
        List<Area> areas = new ArrayList<>();
        Connection conexion = this.getConexion();
        PreparedStatement sentencia = null;
        ResultSet resultadoConsulta = null;

        try {
            String query = "SELECT nombre_reserva, extension, altitud_nivel_bajo, altitud_nivel_alto, profundidad, esacuatica, esterrestre " +
                           "FROM area_geografica";
            sentencia = conexion.prepareStatement(query);
            resultadoConsulta = sentencia.executeQuery();

            while (resultadoConsulta.next()) {
                String reservaNombre = resultadoConsulta.getString("nombre_reserva");
                double extension = resultadoConsulta.getDouble("extension");

                // Verificar si los valores de altitud y profundidad son nulos
                Double altitudInferior = resultadoConsulta.getObject("altitud_nivel_bajo") != null ? resultadoConsulta.getDouble("altitud_nivel_bajo") : null;
                Double altitudSuperior = resultadoConsulta.getObject("altitud_nivel_alto") != null ? resultadoConsulta.getDouble("altitud_nivel_alto") : null;
                Double profundidad = resultadoConsulta.getObject("profundidad") != null ? resultadoConsulta.getDouble("profundidad") : null;

                boolean esAcuatica = resultadoConsulta.getBoolean("esacuatica");
                boolean esTerrestre = resultadoConsulta.getBoolean("esterrestre");

                Area nuevaArea;

                // Determinar el tipo de área según las condiciones
                if (esAcuatica) {
                    nuevaArea = new Area(reservaNombre, extension, profundidad);  // Áreas acuáticas
                } else if (esTerrestre) {
                    nuevaArea = new Area(reservaNombre, extension, altitudInferior, altitudSuperior);  // Áreas terrestres
                } else {
                    nuevaArea = new Area(reservaNombre, extension, false, false);  // Áreas sin tipo definido
                }

                areas.add(nuevaArea);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener las áreas: " + e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion("Error al obtener las áreas: " + e.getMessage());
        } finally {
            // Cerrar solo los recursos utilizados
            try {
                if (resultadoConsulta != null) {
                    resultadoConsulta.close();
                }
                if (sentencia != null) {
                    sentencia.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }

        return areas;
    }
    
    public List<Area> buscarAreasPorNombre(String textoBusqueda) {
        List<Area> areasEncontradas = new ArrayList<>();
        Connection conexion = this.getConexion();
        PreparedStatement sentencia = null;
        ResultSet resultadoConsulta = null;

        try {
            String query = "SELECT nombre_reserva, extension, altitud_nivel_bajo, altitud_nivel_alto, profundidad, esacuatica, esterrestre " +
                           "FROM area_geografica " +
                           "WHERE nombre_reserva ILIKE ?";
            sentencia = conexion.prepareStatement(query);
            sentencia.setString(1, "%" + textoBusqueda + "%");  // Búsqueda parcial
            resultadoConsulta = sentencia.executeQuery();

            while (resultadoConsulta.next()) {
                String nombreReserva = resultadoConsulta.getString("nombre_reserva");
                double extension = resultadoConsulta.getDouble("extension");

                // Verificar si los valores de altitud y profundidad son nulos
                Double altitudBaja = resultadoConsulta.getObject("altitud_nivel_bajo") != null ? resultadoConsulta.getDouble("altitud_nivel_bajo") : 0.0;
                Double altitudAlta = resultadoConsulta.getObject("altitud_nivel_alto") != null ? resultadoConsulta.getDouble("altitud_nivel_alto") : 0.0;
                Double profundidad = resultadoConsulta.getObject("profundidad") != null ? resultadoConsulta.getDouble("profundidad") : 0.0;

                boolean esAcuatica = resultadoConsulta.getBoolean("esacuatica");
                boolean esTerrestre = resultadoConsulta.getBoolean("esterrestre");

                Area nuevaArea;

                // Determinar el tipo de área según las condiciones
                if (esAcuatica && esTerrestre) {
                    nuevaArea = new Area(nombreReserva, extension, profundidad, altitudAlta, altitudBaja, true, true);  // Áreas acuáticas y terrestres
                } else if (esAcuatica) {
                    nuevaArea = new Area(nombreReserva, extension, profundidad);  // Áreas acuáticas
                } else if (esTerrestre) {
                    nuevaArea = new Area(nombreReserva, extension, altitudBaja, altitudAlta);  // Áreas terrestres
                } else {
                    nuevaArea = new Area(nombreReserva, extension, false, false);  // Áreas sin tipo definido
                }

                areasEncontradas.add(nuevaArea);
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar áreas: " + e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion("Error al buscar áreas: " + e.getMessage());
        } finally {
            // Cerrar solo los recursos utilizados
            try {
                if (resultadoConsulta != null) {
                    resultadoConsulta.close();
                }
                if (sentencia != null) {
                    sentencia.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }

        return areasEncontradas;
    }

    public boolean modificarArea(Area area) {
        Connection conexion = this.getConexion();
        PreparedStatement sentencia = null;
        boolean actualizada = false;

        try {
            String sql = "UPDATE area_geografica " +
                         "SET extension = ?, altitud_nivel_bajo = ?, altitud_nivel_alto = ?, profundidad = ?, esacuatica = ?, esterrestre = ? " +
                         "WHERE nombre_reserva = ?";
            sentencia = conexion.prepareStatement(sql);

            // Asignar los valores a los parámetros de la consulta
            sentencia.setDouble(1, area.getExtension());
            sentencia.setObject(2, area.getAltitudBaja());
            sentencia.setObject(3, area.getAltitudAlta());
            sentencia.setObject(4, area.getProfundidad());
            sentencia.setBoolean(5, area.isAcuatica());
            sentencia.setBoolean(6, area.isTerrestre());
            sentencia.setString(7, area.getNombreReserva());

            // Ejecutar la actualización
            actualizada = sentencia.executeUpdate() > 0;

            if (actualizada) {
                System.out.println("Área actualizada correctamente.");
            }

        } catch (SQLException e) {
            System.err.println("Error al actualizar área: " + e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion("Error al actualizar área: " + e.getMessage());
        } finally {
            // Cerrar recursos de manera segura
            try {
                if (sentencia != null) {
                    sentencia.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }

        return actualizada;
    }

    public boolean deleteArea(String nombreReserva) {
        Connection conexion = this.getConexion();
        PreparedStatement sentencia = null;
        boolean eliminada = false;

        try {
            String sql = "DELETE FROM area_geografica WHERE nombre_reserva = ?";
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, nombreReserva);

            // Ejecutar la eliminación
            eliminada = sentencia.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar área: " + e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion("Error al eliminar área: " + e.getMessage());
        } finally {
            // Cerrar la sentencia
            try {
                if (sentencia != null) {
                    sentencia.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }

        return eliminada;
    }

    public boolean NuevaArea(Area area) {
        Connection conexion = this.getConexion();
        PreparedStatement sentencia = null;
        boolean creada = false;

        try {
            String sql = "INSERT INTO area_geografica " +
                         "(nombre_reserva, extension, altitud_nivel_bajo, altitud_nivel_alto, profundidad, esacuatica, esterrestre) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?)";

            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, area.getNombreReserva());
            sentencia.setDouble(2, area.getExtension());

            // Establecer valores de altitud solo si son relevantes (área terrestre)
            if (area.isTerrestre()) {
                sentencia.setObject(3, area.getAltitudBaja());
                sentencia.setObject(4, area.getAltitudAlta());
            } else {
                sentencia.setNull(3, java.sql.Types.DOUBLE);
                sentencia.setNull(4, java.sql.Types.DOUBLE);
            }

            // Establecer profundidad solo si es relevante (área acuática)
            if (area.isAcuatica()) {
                sentencia.setObject(5, area.getProfundidad());
            } else {
                sentencia.setNull(5, java.sql.Types.DOUBLE);
            }

            sentencia.setBoolean(6, area.isAcuatica());
            sentencia.setBoolean(7, area.isTerrestre());

            // Ejecutar la inserción
            creada = sentencia.executeUpdate() > 0;

            if (creada) {
                System.out.println("Área creada correctamente: " + area.getNombreReserva());
            }

        } catch (SQLException e) {
            System.err.println("Error al crear área: " + e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion("Error al crear área: " + e.getMessage());
        } finally {
            try {
                if (sentencia != null) sentencia.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }

        return creada;
    }
}
