/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package baseDatos;

import aplicacion.FachadaAplicacion;
import java.sql.*;
/**
 *
 * @author basesdatos
 */
public abstract class AbstractDAO {
   private FachadaAplicacion fa;
   private Connection conexion;

   
    protected Connection getConexion(){
        return this.conexion;
    }
    
    protected void setConexion(Connection conexion){
        this.conexion=conexion;
    }
   
   protected void setFachadaAplicacion(FachadaAplicacion fa){
       this.fa=fa;
   }
   
   protected FachadaAplicacion getFachadaAplicacion(){
       return fa;
   }
   
   
}
