/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseDatos;

import aplicacion.Area;
import aplicacion.Ejemplar;
import aplicacion.Especie;
import aplicacion.FachadaAplicacion;
import aplicacion.Taxon;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DAOEjemplares extends AbstractDAO {

    public DAOEjemplares(Connection conexion, FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public List<Ejemplar> obtenerEjemplares() {
        List<Ejemplar> resultado = new ArrayList<>();
        Connection con = this.getConexion();
        PreparedStatement stmEjemplar = null, stmEspecie = null, stmArea = null;
        ResultSet rsEjemplar, rsEspecie, rsArea;
        Area area = null;
        Especie especie = null;

        try {
            String consulta = "SELECT id, nombre_cientifico_especie, mote, fec_nac, area_geografica FROM ejemplar";
            String consultaEspecie = "SELECT nombre_cientifico, nombre_comun, descripcion, nombre_taxon from especies where nombre_cientifico = ?";
            String consultaArea = "SELECT nombre_reserva, extension, altitud_nivel_bajo, altitud_nivel_alto, profundidad, esAcuatica, esTerrestre from area_geografica where nombre_reserva = ?";
            stmEjemplar = con.prepareStatement(consulta);
            stmEspecie = con.prepareStatement(consultaEspecie);
            stmArea = con.prepareStatement(consultaArea);
            rsEjemplar = stmEjemplar.executeQuery();

            while (rsEjemplar.next()) {
                int id = rsEjemplar.getInt("id");
                String nombre = rsEjemplar.getString("nombre_cientifico_especie");
                String mote = rsEjemplar.getString("mote");
                Date fec = rsEjemplar.getDate("fec_nac");
                String fec_nac = fec.toString();
                String nom_area = rsEjemplar.getString("area_geografica");
                
                
                
                stmEspecie.setString(1, nombre);
                rsEspecie = stmEspecie.executeQuery();
                
                while(rsEspecie.next()) {
                    String nombreCienEspecie = rsEspecie.getString("nombre_cientifico");
                    String nombreComunEspecie = rsEspecie.getString("nombre_comun");
                    String descripcion = rsEspecie.getString("descripcion");
                    String nombre_taxon = rsEspecie.getString("nombre_taxon");
                    
                   
                    
                    especie = new Especie(nombreCienEspecie, nombreComunEspecie, descripcion, new Taxon(nombre_taxon));
                }
                
                 stmArea.setString(1, nom_area);
                 rsArea = stmArea.executeQuery();
                 while (rsArea.next()) {
                    area = null;
                    String nombre_reserva = rsArea.getString("nombre_reserva");
                    int extension = rsArea.getInt("extension");
                    boolean acuatica = rsArea.getBoolean("esAcuatica");
                    if (acuatica) {
                        Double profundidad = rsArea.getDouble("profundidad");
                        area = new Area(nombre_reserva, extension, profundidad);
                    } else {
                        Double altitud_nivel_bajo = rsArea.getDouble("altitud_nivel_bajo");
                        Double altitud_nivel_alto = rsArea.getDouble("altitud_nivel_alto");
                        area = new Area(nombre_reserva, extension, altitud_nivel_bajo, altitud_nivel_alto);
                    }
                }
                
                
                Ejemplar ejem = new Ejemplar(id, especie, mote, fec_nac, area);
                resultado.add(ejem);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                if (stmEjemplar != null) stmEjemplar.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
    }


    int novoEjemplar(Ejemplar ej) {
        Connection con;
        PreparedStatement stmEjemplar = null;
        int resultado = -1;
        con = super.getConexion();

        try {
            
            stmEjemplar = con.prepareStatement("insert into ejemplar(nombre_cientifico_especie, mote, fec_nac, area_geografica) "
                    + "values (?,?,?,?)");

            stmEjemplar.setString(1, ej.getEspecie().getNombreCientifico());
            stmEjemplar.setString(2, ej.getMote());
            Date fecha = Date.valueOf(ej.getFec_nac());
            stmEjemplar.setDate(3, fecha);
            stmEjemplar.setString(4, ej.getArea().getNombreReserva());
            resultado = stmEjemplar.executeUpdate();
            
        } catch (SQLException ex) {
            this.getFachadaAplicacion().muestraExcepcion(ex.getMessage());
        }
        return resultado;
    }

    int borrarEjemplar(int id, String nom_cient) {
        Connection con;
        PreparedStatement stmEjemplar=null;
        int res = -1;
        con=super.getConexion();

        try {
        stmEjemplar=con.prepareStatement("delete from ejemplar where id = ? and nombre_cientifico_especie = ?");
        stmEjemplar.setInt(1, id);
        stmEjemplar.setString(2, nom_cient);
        res = stmEjemplar.executeUpdate();

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }
        
        return res;
    }

    List<Ejemplar> obterEjemplares(Especie e) {
        List<Ejemplar> resultado = new ArrayList<>();
        Connection con = this.getConexion();
        PreparedStatement stmEjemplar = null, stmArea = null;
        ResultSet rsEjemplar, rsArea;
        Area area = null;

        try {
            String consulta = "SELECT id, mote, fec_nac, area_geografica FROM ejemplar WHERE nombre_cientifico_especie = ?";
            String consultaArea = "SELECT nombre_reserva, extension, altitud_nivel_bajo, altitud_nivel_alto, profundidad, esAcuatica, esTerrestre FROM area_geografica WHERE nombre_reserva = ?";

            stmEjemplar = con.prepareStatement(consulta);
            stmEjemplar.setString(1, e.getNombreCientifico());
            rsEjemplar = stmEjemplar.executeQuery();

            stmArea = con.prepareStatement(consultaArea);

            while (rsEjemplar.next()) {
                int id = rsEjemplar.getInt("id");
                String mote = rsEjemplar.getString("mote");
                Date fec = rsEjemplar.getDate("fec_nac");
                String fec_nac = fec.toString();
                String nom_area = rsEjemplar.getString("area_geografica");

                stmArea.setString(1, nom_area);
                rsArea = stmArea.executeQuery();

                while (rsArea.next()) {
                    area = null;
                    String nombre_reserva = rsArea.getString("nombre_reserva");
                    int extension = rsArea.getInt("extension");
                    boolean acuatica = rsArea.getBoolean("esAcuatica");

                    if (acuatica) {
                        Double profundidad = rsArea.getDouble("profundidad");
                        area = new Area(nombre_reserva, extension, profundidad);
                    } else {
                        Double altitud_nivel_bajo = rsArea.getDouble("altitud_nivel_bajo");
                        Double altitud_nivel_alto = rsArea.getDouble("altitud_nivel_alto");
                        area = new Area(nombre_reserva, extension, altitud_nivel_bajo, altitud_nivel_alto);
                    }
                }

                Ejemplar ejem = new Ejemplar(id, e, mote, fec_nac, area);
                resultado.add(ejem);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(ex.getMessage());
        } finally {
            try {
                if (stmEjemplar != null) stmEjemplar.close();
                if (stmArea != null) stmArea.close();
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
    }

    void actualizarEjemplar(Ejemplar ejemplarSeleccionado, Ejemplar nuevoEjemplar) {
        String sql = "UPDATE ejemplar SET mote = ?, fec_nac = ?, area_geografica = ? WHERE id = ?";

        try (PreparedStatement stm = this.getConexion().prepareStatement(sql)) {
            stm.setString(1, nuevoEjemplar.getMote());
            stm.setDate(2, Date.valueOf(nuevoEjemplar.getFec_nac())); // convertir String a java.sql.Date
            stm.setString(3, nuevoEjemplar.getArea().getNombreReserva());
            stm.setInt(4, ejemplarSeleccionado.getId());

            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al actualizar ejemplar: " + e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }
    }
}
