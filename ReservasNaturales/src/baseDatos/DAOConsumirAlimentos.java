/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseDatos;

import aplicacion.Alimento;
import aplicacion.ConsumirAlimento;
import aplicacion.Ejemplar;
import aplicacion.Especie;
import aplicacion.FachadaAplicacion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOConsumirAlimentos extends AbstractDAO {

    public DAOConsumirAlimentos(Connection conexion, FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public List<ConsumirAlimento> obterConsumirAlimentos() {
        List<ConsumirAlimento> resultado = new ArrayList<>();
        Connection con = this.getConexion();
        PreparedStatement stmConsAlimento = null;
        PreparedStatement stmAlimento = null;
        ResultSet rsConsAlimento;
        ResultSet rsAlimento;

        try {
            Alimento al = null;
            String consulta = "SELECT id_especie, nombre_especie, id_alimento, cantidad, frecuencia FROM consumirAlimentos";
            String consultaAl = "SELECT id, tipo, nombre, distribuidor FROM alimento where id = ?";
            stmConsAlimento = con.prepareStatement(consulta);
            stmAlimento = con.prepareStatement(consultaAl);

            rsConsAlimento = stmConsAlimento.executeQuery();

            while (rsConsAlimento.next()) {
                int id = rsConsAlimento.getInt("id_especie");
                String nombre = rsConsAlimento.getString("nombre_especie");
                int idAlimento = rsConsAlimento.getInt("id_alimento");
                int cantidad = rsConsAlimento.getInt("cantidad");
                int frecuencia = rsConsAlimento.getInt("frecuencia");

                stmAlimento.setInt(1, idAlimento);
                rsAlimento = stmAlimento.executeQuery();
                while (rsAlimento.next()) {
                    al = new Alimento(idAlimento,
                            rsAlimento.getString("tipo"),
                            rsAlimento.getString("nombre"),
                            rsAlimento.getString("distribuidor")
                    );
                }

                ConsumirAlimento consAlm = new ConsumirAlimento(new Ejemplar(id, new Especie(nombre)), al, cantidad, frecuencia);
                resultado.add(consAlm);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                if (stmAlimento != null) {
                    stmAlimento.close();
                }
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
    }

    List<ConsumirAlimento> obterConsumirAlimentos(int idAlimento) {
        List<ConsumirAlimento> resultado = new ArrayList<>();
        Connection con = this.getConexion();
        PreparedStatement stmConsAlimento = null;
        PreparedStatement stmAlimento = null;
        ResultSet rsConsAlimento;
        ResultSet rsAlimento;

        try {
            Alimento al = null;
            String consulta = "SELECT id_especie, nombre_especie, id_alimento, cantidad, frecuencia FROM consumirAlimentos where id_alimento = ?";
            String consultaAl = "SELECT id, tipo, nombre, distribuidor FROM alimento where id = ?";
            stmConsAlimento = con.prepareStatement(consulta);
            stmAlimento = con.prepareStatement(consultaAl);

            stmConsAlimento.setInt(1, idAlimento);
            rsConsAlimento = stmConsAlimento.executeQuery();

            while (rsConsAlimento.next()) {
                int id = rsConsAlimento.getInt("id_especie");
                String nombre = rsConsAlimento.getString("nombre_especie");
                int cantidad = rsConsAlimento.getInt("cantidad");
                int frecuencia = rsConsAlimento.getInt("frecuencia");

                stmAlimento.setInt(1, idAlimento);
                rsAlimento = stmAlimento.executeQuery();
                while (rsAlimento.next()) {
                    al = new Alimento(idAlimento,
                            rsAlimento.getString("tipo"),
                            rsAlimento.getString("nombre"),
                            rsAlimento.getString("distribuidor")
                    );
                }

                ConsumirAlimento consAlm = new ConsumirAlimento(new Ejemplar(id, new Especie(nombre)), al, cantidad, frecuencia);
                resultado.add(consAlm);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                if (stmAlimento != null) {
                    stmAlimento.close();
                }
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
    }

    int anadirConsumirAlimento(ConsumirAlimento ca) {
        Connection con;
        PreparedStatement stmConsAlimento = null;
        int resultado = -1;
        con = super.getConexion();

        try {

            stmConsAlimento = con.prepareStatement("insert into consumirAlimentos(id_especie, nombre_especie, id_alimento, cantidad, frecuencia)"
                    + "values (?,?,?,?,?)");

            stmConsAlimento.setInt(1, ca.getEjemplar().getId());
            stmConsAlimento.setString(2, ca.getEjemplar().getEspecie().getNombreCientifico());
            stmConsAlimento.setInt(3, ca.getAlimento().getId());
            stmConsAlimento.setInt(4, ca.getCantidad());
            stmConsAlimento.setInt(5, ca.getFrecuencia());
            resultado = stmConsAlimento.executeUpdate();

        } catch (SQLException ex) {
            this.getFachadaAplicacion().muestraExcepcion(ex.getMessage());
        }
        return resultado;

    }

    int borrarConsumirAlimento(int idEspecie, String nombreEspecie, int idAlimento) {
        Connection con;
        PreparedStatement stmConsAlimento = null;
        int res = -1;
        con = super.getConexion();

        try {
            stmConsAlimento = con.prepareStatement("delete from consumirAlimentos where id_especie = ? and nombre_especie = ? and id_alimento = ?");
            stmConsAlimento.setInt(1, idEspecie);
            stmConsAlimento.setString(2, nombreEspecie);
            stmConsAlimento.setInt(3, idAlimento);
            res = stmConsAlimento.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }

        return res;
    }

    List<ConsumirAlimento> obterConsumirAlimentosEjemplar(int idEjemplar) {
        List<ConsumirAlimento> resultado = new ArrayList<>();
        Connection con = this.getConexion();
        PreparedStatement stmConsAlimento = null;
        PreparedStatement stmAlimento = null;
        ResultSet rsConsAlimento;
        ResultSet rsAlimento;

        try {
            Alimento al = null;
            String consulta = "SELECT id_especie, nombre_especie, id_alimento, cantidad, frecuencia FROM consumirAlimentos where id_especie = ?";
            String consultaAl = "SELECT id, tipo, nombre, distribuidor FROM alimento where id = ?";
            stmConsAlimento = con.prepareStatement(consulta);
            stmAlimento = con.prepareStatement(consultaAl);

            stmConsAlimento.setInt(1, idEjemplar);
            rsConsAlimento = stmConsAlimento.executeQuery();

            while (rsConsAlimento.next()) {
                int id = rsConsAlimento.getInt("id_especie");
                String nombre = rsConsAlimento.getString("nombre_especie");
                int idAlimento = rsConsAlimento.getInt("id_alimento");
                int cantidad = rsConsAlimento.getInt("cantidad");
                int frecuencia = rsConsAlimento.getInt("frecuencia");

                stmAlimento.setInt(1, idAlimento);
                rsAlimento = stmAlimento.executeQuery();
                while (rsAlimento.next()) {
                    al = new Alimento(idAlimento,
                            rsAlimento.getString("tipo"),
                            rsAlimento.getString("nombre"),
                            rsAlimento.getString("distribuidor")
                    );
                }

                ConsumirAlimento consAlm = new ConsumirAlimento(new Ejemplar(id, new Especie(nombre)), al, cantidad, frecuencia);
                resultado.add(consAlm);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                if (stmAlimento != null) {
                    stmAlimento.close();
                }
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
    }

}
