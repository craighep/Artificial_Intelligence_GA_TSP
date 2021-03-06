package gui.panel;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SpringLayout;

/**
 * Shows the mouse coordinates at the bottom of the GUI frame.
 * Takes the X and Y location of the mouse of the user and displays this
 * in a small panel at the bottom of the frame.
 * @author Craig
 */
public class StatusPanel extends JPanel {

    private JLabel mouseCoords = new JLabel();
    SpringLayout layout = new SpringLayout();
    private JSeparator statusSep = new JSeparator(JSeparator.VERTICAL);

    /**
     * Creates a new status panel to display X and Y coordinates of the users'
     * mouse.
     */
    public StatusPanel() {
        this.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.black));
        this.setLayout(layout);
        this.add(statusSep);
        this.add(mouseCoords);
        layout.putConstraint(SpringLayout.NORTH, statusSep, 2, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.SOUTH, statusSep, -2, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.NORTH, mouseCoords, 2, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.EAST, mouseCoords, -10, SpringLayout.EAST, this);
    }

    /**
     * Sets the X and Y locations of the current users' mouse.
     * @param x X location of the users mouse on the canvas panel
     * @param y Y location of the users mouse on the canvas panel
     */
    public void setCoords(int x, int y) {
        mouseCoords.setText("Mouse Coords: X: " + x + " | Y: " + y);
    }
}
