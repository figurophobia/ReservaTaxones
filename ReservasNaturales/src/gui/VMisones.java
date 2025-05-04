/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package gui;
import aplicacion.Especie;
import aplicacion.Mision;
import aplicacion.Usuario;
import java.awt.Frame;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
/**
 *
 * @author alumnogreibd
 */
public class VMisones extends javax.swing.JDialog {

    aplicacion.FachadaAplicacion fa;
    java.awt.Frame parent;

    int modo_busqueda = 0; // 0: ignorar 1: solo devolver incompletas 2: solo devolver completas

    public VMisones(java.awt.Frame parent, boolean modal,aplicacion.FachadaAplicacion fa) {
        super(parent, modal);
        this.parent=parent;
        this.fa=fa;
        initComponents();
        TablaMisiones.setModel(new ModeloTablaMisiones());
        configurarComboBoxes();



    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buscarTextFieldConfig = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        checkBoxTrabajador = new javax.swing.JCheckBox();
        checkBoxEspecie = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaMisiones = new javax.swing.JTable();
        btnNueva = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnSarlir = new javax.swing.JButton();
        btnCompletada = new javax.swing.JButton();
        checkIncompletas = new javax.swing.JCheckBox();
        checkCompletas = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Misiones");

        buscarTextFieldConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarTextFieldConfigActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        checkBoxTrabajador.setText("Trabajador");
        checkBoxTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxTrabajadorActionPerformed(evt);
            }
        });

        checkBoxEspecie.setText("Especie");

        TablaMisiones.setModel(new ModeloTablaMisiones());
        jScrollPane1.setViewportView(TablaMisiones);

        btnNueva.setText("Nueva Misión");
        btnNueva.setToolTipText("");
        btnNueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaActionPerformed(evt);
            }
        });

        btnEliminar.setText("Borrar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnSarlir.setText("Salir");
        btnSarlir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSarlirActionPerformed(evt);
            }
        });

        btnCompletada.setText("Completada");
        btnCompletada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompletadaActionPerformed(evt);
            }
        });

        checkIncompletas.setText("Solo mostrar incompletas");
        checkIncompletas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkIncompletasActionPerformed(evt);
            }
        });

        checkCompletas.setText("Solo mostrar completadas");
        checkCompletas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCompletasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(checkBoxTrabajador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkBoxEspecie)
                        .addGap(18, 18, 18)
                        .addComponent(checkIncompletas)
                        .addGap(18, 18, 18)
                        .addComponent(checkCompletas)
                        .addGap(0, 262, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buscarTextFieldConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnNueva)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCompletada)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSarlir)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buscarTextFieldConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkBoxTrabajador)
                    .addComponent(checkBoxEspecie)
                    .addComponent(checkIncompletas)
                    .addComponent(checkCompletas))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNueva)
                    .addComponent(btnEliminar)
                    .addComponent(btnCompletada)
                    .addComponent(btnSarlir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarTextFieldConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarTextFieldConfigActionPerformed
    }//GEN-LAST:event_buscarTextFieldConfigActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarMisiones();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void checkBoxTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxTrabajadorActionPerformed
    }//GEN-LAST:event_checkBoxTrabajadorActionPerformed

    private void btnSarlirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSarlirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSarlirActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int selectedRow=TablaMisiones.getSelectedRow();
        if (selectedRow!=-1) {
            ModeloTablaMisiones mm=(ModeloTablaMisiones) TablaMisiones.getModel();
            Mision seleccionado=mm.getFila(selectedRow);
            int confirm = javax.swing.JOptionPane.showConfirmDialog(this, 
            "¿Estás seguro de que deseas eliminar esta mision?",
            "Confirmar eliminación", 
            javax.swing.JOptionPane.YES_NO_OPTION);

            if (confirm == javax.swing.JOptionPane.YES_OPTION) {

                fa.eliminarMision(seleccionado);
                mm.setFilas(fa.obtenerMisiones());

                VAviso aviso = new VAviso((Frame)getParent(), true, "Mision eliminada correctamente.");
                aviso.setVisible(true);
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Debes seleccionar un trabajador para eliminar.",
                "Error", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
        
    }//GEN-LAST:event_btnEliminarActionPerformed
    }
    private void btnCompletadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompletadaActionPerformed
        int filaSeleccionada = TablaMisiones.getSelectedRow();

        if (filaSeleccionada != -1) {
            ModeloTablaMisiones modelo = (ModeloTablaMisiones) TablaMisiones.getModel();
            Mision misionSeleccionada = modelo.getMision(filaSeleccionada); // método que devuelve la misión de esa fila

            if (misionSeleccionada.getFechaFin() == null) {
                fa.completarMision(misionSeleccionada);
                JOptionPane.showMessageDialog(this, "Misión marcada como completada.");
            } else {
                JOptionPane.showMessageDialog(this, "La misión ya estaba completada.");
            }

            // Opcional: actualiza la vista si hace falta
            modelo.fireTableRowsUpdated(filaSeleccionada, filaSeleccionada);
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona una misión para marcar como completada.");
        }
    }//GEN-LAST:event_btnCompletadaActionPerformed

    private void btnNuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaActionPerformed
        int numeroMisionesAntes = fa.contarMisionesActivas();
        VMisionesNuevo vmn = new VMisionesNuevo(this.parent, true, fa);
        vmn.setVisible(true);

        int numeroMisionesActivas = fa.contarMisionesActivas();
        System.out.println("Numero de misiones activas: " + numeroMisionesActivas);
        if (numeroMisionesActivas > 5 && numeroMisionesAntes!=numeroMisionesActivas) {
            Mision misionMasAntigua = fa.obtenerMisionMasAntigua();
            int respuesta = JOptionPane.showConfirmDialog(this, "Tienes más de 5 misiones activas, ¿deseas completar la misión más antigua ("
                    + misionMasAntigua.getFechaInicio() + ") ?", "Completar Misión", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                Mision nuevaMision = misionMasAntigua.clone();
                nuevaMision.setFechaFin(Date.valueOf(LocalDate.now()));
                fa.actualizarMision(misionMasAntigua, nuevaMision);
                JOptionPane.showMessageDialog(this, "La misión más antigua ha sido establecida como completada.");
            }
        }
        // Actualizar la tabla después de agregar una nueva misión
        buscarMisiones();
    }//GEN-LAST:event_btnNuevaActionPerformed

    private void checkIncompletasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkIncompletasActionPerformed
        if (checkIncompletas.isSelected()) {
            // Set modo_busqueda to 1 (solo devolver incompletas)
            modo_busqueda = 1;
            // Uncheck checkCompletas if it's selected
            if (checkCompletas.isSelected()) {
                checkCompletas.setSelected(false);
            }
        } else {
            // If unchecked, reset modo_busqueda to 0 (ignorar)
            modo_busqueda = 0;
        }
        // Actualizar la tabla basada en el nuevo modo de búsqueda
        actualizarTablaSegunFiltros();

    }//GEN-LAST:event_checkIncompletasActionPerformed

    private void checkCompletasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkCompletasActionPerformed
        if (checkCompletas.isSelected()) {
            // Set modo_busqueda to 2 (solo devolver completas)
            modo_busqueda = 2;
            // Uncheck checkIncompletas if it's selected
            if (checkIncompletas.isSelected()) {
                checkIncompletas.setSelected(false);
            }
        } else {
            // If unchecked, reset modo_busqueda to 0 (ignorar)
            modo_busqueda = 0;
        }
        // Actualizar la tabla basada en el nuevo modo de búsqueda
        actualizarTablaSegunFiltros();
    }//GEN-LAST:event_checkCompletasActionPerformed

    private void actualizarTablaSegunFiltros() {
        ModeloTablaMisiones mm = (ModeloTablaMisiones) TablaMisiones.getModel();
        List<Mision> todasLasMisiones = fa.obtenerMisiones();

        if (modo_busqueda == 0) {
            // Mostrar todas las misiones
            mm.setFilas(todasLasMisiones);
        } else if (modo_busqueda == 1) {
            // Filtrar misiones incompletas (fechaFin == null)
            List<Mision> misionesIncompletas = new ArrayList<>();
            for (Mision m : todasLasMisiones) {
                if (m.getFechaFin() == null) {
                    misionesIncompletas.add(m);
                }
            }
            mm.setFilas(misionesIncompletas);
        } else if (modo_busqueda == 2) {
            // Filtrar misiones completas (fechaFin != null)
            List<Mision> misionesCompletas = new ArrayList<>();
            for (Mision m : todasLasMisiones) {
                if (m.getFechaFin() != null) {
                    misionesCompletas.add(m);
                }
            }
            mm.setFilas(misionesCompletas);
        }
    }


    private boolean hasMisionChanged(Mision misionOriginal, Mision misionActual) {
        boolean nombresDistintos = !misionOriginal.getTrabajador().getNombre().equals(misionActual.getTrabajador().getNombre());
        boolean especieDistinta = !misionOriginal.getEspecie().equals(misionActual.getEspecie());
        boolean descripcionDistinta = !misionOriginal.getDescripcion().equals(misionActual.getDescripcion());
        boolean fechaInicioDistinta = !misionOriginal.getFechaInicio().equals(misionActual.getFechaInicio());

        boolean fechaFinDistinta = false;
        if (misionOriginal.getFechaFin() == null && misionActual.getFechaFin() != null) {
            fechaFinDistinta = true;
        } else if (misionOriginal.getFechaFin() != null && !misionOriginal.getFechaFin().equals(misionActual.getFechaFin())) {
            fechaFinDistinta = true;
        }

        return nombresDistintos || especieDistinta || descripcionDistinta || fechaInicioDistinta || fechaFinDistinta;
    }

    
        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaMisiones;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCompletada;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNueva;
    private javax.swing.JButton btnSarlir;
    private javax.swing.JTextField buscarTextFieldConfig;
    private javax.swing.JCheckBox checkBoxEspecie;
    private javax.swing.JCheckBox checkBoxTrabajador;
    private javax.swing.JCheckBox checkCompletas;
    private javax.swing.JCheckBox checkIncompletas;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private void buscarMisiones() {
        ModeloTablaMisiones mm;
        mm=(ModeloTablaMisiones) TablaMisiones.getModel();
        String textoBusqueda=buscarTextFieldConfig.getText().trim();

        if (textoBusqueda.isEmpty()) {
            mm.setFilas(fa.obtenerMisiones());
        }
        else if (checkBoxTrabajador.isSelected()) {
           mm.setFilas(fa.obtenerMisionesTrabajador(textoBusqueda));
        }else if (checkBoxEspecie.isSelected()) {
           mm.setFilas(fa.obtenerMisionesEspecie(textoBusqueda));
        }else{
           mm.setFilas(fa.obtenerMisionesGeneral(textoBusqueda));
        }
    }
    public void configurarComboBoxes(){
        
            // Suponiendo que ya tienes la lista de trabajadores y especies
    List<Usuario> listaTrabajadores = fa.obtenerTodosLosTrabajadores(); // Obtener trabajadores
    List<Especie> listaEspecies = fa.obtenerEspecies("");

    // Crear los combo boxes
    JComboBox<String> comboTrabajadores = new JComboBox<>();
    for (Usuario u : listaTrabajadores) {
        comboTrabajadores.addItem(u.getNombre());
    }

    JComboBox<String> comboEspecies = new JComboBox<>();
    for (Especie especie : listaEspecies) {
        comboEspecies.addItem(especie.getNombreCientifico());
    }

    // Asignar el editor del combo box a las columnas correspondientes
    TablaMisiones.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(comboTrabajadores));
    TablaMisiones.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(comboEspecies));

    }
}
