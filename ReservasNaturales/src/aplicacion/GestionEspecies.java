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
public class GestionEspecies {
     
    private FachadaGui fgui;
    private FachadaBaseDatos fbd;
    
    public GestionEspecies(FachadaGui fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }

    public List<Especie> obtenerEspecies(String nombreCientifico) {
        return fbd.obtenerEspecies(nombreCientifico);
    }

    void nuevaEspecie() {
        fgui.nuevaEspecie();
    }

    void editarEspecie(Especie e) {
        fgui.editarEspecie(e);
    }

    void anhadirEspecie(Especie e) {
        fbd.anhadirEspecie(e);
    }

    void actualizarEspecie(Especie e, Especie eNueva) {
        fbd.actualizarEspecie(e,eNueva);
    }

    void borrarEspecie(Especie e) {
        fbd.borrarEspecie(e);
    }
}
