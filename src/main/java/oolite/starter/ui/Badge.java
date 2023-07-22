/*
 */
package oolite.starter.ui;

import java.awt.Color;

/**
 * Represents a badge (name/value tag).
 *
 * @author hiran
 */
public class Badge extends javax.swing.JPanel {
    
    /**
     * Creates new form Badge.
     */
    public Badge() {
        initComponents();
    }

    /**
     * Creates new form Badge.
     */
    public Badge(String name, String value) {
        initComponents();
        
        jlName.setText(name);
        txtValue.setText(value);
    }
    
    /**
     * Creates new form Badge.
     */
    public Badge(String name, String value, Color color) {
        initComponents();
        
        jlName.setText(name);
        txtValue.setText(value);
        setBackground(color);
    }
    
    /**
     * Sets the name for the badge.
     * 
     * @param name 
     */
    public void setName(String name) {
        jlName.setText(name);
    }

    /**
     * Sets the value for the badge.
     * 
     * @param value 
     */
    public void setValue(String value) {
        txtValue.setText(value);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlName = new javax.swing.JLabel();
        txtValue = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtValue.setEditable(false);
        txtValue.setText("jTextField1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jlName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtValue, javax.swing.GroupLayout.PREFERRED_SIZE, 59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jlName)
                .addComponent(txtValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jlName;
    private javax.swing.JTextField txtValue;
    // End of variables declaration//GEN-END:variables
}
