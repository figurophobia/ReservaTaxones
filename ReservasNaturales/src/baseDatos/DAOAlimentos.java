/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseDatos;

import aplicacion.Alimento;
import aplicacion.FachadaAplicacion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOAlimentos extends AbstractDAO {

    public DAOAlimentos(Connection conexion, FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public List<Alimento> obtenerAlimentos() {
        List<Alimento> resultado = new ArrayList<>();
        Connection con = this.getConexion();
        PreparedStatement stmAlimento = null;
        ResultSet rsAlimento;

        try {
            String consulta = "SELECT id, tipo, nombre FROM alimento";
            stmAlimento = con.prepareStatement(consulta);
            rsAlimento = stmAlimento.executeQuery();

            while (rsAlimento.next()) {
                int id = rsAlimento.getInt("id");
                String nombre = rsAlimento.getString("nombre");
                String tipo = rsAlimento.getString("tipo");
                
                
                Alimento alim = new Alimento(id, tipo, nombre);
                resultado.add(alim);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                if (stmAlimento != null) stmAlimento.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
    }

    int anadirAlimento(String nome, String tipo) {
        Connection con;
        PreparedStatement stmAlimento = null;
        int resultado = -1;
        con = super.getConexion();

        try {
            
            stmAlimento = con.prepareStatement("insert into alimento(tipo, nombre) "
                    + "values (?,?)");

            stmAlimento.setString(1, tipo);
            stmAlimento.setString(2, nome);
            resultado = stmAlimento.executeUpdate();
            
        } catch (SQLException ex) {
            this.getFachadaAplicacion().muestraExcepcion(ex.getMessage());
        }
        return resultado;
           
        
    }

    int borrarAlimento(String nome, String tipo) {
        Connection con;
        PreparedStatement stmAlimento=null;
        int res = -1;
        con=super.getConexion();

        try {
        stmAlimento=con.prepareStatement("delete from alimento where tipo = ? and nombre = ?");
        stmAlimento.setString(1, nome);
        stmAlimento.setString(2, tipo);
        res = stmAlimento.executeUpdate();

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }
        
        return res;
    }
}
