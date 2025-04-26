package gui;
import  aplicacion.Usuario;
import aplicacion.Mision;
import aplicacion.Usuario;
import java.beans.beancontext.BeanContextMembershipEvent;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.ArrayList;

public class ModeloTablaMisiones extends AbstractTableModel {
    private List<Mision> misiones;
    private List<Usuario> usuarios;
    
    public ModeloTablaMisiones() {
        this.misiones = new ArrayList<Mision>();
        this.usuarios=new ArrayList<Usuario>();
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
        String nombre="";
        switch (col) {
            case 0: nombre="Trabajador";
            case 1 : nombre= "Especie";
            case 2: nombre= "Fecha Inicio";
            case 3: nombre= "Fecha Fin";
            case 4: nombre="Descripción";
            case 5:nombre= "Estado";
          
        }
        return nombre;
    }

    @Override
    public Class<?> getColumnClass(int col) {
         Class clase=null;

        switch (col){
            case 0: clase= java.lang.String.class; break;
            case 1: clase= java.lang.String.class; break;
            case 2: clase=java.lang.String.class; break;
            case 3: clase=java.lang.String.class; break;
            case 4: clase=java.lang.String.class; break;
            case 5: clase=java.lang.String.class; break;
            
        }
        return clase;
        
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return true;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Mision m = misiones.get(row);
        Usuario u=usuarios.get(col);
       Object resultado=null;
        switch (col) {
            case 0:resultado= m.getTrabajador().getNombre();
            case 1:resultado=m.getEspecie();
            case 2: resultado=m.getFechaInicio();
            case 3: resultado=m.getFechaFin();
            case 4:resultado= m.getDescripcion();
            case 5: resultado=m.estaCompletada();
           
        }
        return resultado
        ;
    }

    @Override
   public void setValueAt(Object aValue, int row, int col) {
    Mision m = misiones.get(row);
    
    if (col == 0) {
        // Solo se permite editar el nombre del trabajador, no cambiar el Usuario entero
        m.getTrabajador().setNombre((String) aValue);
    } else if (col == 1) {
        m.setEspecie((String) aValue);
    } else if (col == 2) {
        m.setFechaInicio((String) aValue);
    } else if (col == 3) {
        m.setFechaFin((String) aValue);
    } else if (col == 4) {
        m.setDescripcion((String) aValue);
    } else if (col == 5) {
        m.setCompletada(Boolean.parseBoolean(aValue.toString()));
    }

    fireTableCellUpdated(row, col);
}

    public void setFilas(List<Mision> misiones) {
        this.misiones = misiones;
        fireTableDataChanged();
    }

    public List<Mision> getFilas() {
        return this.misiones;
    }

    public Mision obtenerMision(int i) {
        return this.misiones.get(i);
    }

    public void anhadeFila(Mision nueva) {
        this.misiones.add(nueva);
        fireTableRowsInserted(misiones.size() - 1, misiones.size() - 1);
    }

    public Mision getFila(int i) {
        return this.misiones.get(i);
    }
}
