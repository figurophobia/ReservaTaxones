/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion;

import gui.FachadaGui;
import baseDatos.FachadaBaseDatos;
import java.util.List;

/**
 *
 * @author davidavirn
 */
public class GestionDistribuidores {
     
    private FachadaGui fgui;
    private FachadaBaseDatos fbd;
    
    public GestionDistribuidores(FachadaGui fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }

    List<Distribuidor> obterDistribuidores() {
        return fbd.obterDistribuidores();
    }

}
