/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseDatos;

import aplicacion.FachadaAplicacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import aplicacion.ClinicaMedica;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumnogreibd
 */

public class DAOClinicas extends AbstractDAO{

    public DAOClinicas(Connection conexion, FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    public List<ClinicaMedica> obtenerClinicas(String textoBusqueda) {
    List<ClinicaMedica> resultado = new ArrayList<>();
    Connection con = this.getConexion();
    PreparedStatement stmClinica = null;
    ResultSet rsClinica;

    try {
        String consulta = "SELECT nombre, ubicacion, num_empleados FROM clinica_medica WHERE nombre ILIKE ?";
        stmClinica = con.prepareStatement(consulta);
        stmClinica.setString(1, "%" + textoBusqueda + "%");
        rsClinica = stmClinica.executeQuery();

        while (rsClinica.next()) {
            String nombre = rsClinica.getString("nombre");
            String ubicacion = rsClinica.getString("ubicacion");
            int numEmpleados = rsClinica.getInt("num_empleados");

            ClinicaMedica c = new ClinicaMedica(nombre, ubicacion, numEmpleados);
            resultado.add(c);
        }

    } catch (SQLException e) {
        System.out.println(e.getMessage());
        this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
    } finally {
        try {
            if (stmClinica != null) stmClinica.close();
        } catch (SQLException e) {
            System.out.println("Imposible cerrar cursores");
        }
    }

    return resultado;
}

    
}
