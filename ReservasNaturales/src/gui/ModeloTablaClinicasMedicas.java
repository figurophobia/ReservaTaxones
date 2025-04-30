package gui;

import aplicacion.ClinicaMedica;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.ArrayList;

public class ModeloTablaClinicasMedicas extends AbstractTableModel {
    private List<ClinicaMedica> clinicas;

    public ModeloTablaClinicasMedicas() {
        this.clinicas = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return clinicas.size();
    }

    @Override
    public int getColumnCount() {
        return 3; // nombre, ubicacion, num_empleados
    }

    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0: return "Nombre";
            case 1: return "Ubicación";
            case 2: return "Nº Empleados";
            default: return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int col) {
        return (col == 2) ? Integer.class : String.class;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false; // Tabla no editable
    }

    @Override
    public Object getValueAt(int row, int col) {
        ClinicaMedica c = clinicas.get(row);
        switch (col) {
            case 0: return c.getNombre();
            case 1: return c.getUbicacion();
            case 2: return c.getNumEmpleados();
            default: return null;
        }
    }

    // Métodos auxiliares
    public void setFilas(List<ClinicaMedica> clinicas) {
        this.clinicas = clinicas;
        fireTableDataChanged();
    }

    public List<ClinicaMedica> getFilas() {
        return this.clinicas;
    }

    public ClinicaMedica obtenerClinica(int i) {
        return this.clinicas.get(i);
    }

    public void anhadeFila(ClinicaMedica nueva) {
        this.clinicas.add(nueva);
        fireTableRowsInserted(clinicas.size() - 1, clinicas.size() - 1);
    }

    public ClinicaMedica getFila(int i) {
        return this.clinicas.get(i);
    }
}
