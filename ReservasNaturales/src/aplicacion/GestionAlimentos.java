/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

import baseDatos.FachadaBaseDatos;
import gui.FachadaGui;
import java.util.List;

/**
 *
 * @author alumnogreibd
 */
public class GestionAlimentos {
    private FachadaGui fgui;
    private FachadaBaseDatos fbd;
    
    public GestionAlimentos(FachadaGui fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }

    public List<Alimento> obtenerAlimentos() {
        return fbd.obtenerAlimentos();
    }

    int anadirAlimento(String nome, String tipo) {
        return fbd.anadirAlimento(nome, tipo);
    }

    int borrarAlimento(String nome, String tipo) {
        return fbd.borrarAlimento(nome, tipo);
    }
}
