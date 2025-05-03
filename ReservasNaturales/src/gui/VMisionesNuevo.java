/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package gui;

import aplicacion.Especie;
import aplicacion.FachadaAplicacion;
import aplicacion.Mision;
import aplicacion.Usuario;

import javax.swing.*;
import java.util.List;
import java.sql.Date;

/**
 *
 * @author jaime
 */
public class VMisionesNuevo extends javax.swing.JDialog {

    aplicacion.FachadaAplicacion fa;
    boolean valid = false, selected_especies = false, selected_area = false, selected_trabajador = false, descripted = false;
    List<Usuario> usuarios_disponibles;

    public VMisionesNuevo(java.awt.Frame parent, boolean modal, FachadaAplicacion fa) {
        super(parent, modal);
        initComponents();
        this.fa = fa;

        cbxEspecies.setModel(new javax.swing.DefaultComboBoxModel<>());
        cbxAreas.setModel(new javax.swing.DefaultComboBoxModel<>());
        cbxTrabajador.setModel(new javax.swing.DefaultComboBoxModel<>());

        cbxAreas.setEnabled(false);
        cbxTrabajador.setEnabled(false);
        bSeleccionarMasExp.setEnabled(false);

        setupComboBoxListeners();
        setupTextAreaListener();

        cargarDatosComboBoxes();
    }

    private void setupComboBoxListeners() {
        cbxEspecies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Object selectedItem = cbxEspecies.getSelectedItem();
                selected_especies = (selectedItem != null && !selectedItem.toString().isEmpty());

                cbxAreas.setEnabled(selected_especies);
                if (selected_especies) {
                    TODO:cargarAreas(selectedItem.toString());
                } else {
                    cbxAreas.removeAllItems();
                    selected_area = false;
                    cbxTrabajador.setEnabled(false);
                    bSeleccionarMasExp.setEnabled(false);
                    cbxTrabajador.removeAllItems();
                    selected_trabajador = false;
                }
                updateValidState();
            }
        });

        cbxAreas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Object selectedItem = cbxAreas.getSelectedItem();
                selected_area = (selectedItem != null && !selectedItem.toString().isEmpty());

                cbxTrabajador.setEnabled(selected_area);
                bSeleccionarMasExp.setEnabled(selected_area);
                if (selected_area) {
                    cargarTrabajadores(selectedItem.toString());
                } else {
                    cbxTrabajador.removeAllItems();
                    selected_trabajador = false;
                }
                updateValidState();
            }
        });

        cbxTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Object selectedItem = cbxTrabajador.getSelectedItem();
                selected_trabajador = (selectedItem != null && !selectedItem.toString().isEmpty());
                updateValidState();
            }
        });
    }

    private void setupTextAreaListener() {
        textDescripcion.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                updateDescriptionState();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                updateDescriptionState();
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                updateDescriptionState();
            }
        });
    }

    private void updateDescriptionState() {
        String text = textDescripcion.getText();
        descripted = (text != null && !text.trim().isEmpty());
        updateValidState();
    }

    private void updateValidState() {
        valid = selected_especies && selected_area && selected_trabajador && descripted;

        bAgregar.setEnabled(valid);
    }

    public void cargarDatosComboBoxes() {
        // Cargar especies en el primer combo box
        List<Especie> especies = fa.obtenerEspecies("");
        DefaultComboBoxModel<String> modeloEspecies = new DefaultComboBoxModel<>();
        modeloEspecies.addElement(""); // Opción vacía inicial
        for (Especie especie : especies) {
            modeloEspecies.addElement(especie.getNombreCientifico());
        }
        cbxEspecies.setModel(modeloEspecies);

        // El combo box de áreas y trabajadores se poblará cuando se seleccione una especie
        cbxAreas.removeAllItems();
        cbxTrabajador.removeAllItems();
    }

    private void cargarAreas(String nombreCientificoEspecie) {
        List<String> areas = fa.obtenerAreasPorEspecie(nombreCientificoEspecie);
        DefaultComboBoxModel<String> modeloAreas = new DefaultComboBoxModel<>();
        modeloAreas.addElement("");
        for (String area : areas) {
            modeloAreas.addElement(area);
        }
        cbxAreas.setModel(modeloAreas);
    }

    private void cargarTrabajadores(String area) {
        List<Usuario> trabajadores = fa.obtenerTrabajadoresPorArea(area);
        usuarios_disponibles = trabajadores;
        DefaultComboBoxModel<String> modeloTrabajadores = new DefaultComboBoxModel<>();
        modeloTrabajadores.addElement(""); // Opción vacía inicial
        for (Usuario trabajador : trabajadores) {
            modeloTrabajadores.addElement(trabajador.getNombre());
        }
        cbxTrabajador.setModel(modeloTrabajadores);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labEspecies = new javax.swing.JLabel();
        labArea = new javax.swing.JLabel();
        labTrabajador = new javax.swing.JLabel();
        cbxEspecies = new javax.swing.JComboBox<>();
        cbxAreas = new javax.swing.JComboBox<>();
        cbxTrabajador = new javax.swing.JComboBox<>();
        bSeleccionarMasExp = new javax.swing.JButton();
        bAgregar = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textDescripcion = new javax.swing.JTextArea();
        labDescripcion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labEspecies.setText("Especie");

        labArea.setText("Área");

        labTrabajador.setText("Trabajador");

        cbxEspecies.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbxAreas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbxTrabajador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        bSeleccionarMasExp.setText("Seleccionar Trabajador con Mayor Experiencia");
        bSeleccionarMasExp.setToolTipText("");
        bSeleccionarMasExp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSeleccionarMasExpActionPerformed(evt);
            }
        });

        bAgregar.setText("Crear");
        bAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAgregarActionPerformed(evt);
            }
        });

        bCancelar.setText("Cancelar");
        bCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelarActionPerformed(evt);
            }
        });

        textDescripcion.setColumns(20);
        textDescripcion.setRows(5);
        jScrollPane1.setViewportView(textDescripcion);

        labDescripcion.setText("Descripción");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labArea)
                                .addGap(18, 18, 18)
                                .addComponent(cbxAreas, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labEspecies)
                                .addGap(18, 18, 18)
                                .addComponent(cbxEspecies, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labTrabajador)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbxTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labDescripcion)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(bSeleccionarMasExp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labDescripcion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labEspecies)
                            .addComponent(cbxEspecies, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labArea)
                            .addComponent(cbxAreas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labTrabajador)
                            .addComponent(cbxTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bSeleccionarMasExp))))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bAgregar)
                    .addComponent(bCancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bSeleccionarMasExpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSeleccionarMasExpActionPerformed
        Usuario masExperto = fa.obtenerTrabajadorMasExperimentado(usuarios_disponibles);
        cbxTrabajador.setSelectedItem(masExperto.getNombre());
    }//GEN-LAST:event_bSeleccionarMasExpActionPerformed

    private void bAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAgregarActionPerformed
        Usuario trabajador = null;
        for (Usuario usuario : usuarios_disponibles) {
            if (usuario.getNombre().equals(cbxTrabajador.getSelectedItem())) {
                trabajador = usuario;
            }
        }

        java.sql.Date fechaInicio = new java.sql.Date(new java.util.Date().getTime());
        java.sql.Date fechaFin = null; // Se puede dejar como null si no se especifica
        String descripcion = textDescripcion.getText();

        Mision nuevaMision = new Mision(trabajador, cbxEspecies.getSelectedItem().toString(), fechaInicio, fechaFin, descripcion);
        fa.agregarNuevaMision(nuevaMision);
        this.dispose();
    }//GEN-LAST:event_bAgregarActionPerformed

    private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_bCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAgregar;
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bSeleccionarMasExp;
    private javax.swing.JComboBox<String> cbxAreas;
    private javax.swing.JComboBox<String> cbxEspecies;
    private javax.swing.JComboBox<String> cbxTrabajador;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labArea;
    private javax.swing.JLabel labDescripcion;
    private javax.swing.JLabel labEspecies;
    private javax.swing.JLabel labTrabajador;
    private javax.swing.JTextArea textDescripcion;
    // End of variables declaration//GEN-END:variables
}
