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
public class VAreasNuevo extends javax.swing.JDialog {


    FachadaAplicacion fa;
    private VPrincipal padre;

    public VAreasNuevo(java.awt.Frame parent, boolean modal, FachadaAplicacion fa) {
        super(parent, modal);
        initComponents();
        this.fa = fa;
        padre = (VPrincipal) parent;
        bCrear.setEnabled(false);

        javax.swing.event.DocumentListener documentListener = new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                habilitarBotonCrear();
            }
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                habilitarBotonCrear();
            }
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                habilitarBotonCrear();
            }
        };

        textoNombre.getDocument().addDocumentListener(documentListener);
    }

    private void habilitarBotonCrear() {
        bCrear.setEnabled(!textoNombre.getText().trim().isEmpty());
    }

    Area crearArea() {
        boolean acuatica = false;
        boolean terrestre = false;
        double altitudAlta = 0;
        double altitudBaja = 0;
        double extension = 0;
        double profundidad = 0;
        String nombre = "";

        Area area;

        try {
            nombre = textoNombre.getText();}
        catch (Exception e) {
            System.err.println("Es necesario poner un nombre: " + e.getMessage());
        }
        try {
            altitudAlta = Double.parseDouble(textoAltMax.getText()!= null ? textoAltMax.getText() : "0");}
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        try {
            altitudBaja = Double.parseDouble(textoAltMin.getText()!= null ? textoAltMin.getText() : "0");}
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        try {
            altitudBaja = Double.parseDouble(textoAltMin.getText()!= null ? textoAltMin.getText() : "0");}
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        try {
            altitudBaja = Double.parseDouble(textoAltMin.getText()!= null ? textoAltMin.getText() : "0");}
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        try {
            extension = Double.parseDouble(textoExtension.getText()!= null ? textoExtension.getText() : "0");}
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        try {
            profundidad = Double.parseDouble(textoProfundidad.getText()!= null ? textoProfundidad.getText() : "0");}
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        terrestre = checkTerrestre.isSelected();
        acuatica = checkAcuatica.isSelected();

        if (acuatica && terrestre) {
            area = new Area(nombre, extension, profundidad, altitudAlta, altitudBaja, acuatica, terrestre);
        } else if (acuatica) {
            area = new Area(nombre, extension, profundidad);
        } else if (terrestre) {
            area = new Area(nombre, extension, altitudBaja, altitudAlta);
        } else
            area = new Area(nombre, extension, acuatica, terrestre);

        return area;
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
        bSalir = new javax.swing.JButton();
        bCrear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labNombre.setText("Nombre");

        labExtension.setText("Extensión");

        labProfundidad.setText("Profundidad");

        labAltMax.setText("Altura Máxima");

        labAltMin.setText("Altura mínima");

        checkTerrestre.setText("Terrestre");
        checkTerrestre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkTerrestreActionPerformed(evt);
            }
        });

        checkAcuatica.setText("Acuática");

        bSalir.setText("Salir");
        bSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalirActionPerformed(evt);
            }
        });

        bCrear.setText("Crear");
        bCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCrearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(labNombre)
                        .addGap(18, 18, 18)
                        .addComponent(textoNombre))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labExtension)
                            .addComponent(labProfundidad))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textoExtension, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(textoProfundidad))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(labAltMin))
                            .addComponent(labAltMax))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textoAltMax)
                            .addComponent(textoAltMin, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkAcuatica)
                            .addComponent(checkTerrestre)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bCrear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bSalir)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labNombre))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textoExtension, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labExtension))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textoProfundidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labProfundidad)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(checkTerrestre)
                        .addGap(18, 18, 18)
                        .addComponent(checkAcuatica))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labAltMax)
                            .addComponent(textoAltMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labAltMin)
                            .addComponent(textoAltMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bCrear)
                    .addComponent(bSalir))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkTerrestreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkTerrestreActionPerformed

    }//GEN-LAST:event_checkTerrestreActionPerformed

    private void bSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_bSalirActionPerformed

    private void bCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCrearActionPerformed
        Area area = crearArea();
        boolean resultado = fa.crearArea(area);
        if (resultado)
            this.dispose();
    }//GEN-LAST:event_bCrearActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCrear;
    private javax.swing.JButton bSalir;
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
}
