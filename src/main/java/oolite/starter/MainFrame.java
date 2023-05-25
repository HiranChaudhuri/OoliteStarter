/*
 */
package oolite.starter;

import java.io.File;
import java.io.IOException;
import javax.swing.JLabel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import oolite.starter.ui.ExpansionsPanel;
import oolite.starter.ui.StartGamePanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

/**
 * This is the main entry point for Oolite.
 * 
 * @author hiran
 */
public class MainFrame extends javax.swing.JFrame {
    private static final Logger log = LogManager.getLogger();

    /**
     * Creates new form MainFrame.
     */
    public MainFrame() throws IOException, SAXException, ParserConfigurationException, XPathExpressionException {
        initComponents();
        setTitle(MainFrame.class.getPackage().getImplementationTitle() + " " + MainFrame.class.getPackage().getImplementationVersion());

        Oolite oolite = new Oolite();
        File confFile = new File(System.getProperty("oolite.starter.configuration", "oolite-starter.properties"));
        if (confFile.exists()) {
            oolite.setConfiguration(new Configuration(confFile));
        } else {
            log.warn("Configuration {} does not exist. Loading builtin defaults.", confFile.getAbsolutePath());
            oolite.setConfiguration(new Configuration());
        }

        JLabel l = null;
        
        StartGamePanel sgp = new StartGamePanel();
        sgp.setOolite(oolite);
        jTabbedPane1.add(sgp);

        ExpansionsPanel ep = new ExpansionsPanel();
        ep.setOolite(oolite);
        jTabbedPane1.add(ep);

//        l = new JLabel("Show Oolite available versions. Allow to install/uninstall them");
//        l.setName("Oolite Version");
//        jTabbedPane1.add(l);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame mf = new MainFrame();
                    mf.pack();
                    mf.setLocationRelativeTo(null);
                    mf.setVisible(true);
                } catch (Exception e) {
                    System.out.println("Could not initialize UI");
                    e.printStackTrace(System.out);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
