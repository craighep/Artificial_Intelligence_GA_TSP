package gui.frame;

import java.awt.BorderLayout;
import java.awt.*;
import javax.swing.JFrame;

import gui.listener.*;
import gui.panel.*;

/**
 * Creates the main GUI frame.
 * Creates the frame and adds each of the different panels to it, alongside
 * the mouse listeners for placing cities on the map.
 * @author Craig
 */
public class TSPFrame extends JFrame {

    private ButtonPanel btPanel;
    private StatusPanel stPanel;
    private CanvasPanel cvPanel;
    private MouseInterListener mIL;
    private MousePositionListener mPL;

    /**
     * Creates a new window, and initialises each panel before adding them to it.
     * Sets the size of the window, and the location.
     */
    public TSPFrame() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("TSP Solver");

        stPanel = new StatusPanel();
        stPanel.setPreferredSize(new Dimension(this.getWidth(), 25));

        cvPanel = new CanvasPanel(stPanel);

        btPanel = new ButtonPanel(cvPanel);
        btPanel.setPreferredSize(new Dimension(175, this.getHeight()));

        add(btPanel, BorderLayout.WEST);
        add(cvPanel, BorderLayout.CENTER);
        add(stPanel, BorderLayout.SOUTH);

        pack();

        mIL = new MouseInterListener(cvPanel, btPanel);
        cvPanel.addMouseListener(mIL);
        mPL = new MousePositionListener(cvPanel, btPanel);
        cvPanel.addMouseMotionListener(mPL);

        Toolkit k = Toolkit.getDefaultToolkit();
        Dimension d = k.getScreenSize();
        this.setLocation(d.width / 2 - this.getWidth() / 2, d.height / 2 - this.getHeight() / 2);
    }

    /**
     * Opens the generated frame in a new window.
     */
    public void showIt() {
        this.setVisible(true);
    }

}
