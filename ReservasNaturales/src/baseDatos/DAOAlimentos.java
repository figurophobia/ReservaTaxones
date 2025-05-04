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
            String consulta = "SELECT id, tipo, nombre, distribuidor FROM alimento";
            stmAlimento = con.prepareStatement(consulta);
            rsAlimento = stmAlimento.executeQuery();

            while (rsAlimento.next()) {
                int id = rsAlimento.getInt("id");
                String nombre = rsAlimento.getString("nombre");
                String tipo = rsAlimento.getString("tipo");
                String distribuidor = rsAlimento.getString("distribuidor");

                Alimento alim = new Alimento(id, tipo, nombre, distribuidor);
                resultado.add(alim);
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

    int anadirAlimento(String nome, String tipo, String distribuidor) {
        Connection con;
        PreparedStatement stmAlimento = null;
        int resultado = -1;
        con = super.getConexion();

        try {

            stmAlimento = con.prepareStatement("insert into alimento(tipo, nombre,distribuidor) "
                    + "values (?,?,?)");

            stmAlimento.setString(1, tipo);
            stmAlimento.setString(2, nome);
            stmAlimento.setString(3, distribuidor);
            resultado = stmAlimento.executeUpdate();

        } catch (SQLException ex) {
            this.getFachadaAplicacion().muestraExcepcion(ex.getMessage());
        }
        return resultado;

    }

    int borrarAlimento(String nome, String tipo, String distribuidor) {
        int res = -1;
        Connection con = null;
        PreparedStatement stmAlimento = null;

        try {
            // Obtener la conexión (asegúrate de que esta conexión sea válida)
            con = super.getConexion();

            // Sentencia SQL para eliminar el alimento basado en nombre, tipo y distribuidor
            String sql = "DELETE FROM alimento WHERE nombre = ? AND tipo = ? AND distribuidor = ?";
            stmAlimento = con.prepareStatement(sql);

            // Establecer los parámetros en la sentencia SQL
            stmAlimento.setString(1, nome);
            stmAlimento.setString(2, tipo);
            stmAlimento.setString(3, distribuidor);

            // Ejecutar la sentencia
            res = stmAlimento.executeUpdate();

            // Verifica cuántas filas fueron afectadas
            if (res > 0) {
                System.out.println("Alimento eliminado correctamente.");
            } else {
                System.out.println("No se encontró el alimento para eliminar.");
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar alimento: " + e.getMessage());
        } finally {
            try {
                // Cerrar los recursos después de usarlos
                if (stmAlimento != null) {
                    stmAlimento.close();
                }
                if (con != null) {

                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }

        return res;
    }

    public boolean eliminarNoConsumidos() {
        Connection con = this.getConexion();
        PreparedStatement stmAlimento = null;
        ResultSet rsAlimento = null;
        int filasEliminadas = 0;

        try {
            
            String sqlSelect
                    = "SELECT a.id, a.nombre, a.tipo, a.distribuidor "
                    + "FROM alimento a "
                    + "LEFT JOIN consumirAlimentos ca ON a.id = ca.id_alimento "
                    + "WHERE ca.id_alimento IS NULL "
                    + "AND a.distribuidor IN ( "
                    + "SELECT distribuidor "
                    + "FROM alimento "
                    + "GROUP BY distribuidor "
                    + "HAVING COUNT(*) <= 2 "
                    + ") "
                    + "ORDER BY a.distribuidor, a.tipo";

       
            stmAlimento = con.prepareStatement(sqlSelect);
            rsAlimento = stmAlimento.executeQuery();


            System.out.println("Alimentos que serán eliminados:");

            while (rsAlimento.next()) {
                int id = rsAlimento.getInt("id");
                String nombre = rsAlimento.getString("nombre");
                String tipo = rsAlimento.getString("tipo");
                String distribuidor = rsAlimento.getString("distribuidor");

        
                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Tipo: " + tipo + ", Distribuidor: " + distribuidor);


                String sqlDelete = "DELETE FROM alimento WHERE id = ?";
                PreparedStatement stmDelete = con.prepareStatement(sqlDelete);
                stmDelete.setInt(1, id);
                filasEliminadas += stmDelete.executeUpdate();
            }

            if (filasEliminadas > 0) {
                System.out.println(filasEliminadas + " alimentos no consumidos han sido eliminados.");
                return true;
            } else {
                System.out.println("No se encontraron alimentos no consumidos para eliminar.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar alimentos no consumidos: " + e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
            return false;
        } finally {
            try {
                if (rsAlimento != null) {
                    rsAlimento.close();
                }
                if (stmAlimento != null) {
                    stmAlimento.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }
    }

    public int actualizarAlimento(String tipo, String nombre, String distribuidor) {
        Connection con = this.getConexion();
        PreparedStatement stmAlimento = null;
        int resultado = -1;

        try {
            // Consulta SQL para actualizar el distribuidor de un alimento basado en su tipo y nombre
            String consulta = "UPDATE alimento SET distribuidor = ? WHERE tipo = ? AND nombre = ?";
            stmAlimento = con.prepareStatement(consulta);

            // Establecer los parámetros de la consulta
            stmAlimento.setString(1, distribuidor);  // Actualizar distribuidor
            stmAlimento.setString(2, tipo);  // Identificar el alimento por tipo
            stmAlimento.setString(3, nombre);  // Identificar el alimento por nombre

            // Ejecutar la actualización
            resultado = stmAlimento.executeUpdate();

        } catch (SQLException e) {
            // Manejar excepciones y mostrar el mensaje de error
            System.out.println("Error al actualizar el alimento: " + e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            // Cerrar la sentencia preparada
            try {
                if (stmAlimento != null) {
                    stmAlimento.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la sentencia: " + e.getMessage());
            }
        }

        return resultado;  // Si se actualizó correctamente, `resultado` será > 0
    }

}
