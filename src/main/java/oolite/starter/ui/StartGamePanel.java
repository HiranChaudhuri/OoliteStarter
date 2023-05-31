/*
 */
package oolite.starter.ui;

import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import oolite.starter.Oolite;
import oolite.starter.model.SaveGame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author hiran
 */
public class StartGamePanel extends javax.swing.JPanel implements Oolite.OoliteListener {
    private static final Logger log = LogManager.getLogger();
    
    private Oolite oolite;
    private SaveGameTableModel model;
    private SaveGamePanel sgp;

    /**
     * Creates new form StartGamePanel.
     */
    public StartGamePanel() {
        initComponents();
        setName("Start Game");
    }
    
    /**
     * Sets the Oolite instance to run the savegames from.
     * 
     * @param oolite the oolite instance
     * @throws IOException something went wrong
     * @throws SAXException something went wrong
     * @throws ParserConfigurationException something went wrong
     * @throws XPathExpressionException  something went wrong
     */
    public void setOolite(Oolite oolite) {
        this.oolite = oolite;
        
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                log.debug("valueChanged({})", lse);
                if (!lse.getValueIsAdjusting()) {
                    // we have a final value - let's render it
                    int rowIndex = jTable1.getSelectedRow();
                    if (rowIndex >=0 ) {
                        rowIndex = jTable1.convertRowIndexToModel(rowIndex);
                        SaveGame row = model.getRow(rowIndex);
                        sgp.setData(row);
                        sgp.setVisible(true);
                    }
                }
            }
        });
        
        sgp = new SaveGamePanel();
        sgp.setVisible(false);
        add(sgp, BorderLayout.SOUTH);
        
        update();
    }
    
    private void update() {
        try {
            model = new SaveGameTableModel(oolite.getSaveGames());
            jTable1.clearSelection();
            jTable1.setModel(model);
            sgp.setVisible(false);
            sgp.setData(null);
        } catch (Exception e) {
            log.warn("Could not update", e);
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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridLayout(0, 1));

        jButton1.setText("New");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        jButton2.setText("Continue");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);

        add(jPanel1, java.awt.BorderLayout.LINE_END);

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"No save games found"},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Save Games"
            }
        ));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // new game button
        try {
            SwingUtilities.getRoot(this).setVisible(false);
            
            oolite.run();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Could not run game");
        } finally {
            SwingUtilities.getRoot(this).setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // run savegame
        try {
            int rowIndex = jTable1.convertRowIndexToModel(jTable1.getSelectedRow());
            SaveGame row = model.getRow(rowIndex);

            SwingUtilities.getRoot(this).setVisible(false);
            oolite.run(row);
            
            update();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Could not run game");
        } finally {
            SwingUtilities.getRoot(this).setVisible(true);
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void launched() {
    }

    @Override
    public void terminated() {
        update();
    }
}
