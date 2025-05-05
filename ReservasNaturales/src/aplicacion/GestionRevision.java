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

public class GestionRevision {
    private FachadaGui fgui;
    private FachadaBaseDatos fbd;

    public GestionRevision(FachadaBaseDatos fbd, FachadaGui fgui) {
        this.fgui=fgui;
        this.fbd=fbd;
    }

    List<Revision> obtenerRevisiones(String nClinica, int Id) {
        return fbd.obtenerRevisiones(nClinica, Id);
    }

    void añadirRevsion(ClinicaMedica clinicaRevision, Ejemplar ejemplarRevision, String text) {
        fbd.añadirRevsion(clinicaRevision,ejemplarRevision,text);
    }

    void buscarMaxRevisiones() {
        fbd.buscarMaxRevisiones();
    }

    List<Revision> obtenerRevisiones() {
        return fbd.obtenerRevisiones();
    }

    
    
}
