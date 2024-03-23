/*
 */
package oolite.starter.ui2;

import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import oolite.starter.ExpansionManager;
import oolite.starter.Oolite;
import oolite.starter.Oolite2;
import oolite.starter.model.Command;
import oolite.starter.ui.MrGimlet;
import oolite.starter.ui.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.NodeList;

/**
 * Allows users to store and load expansion sets.
 *
 * @author hiran
 */
public class ExpansionSetPanel extends javax.swing.JPanel {
    private static final Logger log = LogManager.getLogger();
    
    private Oolite oolite;
    private Oolite2 oolite2;

    /**
     * Creates new form ExpansionSetPanel.
     */
    public ExpansionSetPanel() {
        log.debug("ExpansionSetPanel()");
        initComponents();
    }

    /**
     * Sets the oolite instance to be used.
     * 
     * @param oolite the instance
     */
    public void setOolite(Oolite oolite, Oolite2 oolite2) {
        log.debug("setOolite({}, {})", oolite, oolite2);
        this.oolite = oolite;
        this.oolite2 = oolite2;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btLoad = new javax.swing.JButton();
        btSave = new javax.swing.JButton();

        btLoad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/upload_FILL0_wght400_GRAD0_opsz48.png"))); // NOI18N
        btLoad.setText("Load...");
        btLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLoadActionPerformed(evt);
            }
        });

        btSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/download_FILL0_wght400_GRAD0_opsz48.png"))); // NOI18N
        btSave.setText("Save...");
        btSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btLoad)
                .addGap(18, 18, 18)
                .addComponent(btSave)
                .addContainerGap(123, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btLoad)
                    .addComponent(btSave))
                .addContainerGap(240, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLoadActionPerformed
        try {
            JFileChooser jfc = new JFileChooser();
            FileFilter filter = new FileNameExtensionFilter("Oolite Expansion Set (*.oolite-es)", "oolite-es");
            jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            jfc.addChoosableFileFilter(filter);
            jfc.setFileFilter(filter);
            if (jfc.showDialog(this, "Activate") == JFileChooser.APPROVE_OPTION) {
                log.info("activating {}", jfc.getSelectedFile());

                // create a plan
                NodeList nl = oolite.parseExpansionSet(jfc.getSelectedFile());
                List<Command> plan = oolite.buildCommandList(oolite2.getExpansions(), nl);
                
                // have user approve the plan
                if (JOptionPane.showConfirmDialog(this, Util.createCommandListPanel(plan), "Confirm these actions...", JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION) {
                    // execute the plan
                    ExpansionManager.getInstance().addCommands(plan);
                    MrGimlet.showMessage(ExpansionSetPanel.this, "Working on it...");
                }
            }
        } catch (Exception e) {
            log.error("Could not load", e);
            JOptionPane.showMessageDialog(this, "Could not load: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btLoadActionPerformed

    private void btSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveActionPerformed
        try {
            JFileChooser jfc = new JFileChooser();
            FileFilter filter = new FileNameExtensionFilter("Oolite Expansion Set (*.oolite-es)", "oolite-es");
            jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            jfc.addChoosableFileFilter(filter);
            jfc.setFileFilter(filter);
            if (jfc.showDialog(this, "Export") == JFileChooser.APPROVE_OPTION) {
                
                File f = jfc.getSelectedFile();
                
                // Java does not automatically add the extension
                if (jfc.getFileFilter() instanceof FileNameExtensionFilter fnef
                        && !fnef.accept(f)
                ) {
                    // attach extension
                    f = new File(f.getAbsolutePath() + "." + fnef.getExtensions()[0]);
                }
                
                if (f.exists()
                    && JOptionPane.showConfirmDialog(this, String.format("File %s exists. Do you want to overwrite?", f.getAbsolutePath())) != JOptionPane.OK_OPTION
                ) {
                    return;
                }
                
                oolite.exportEnabledExpansions(f);
            }
        } catch (Exception e) {
            log.error("Could not save", e);
            JOptionPane.showMessageDialog(this, "Could not save: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btSaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btLoad;
    private javax.swing.JButton btSave;
    // End of variables declaration//GEN-END:variables
}
