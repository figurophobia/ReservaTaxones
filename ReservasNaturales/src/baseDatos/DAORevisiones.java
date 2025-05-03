/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseDatos;

import aplicacion.FachadaAplicacion;
import aplicacion.Revision;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

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


    
}
