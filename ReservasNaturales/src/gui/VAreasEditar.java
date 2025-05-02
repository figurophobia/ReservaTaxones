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
        bGuardar.setEnabled(false);
        actualizarTextos(area);

        javax.swing.event.DocumentListener documentListener = new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                enableSaveButton();
            }
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                enableSaveButton();
            }
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                enableSaveButton();
            }
        };

        // Add document listeners to text fields
        textoExtension.getDocument().addDocumentListener(documentListener);
        textoProfundidad.getDocument().addDocumentListener(documentListener);
        textoAltMax.getDocument().addDocumentListener(documentListener);
        textoAltMin.getDocument().addDocumentListener(documentListener);

        // Add item listeners to checkboxes
        java.awt.event.ItemListener checkboxListener = new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent e) {
                actualizarArea(area);
                enableSaveButton();
            }
        };

        checkTerrestre.addItemListener(checkboxListener);
        checkAcuatica.addItemListener(checkboxListener);
    }

    private void enableSaveButton() {
        bGuardar.setEnabled(true);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labNombre = new javax.swing.JLabel();
        textoNombre = new javax.swing.JTextField();
        labExtension = new javax.swing.JLabel();
        labProfundidad = new javax.swing.JLabel();
        textoExtension = new javax.swing.JTextField();
        textoProfundidad = new javax.swing.JTextField();
        labAltMax = new javax.swing.JLabel();
        labAltMin = new javax.swing.JLabel();
        textoAltMax = new javax.swing.JTextField();
        textoAltMin = new javax.swing.JTextField();
        checkTerrestre = new javax.swing.JCheckBox();
        checkAcuatica = new javax.swing.JCheckBox();
        bGuardar = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labNombre.setText("Nombre");

        textoNombre.setEnabled(false);

        labExtension.setText("Extensión");

        labProfundidad.setText("Profundidad");

        labAltMax.setText("Altitud Máxima");

        labAltMin.setText("Altitud Mínima");

        checkTerrestre.setText("Terrestre");

        checkAcuatica.setText("Acuática");

        bGuardar.setText("Guardar");
        bGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGuardarActionPerformed(evt);
            }
        });

        bCancelar.setText("Cancelar");
        bCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelarActionPerformed(evt);
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
                        .addComponent(bGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(labProfundidad)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textoProfundidad, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(labExtension)
                                .addGap(18, 18, 18)
                                .addComponent(textoExtension, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labAltMax)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textoAltMax, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(checkTerrestre))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labAltMin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textoAltMin, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(checkAcuatica)))
                        .addGap(0, 19, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labNombre)
                        .addGap(18, 18, 18)
                        .addComponent(textoNombre)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labNombre)
                    .addComponent(textoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labExtension)
                    .addComponent(textoExtension, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labAltMax)
                    .addComponent(textoAltMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkTerrestre))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labProfundidad)
                    .addComponent(textoProfundidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labAltMin)
                    .addComponent(textoAltMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkAcuatica))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bGuardar)
                    .addComponent(bCancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_bCancelarActionPerformed

    private void bGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bGuardarActionPerformed
        guardar(area);
    }//GEN-LAST:event_bGuardarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bGuardar;
    private javax.swing.JCheckBox checkAcuatica;
    private javax.swing.JCheckBox checkTerrestre;
    private javax.swing.JLabel labAltMax;
    private javax.swing.JLabel labAltMin;
    private javax.swing.JLabel labExtension;
    private javax.swing.JLabel labNombre;
    private javax.swing.JLabel labProfundidad;
    private javax.swing.JTextField textoAltMax;
    private javax.swing.JTextField textoAltMin;
    private javax.swing.JTextField textoExtension;
    private javax.swing.JTextField textoNombre;
    private javax.swing.JTextField textoProfundidad;
    // End of variables declaration//GEN-END:variables

    void actualizarTextos(Area area) {
        checkAcuatica.setSelected(area.isAcuatica());
        checkTerrestre.setSelected(area.isTerrestre());
        textoAltMax.setText(area.getAltitudAlta().toString());
        textoAltMin.setText(area.getAltitudBaja().toString());
        String extension = Double.valueOf(area.getExtension()).toString();
        textoExtension.setText(extension);
        textoProfundidad.setText(area.getProfundidad().toString());
        textoNombre.setText(area.getNombreReserva());
    }

    void actualizarArea(Area area) {
        area.setAcuatica(checkAcuatica.isSelected());
        area.setTerrestre(checkTerrestre.isSelected());

        try {
            area.setAltitudAlta(Double.parseDouble(textoAltMax.getText()));
            area.setAltitudBaja(Double.parseDouble(textoAltMin.getText()));
            area.setExtension(Double.parseDouble(textoExtension.getText()));
            area.setProfundidad(Double.parseDouble(textoProfundidad.getText()));
            if(checkTerrestre.isSelected())
                area.setTerrestre(true);
            else
                area.setTerrestre(false);
            if(checkAcuatica.isSelected())
                area.setAcuatica(true);
            else
                area.setAcuatica(false);

        } catch (NumberFormatException e) {
            System.err.println("Error al convertir valores numéricos: " + e.getMessage());
        }
    }


    void guardar(Area area) {
        actualizarArea(area);
        boolean result = false;
        if (area != null)
            result = fa.actualizarArea(area);
        else
            System.out.println("Error: area es nula");

        if (result)
            this.dispose();
    }
}

