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
            String consulta = "SELECT nombre_cientifico, nombre_comun, descripcion, nombre_taxon FROM especies";
            if (nombreCientifico != null && !nombreCientifico.isEmpty()) {
                consulta += " WHERE nombre_cientifico LIKE ?";
            }

            stmEspecie = con.prepareStatement(consulta);

            if (nombreCientifico != null && !nombreCientifico.isEmpty()) {
                stmEspecie.setString(1, "%" + nombreCientifico + "%");
            }

            rsEspecie = stmEspecie.executeQuery();

            while (rsEspecie.next()) {
                Taxon taxon = new Taxon();
                taxon.setNombre(rsEspecie.getString("nombre_taxon"));

                Especie especie = new Especie(
                    rsEspecie.getString("nombre_cientifico"),
                    rsEspecie.getString("nombre_comun"),
                    rsEspecie.getString("descripcion"),
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

    public void anhadirEspecie(Especie e) {
        Connection con = this.getConexion();
        PreparedStatement stmEspecie = null;

        try {
            String consulta = "INSERT INTO especies (nombre_cientifico, nombre_comun, descripcion, nombre_taxon) VALUES (?, ?, ?, ?)";

            stmEspecie = con.prepareStatement(consulta);
            stmEspecie.setString(1, e.getNombreCientifico());
            stmEspecie.setString(2, e.getNombreComun());
            stmEspecie.setString(3, e.getDescripcion());
            stmEspecie.setString(4, e.getTaxon().getNombre());        // Suponiendo que Taxon usa nombre como PK

            stmEspecie.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error al insertar especie: " + ex.getMessage());
            this.getFachadaAplicacion().muestraExcepcion("Error al insertar especie: " + ex.getMessage());
        } finally {
            try {
                if (stmEspecie != null) stmEspecie.close();
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores: " + ex.getMessage());
            }
        }
    }

    public void actualizarEspecie(Especie e, Especie eNueva) {
        Connection con = this.getConexion();
        PreparedStatement stmEspecie = null;

        try {
            String consulta = "UPDATE especies SET nombre_cientifico = ?, nombre_comun = ?, descripcion = ?, nombre_taxon = ? WHERE nombre_cientifico = ?";

            stmEspecie = con.prepareStatement(consulta);
            stmEspecie.setString(1, eNueva.getNombreCientifico());
            stmEspecie.setString(2, eNueva.getNombreComun());
            stmEspecie.setString(3, eNueva.getDescripcion());
            stmEspecie.setString(4, eNueva.getTaxon().getNombre());
            stmEspecie.setString(5, e.getNombreCientifico()); // condición WHERE

            stmEspecie.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error al actualizar especie: " + ex.getMessage());
            this.getFachadaAplicacion().muestraExcepcion("Error al actualizar especie: " + ex.getMessage());
        } finally {
            try {
                if (stmEspecie != null) stmEspecie.close();
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores: " + ex.getMessage());
            }
        }
    }

    public void borrarEspecie(Especie e) {
        Connection con = this.getConexion();
        PreparedStatement stmEspecie = null;

        try {
            String consulta = "DELETE FROM especies WHERE nombre_cientifico = ?";

            stmEspecie = con.prepareStatement(consulta);
            stmEspecie.setString(1, e.getNombreCientifico());

            int filasAfectadas = stmEspecie.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Especie borrada correctamente.");
            } else {
                System.out.println("No se encontró la especie a borrar.");
            }

        } catch (SQLException ex) {
            System.out.println("Error al borrar especie: " + ex.getMessage());
            this.getFachadaAplicacion().muestraExcepcion("Error al borrar especie");
        } finally {
            try {
                if (stmEspecie != null) stmEspecie.close();
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores: " + ex.getMessage());
            }
        }
    }



}
