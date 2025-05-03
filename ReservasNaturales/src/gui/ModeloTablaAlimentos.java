/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import aplicacion.Alimento;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alumnogreibd
 */
public class ModeloTablaAlimentos extends AbstractTableModel {
 private List<Alimento> alimentos;

    public ModeloTablaAlimentos() {
        this.alimentos = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return alimentos.size();
    }

    @Override
    public int getColumnCount() {
        return 3; // Nombre alimento
    }

    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0: return "Nombre";
            case 1: return "Tipo";
            case 2: return "Distribuidor";
            default: return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int col) {
        return String.class; // Todas las columnas las tratamos como String
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false; // No editable
    }

    @Override
    public Object getValueAt(int row, int col) {
        Alimento a = alimentos.get(row);
        switch (col) {
            case 0: return a.getNombre();
            case 1: return a.getTipo();
            case 2: return a.getDistribuidor();
            default: return null;
        }
    }

    // Métodos auxiliares para manejar las filas
    public void setFilas(List<Alimento> alimentos) {
        this.alimentos = alimentos;
        fireTableDataChanged();
    }

    public List<Alimento> getFilas() {
        return this.alimentos;
    }

    public Alimento obtenerEspecie(int i) {
        return this.alimentos.get(i);
    }

    public Alimento getFila(int i) {
        return this.alimentos.get(i);
    }
    
}
