/*
 */
package oolite.starter.ui;

import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;
import oolite.starter.Configuration;
import oolite.starter.model.Installation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author hiran
 */
public class InstallationsPanel extends javax.swing.JPanel {
    private static final Logger log = LogManager.getLogger();

    private InstallationForm installationForm;
    private InstallationTableModel model;
    private TableRowSorter<InstallationTableModel> trw;
    private Configuration configuration;
    
    /**
     * Creates new form InstallationsPanel.
     */
    public InstallationsPanel() {
        initComponents();
        btScan.setVisible(false);
        
        installationForm = new InstallationForm();
        installationForm.setEnabled(false);
        jSplitPane1.setRightComponent(installationForm);
    }
    
    /**
     * Sets the configuration to be used for managing installations.
     * 
     * @param configuration the configuration
     */
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;

        model = new InstallationTableModel(configuration);
        jTable1.setModel(model);
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                log.debug("valueChanged({})", lse);
                if (!lse.getValueIsAdjusting()) {
                    // we have a final value - let's render it
                    showDetailsOfSelection();
                }
            }
        });
        trw = new TableRowSorter<InstallationTableModel>(model);
        jTable1.setRowSorter(trw);
    }
    
    private void showDetailsOfSelection() {
        int rowIndex = jTable1.getSelectedRow();
        if (rowIndex >=0) {
            rowIndex = jTable1.convertRowIndexToModel(rowIndex);
            Installation row = model.getRow(rowIndex);
            installationForm.setData(row);
        } else {
            installationForm.setData(null);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btAdd = new javax.swing.JButton();
        btEdit = new javax.swing.JButton();
        btRemove = new javax.swing.JButton();
        btScan = new javax.swing.JButton();
        btSave = new javax.swing.JButton();
        btActivate = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setName("Installations"); // NOI18N
        setLayout(new java.awt.BorderLayout());

        btAdd.setText("Add...");
        btAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddActionPerformed(evt);
            }
        });

        btEdit.setText("Edit...");
        btEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditActionPerformed(evt);
            }
        });

        btRemove.setText("Remove");
        btRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoveActionPerformed(evt);
            }
        });

        btScan.setText("Scan...");
        btScan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btScanActionPerformed(evt);
            }
        });

        btSave.setText("Save");
        btSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveActionPerformed(evt);
            }
        });

        btActivate.setText("Activate");
        btActivate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btActivateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(btAdd)
                .addGap(6, 6, 6)
                .addComponent(btEdit)
                .addGap(6, 6, 6)
                .addComponent(btRemove)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btScan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btActivate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(btSave)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btAdd)
                    .addComponent(btEdit)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btRemove)
                        .addComponent(btScan)
                        .addComponent(btSave)
                        .addComponent(btActivate)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jSplitPane1.setDividerLocation(300);
        jSplitPane1.setResizeWeight(0.5);
        jSplitPane1.setOneTouchExpandable(true);

        jScrollPane1.setViewportView(jTable1);

        jSplitPane1.setLeftComponent(jScrollPane1);

        add(jSplitPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btScanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btScanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btScanActionPerformed

    private void btAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddActionPerformed
        try {
            InstallationForm installationForm = new InstallationForm();
            if (JOptionPane.showOptionDialog(this, installationForm, "Add Installation...", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null) == JOptionPane.OK_OPTION) {
                model.addRow(installationForm.getData());
            }
        } catch (Exception e) {
            log.error("Error", e);
            JOptionPane.showMessageDialog(this, "Error");
        }
    }//GEN-LAST:event_btAddActionPerformed

    private void btEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditActionPerformed
        try {
            int rowIndex = jTable1.getSelectedRow();
            if (rowIndex == -1) {
                JOptionPane.showConfirmDialog(this, "Please select row");
                return;
            }
            
            rowIndex = jTable1.convertRowIndexToModel(rowIndex);
            Installation i = model.getRow(rowIndex);
            
            InstallationForm installationForm = new InstallationForm();
            installationForm.setData(i);
            
            if (JOptionPane.showOptionDialog(this, installationForm, "Add Installation...", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null) == JOptionPane.OK_OPTION) {
                Installation data = installationForm.getData();
                model.updateRow(data);
                if (model.getRow(jTable1.getSelectedRow()) == data) {
                    this.installationForm.setData(data);
                }
            }
        } catch (Exception e) {
            log.error("Error", e);
            JOptionPane.showMessageDialog(this, "Error");
        }        
    }//GEN-LAST:event_btEditActionPerformed

    private void btRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoveActionPerformed
        try {
            int rowIndex = jTable1.getSelectedRow();
            if (rowIndex == -1) {
                JOptionPane.showConfirmDialog(this, "Please select row");
                return;
            }
            
            rowIndex = jTable1.convertRowIndexToModel(rowIndex);
            Installation i = model.getRow(rowIndex);
            
            model.removeRow(rowIndex);
        } catch (Exception e) {
            log.error("Error", e);
            JOptionPane.showMessageDialog(this, "Error");
        }        
    }//GEN-LAST:event_btRemoveActionPerformed

    private void btSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveActionPerformed
        try {
            configuration.saveConfiguration(new File(System.getProperty("user.home") + File.separator + ".oolite-starter.conf"));
        } catch (Exception e) {
            log.error("Could not save", e);
            JOptionPane.showMessageDialog(this, "Could not save");
        }        
    }//GEN-LAST:event_btSaveActionPerformed

    private void btActivateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btActivateActionPerformed
        try {
            int rowIndex = jTable1.getSelectedRow();
            if (rowIndex == -1) {
                JOptionPane.showConfirmDialog(this, "Please select row");
                return;
            }
            
            int modelIndex = jTable1.convertRowIndexToModel(rowIndex);
            Installation i = model.getRow(modelIndex);
            
            configuration.activateInstallation(i);
            model.fireTableDataChanged();
            jTable1.getSelectionModel().setSelectionInterval(rowIndex, rowIndex);
        } catch (Exception e) {
            log.error("Could not save", e);
            JOptionPane.showMessageDialog(this, "Could not save");
        }        
    }//GEN-LAST:event_btActivateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btActivate;
    private javax.swing.JButton btAdd;
    private javax.swing.JButton btEdit;
    private javax.swing.JButton btRemove;
    private javax.swing.JButton btSave;
    private javax.swing.JButton btScan;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}