/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import aplicacion.ClinicaMedica;
import aplicacion.FachadaAplicacion;
import aplicacion.Ejemplar;

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

    public void crearRevision(ClinicaMedica clinicaSeleccionada,Ejemplar ejemplar) {
        VRevisiones vr;
        
        vr = new VRevisiones(vp, true ,fa, ejemplar, clinicaSeleccionada);
        vr.setVisible(true);
        
    }
    public void crearVClinicas(Ejemplar selecionado){
        VClinicas vc;
        vc = new VClinicas(vp,true,fa,selecionado);
        vc.setVisible(true);
        
    }

    
   
}
