/*
 */

package oolite.starter.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implements a draggable splash screen for OoliteStarter.
 *
 * @author hiran
 */
public class SplashPanel extends JPanel implements MouseListener, MouseMotionListener {
    private static final Logger log = LogManager.getLogger();

    private static Image logo;
    static {
        try {
            logo = ImageIO.read(MrGimlet.class.getResourceAsStream("/oolite_logo.png"));
        } catch (Exception e) {
            log.error("Could not load image", e);
        }
    }
    
    private String text;
    private Point dragOriginMouse;
    private Point dragOriginScreen;
    
    private String motd;

    /**
     * Creates a new instance.
     * 
     * @param background the background image
     */
    public SplashPanel(ImageIcon background) {
        this(background, null);
    }
    
    /**
     * Creates a new instance.
     * 
     * @param background the background image
     * @param motd Mesage Of The Day
     */
    public SplashPanel(ImageIcon background, String motd) {
        log.debug("SplashPanel(..., {})", motd);
        
        setLayout(new BorderLayout());
        add(new JLabel(background));

        text = SplashPanel.class.getPackage().getImplementationTitle()
            + " " + SplashPanel.class.getPackage().getImplementationVersion();
        
        addMouseListener(this);
        addMouseMotionListener(this);
        setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        
        this.motd = motd;
        
        JProgressBar jpb = new JProgressBar();
        jpb.setIndeterminate(true);
        jpb.setBackground(Color.black);
        jpb.setBorderPainted(false);
        jpb.setString("Scanning expansions...");
        jpb.setStringPainted(true);
        add(jpb, BorderLayout.SOUTH);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g); 
        
        Graphics2D g2d = (Graphics2D)g;
        
        int x = getWidth() - logo.getWidth(null) - 50;
        int y = getHeight() - logo.getHeight(null) - 30;
        g2d.drawImage(logo, x, y, null);

        g2d.setFont(g.getFont().deriveFont(Font.BOLD, 22.0f));
        g2d.setColor(Color.white);
        g2d.drawString(text, 31, 51);
        g2d.setColor(new Color(46, 64, 82));
        g2d.drawString(text, 30, 50);
        
        if (motd != null) {
            g2d.setFont(g.getFont().deriveFont(Font.BOLD, 14.0f));
            g2d.setColor(Color.white);
            g2d.drawString(motd, 31, 101);
            g2d.setColor(new Color(86, 84, 82));
            g2d.drawString(motd, 30, 100);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        // not needed
    }

    @Override
    public void mousePressed(MouseEvent me) {
        JFrame f = (JFrame)SwingUtilities.getRoot(this);
        
        dragOriginMouse = me.getLocationOnScreen();
        dragOriginScreen = f.getLocation();
        log.trace("mousePressed {}", dragOriginMouse);
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        // not needed
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        // not needed
    }

    @Override
    public void mouseExited(MouseEvent me) {
        // not needed
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        log.trace("mouseDragged({})", me);
        Point relMove = new Point(me.getLocationOnScreen().x - dragOriginMouse.x, me.getLocationOnScreen().y - dragOriginMouse.y);
        log.trace("relMove {}", relMove);

        JFrame f = (JFrame)SwingUtilities.getRoot(this);
        Point newLoc = new Point(dragOriginScreen.x + relMove.x, dragOriginScreen.y + relMove.y);
        f.setLocation(newLoc);
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        // not needed
    }
}
