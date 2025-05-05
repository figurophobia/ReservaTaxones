/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package gui;

import aplicacion.Area;
import aplicacion.FachadaAplicacion;

/**
 *
 * @author jaime
 */
public class VAreasEditar extends javax.swing.JDialog {

    FachadaAplicacion fa;
    private VPrincipal padre;
    private Area area;

    public VAreasEditar(java.awt.Frame parent, boolean modal, FachadaAplicacion fa, Area area) {
        super(parent, modal);
        this.fa = fa;
        initComponents();
        padre = (VPrincipal) parent;
        this.area = area;
        guardarbtn.setEnabled(false);
        refreshTF(area);
        añadirListenersTexto();
        añadirListenersCheckbox();
    }

    private void añadirListenersTexto() {
        javax.swing.event.DocumentListener listener = new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { enableSaveButton(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { enableSaveButton(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { enableSaveButton(); }
        };

        extensionTF.getDocument().addDocumentListener(listener);
        profundidadTF.getDocument().addDocumentListener(listener);
        AltMaxTF.getDocument().addDocumentListener(listener);
        AltMinTF.getDocument().addDocumentListener(listener);
    }

    private void añadirListenersCheckbox() {
        java.awt.event.ItemListener listener = e -> {
            updateArea(area);
            enableSaveButton();
        };

        checkTerrestre.addItemListener(listener);
        checkAcuatica.addItemListener(listener);
    }

    private void enableSaveButton() {
        guardarbtn.setEnabled(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labNombre = new javax.swing.JLabel();
        nombreTF = new javax.swing.JTextField();
        labExtension = new javax.swing.JLabel();
        labProfundidad = new javax.swing.JLabel();
        extensionTF = new javax.swing.JTextField();
        profundidadTF = new javax.swing.JTextField();
        labAltMax = new javax.swing.JLabel();
        labAltMin = new javax.swing.JLabel();
        AltMaxTF = new javax.swing.JTextField();
        AltMinTF = new javax.swing.JTextField();
        checkTerrestre = new javax.swing.JCheckBox();
        checkAcuatica = new javax.swing.JCheckBox();
        guardarbtn = new javax.swing.JButton();
        cancelarbtm = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labNombre.setText("Nombre");

        nombreTF.setEnabled(false);

        labExtension.setText("Extensión");

        labProfundidad.setText("Profundidad");

        labAltMax.setText("Altitud Máxima");

        labAltMin.setText("Altitud Mínima");

        checkTerrestre.setText("Terrestre");

        checkAcuatica.setText("Acuática");

        guardarbtn.setBackground(new java.awt.Color(30, 132, 73));
        guardarbtn.setText("Guardar");
        guardarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarbtnActionPerformed(evt);
            }
        });

        cancelarbtm.setBackground(new java.awt.Color(231, 76, 60));
        cancelarbtm.setText("Cancelar");
        cancelarbtm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarbtmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labNombre)
                        .addGap(42, 42, 42)
                        .addComponent(nombreTF))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(guardarbtn)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labExtension)
                                        .addGap(34, 34, 34)
                                        .addComponent(extensionTF, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(labAltMax))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labProfundidad)
                                        .addGap(18, 18, 18)
                                        .addComponent(profundidadTF, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(labAltMin)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(AltMaxTF, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(AltMinTF, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(checkTerrestre)
                                    .addComponent(checkAcuatica))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(cancelarbtm)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labNombre)
                    .addComponent(nombreTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labExtension)
                    .addComponent(extensionTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labAltMax)
                    .addComponent(AltMaxTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkTerrestre))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labProfundidad)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(profundidadTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labAltMin)
                        .addComponent(AltMinTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(checkAcuatica)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardarbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelarbtm, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarbtmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarbtmActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelarbtmActionPerformed

    private void guardarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarbtnActionPerformed
        saveArea(area);
    }//GEN-LAST:event_guardarbtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AltMaxTF;
    private javax.swing.JTextField AltMinTF;
    private javax.swing.JButton cancelarbtm;
    private javax.swing.JCheckBox checkAcuatica;
    private javax.swing.JCheckBox checkTerrestre;
    private javax.swing.JTextField extensionTF;
    private javax.swing.JButton guardarbtn;
    private javax.swing.JLabel labAltMax;
    private javax.swing.JLabel labAltMin;
    private javax.swing.JLabel labExtension;
    private javax.swing.JLabel labNombre;
    private javax.swing.JLabel labProfundidad;
    private javax.swing.JTextField nombreTF;
    private javax.swing.JTextField profundidadTF;
    // End of variables declaration//GEN-END:variables

    void refreshTF(Area area) {
        checkAcuatica.setSelected(area.isAcuatica());
        checkTerrestre.setSelected(area.isTerrestre());
        AltMaxTF.setText(area.getAltitudAlta().toString());
        AltMinTF.setText(area.getAltitudBaja().toString());
        String extension = Double.valueOf(area.getExtension()).toString();
        extensionTF.setText(extension);
        profundidadTF.setText(area.getProfundidad().toString());
        nombreTF.setText(area.getNombreReserva());
    }

    void updateArea(Area area) {
        if (area == null) {
            System.err.println("Error: el área proporcionada es nula.");
            return;
        }

        area.setAcuatica(checkAcuatica.isSelected());
        area.setTerrestre(checkTerrestre.isSelected());

        try {
            area.setAltitudAlta(Double.parseDouble(AltMaxTF.getText().trim()));
            area.setAltitudBaja(Double.parseDouble(AltMinTF.getText().trim()));
            area.setExtension(Double.parseDouble(extensionTF.getText().trim()));
            area.setProfundidad(Double.parseDouble(profundidadTF.getText().trim()));
        } catch (NumberFormatException e) {
            System.err.println("Error al convertir valores numéricos: " + e.getMessage());
        }
    }

    void saveArea(Area area) {
        if (area == null) {
            System.err.println("Error: área es nula");
            return;
        }

        updateArea(area);

        if (fa.actualizarArea(area)) {
            this.dispose();
        } else {
            System.err.println("Error al guardar el área en la base de datos.");
        }
    }

}

