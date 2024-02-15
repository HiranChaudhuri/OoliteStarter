/*
 */
package oolite.starter.ui2;

import oolite.starter.model.Expansion;
import oolite.starter.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Panel to show expansion details.
 * 
 * @author hiran
 */
public class ExpansionPanel extends javax.swing.JPanel implements ExpansionsPanel2.SelectionListener {
    private static final Logger log = LogManager.getLogger();

    /**
     * Creates new form ExpansionPanel.
     */
    public ExpansionPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();

        jEditorPane1.setEditable(false);
        jEditorPane1.setContentType("text/html"); // NOI18N
        jScrollPane1.setViewportView(jEditorPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void selectionChanged(Expansion expansion) {
        log.debug("selectionChanged({})", expansion);
        
        String text = ("<html>"
                + "<table><tr>"
                + "<td>Title</td><td>%s</td>"
                + "</tr><tr>"
                + "<td valign=\"top\">Description</td><td valign=\"top\">%s</td>"
                + "</tr><tr>"
                + "<td>Version</td><td>%s<</td>"
                + "</tr><tr>"
                + "<td>Size</td><td>%s</td>"
                + "</tr><tr>"
                + "<td>Local File</td><td>%s</td>"
                + "</tr></table>"
                + "</html>")
                .formatted(
                        expansion.getTitle(), 
                        expansion.getDescription(),
                        expansion.getVersion(), 
                        Util.humanreadableSize(expansion.getFileSize()), 
                        expansion.getLocalFile()
                );
        
        jEditorPane1.setText(text);
        jEditorPane1.setCaretPosition(0);
    }
}
