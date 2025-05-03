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
public class GestionClinicas {
    private FachadaBaseDatos fbd;
    private FachadaGui fgui;
    
    public GestionClinicas(FachadaBaseDatos fbd, FachadaGui fgui) {
        this.fbd = fbd;
        this.fgui = fgui;
    }

    List<ClinicaMedica> obtenerClinicas(String textoBusqueda) {
        return fbd.obtenerClinicas(textoBusqueda);
    }

    ClinicaMedica nuevaClinica(ClinicaMedica clinica) {
         return fbd.nuevaClinica(clinica);
    }

    void borrarClinica(ClinicaMedica clinicaSeleccionada) {
        fbd.borrarClinica(clinicaSeleccionada);
    }
    
}
