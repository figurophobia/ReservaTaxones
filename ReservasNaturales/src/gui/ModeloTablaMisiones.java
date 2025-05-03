package gui;

import aplicacion.Mision;
import aplicacion.Usuario;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class ModeloTablaMisiones extends AbstractTableModel {
    private List<Mision> misiones;

    public ModeloTablaMisiones() {
        this.misiones = new ArrayList<Mision>();
    }

    @Override
    public int getRowCount() {
        return misiones.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int col) {
        String nombre = "";
        switch (col) {
            case 0:
                nombre = "Trabajador";
                break;
            case 1:
                nombre = "Especie";
                break;
            case 2:
                nombre = "Fecha Inicio";
                break;
            case 3:
                nombre = "Fecha Fin";
                break;
            case 4:
                nombre = "Descripción";
                break;
            case 5:
                nombre = "Completada";
                break;
        }
        return nombre;
    }

    @Override
    public Class<?> getColumnClass(int col) {
        if (col == 0 || col == 1 || col == 4 || col == 5) {
            return String.class;
        } else if (col == 2 || col == 3) {
            return Date.class;
        }
        return Object.class;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return true;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Mision m = misiones.get(row);
        switch (col) {
            case 0:
                return m.getTrabajador().getNombre();
            case 1:
                return m.getEspecie();
            case 2:
                return m.getFechaInicio(); // Date
            case 3:
                return m.getFechaFin(); // Date
            case 4:
                return m.getDescripcion();
            case 5:
                return m.getEstado(); // El valor devuelto es un String ("Completada" o "Incompleta")
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int row, int col) {
        Mision m = misiones.get(row);

        switch (col) {
            case 0:
                m.getTrabajador().setNombre((String) aValue);
                break;
            case 1:
                m.setEspecie((String) aValue);
                break;
            case 2:
                if (aValue instanceof Date) {
                    m.setFechaInicio(new java.sql.Date(((Date) aValue).getTime()));
                }
                break;
            case 3:
                if (aValue instanceof Date) {
                    m.setFechaFin(new java.sql.Date(((Date) aValue).getTime()));
                }
                break;
            case 4:
                m.setDescripcion((String) aValue);
                break;
            case 5:
                if (aValue instanceof Boolean) {
                    m.setCompletada((Boolean) aValue); // Cambié el tipo a Boolean
                }
                break;
        }
        fireTableCellUpdated(row, col);
    }

    // --- Setters y Getters adicionales ---

    public List<Mision> getMisiones() {
        return misiones;
    }

    public void setMisiones(List<Mision> misiones) {
        this.misiones = misiones;
        fireTableDataChanged();
    }

    public void setFilas(List<Mision> misiones) {
        setMisiones(misiones);
    }

    public List<Mision> getFilas() {
        return getMisiones();
    }

    public Mision getMision(int index) {
        if (index >= 0 && index < misiones.size()) {
            return misiones.get(index);
        }
        return null;
    }

    public void setMision(int index, Mision mision) {
        if (index >= 0 && index < misiones.size()) {
            misiones.set(index, mision);
            fireTableRowsUpdated(index, index);
        }
    }

    public void anhadeFila(Mision nueva) {
        this.misiones.add(nueva);
        fireTableRowsInserted(misiones.size() - 1, misiones.size() - 1);
    }

    public void eliminarFila(int index) {
        if (index >= 0 && index < misiones.size()) {
            this.misiones.remove(index);
            fireTableRowsDeleted(index, index);
        }
    }

    public Mision obtenerMision(int i) {
        return getMision(i);
    }

    public Mision getFila(int i) {
        return getMision(i);
    }
}
