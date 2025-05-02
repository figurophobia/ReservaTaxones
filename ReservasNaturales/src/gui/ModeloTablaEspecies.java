package gui;

import aplicacion.Especie;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.ArrayList;

public class ModeloTablaEspecies extends AbstractTableModel {
    private List<Especie> especies;

    public ModeloTablaEspecies() {
        this.especies = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return especies.size();
    }

    @Override
    public int getColumnCount() {
        return 4; // Nombre científico, nombre común, descripción, área, taxón
    }

    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0: return "Nombre Científico";
            case 1: return "Nombre Común";
            case 2: return "Descripción";
            case 3: return "Taxón";
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
        Especie e = especies.get(row);
        switch (col) {
            case 0: return e.getNombreCientifico();
            case 1: return e.getNombreComun();
            case 2: return e.getDescripcion();
            case 3: return e.getTaxon().getNombre();
            default: return null;
        }
    }

    // Métodos auxiliares para manejar las filas
    public void setFilas(List<Especie> especies) {
        this.especies = especies;
        fireTableDataChanged();
    }

    public List<Especie> getFilas() {
        return this.especies;
    }

    public Especie obtenerEspecie(int i) {
        return this.especies.get(i);
    }

    public void anhadeFila(Especie nueva) {
        this.especies.add(nueva);
        fireTableRowsInserted(especies.size() - 1, especies.size() - 1);
    }

    public Especie getFila(int i) {
        return this.especies.get(i);
    }
}
