/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;


import aplicacion.ConsumirAlimento;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alumnogreibd
 */
public class ModeloTablaConsumirAlimentos extends AbstractTableModel{
    private List<ConsumirAlimento> cons_alimentos;

    public ModeloTablaConsumirAlimentos() {
        this.cons_alimentos = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return cons_alimentos.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0: return "id_especie";
            case 1: return "nombre_especie";
            case 2: return "id_alimento";
            case 3: return "cantidad";
            case 4: return "frecuencia";
            default: return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 0: case 2: case 3: case 4: return Integer.class;
            case 1: return String.class;
        }
        return String.class;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false; // No editable
    }

    @Override
    public Object getValueAt(int row, int col) {
        ConsumirAlimento ca = cons_alimentos.get(row);
        switch (col) {
            case 0: return ca.getEjemplar().getId();
            case 1: return ca.getEjemplar().getEspecie().getNombreCientifico();
            case 2: return ca.getAlimento().getId();
            case 3: return ca.getCantidad();
            case 4: return ca.getFrecuencia();
            default: return null;
        }
    }

    // Métodos auxiliares para manejar las filas
    public void setFilas(List<ConsumirAlimento> cons_alimentos) {
        this.cons_alimentos = cons_alimentos;
        fireTableDataChanged();
    }

    public List<ConsumirAlimento> getFilas() {
        return this.cons_alimentos;
    }

    public ConsumirAlimento obtenerConsAlimento(int i) {
        return this.cons_alimentos.get(i);
    }

    public ConsumirAlimento getFila(int i) {
        return this.cons_alimentos.get(i);
    }
    
}
