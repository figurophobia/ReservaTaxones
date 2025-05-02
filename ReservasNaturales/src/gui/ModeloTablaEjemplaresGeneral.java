/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import aplicacion.Ejemplar;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alumnogreibd
 */
public class ModeloTablaEjemplaresGeneral extends AbstractTableModel{
    private List<Ejemplar> ejemplares;

    public ModeloTablaEjemplaresGeneral() {
        this.ejemplares = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return ejemplares.size();
    }

    @Override
    public int getColumnCount() {
        return 5; // Nombre alimento
    }

    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0: return "Id";
            case 1: return "Especie";
            case 2: return "Mote";
            case 3: return "Fecha Nacimiento";
            case 4: return "Área Geográfica";
            default: return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 0:
                return Integer.class;
            case 1: case 2: case 3: case 4: 
                return String.class;
            
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false; // No editable
    }

    @Override
    public Object getValueAt(int row, int col) {
        Ejemplar ej = ejemplares.get(row);
        switch (col) {
            case 0: return ej.getId();
            case 1: return ej.getEspecie().getNombreCientifico();
            case 2: return ej.getMote();
            case 3: return ej.getFec_nac();
            case 4: return ej.getArea().getNombreReserva();
            default: return null;
        }
    }

    // Métodos auxiliares para manejar las filas
    public void setFilas(List<Ejemplar> ejemplares) {
        this.ejemplares = ejemplares;
        fireTableDataChanged();
    }

    public List<Ejemplar> getFilas() {
        return this.ejemplares;
    }

    public Ejemplar obtenerEjemplar(int i) {
        return this.ejemplares.get(i);
    }

    public Ejemplar getFila(int i) {
        return this.ejemplares.get(i);
    }
    
}
