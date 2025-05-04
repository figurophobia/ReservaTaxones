/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseDatos;

import aplicacion.Alimento;
import aplicacion.Area;
import aplicacion.ConsumirAlimento;
import aplicacion.Distribuidor;
import aplicacion.Ejemplar;
import aplicacion.Especie;
import aplicacion.FachadaAplicacion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAODistribuidores extends AbstractDAO {

   
    public DAODistribuidores(Connection conexion, FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    List<Distribuidor> obterDistribuidores() {
        List<Distribuidor> resultado = new ArrayList<>();
        Connection con = this.getConexion();
        PreparedStatement stmDistr = null;
        
        ResultSet rsDistr;
        

        
        try {
            String consulta = "SELECT nombre FROM empresa_distribuidora";
           
            stmDistr = con.prepareStatement(consulta);
            rsDistr =  stmDistr.executeQuery();

            while (rsDistr.next()) {
                
                String nombre = rsDistr.getString("nombre");
                Distribuidor distribuidor = new Distribuidor(nombre);
                resultado.add(distribuidor);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                if (stmDistr != null) stmDistr.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
    }

    
}


   
   

