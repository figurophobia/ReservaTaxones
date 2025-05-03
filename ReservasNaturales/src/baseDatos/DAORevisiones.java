/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseDatos;

import aplicacion.ClinicaMedica;
import aplicacion.Ejemplar;
import aplicacion.FachadaAplicacion;
import aplicacion.Revision;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date; // Ojo: java.sql.Date, no java.util.Date


/**
 *
 * @author alumnogreibd
 */
public class DAORevisiones extends AbstractDAO{

    public DAORevisiones(Connection conexion, FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa); 
    }
    
 

    public List<Revision> obtenerRevisiones(String nClinica, int idEspecie) {
        List<Revision> resultado = new ArrayList<>();
        Connection con = this.getConexion();
        PreparedStatement stmRevision = null;
        ResultSet rsRevision;

        try {
               String consulta = 
                "SELECT clinica, ejemplar, especie_asociada, fecha_revision, informe " +
                "FROM revisar " +
                "WHERE clinica = ? AND ejemplar = ?";
            stmRevision = con.prepareStatement(consulta);
            stmRevision.setString(1, nClinica);
            stmRevision.setInt(2, idEspecie);
            rsRevision = stmRevision.executeQuery();

            while (rsRevision.next()) {
                String clinica = rsRevision.getString("clinica");
                int ejemplar = rsRevision.getInt("ejemplar");
                String especieAsociada = rsRevision.getString("especie_asociada");
                Date fechaRevisionSQL = rsRevision.getDate("fecha_revision");
                Date fechaRevision = fechaRevisionSQL;
                String informe = rsRevision.getString("informe");

                Revision r = new Revision(clinica, ejemplar, especieAsociada, fechaRevision, informe);
                resultado.add(r);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                if (stmRevision != null) stmRevision.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
}

   
    void añadirRevsion(ClinicaMedica clinicaRevision, Ejemplar ejemplarRevision, String text) {
        Connection con = this.getConexion();
        PreparedStatement stmInsert = null;

        try {
            String consulta = "INSERT INTO revisar (clinica, ejemplar, especie_asociada, fecha_revision, informe) " +
                              "VALUES (?, ?, ?, ?, ?)";

            stmInsert = con.prepareStatement(consulta);
            stmInsert.setString(1, clinicaRevision.getNombre());
            stmInsert.setInt(2, ejemplarRevision.getId());
            stmInsert.setString(3, ejemplarRevision.getEspecie().getNombreCientifico());

            // Fecha actual
            java.sql.Date fechaActual = new java.sql.Date(System.currentTimeMillis());
            stmInsert.setDate(4, fechaActual);

            stmInsert.setString(5, text);

            stmInsert.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error insertando revisión: " + e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion("Error insertando revisión:\n" + e.getMessage());
        } finally {
            try {
                if (stmInsert != null) stmInsert.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar el statement: " + e.getMessage());
            }
        }
    }


    
}
