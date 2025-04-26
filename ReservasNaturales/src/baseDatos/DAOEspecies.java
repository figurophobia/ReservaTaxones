/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseDatos;

import aplicacion.Area;
import aplicacion.Especie;
import aplicacion.FachadaAplicacion;
import aplicacion.Taxon;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOEspecies extends AbstractDAO {

    public DAOEspecies(Connection conexion, FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public List<Especie> obtenerEspecies(String nombreCientifico) {
        List<Especie> resultado = new ArrayList<>();
        Connection con = this.getConexion();
        PreparedStatement stmEspecie = null;
        ResultSet rsEspecie;

        try {
            String consulta = "SELECT nombre_cientifico, nombre_comun, descripcion, area_geografica, nombre_taxon FROM especies";
            if (nombreCientifico != null && !nombreCientifico.isEmpty()) {
                consulta += " WHERE nombre_cientifico LIKE ?";
            }

            stmEspecie = con.prepareStatement(consulta);

            if (nombreCientifico != null && !nombreCientifico.isEmpty()) {
                stmEspecie.setString(1, "%" + nombreCientifico + "%");
            }

            rsEspecie = stmEspecie.executeQuery();

            while (rsEspecie.next()) {
                // Crear Area y Taxon solo con nombre, ya que no hay más columnas
                Area area = new Area();
                area.setNombreReserva(rsEspecie.getString("area_geografica"));

                Taxon taxon = new Taxon();
                taxon.setNombre(rsEspecie.getString("nombre_taxon"));

                Especie especie = new Especie(
                    rsEspecie.getString("nombre_cientifico"),
                    rsEspecie.getString("nombre_comun"),
                    rsEspecie.getString("descripcion"),
                    area,
                    taxon
                );

                resultado.add(especie);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                if (stmEspecie != null) stmEspecie.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
    }
}
