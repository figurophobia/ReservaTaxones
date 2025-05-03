package gui;

import aplicacion.Revision;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModeloTablaRevisiones extends AbstractTableModel {
    private List<Revision> revisiones;

    public ModeloTablaRevisiones() {
        this.revisiones = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return revisiones.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int col) {
        String nombre = "";
        switch (col) {
            case 0: nombre = "Clínica"; break;
            case 1: nombre = "Ejemplar"; break;
            case 2: nombre = "Especie Asociada"; break;
            case 3: nombre = "Fecha Revisión"; break;
           
        }
        return nombre;
    }

    @Override
    public Class<?> getColumnClass(int col) {
        Class clase = null;
        switch (col) {
            case 0: 
            case 2: 
            
            case 1: clase = Integer.class; break;
            case 3: clase = Date.class; break;
        }
        return clase;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return true;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Revision r = revisiones.get(row);
        Object resultado = null;
        switch (col) {
            case 0: resultado = r.getClinica(); break;
            case 1: resultado = r.getEjemplar(); break;
            case 2: resultado = r.getEspecieAsociada(); break;
            case 3: resultado = r.getFechaRevision(); break;
            
        }
        return resultado;
    }

    @Override
    public void setValueAt(Object aValue, int row, int col) {
        Revision r = revisiones.get(row);
        switch (col) {
            case 0: r.setClinica((String) aValue); break;
            case 1: r.setEjemplar((Integer) aValue); break;
            case 2: r.setEspecieAsociada((String) aValue); break;
            case 3: r.setFechaRevision((Date) aValue); break;
          
        }
        fireTableCellUpdated(row, col);
    }

    public void setFilas(List<Revision> nuevasRevisiones) {
        this.revisiones = nuevasRevisiones;
        fireTableDataChanged();
    }

    public List<Revision> getFilas() {
        return this.revisiones;
    }

    public Revision obtenerRevision(int i) {
        return this.revisiones.get(i);
    }

    public void anhadeFila(Revision nuevaRevision) {
        this.revisiones.add(nuevaRevision);
        fireTableRowsInserted(revisiones.size() - 1, revisiones.size() - 1);
    }

    public Revision getFila(int i) {
        return this.revisiones.get(i);
    }
}
