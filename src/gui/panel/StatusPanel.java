package gui.panel;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SpringLayout;

public class StatusPanel extends JPanel {

    private JLabel mouseCoords = new JLabel();
    SpringLayout layout = new SpringLayout();
    private JSeparator statusSep = new JSeparator(JSeparator.VERTICAL);

    public StatusPanel(CanvasPanel cp) {

        //Initialise and add components to panel
        this.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.black));
        this.setLayout(layout);
        this.add(statusSep);
        this.add(mouseCoords);

        layout.putConstraint(SpringLayout.NORTH, statusSep, 2, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.SOUTH, statusSep, -2, SpringLayout.SOUTH, this);

        layout.putConstraint(SpringLayout.NORTH, mouseCoords, 2, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.EAST, mouseCoords, -10, SpringLayout.EAST, this);

    }

    public void setCoords(int x, int y) {
        mouseCoords.setText("Mouse Coords: X: " + x + " | Y: " + y);
    }
}
