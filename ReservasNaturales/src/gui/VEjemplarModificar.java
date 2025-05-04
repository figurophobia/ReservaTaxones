/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package gui;

import aplicacion.Alimento;
import aplicacion.Area;
import aplicacion.ConsumirAlimento;
import aplicacion.Ejemplar;
import aplicacion.Especie;
import aplicacion.FachadaAplicacion;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;


public class VEjemplarModificar extends javax.swing.JDialog {


    FachadaAplicacion fa;
    private VPrincipal padre;
    Ejemplar ej;

    public VEjemplarModificar(java.awt.Frame parent, boolean modal, FachadaAplicacion fa, Ejemplar ej) {
        super(parent, modal);
        initComponents();
        this.fa = fa;
        this.ej = ej;
        padre = (VPrincipal) parent;
        setTitle("Modificar ejemplar");
        
        cargarAreas();
        cargarAlimentos();
        cargarAlimentosEjemplarEspecifico(ej.getId());
        
        tf_idModificarEjemplar.setText(String.valueOf(ej.getId()));
        tf_modificarNombreCient.setText(ej.getEspecie().getNombreCientifico());
        tf_novoMote.setText(ej.getMote());
        tf_novaFechaNac.setText(ej.getFec_nac());
        
       
        

        listenerCheckBoxCambiarAlimento();
    }
    
    private void listenerCheckBoxCambiarAlimento() {
        cb_cambiarAlimento.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
               if (ie.getStateChange() == ItemEvent.SELECTED) {
                   comboBox_alimentos.setEnabled(true);
                   comboBox_alimentoModificar.setEnabled(true);
               } else {
                   comboBox_alimentos.setEnabled(false);
                   comboBox_alimentoModificar.setEnabled(false);
               }
            }
        });
    }
    
    private void cargarAlimentos() {
        List<Alimento> alimentos = fa.obtenerAlimentos();

        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();

        for (Alimento a : alimentos) {
            // Formato : id - nombreAlimento - tipoAlimento
            modelo.addElement(String.valueOf(a.getId()) + "-" + a.getNombre() + "-" + a.getTipo()); 
        }

        comboBox_alimentos.setModel(modelo); // asignar el modelo al ComboBox
    }
    
     private void cargarAlimentosEjemplarEspecifico(int idEjemplar) {
        List<ConsumirAlimento> alimentos = fa.obterConsumirAlimentosEjemplar(idEjemplar);

        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();

        for (ConsumirAlimento a : alimentos) {
            // Formato : id - nombreAlimento - tipoAlimento
            modelo.addElement(a.getAlimento().getId() + "-" + a.getAlimento().getNombre() + "-" + a.getAlimento().getTipo()); 
        }

        comboBox_alimentoModificar.setModel(modelo); // asignar el modelo al ComboBox
    }
    
    private void cargarAreas() {
        
        List<Area> areas = fa.obtenerAreas(); // obtener la lista de áreas

        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();

        for (Area area : areas) {
            modelo.addElement(area.getNombreReserva()); // suponiendo que el área tiene un método getNombre()
        }

        comboBox_areas.setModel(modelo); // asignar el modelo al ComboBox
        
        for (int i = 0; i < comboBox_areas.getItemCount(); i++) {
            if (comboBox_areas.getItemAt(i).toString().equals(ej.getArea().getNombreReserva())) {
                comboBox_areas.setSelectedIndex(i);
                break;
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labNombre = new javax.swing.JLabel();
        tf_modificarNombreCient = new javax.swing.JTextField();
        tf_novoMote = new javax.swing.JTextField();
        bSalir = new javax.swing.JButton();
        btnAceptarCambiosEjemplar = new javax.swing.JButton();
        jlabel1 = new javax.swing.JLabel();
        tf_idModificarEjemplar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tf_novaFechaNac = new javax.swing.JTextField();
        comboBox_areas = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cb_cambiarAlimento = new javax.swing.JCheckBox();
        comboBox_alimentos = new javax.swing.JComboBox<>();
        comboBox_alimentoModificar = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labNombre.setText("Nombre Científico");

        tf_modificarNombreCient.setEnabled(false);

        bSalir.setText("Salir");
        bSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalirActionPerformed(evt);
            }
        });

        btnAceptarCambiosEjemplar.setText("Realizar Cambios");
        btnAceptarCambiosEjemplar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarCambiosEjemplarActionPerformed(evt);
            }
        });

        jlabel1.setText("ID");

        tf_idModificarEjemplar.setEnabled(false);

        jLabel1.setText("Nuevo mote");

        jLabel2.setText("Nueva fecha nacimiento");

        jLabel3.setText("Nueva Área");

        cb_cambiarAlimento.setText("Cambio Alimento");

        comboBox_alimentos.setEnabled(false);

        comboBox_alimentoModificar.setEnabled(false);

        jLabel4.setText("Alimento modificar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAceptarCambiosEjemplar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bSalir))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labNombre)
                            .addComponent(jLabel1))
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tf_novoMote, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_modificarNombreCient, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_idModificarEjemplar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 160, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboBox_areas, 0, 216, Short.MAX_VALUE)
                            .addComponent(tf_novaFechaNac))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cb_cambiarAlimento)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboBox_alimentoModificar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboBox_alimentos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_modificarNombreCient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labNombre)
                    .addComponent(jlabel1)
                    .addComponent(tf_idModificarEjemplar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_novoMote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tf_novaFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBox_alimentoModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBox_areas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_cambiarAlimento)
                    .addComponent(comboBox_alimentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptarCambiosEjemplar)
                    .addComponent(bSalir))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_bSalirActionPerformed

    private void btnAceptarCambiosEjemplarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarCambiosEjemplarActionPerformed
        if (!tf_novaFechaNac.getText().isEmpty() && !tf_novoMote.getText().isEmpty() && comboBox_areas.getSelectedIndex() != -1) {
            int res = -1;
            
            Especie e = new Especie(tf_modificarNombreCient.getText());
            Area a = new Area(comboBox_areas.getSelectedItem().toString());
            
            Ejemplar ejemploModificar = new Ejemplar(Integer.parseInt(tf_idModificarEjemplar.getText()), e, tf_novoMote.getText(), tf_novaFechaNac.getText(), a);
            
            if (cb_cambiarAlimento.isSelected()) {
                
                String infoAlimento = comboBox_alimentos.getSelectedItem().toString();
                String[] info = infoAlimento.split("-");
                Alimento al = new Alimento(Integer.parseInt(info[0]));
                
                infoAlimento = comboBox_alimentoModificar.getSelectedItem().toString();
                info = infoAlimento.split("-");
                res = fa.modificarEjemplar_cambioAlimentoPoArea(ejemploModificar, al, Integer.parseInt(info[0]));
                if (res != -1) {
                    cargarAlimentosEjemplarEspecifico(ej.getId());
                } else {
                    System.out.println("MERDAAAA");
                }
            } else {
                res = fa.modificarEjemplar(ejemploModificar);
            }
            
      
            
        }
    }//GEN-LAST:event_btnAceptarCambiosEjemplarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bSalir;
    private javax.swing.JButton btnAceptarCambiosEjemplar;
    private javax.swing.JCheckBox cb_cambiarAlimento;
    private javax.swing.JComboBox<String> comboBox_alimentoModificar;
    private javax.swing.JComboBox<String> comboBox_alimentos;
    private javax.swing.JComboBox<String> comboBox_areas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jlabel1;
    private javax.swing.JLabel labNombre;
    private javax.swing.JTextField tf_idModificarEjemplar;
    private javax.swing.JTextField tf_modificarNombreCient;
    private javax.swing.JTextField tf_novaFechaNac;
    private javax.swing.JTextField tf_novoMote;
    // End of variables declaration//GEN-END:variables
}
