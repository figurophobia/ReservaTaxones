/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import aplicacion.Especie;
import aplicacion.FachadaAplicacion;


/**
 *
 * @author alumno
 */
public class FachadaGui {
    FachadaAplicacion fa;
    VPrincipal vp;
    
   public FachadaGui(FachadaAplicacion fa){
     this.fa=fa;
     this.vp = new VPrincipal(fa);
   } 
    
    
    
    public void iniciaVista(){
      VAutentificacion va;
    
      va = new VAutentificacion(vp, true, fa);
      vp.setVisible(true);
      va.setVisible(true);
    }
    

    
    public void muestraExcepcion(String txtExcepcion){
       VAviso va;
       
       va = new VAviso(vp, true, txtExcepcion);
       va.setVisible(true);
    }

    public void nuevaEspecie() {
        VEspecies ve;
        ve = new VEspecies(vp, true, fa);
        ve.setVisible(true);
    }

    public void nuevaArea() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void editarEspecie(Especie e) {
        VEspecies ve;
        ve = new VEspecies(vp, true, fa, e);
        ve.setVisible(true);
    }

    
   
}
