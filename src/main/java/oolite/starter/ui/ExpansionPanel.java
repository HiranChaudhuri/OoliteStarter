/*
 */
package oolite.starter.ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.SwingUtilities;
import oolite.starter.model.Expansion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author hiran
 */
public class ExpansionPanel extends javax.swing.JPanel implements PropertyChangeListener {
    private static final Logger log = LogManager.getLogger();

    private Expansion data;
    
    /**
     * Creates new form ExpansionPanel.
     */
    public ExpansionPanel() {
        initComponents();
    }
    
    /**
     * Sets the Expansion whose data is to be shown.
     * 
     * @param data the expansion
     */
    public void setData(Expansion data) {
        if (this.data != null) {
            this.data.removePropertyChangeListener(this);
        }
        this.data = data;
        if (data != null) {
            data.addPropertyChangeListener(this);
        }
        
        update();
    }
    
    private void update() {
        if (data == null) {
            txtDescription.setText("");
            txtLocalFile.setText("");
            txtRequires.setText("");
            txtConflicts.setText("");
            txtOptional.setText("");
            btInstall.setEnabled(false);
            btEnable.setEnabled(false);
            btDisable.setEnabled(false);
            btRemove.setEnabled(false);
        } else {
            txtDescription.setText(data.getDescription());
            txtLocalFile.setText(String.valueOf(data.getLocalFile()));
            txtRequires.setText(String.valueOf(data.getRequiresOxps()));
            txtConflicts.setText(String.valueOf(data.getConflictOxps()));
            txtOptional.setText(String.valueOf(data.getOptionalOxps()));
            btInstall.setEnabled(data.isOnline() && !data.isLocal());
            btEnable.setEnabled(data.isLocal() && !data.isEnabled());
            btDisable.setEnabled(data.isLocal() && data.isEnabled());
            btRemove.setEnabled(data.isLocal());
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        btInstall = new javax.swing.JButton();
        btEnable = new javax.swing.JButton();
        btDisable = new javax.swing.JButton();
        btRemove = new javax.swing.JButton();
        txtLocalFile = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtRequires = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtConflicts = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtOptional = new javax.swing.JTextField();

        jLabel1.setText("Description");

        txtDescription.setEditable(false);
        txtDescription.setColumns(20);
        txtDescription.setLineWrap(true);
        txtDescription.setRows(5);
        txtDescription.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtDescription);

        btInstall.setText("Install");
        btInstall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btInstallActionPerformed(evt);
            }
        });

        btEnable.setText("Enable");
        btEnable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEnableActionPerformed(evt);
            }
        });

        btDisable.setText("Disable");
        btDisable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDisableActionPerformed(evt);
            }
        });

        btRemove.setText("Remove");
        btRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoveActionPerformed(evt);
            }
        });

        txtLocalFile.setEditable(false);

        jLabel2.setText("Local File");

        jLabel3.setText("Requires");

        txtRequires.setEditable(false);

        jLabel4.setText("Conflicts");

        txtConflicts.setEditable(false);

        jLabel5.setText("Optional");

        txtOptional.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLocalFile)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                    .addComponent(txtRequires)
                    .addComponent(txtConflicts)
                    .addComponent(txtOptional))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btRemove, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btDisable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btEnable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btInstall, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btInstall)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btEnable)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btDisable)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btRemove))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLocalFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtRequires, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtConflicts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtOptional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btInstallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btInstallActionPerformed
        try {
            new ExpansionWorker(data, ExpansionWorker.Action.install, this).execute();
        } catch (Exception e) {
            log.error("Could not trigger install", e);
        }
    }//GEN-LAST:event_btInstallActionPerformed

    private void btEnableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEnableActionPerformed
        try {
            new ExpansionWorker(data, ExpansionWorker.Action.enable, this).execute();
        } catch (Exception e) {
            log.error("Could not trigger enable", e);
        }
    }//GEN-LAST:event_btEnableActionPerformed

    private void btDisableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDisableActionPerformed
        try {
            new ExpansionWorker(data, ExpansionWorker.Action.disable, this).execute();
        } catch (Exception e) {
            log.error("Could not trigger disable", e);
        }
    }//GEN-LAST:event_btDisableActionPerformed

    private void btRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoveActionPerformed
        try {
            new ExpansionWorker(data, ExpansionWorker.Action.remove, this).execute();
        } catch (Exception e) {
            log.error("Could not trigger remove", e);
        }
    }//GEN-LAST:event_btRemoveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btDisable;
    private javax.swing.JButton btEnable;
    private javax.swing.JButton btInstall;
    private javax.swing.JButton btRemove;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtConflicts;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtLocalFile;
    private javax.swing.JTextField txtOptional;
    private javax.swing.JTextField txtRequires;
    // End of variables declaration//GEN-END:variables

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                update();
            }
        });
    }
}
