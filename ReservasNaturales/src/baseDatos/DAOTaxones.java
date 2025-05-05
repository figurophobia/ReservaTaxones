/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseDatos;

import aplicacion.Taxon;
import aplicacion.FachadaAplicacion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOTaxones extends AbstractDAO {

    public DAOTaxones(Connection conexion, FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public List<Taxon> obtenerTaxones() {
        List<Taxon> resultado = new ArrayList<>();
        Connection con = this.getConexion();
        PreparedStatement stmTaxon = null;
        ResultSet rsTaxon;

        try {
            String consulta = "SELECT nombre, tipo, taxon_superior FROM taxones";
            stmTaxon = con.prepareStatement(consulta);
            rsTaxon = stmTaxon.executeQuery();

            while (rsTaxon.next()) {
                String nombre = rsTaxon.getString("nombre");
                String tipo = rsTaxon.getString("tipo");

                // Obtener taxon superior, si existe
                Taxon taxonSuperior = null;
                String taxonSuperiorNombre = rsTaxon.getString("taxon_superior");

                if (taxonSuperiorNombre != null) {
                    // Crear el Taxon superior si existe en la base de datos
                    taxonSuperior = new Taxon(taxonSuperiorNombre, null, null);  // Asumimos que no tenemos más detalles sobre el superior por ahora
                }

                // Crear el objeto Taxon
                Taxon taxon = new Taxon(nombre, tipo, taxonSuperior);
                resultado.add(taxon);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                if (stmTaxon != null) {
                    stmTaxon.close();
                }
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
    }
}
