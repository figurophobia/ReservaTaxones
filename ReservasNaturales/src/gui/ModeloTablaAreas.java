package gui;

import java.util.ArrayList;
import java.util.List;
import aplicacion.Area;
import javax.swing.table.AbstractTableModel;

public class ModeloTablaAreas extends AbstractTableModel{
    private ArrayList<Area> areas;


    public ModeloTablaAreas() {
        this.areas = new ArrayList<>();
    }

    public int getRowCount() {
        return areas.size();
    }


    public int getColumnCount() {
        return 7; // Nombre científico, nombre común, descripción, área, taxón
    }


    public String getColumnName(int col) {
        switch (col) {
            case 0: return "Nombre";
            case 1: return "Extensión";
            case 2: return "Altitud mínima";
            case 3: return "Altitud máxima";
            case 4: return "Profundidad";
            case 5: return "Acuática";
            case 6: return "Terrestre";
            default: return "";
        }
    }


    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 0: return String.class;
            case 1: return int.class;
            case 2: return int.class;
            case 3: return int.class;
            case 4: return int.class;
            case 5: return boolean.class;
            case 6: return boolean.class;
            default: return null;
        }
    }


    public boolean isCellEditable(int row, int col) {
        return false;
    }


    public Object getValueAt(int row, int col) {
        Area a = areas.get(row);
        switch (col) {
            case 0: return a.getNombreReserva();
            case 1: return (int)a.getExtension() + " km²";
            case 2: return a.getAltitudBaja().intValue() + " m";
            case 3: return a.getAltitudAlta().intValue() + " m";
            case 4: return a.getProfundidad().intValue() + " m";
            case 5: return a.isAcuatica() ? "Sí" : "No";
            case 6: return a.isTerrestre() ? "Sí" : "No";
            default: return null;
        }
    }


    public void setFilas(ArrayList<Area> areas) {
        this.areas = areas;
        fireTableDataChanged();
    }

    public List<Area> getFilas() {
        return this.areas;
    }

    public Area obtenerEspecie(int i) {
        return this.areas.get(i);
    }

    public void anhadeFila(Area nueva) {
        this.areas.add(nueva);
        fireTableRowsInserted(areas.size() - 1, areas.size() - 1);
    }

    public Area getFila(int i) {
        return this.areas.get(i);
    }
}
