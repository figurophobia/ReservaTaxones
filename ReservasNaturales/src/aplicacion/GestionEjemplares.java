/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

import gui.FachadaGui;
import baseDatos.FachadaBaseDatos;
import java.util.List;

/**
 *
 * @author davidavirn
 */
public class GestionEjemplares {
     
    private FachadaGui fgui;
    private FachadaBaseDatos fbd;
    
    public GestionEjemplares(FachadaGui fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }

    public List<Ejemplar> obtenerEjemplares() {
        return fbd.obtenerEjemplares();
    }

    int novoEjemplar(Ejemplar ej) {
        return fbd.novoEjemplar(ej);
    }

    int borrarEjemplar(int id, String nom_cient) {
        return fbd.borrarEjemplar(id, nom_cient);
    }

    int modificarEjemplar_cambioAlimentoPorArea(Ejemplar ejemplarModificar, Alimento al, int idAlimentoVello) {
        return fbd.modificarEjemplar_cambioAlimentoPorArea(ejemplarModificar, al, idAlimentoVello);
    }

    int modificarEjemplar(Ejemplar ejemplarModificar) {
        return fbd.modificarEjemplar(ejemplarModificar);
    }

    List<Ejemplar> obtenerEjemplares(String nomCient) {
        return fbd.obtenerEjemplares(nomCient);
    }
}

  
   