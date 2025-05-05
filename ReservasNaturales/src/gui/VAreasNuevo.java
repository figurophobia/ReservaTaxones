/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package gui;

import aplicacion.Area;
import aplicacion.FachadaAplicacion;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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

        inicializarEstadoInicial();
        inicializarListeners();

    }
    
    private void inicializarEstadoInicial() {
    Crearbtn.setEnabled(false);
}

    private void inicializarListeners() {
        DocumentListener listenerNombre = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                verificarCamposObligatorios();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                verificarCamposObligatorios();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                verificarCamposObligatorios();
            }
        };

        NombreTF.getDocument().addDocumentListener(listenerNombre);
    }

    private void verificarCamposObligatorios() {
        Crearbtn.setEnabled(!NombreTF.getText().trim().isEmpty());
    }

    private void habilitarBotonCrear() {
        Crearbtn.setEnabled(!NombreTF.getText().trim().isEmpty());
    }

    Area crearArea() {
        String nombre = NombreTF.getText().trim();
        double altitudAlta = parseDouble(AltMaxTF.getText());
        double altitudBaja = parseDouble(AltMinTF.getText());
        double extension = parseDouble(Extensiontf.getText());
        double profundidad = parseDouble(ProfundidadTF.getText());
        boolean acuatica = checkAcuatica.isSelected();
        boolean terrestre = checkTerrestre.isSelected();

        if (nombre.isEmpty()) {
            System.err.println("Es necesario poner un nombre.");
        }

        Area area;

        if (acuatica && terrestre) {
            area = new Area(nombre, extension, profundidad, altitudAlta, altitudBaja, true, true);
        } else if (acuatica) {
            area = new Area(nombre, extension, profundidad);
        } else if (terrestre) {
            area = new Area(nombre, extension, altitudBaja, altitudAlta);
        } else {
            area = new Area(nombre, extension, false, false);
        }

        return area;
    }

    // Método auxiliar para evitar repetir parseos y capturar excepciones
    private double parseDouble(String texto) {
        try {
            return Double.parseDouble(texto != null && !texto.isEmpty() ? texto : "0");
        } catch (NumberFormatException e) {
            System.err.println("Error al parsear número: " + e.getMessage());
            return 0;
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labNombre = new javax.swing.JLabel();
        NombreTF = new javax.swing.JTextField();
        labExtension = new javax.swing.JLabel();
        labProfundidad = new javax.swing.JLabel();
        Extensiontf = new javax.swing.JTextField();
        ProfundidadTF = new javax.swing.JTextField();
        labAltMax = new javax.swing.JLabel();
        labAltMin = new javax.swing.JLabel();
        AltMaxTF = new javax.swing.JTextField();
        AltMinTF = new javax.swing.JTextField();
        checkTerrestre = new javax.swing.JCheckBox();
        checkAcuatica = new javax.swing.JCheckBox();
        Salirbtn = new javax.swing.JButton();
        Crearbtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

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

        Salirbtn.setBackground(new java.awt.Color(231, 76, 60));
        Salirbtn.setText("Salir");
        Salirbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirbtnActionPerformed(evt);
            }
        });

        Crearbtn.setText("Crear");
        Crearbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearbtnActionPerformed(evt);
            }
        });

        jLabel1.setText("puede ser ambas");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labNombre)
                            .addComponent(labExtension)
                            .addComponent(labProfundidad)
                            .addComponent(Crearbtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Salirbtn))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(checkTerrestre)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(checkAcuatica)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel1))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(labAltMax)
                            .addGap(28, 28, 28)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Extensiontf, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                                .addComponent(ProfundidadTF, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                                .addComponent(NombreTF)
                                .addComponent(AltMaxTF))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labAltMin)
                        .addGap(31, 31, 31)
                        .addComponent(AltMinTF)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NombreTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labNombre))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Extensiontf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labExtension))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProfundidadTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labProfundidad))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labAltMax)
                    .addComponent(AltMaxTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labAltMin)
                    .addComponent(AltMinTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(checkTerrestre)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkAcuatica)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Crearbtn)
                    .addComponent(Salirbtn))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkTerrestreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkTerrestreActionPerformed

    }//GEN-LAST:event_checkTerrestreActionPerformed

    private void SalirbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirbtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_SalirbtnActionPerformed

    private void CrearbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearbtnActionPerformed
        Area area = crearArea();
        boolean resultado = fa.crearArea(area);
        if (resultado)
            this.dispose();
    }//GEN-LAST:event_CrearbtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AltMaxTF;
    private javax.swing.JTextField AltMinTF;
    private javax.swing.JButton Crearbtn;
    private javax.swing.JTextField Extensiontf;
    private javax.swing.JTextField NombreTF;
    private javax.swing.JTextField ProfundidadTF;
    private javax.swing.JButton Salirbtn;
    private javax.swing.JCheckBox checkAcuatica;
    private javax.swing.JCheckBox checkTerrestre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labAltMax;
    private javax.swing.JLabel labAltMin;
    private javax.swing.JLabel labExtension;
    private javax.swing.JLabel labNombre;
    private javax.swing.JLabel labProfundidad;
    // End of variables declaration//GEN-END:variables
}
