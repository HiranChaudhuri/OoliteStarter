/*
 */
package oolite.starter.ui;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import oolite.starter.model.Installation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author hiran
 */
public class InstallationForm extends javax.swing.JPanel {
    private static final Logger log = LogManager.getLogger();
    
    private Installation data;

    /**
     * Creates new form InstallationForm
     */
    public InstallationForm() {
        initComponents();
        this.setData(new Installation());
    }
    
    public void setData(Installation data) {
        this.data = data;

        if (data == null) {
            txtAddOnDir.setText("");
            txtDeactivatedAddOnDir.setText("");
            txtExecutable.setText("");
            txtHomeDir.setText("");
            txtManagedAddOnDir.setText("");
            txtSavegameDir.setText("");
            txtVersion.setText("");
        } else {
            txtAddOnDir.setText(data.getAddonDir());
            txtDeactivatedAddOnDir.setText(data.getDeactivatedAddonDir());
            txtExecutable.setText(data.getExcecutable());
            txtHomeDir.setText(data.getHomeDir());
            txtManagedAddOnDir.setText(data.getManagedAddonDir());
            txtSavegameDir.setText(data.getSavegameDir());
            txtVersion.setText(data.getVersion());
        }
    }
    
    public Installation getData() {
        data.setAddonDir(txtAddOnDir.getText());
        data.setDeactivatedAddonDir(txtDeactivatedAddOnDir.getText());
        data.setExcecutable(txtExecutable.getText());
        data.setHomeDir(txtHomeDir.getText());
        data.setManagedAddonDir(txtManagedAddOnDir.getText());
        data.setSavegameDir(txtSavegameDir.getText());
        data.setVersion(txtVersion.getText());
        return data;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        btHomeDir.setVisible(enabled);
        btExecutable.setVisible(enabled);
        btSavegameDir.setVisible(enabled);
        btAddOnDir.setVisible(enabled);
        btManagedAddOnDir.setVisible(enabled);
        btDeactivatedAddOnDir.setVisible(enabled);
        
        txtHomeDir.setEnabled(enabled);
        txtExecutable.setEnabled(enabled);
        txtSavegameDir.setEnabled(enabled);
        txtAddOnDir.setEnabled(enabled);
        txtManagedAddOnDir.setEnabled(enabled);
        txtDeactivatedAddOnDir.setEnabled(enabled);
        txtVersion.setEnabled(enabled);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtDeactivatedAddOnDir = new javax.swing.JTextField();
        txtManagedAddOnDir = new javax.swing.JTextField();
        txtAddOnDir = new javax.swing.JTextField();
        txtSavegameDir = new javax.swing.JTextField();
        txtExecutable = new javax.swing.JTextField();
        txtVersion = new javax.swing.JTextField();
        txtHomeDir = new javax.swing.JTextField();
        btHomeDir = new javax.swing.JButton();
        btExecutable = new javax.swing.JButton();
        btSavegameDir = new javax.swing.JButton();
        btAddOnDir = new javax.swing.JButton();
        btManagedAddOnDir = new javax.swing.JButton();
        btDeactivatedAddOnDir = new javax.swing.JButton();

        jLabel1.setText("Home Directory");

        jLabel2.setText("Version");

        jLabel3.setText("Executable");

        jLabel4.setText("Savegame Directory");

        jLabel5.setText("AddOn Directory");

        jLabel6.setText("Managed AddOn Directory");

        jLabel7.setText("Deactivated AddOn Directory");

        btHomeDir.setText("...");
        btHomeDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHomeDirActionPerformed(evt);
            }
        });

        btExecutable.setText("...");
        btExecutable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExecutableActionPerformed(evt);
            }
        });

        btSavegameDir.setText("...");
        btSavegameDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSavegameDirActionPerformed(evt);
            }
        });

        btAddOnDir.setText("...");
        btAddOnDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddOnDirActionPerformed(evt);
            }
        });

        btManagedAddOnDir.setText("...");
        btManagedAddOnDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btManagedAddOnDirActionPerformed(evt);
            }
        });

        btDeactivatedAddOnDir.setText("...");
        btDeactivatedAddOnDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeactivatedAddOnDirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtHomeDir, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btHomeDir))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtVersion, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDeactivatedAddOnDir)
                            .addComponent(txtManagedAddOnDir)
                            .addComponent(txtAddOnDir)
                            .addComponent(txtSavegameDir)
                            .addComponent(txtExecutable))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btExecutable, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btSavegameDir, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btAddOnDir, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btManagedAddOnDir, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btDeactivatedAddOnDir, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtHomeDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btHomeDir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtVersion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtExecutable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btExecutable))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSavegameDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSavegameDir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtAddOnDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btAddOnDir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtManagedAddOnDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btManagedAddOnDir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtDeactivatedAddOnDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btDeactivatedAddOnDir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btHomeDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHomeDirActionPerformed
        try {
            JFileChooser jfc = null;
        } catch (Exception e) {
            log.error("Could not set home dir", e);
            JOptionPane.showMessageDialog(this, "Error");
        }
    }//GEN-LAST:event_btHomeDirActionPerformed

    private void btExecutableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExecutableActionPerformed
        JOptionPane.showMessageDialog(this, "jau!");
    }//GEN-LAST:event_btExecutableActionPerformed

    private void btSavegameDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSavegameDirActionPerformed
        JOptionPane.showMessageDialog(this, "jau!");
    }//GEN-LAST:event_btSavegameDirActionPerformed

    private void btAddOnDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddOnDirActionPerformed
        JOptionPane.showMessageDialog(this, "jau!");
    }//GEN-LAST:event_btAddOnDirActionPerformed

    private void btManagedAddOnDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btManagedAddOnDirActionPerformed
        JOptionPane.showMessageDialog(this, "jau!");
    }//GEN-LAST:event_btManagedAddOnDirActionPerformed

    private void btDeactivatedAddOnDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeactivatedAddOnDirActionPerformed
        JOptionPane.showMessageDialog(this, "jau!");
    }//GEN-LAST:event_btDeactivatedAddOnDirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAddOnDir;
    private javax.swing.JButton btDeactivatedAddOnDir;
    private javax.swing.JButton btExecutable;
    private javax.swing.JButton btHomeDir;
    private javax.swing.JButton btManagedAddOnDir;
    private javax.swing.JButton btSavegameDir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtAddOnDir;
    private javax.swing.JTextField txtDeactivatedAddOnDir;
    private javax.swing.JTextField txtExecutable;
    private javax.swing.JTextField txtHomeDir;
    private javax.swing.JTextField txtManagedAddOnDir;
    private javax.swing.JTextField txtSavegameDir;
    private javax.swing.JTextField txtVersion;
    // End of variables declaration//GEN-END:variables
}
