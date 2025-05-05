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
        List<Ejemplar> listaEjemplares = new ArrayList<>();
        Connection conexion = this.getConexion();
        PreparedStatement psEjemplar = null, psEspecie = null, psZona = null;
        ResultSet rsEjemplar = null, rsEspecie = null, rsZona = null;
    
        try {
            String sqlEjemplares = "SELECT id, nombre_cientifico_especie, mote, fec_nac, area_geografica FROM ejemplar";
            String sqlEspecie = "SELECT nombre_cientifico, nombre_comun, descripcion, nombre_taxon FROM especies WHERE nombre_cientifico = ?";
            String sqlArea = "SELECT nombre_reserva, extension, altitud_nivel_bajo, altitud_nivel_alto, profundidad, esAcuatica, esTerrestre FROM area_geografica WHERE nombre_reserva = ?";
    
            psEjemplar = conexion.prepareStatement(sqlEjemplares);
            psEspecie = conexion.prepareStatement(sqlEspecie);
            psZona = conexion.prepareStatement(sqlArea);
    
            rsEjemplar = psEjemplar.executeQuery();
    
            while (rsEjemplar.next()) {
                int identificador = rsEjemplar.getInt("id");
                String nombreCientifico = rsEjemplar.getString("nombre_cientifico_especie");
                String apodo = rsEjemplar.getString("mote");
                Date fechaNacimiento = rsEjemplar.getDate("fec_nac");
                String fechaFormateada = fechaNacimiento.toString();
                String nombreArea = rsEjemplar.getString("area_geografica");
    
                // Recuperar especie
                Especie especieAsociada = null;
                psEspecie.setString(1, nombreCientifico);
                rsEspecie = psEspecie.executeQuery();
    
                if (rsEspecie.next()) {
                    String nombreCient = rsEspecie.getString("nombre_cientifico");
                    String nombrePopular = rsEspecie.getString("nombre_comun");
                    String desc = rsEspecie.getString("descripcion");
                    String taxon = rsEspecie.getString("nombre_taxon");
    
                    especieAsociada = new Especie(nombreCient, nombrePopular, desc, new Taxon(taxon));
                }
    
                // Recuperar área
                Area zona = null;
                psZona.setString(1, nombreArea);
                rsZona = psZona.executeQuery();
    
                if (rsZona.next()) {
                    String reserva = rsZona.getString("nombre_reserva");
                    int tamano = rsZona.getInt("extension");
                    boolean esAcuatica = rsZona.getBoolean("esAcuatica");
    
                    if (esAcuatica) {
                        double prof = rsZona.getDouble("profundidad");
                        zona = new Area(reserva, tamano, prof);
                    } else {
                        double altBaja = rsZona.getDouble("altitud_nivel_bajo");
                        double altAlta = rsZona.getDouble("altitud_nivel_alto");
                        zona = new Area(reserva, tamano, altBaja, altAlta);
                    }
                }
    
                Ejemplar ejemplar = new Ejemplar(identificador, especieAsociada, apodo, fechaFormateada, zona);
                listaEjemplares.add(ejemplar);
            }
    
        } catch (SQLException ex) {
            System.out.println("Error al obtener ejemplares: " + ex.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(ex.getMessage());
        } finally {
            try {
                if (psEjemplar != null) psEjemplar.close();
                if (psEspecie != null) psEspecie.close();
                if (psZona != null) psZona.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando recursos: " + ex.getMessage());
            }
        }
    
        return listaEjemplares;
    }

    int nuevoEjemplar(Ejemplar ejemplar) {
        Connection conexion;
        PreparedStatement psInsertar = null;
        int filasAfectadas = -1;
        conexion = this.getConexion();

        try {
            String sql = "INSERT INTO ejemplar(nombre_cientifico_especie, mote, fec_nac, area_geografica) VALUES (?, ?, ?, ?)";
            psInsertar = conexion.prepareStatement(sql);

            psInsertar.setString(1, ejemplar.getEspecie().getNombreCientifico());
            psInsertar.setString(2, ejemplar.getMote());
            psInsertar.setDate(3, Date.valueOf(ejemplar.getFec_nac()));
            psInsertar.setString(4, ejemplar.getArea().getNombreReserva());

            filasAfectadas = psInsertar.executeUpdate();

        } catch (SQLException ex) {
            this.getFachadaAplicacion().muestraExcepcion(ex.getMessage());
        }

        return filasAfectadas;
    }

    int eliminarEjemplar(int identificador, String especieNombre) {
        Connection conexion;
        PreparedStatement psEliminar = null;
        int resultado = -1;
        conexion = super.getConexion();

        try {
            String sql = "DELETE FROM ejemplar WHERE id = ? AND nombre_cientifico_especie = ?";
            psEliminar = conexion.prepareStatement(sql);

            psEliminar.setInt(1, identificador);
            psEliminar.setString(2, especieNombre);

            resultado = psEliminar.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error al eliminar ejemplar: " + ex.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(ex.getMessage());
        }

        return resultado;
}

    List<Ejemplar> ejemplaresPorEspecie(Especie especieSeleccionada) {
        List<Ejemplar> ejemplares = new ArrayList<>();
        Connection conexion = this.getConexion();
        PreparedStatement psEjemplar = null;
        PreparedStatement psArea = null;
        ResultSet resultadoEjemplar = null;
        ResultSet resultadoArea = null;
        Area zonaGeografica = null;

        try {
            String sqlEjemplar = "SELECT id, mote, fec_nac, area_geografica FROM ejemplar WHERE nombre_cientifico_especie = ?";
            String sqlArea = "SELECT nombre_reserva, extension, altitud_nivel_bajo, altitud_nivel_alto, profundidad, esAcuatica, esTerrestre FROM area_geografica WHERE nombre_reserva = ?";

            psEjemplar = conexion.prepareStatement(sqlEjemplar);
            psEjemplar.setString(1, especieSeleccionada.getNombreCientifico());
            resultadoEjemplar = psEjemplar.executeQuery();

            psArea = conexion.prepareStatement(sqlArea);

            while (resultadoEjemplar.next()) {
                int codigoEjemplar = resultadoEjemplar.getInt("id");
                String apodo = resultadoEjemplar.getString("mote");
                String fechaNacimiento = resultadoEjemplar.getDate("fec_nac").toString();
                String nombreZona = resultadoEjemplar.getString("area_geografica");

                psArea.setString(1, nombreZona);
                resultadoArea = psArea.executeQuery();

                if (resultadoArea.next()) {
                    zonaGeografica = null;
                    String nombreReserva = resultadoArea.getString("nombre_reserva");
                    int tamanyo = resultadoArea.getInt("extension");

                    if (resultadoArea.getBoolean("esAcuatica")) {
                        double prof = resultadoArea.getDouble("profundidad");
                        zonaGeografica = new Area(nombreReserva, tamanyo, prof);
                    } else {
                        double altMin = resultadoArea.getDouble("altitud_nivel_bajo");
                        double altMax = resultadoArea.getDouble("altitud_nivel_alto");
                        zonaGeografica = new Area(nombreReserva, tamanyo, altMin, altMax);
                    }
                }

                ejemplares.add(new Ejemplar(codigoEjemplar, especieSeleccionada, apodo, fechaNacimiento, zonaGeografica));
            }

        } catch (SQLException error) {
            System.out.println(error.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(error.getMessage());
        } finally {
            try {
                if (psEjemplar != null) psEjemplar.close();
                if (psArea != null) psArea.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos");
            }
        }

        return ejemplares;
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
