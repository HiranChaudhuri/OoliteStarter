/*
 */

package oolite.starter.ui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import oolite.starter.model.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Shows a command to the user.
 *
 * @author hiran
 */
public class CommandCellRenderer extends JPanel implements ListCellRenderer<Command> {
    private static final Logger log = LogManager.getLogger();
    
    private static final ImageIcon iiInstall = new ImageIcon(ExpansionReferenceCellRenderer.class.getResource("/icons/download_FILL0_wght400_GRAD0_opsz24.png"));
    private static final ImageIcon iiEnable = new ImageIcon(ExpansionReferenceCellRenderer.class.getResource("/icons/switches_enable_FILL0_wght400_GRAD0_opsz24.png"));
    private static final ImageIcon iiDisable = new ImageIcon(ExpansionReferenceCellRenderer.class.getResource("/icons/switches_disable_FILL0_wght400_GRAD0_opsz24.png"));
    private static final ImageIcon iiDelete = new ImageIcon(ExpansionReferenceCellRenderer.class.getResource("/icons/delete_forever_FILL0_wght400_GRAD0_opsz24.png"));
    private static final ImageIcon iiError = new ImageIcon(ExpansionReferenceCellRenderer.class.getResource("/icons/report_FILL0_wght400_GRAD0_opsz24_red.png"));
    private static final ImageIcon iiWarn = new ImageIcon(ExpansionReferenceCellRenderer.class.getResource("/icons/warning_FILL0_wght400_GRAD0_opsz24_orange.png"));
    private static final ImageIcon iiKeep = new ImageIcon(ExpansionReferenceCellRenderer.class.getResource("/icons/check_circle_FILL0_wght400_GRAD0_opsz24.png"));
    
    private JLabel lbIcon;
    private JLabel lbAction;
    private JLabel lbTitle;
    
    /**
     * Creates a new CommandCellRenderer.
     */
    public CommandCellRenderer() {
        setOpaque(true);
        setLayout(new GridBagLayout());
        
        lbIcon = new JLabel(iiWarn);
        add(lbIcon, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,5,0,5), 0, 0));
        
        lbAction = new JLabel();
        add(lbAction, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,5,0,5), 0, 0));
        
        lbTitle = new JLabel();
        add(lbTitle, new GridBagConstraints(2, 0, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0,5,0,5), 0, 0));
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Command> list, Command command, int index, boolean isSelected, boolean isFocused) {
        switch (command.getAction()) {
            case install:
                lbIcon.setIcon(iiInstall);
                break;
            case enable:
                lbIcon.setIcon(iiEnable);
                break;
            case keep:
                lbIcon.setIcon(iiKeep);
                break;
            case unknown:
                lbIcon.setIcon(iiError);
                break;
            case disable:
                lbIcon.setIcon(iiDisable);
                break;
            case delete:
                lbIcon.setIcon(iiDelete);
                break;
            default:
                lbIcon.setIcon(null);
        }
        lbAction.setText(String.valueOf(command.getAction()));
        lbTitle.setText(command.getExpansion().getTitle() + " @ " + command.getExpansion().getVersion());
        
        return this;
    }

}
