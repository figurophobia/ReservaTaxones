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
                    area = new Area(nombreReserva, extension, "Desconocido");
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
}
