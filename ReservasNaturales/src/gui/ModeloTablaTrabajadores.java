/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;
import aplicacion.Area;
import  aplicacion.Usuario;
import javax.swing.table.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author alumnogreibd
 */
public class ModeloTablaTrabajadores extends AbstractTableModel {
    private  List<Usuario> usuarios;
    private boolean crearTrabajador = false;

    public ModeloTablaTrabajadores() {
        this.usuarios= new ArrayList<Usuario>();
     }
    public void setCrearTrabajador(boolean crear) {
    this.crearTrabajador = crear;
}

public boolean isCrearTrabajador() {
    return this.crearTrabajador;
}
    
    @Override
    public int getRowCount() {
        return usuarios.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }
    @Override
    public String getColumnName(int col){
        String nombre="";

        switch (col){
            case 0: nombre= "DNI"; break;
            case 1: nombre= "Nombre"; break;
            case 2: nombre="Horas"; break;
            case 3: nombre="Sueldo"; break;
            case 4: nombre="Area Geográfica";break;
        }
        return nombre;
    }

    @Override
    public Class getColumnClass(int col){
        Class clase=null;

        switch (col){
            case 0: clase= java.lang.String.class; break;
            case 1: clase= java.lang.String.class; break;
            case 2: clase=java.lang.Integer.class; break;
            case 3: clase=java.lang.Float.class; break;
            case 4: clase= java.lang.String.class;break;
            
        }
        return clase;
    }

    

    @Override
    public Object getValueAt(int row, int col) {
        Object resultado=null;

        switch (col){
            case 0: resultado= usuarios.get(row).getDni(); break;
            case 1: resultado= usuarios.get(row).getNombre(); break;
            case 2: resultado=usuarios.get(row).getHoras();break;
            case 3: resultado=usuarios.get(row).getSueldo(); break;
            case 4: resultado=usuarios.get(row).getArea();break;
            
        }
        return resultado;
    }
    @Override
public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    Usuario t = usuarios.get(rowIndex); // tu lista interna
    switch (columnIndex) {
        case 0: t.setDni((String) aValue); break;
        case 1: t.setNombre((String) aValue); break;
        case 2: t.setHoras((int) aValue); break;
        case 3: t.setSueldo((float) aValue); break;
        case 4: t.setArea((Area) aValue);break;
        
    }
    fireTableCellUpdated(rowIndex, columnIndex);
}
    public void setFilas(java.util.List<Usuario> usuario){
        this.usuarios=usuario;
        fireTableDataChanged();
    }
    public List<Usuario> getFilas() {
    return this.usuarios;
    }

    

    public Usuario obtenerUsuario(int i){
        return this.usuarios.get(i);
    }

    void anhadeFila(Usuario nuevoUsuario) {
    this.usuarios.add(nuevoUsuario);
    fireTableRowsInserted(usuarios.size() - 1, usuarios.size() - 1);    }

    public Usuario getFila(int i) {
        return this.usuarios.get(i);
        
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    if (!crearTrabajador) {
        
        return columnIndex != 0 && columnIndex != 4;
    } else {
        
        return true;
    }
}
    
    
}
