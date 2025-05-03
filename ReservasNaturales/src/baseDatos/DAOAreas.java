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

    public List<Area> obtenerAreas() {
        List<Area> resultado = new ArrayList<>();
        Connection con = this.getConexion();
        PreparedStatement stmArea = null;
        ResultSet rsArea;

        try {
            String consulta = "SELECT nombre_reserva, extension, altitud_nivel_bajo, altitud_nivel_alto, profundidad, esacuatica, esterrestre " +
                              "FROM area_geografica";
            stmArea = con.prepareStatement(consulta);
            rsArea = stmArea.executeQuery();

            while (rsArea.next()) {
                String nombreReserva = rsArea.getString("nombre_reserva");
                double extension = rsArea.getDouble("extension");
                Double altitudBaja = rsArea.getObject("altitud_nivel_bajo") != null ? rsArea.getDouble("altitud_nivel_bajo") : null;
                Double altitudAlta = rsArea.getObject("altitud_nivel_alto") != null ? rsArea.getDouble("altitud_nivel_alto") : null;
                Double profundidad = rsArea.getObject("profundidad") != null ? rsArea.getDouble("profundidad") : null;
                boolean esAcuatica = rsArea.getBoolean("esacuatica");  // Corregir nombre de columna aquí
                boolean esTerrestre = rsArea.getBoolean("esterrestre");

                Area area;

                if (esAcuatica) {
                    area = new Area(nombreReserva, extension, profundidad);  // Áreas acuáticas
                } else if (esTerrestre) {
                    area = new Area(nombreReserva, extension, altitudBaja, altitudAlta);  // Áreas terrestres
                } else {
                    // Si no es acuática ni terrestre, tratamos de devolverlo de forma general
                    area = new Area(nombreReserva, extension, false, false);
                }

                resultado.add(area);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                if (stmArea != null) stmArea.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
    }
    
    public List<Area> buscarAreas(String textoBusqueda) {
        List<Area> resultado = new ArrayList<>();
        Connection con = this.getConexion();
        PreparedStatement stmArea = null;
        ResultSet rsArea;

        try {
            String consulta = "SELECT nombre_reserva, extension, altitud_nivel_bajo, altitud_nivel_alto, profundidad, esacuatica, esterrestre " +
                              "FROM area_geografica " +
                              "WHERE nombre_reserva ILIKE ?";
            stmArea = con.prepareStatement(consulta);
            stmArea.setString(1, "%" + textoBusqueda + "%"); // Búsqueda parcial
            rsArea = stmArea.executeQuery();

            while (rsArea.next()) {
                String nombreReserva = rsArea.getString("nombre_reserva");
                double extension = rsArea.getDouble("extension");
                Double altitudBaja = rsArea.getObject("altitud_nivel_bajo") != null ? rsArea.getDouble("altitud_nivel_bajo") : 0.0;
                Double altitudAlta = rsArea.getObject("altitud_nivel_alto") != null ? rsArea.getDouble("altitud_nivel_alto") : 0.0;
                Double profundidad = rsArea.getObject("profundidad") != null ? rsArea.getDouble("profundidad") : 0.0;
                boolean esAcuatica = rsArea.getBoolean("esacuatica");
                boolean esTerrestre = rsArea.getBoolean("esterrestre");

                Area area;
                if (esAcuatica && esTerrestre) {
                    area = new Area(nombreReserva, extension, profundidad, altitudAlta, altitudBaja, true, true);
                } else if (esAcuatica) {
                    area = new Area(nombreReserva, extension, profundidad);
                } else if (esTerrestre) {
                    area = new Area(nombreReserva, extension, altitudBaja, altitudAlta);
                } else {
                    area = new Area(nombreReserva, extension, false, false);
                }

                resultado.add(area);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                if (stmArea != null) stmArea.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
    }

    public boolean actualizarArea(Area area) {
        Connection con = this.getConexion();
        PreparedStatement stmArea = null;
        boolean actualizado = false;
        try {
            String consulta = "UPDATE area_geografica " +
                              "SET extension = ?, altitud_nivel_bajo = ?, altitud_nivel_alto = ?, profundidad = ?, esacuatica = ?, esterrestre = ? " +
                              "WHERE nombre_reserva = ?";
            stmArea = con.prepareStatement(consulta);
            stmArea.setDouble(1, area.getExtension());
            stmArea.setObject(2, area.getAltitudBaja());
            stmArea.setObject(3, area.getAltitudAlta());
            stmArea.setObject(4, area.getProfundidad());
            stmArea.setBoolean(5, area.isAcuatica());
            stmArea.setBoolean(6, area.isTerrestre());
            stmArea.setString(7, area.getNombreReserva());

            actualizado = stmArea.executeUpdate() > 0;

            System.out.println("Area actualizada correctamente.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                if (stmArea != null) stmArea.close();
                System.out.println("hola");
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return actualizado;
    }

    public boolean eliminarArea(String nombreReserva) {
        Connection con = this.getConexion();
        PreparedStatement stmArea = null;
        boolean eliminado = false;

        try {
            String consulta = "DELETE FROM area_geografica WHERE nombre_reserva = ?";
            stmArea = con.prepareStatement(consulta);
            stmArea.setString(1, nombreReserva);

            eliminado = stmArea.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                if (stmArea != null) stmArea.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return eliminado;
    }

    public boolean crearArea(Area area) {
        Connection con = this.getConexion();
        PreparedStatement stmArea = null;
        boolean creado = false;

        try {
            String consulta = "INSERT INTO area_geografica " +
                    "(nombre_reserva, extension, altitud_nivel_bajo, altitud_nivel_alto, profundidad, esacuatica, esterrestre) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            stmArea = con.prepareStatement(consulta);
            stmArea.setString(1, area.getNombreReserva());
            stmArea.setDouble(2, area.getExtension());

            // Establecer valores de altitud solo si son relevantes (área terrestre)
            if (area.isTerrestre()) {
                stmArea.setObject(3, area.getAltitudBaja());
                stmArea.setObject(4, area.getAltitudAlta());
            } else {
                stmArea.setNull(3, java.sql.Types.DOUBLE);
                stmArea.setNull(4, java.sql.Types.DOUBLE);
            }

            // Establecer profundidad solo si es relevante (área acuática)
            if (area.isAcuatica()) {
                stmArea.setObject(5, area.getProfundidad());
            } else {
                stmArea.setNull(5, java.sql.Types.DOUBLE);
            }

            stmArea.setBoolean(6, area.isAcuatica());
            stmArea.setBoolean(7, area.isTerrestre());

            creado = stmArea.executeUpdate() > 0;

            if (creado) {
                System.out.println("Área creada correctamente: " + area.getNombreReserva());
            }

        } catch (SQLException e) {
            System.out.println("Error al crear área: " + e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                if (stmArea != null) stmArea.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return creado;
    }

    public List<String> obtenerAreasPorEspecie(String nombreCientifico) {
        List<String> resultado = new ArrayList<>();
        Connection con = this.getConexion();
        PreparedStatement stmAreas = null;
        ResultSet rsAreas = null;

        try {
            // Consulta SQL para obtener áreas con ejemplares de la especie especificada
            String consulta = "SELECT DISTINCT area_geografica " +
                    "FROM ejemplar " +
                    "WHERE nombre_cientifico_especie = ? " +
                    "ORDER BY area_geografica";

            stmAreas = con.prepareStatement(consulta);
            stmAreas.setString(1, nombreCientifico);
            rsAreas = stmAreas.executeQuery();

            while (rsAreas.next()) {
                String nombreArea = rsAreas.getString("area_geografica");
                resultado.add(nombreArea);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener áreas por especie: " + e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                if (rsAreas != null) rsAreas.close();
                if (stmAreas != null) stmAreas.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
    }



}
