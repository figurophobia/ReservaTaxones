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
public class GestionConsumirAlimentos {
    private FachadaGui fgui;
    private FachadaBaseDatos fbd;
    
    public GestionConsumirAlimentos(FachadaGui fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }

    List<ConsumirAlimento> obterConsumirAlmentos() {
        return fbd.obterConsumirAlimentos();
    }

    int anadirConsumirAlimentos(ConsumirAlimento consumirAlimento) {
        return fbd.anadirConsumirAlimentos(consumirAlimento);
    }

    int borrarConsumirAlimento(int idEjemplar, String nombreCientifico, int idAlimento) {
        return fbd.borrarConsumirAlimento(idEjemplar, nombreCientifico, idAlimento);
    }

    List<ConsumirAlimento> obterConsumirAlimentos(int idAlimento) {
        return fbd.obterConsumirAlimentos(idAlimento);
    }

    List<ConsumirAlimento> obterConsumirAlmentosEjemplar(int idEjemplar) {
        return fbd.obterConsumirAlimentosEjemplar(idEjemplar);
    }

    
    
}
