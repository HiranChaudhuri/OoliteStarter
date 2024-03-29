/*
 */
package oolite.starter.ui;

import java.awt.Color;
import java.awt.Desktop;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URI;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import oolite.starter.Configuration;
import oolite.starter.ExpansionManager;
import oolite.starter.Oolite;
import oolite.starter.model.Command;
import oolite.starter.model.Expansion;
import oolite.starter.model.ExpansionReference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author hiran
 */
public class ExpansionPanel extends javax.swing.JPanel implements PropertyChangeListener {
    private static final Logger log = LogManager.getLogger();

    private transient Expansion data;
    
    /**
     * Creates new form ExpansionPanel.
     */
    public ExpansionPanel() {
        initComponents();
        update();
        
        ExpansionReferenceCellRenderer ercr = new ExpansionReferenceCellRenderer();
        
        lsRequires.setCellRenderer(ercr);
        lsConflicts.setCellRenderer(ercr);
        lsOptional.setCellRenderer(ercr);
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
    
    /**
     * Empties all fieds to indicate we have no data.
     * Useful when data has been set to null.
     */
    private void emptyFields() {
        txtTitle.setText("");
        txtDescription.setText("");
        txtLocalFile.setText("");
        txtIdentifier.setText("");

        lsRequires.setModel(new DefaultListModel<>());
        lsConflicts.setModel(new DefaultListModel<>());
        lsOptional.setModel(new DefaultListModel<>());
        txtMinVersion.setText("");
        txtMaxVersion.setText("");
        btInstall.setEnabled(false);
        btEnable.setEnabled(false);
        btDisable.setEnabled(false);
        btRemove.setEnabled(false);
        btWiki.setEnabled(false);
    }
    
    /**
     * Updates all fields to show the data.
     */
    private void showData() {
        txtTitle.setText(data.getTitle());
        txtDescription.setText(data.getDescription());
        txtLocalFile.setText(String.valueOf(data.getLocalFile()));
        txtIdentifier.setText(data.getIdentifier());
        DefaultListModel<ExpansionReference> lm = new DefaultListModel<>();
        if (data.getRequiresOxps() != null) {
            lm.addAll(data.getRequiredRefs());
        }
        lsRequires.setModel(lm);
        lm = new DefaultListModel<>();
        if (data.getConflictOxps() != null) {
            lm.addAll(data.getConflictRefs());
        }
        lsConflicts.setModel(lm);
        lm = new DefaultListModel<>();
        if (data.getOptionalOxps() != null) {
            lm.addAll(data.getOptionalRefs());
        }
        lsOptional.setModel(lm);
        txtMinVersion.setText(String.valueOf(data.getRequiredOoliteVersion()));
        txtMaxVersion.setText(String.valueOf(data.getMaximumOoliteVersion()));
        btInstall.setEnabled(data.isOnline() && !data.isLocal());
        btEnable.setEnabled(data.isLocal() && !data.isEnabled());
        btDisable.setEnabled(data.isLocal() && data.isEnabled());
        btRemove.setEnabled(data.isLocal());
        btWiki.setEnabled(true);
    }
    
    private void showStandardTags() {
        if (data.isOnline()) {
            jpDownThere.add(new Tag("Online", Color.GREEN));
        }
        if (data.isLocal() && data.isEnabled()) {
            jpDownThere.add(new Tag("Enabled", Color.GREEN));
        }
        if (data.isLocal() && !data.isEnabled()) {
            jpDownThere.add(new Tag("Disabled", Color.GREEN));
        }
        if (data.isLocal() && !data.isManaged()) {
            jpDownThere.add(new Tag("Manual", Color.RED, Color.BLACK));
        }
        if (data.isLocal() && !data.isOnline()) {
            jpDownThere.add(new Tag("No download", Color.BLUE));
        }
        if (data.isOnline() && !data.isEnabled()) {
            jpDownThere.add(new Tag("Installable", Color.YELLOW));
        }
    }

    private void showExtendedTags() {
        if (data.isLocal() && data.getEMStatus().isLatest()) {
            jpDownThere.add(new Tag("Current", Color.WHITE));
        }
        if (data.isLocal() && !data.getEMStatus().isLatest()) {
            jpDownThere.add(new Tag("Updatable", Color.CYAN));
        }
        if (data.getEMStatus().isConflicting()) {
            jpDownThere.add(new Tag("Conflict", Color.RED, Color.BLACK));
        }
        if (data.isOnline() && data.getEMStatus().isMissingDeps()) {
            jpDownThere.add(new Tag("Install+", Color.ORANGE));
        }
        if (data.isEnabled() && data.getEMStatus().isConflicting()) {
            jpDownThere.add(new Tag("Conflict", new Color(150, 70, 50)));
        }
        if (data.getEMStatus().isIncompatible()) {
            jpDownThere.add(new Tag("Incompatible", Color.GRAY));
            txtMinVersion.setBorder(new LineBorder(Configuration.COLOR_ATTENTION));
            txtMaxVersion.setBorder(new LineBorder(Configuration.COLOR_ATTENTION));
        } else {
            txtMinVersion.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
            txtMaxVersion.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        }

    }
    
    private void update() {
        jpDownThere.removeAll();
        if (data == null) {
            emptyFields();
        } else {
            showData();
            showStandardTags();
            showExtendedTags();

            if (data.isEnabled() && data.getEMStatus().isConflicting()) {
                jpDownThere.add(new Tag("Conflicting", Configuration.COLOR_ATTENTION));
                spConflict.setBorder(new LineBorder(Configuration.COLOR_ATTENTION));
            } else {
                spConflict.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("ScrollPane.border"));
            }
            if (data.isEnabled() && data.getEMStatus().isMissingDeps()) {
                jpDownThere.add(new Tag("MissingDeps", Configuration.COLOR_ATTENTION));
                spRequires.setBorder(new LineBorder(Configuration.COLOR_ATTENTION));
            } else {
                spRequires.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("ScrollPane.border"));
            }
            
            // check if we have nested OXPs
            if (data.isNested()) {
                btDisable.setEnabled(false);
            }
        }
        
        validate();
        repaint();
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
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMinVersion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtMaxVersion = new javax.swing.JTextField();
        jpDownThere = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtTitle = new javax.swing.JTextField();
        txtLocalFile = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        spRequires = new javax.swing.JScrollPane();
        lsRequires = new javax.swing.JList<>();
        jLabel4 = new javax.swing.JLabel();
        spConflict = new javax.swing.JScrollPane();
        lsConflicts = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        lsOptional = new javax.swing.JList<>();
        btWiki = new javax.swing.JButton();
        txtIdentifier = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        jLabel1.setText("Description");

        txtDescription.setEditable(false);
        txtDescription.setColumns(20);
        txtDescription.setLineWrap(true);
        txtDescription.setRows(5);
        txtDescription.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtDescription);

        btInstall.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/download_for_offline_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        btInstall.setText("Install");
        btInstall.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btInstall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btInstallActionPerformed(evt);
            }
        });

        btEnable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/switches_enable_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        btEnable.setText("Enable");
        btEnable.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btEnable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEnableActionPerformed(evt);
            }
        });

        btDisable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/switches_disable_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        btDisable.setText("Disable");
        btDisable.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btDisable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDisableActionPerformed(evt);
            }
        });

        btRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete_forever_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        btRemove.setText("Delete");
        btRemove.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoveActionPerformed(evt);
            }
        });

        jLabel2.setText("Local File");

        jLabel6.setText("Oolite Version");

        jLabel7.setText("min");

        txtMinVersion.setEditable(false);

        jLabel8.setText("max");

        txtMaxVersion.setEditable(false);

        jpDownThere.setAlignmentX(0.0F);
        jpDownThere.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel9.setText("Title");

        txtTitle.setEditable(false);

        txtLocalFile.setEditable(false);

        jLabel3.setText("Requires");

        spRequires.setViewportView(lsRequires);

        jLabel4.setText("Conflicts");

        spConflict.setViewportView(lsConflicts);

        jLabel5.setText("Optional");

        jScrollPane4.setViewportView(lsOptional);

        btWiki.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/OoliteIcon24.png"))); // NOI18N
        btWiki.setText("Wiki");
        btWiki.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btWiki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btWikiActionPerformed(evt);
            }
        });

        txtIdentifier.setEditable(false);

        jLabel10.setText("Identifier");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpDownThere, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel1)
                            .addComponent(jLabel10)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane4)
                                .addGap(125, 125, 125))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtTitle)
                                    .addComponent(txtIdentifier)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtMinVersion, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtMaxVersion))
                                    .addComponent(spRequires)
                                    .addComponent(spConflict)
                                    .addComponent(txtLocalFile))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btWiki, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btInstall, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btEnable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btDisable, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btRemove, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(btWiki))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btInstall)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btEnable)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btDisable)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btRemove))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtIdentifier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel10))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtMinVersion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)
                                    .addComponent(txtMaxVersion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)))))
                    .addComponent(txtLocalFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(spRequires, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(spConflict, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpDownThere, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btInstallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btInstallActionPerformed
        log.debug("btInstallActionPerformed({})", evt);
        try {
            ExpansionManager.getInstance().addCommand(new Command(Command.Action.INSTALL, data));
        } catch (Exception e) {
            log.error("Could not trigger install", e);
        }
    }//GEN-LAST:event_btInstallActionPerformed

    private void btEnableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEnableActionPerformed
        log.debug("btEnableActionPerformed({})", evt);
        try {
            ExpansionManager.getInstance().addCommand(new Command(Command.Action.ENABLE, data));
        } catch (Exception e) {
            log.error("Could not trigger enable", e);
        }
    }//GEN-LAST:event_btEnableActionPerformed

    private void btDisableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDisableActionPerformed
        log.debug("btDisableActionPerformed({})", evt);
        try {
            ExpansionManager.getInstance().addCommand(new Command(Command.Action.DISABLE, data));
        } catch (Exception e) {
            log.error("Could not trigger disable", e);
        }
    }//GEN-LAST:event_btDisableActionPerformed

    private void btRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoveActionPerformed
        log.debug("btRemoveActionPerformed({})", evt);
        
        if (!data.isOnline() && JOptionPane.showConfirmDialog(btRemove, "Great! Getting rid of stuff!! But there is no way back. Are you sure?", "Delete...", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            return;
        }
        
        try {
            ExpansionManager.getInstance().addCommand(new Command(Command.Action.DELETE, data));
        } catch (Exception e) {
            log.error("Could not trigger remove", e);
        }
    }//GEN-LAST:event_btRemoveActionPerformed

    private void btWikiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btWikiActionPerformed
        log.debug("btWikiActionPerformed({})", evt);
        
        String urlStr = Oolite.getOoliteWikiPageUrl(data.getTitle());
        try {
            Desktop.getDesktop().browse(new URI(urlStr));
        } catch (Exception e) {
            log.error("Could not show wiki page {}", urlStr, e);
        }
    }//GEN-LAST:event_btWikiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btDisable;
    private javax.swing.JButton btEnable;
    private javax.swing.JButton btInstall;
    private javax.swing.JButton btRemove;
    private javax.swing.JButton btWiki;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel jpDownThere;
    private javax.swing.JList<ExpansionReference> lsConflicts;
    private javax.swing.JList<ExpansionReference> lsOptional;
    private javax.swing.JList<ExpansionReference> lsRequires;
    private javax.swing.JScrollPane spConflict;
    private javax.swing.JScrollPane spRequires;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtIdentifier;
    private javax.swing.JTextField txtLocalFile;
    private javax.swing.JTextField txtMaxVersion;
    private javax.swing.JTextField txtMinVersion;
    private javax.swing.JTextField txtTitle;
    // End of variables declaration//GEN-END:variables

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        SwingUtilities.invokeLater(this::update);
    }
}
