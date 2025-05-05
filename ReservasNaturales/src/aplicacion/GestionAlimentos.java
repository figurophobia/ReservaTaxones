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

    int anadirAlimento(String nome, String tipo, String distribuidor) {
        return fbd.anadirAlimento(nome, tipo,distribuidor);
    }

    int borrarAlimento(String nome, String tipo, String distribuidorAlimento) {
        return fbd.borrarAlimento(nome, tipo,distribuidorAlimento);
    }

    boolean eliminarNoConsumidos() {
        return fbd.eliminarNoConsumidos();
    }

    int actualizarAlimento(String tipo, String nombre, String distribuidor) {
        return fbd.actualizarAlimento(tipo,nombre,distribuidor);
    }

    void bajarMitad() {
        fbd.bajarMitad();
    }

    void aumentarFrecuenciaAlimentos(String nombre, String area) {
        fbd.aumentarFrecuenciaAlimentos(nombre,area);
    }
}
