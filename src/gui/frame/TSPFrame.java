package gui.frame;

import java.awt.BorderLayout;
import java.awt.*;
import javax.swing.JFrame;

import gui.listener.*;
import gui.panel.*;

public class TSPFrame extends JFrame {

    private ButtonPanel btPanel;
    private StatusPanel stPanel;
    private CanvasPanel cvPanel;
    private MouseInterListener mIL;
    private MousePositionListener mPL;

    public TSPFrame() {

        //Locate and set frame icon
        //Initialise and set up frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("TSP Solver");

        //Create new StatusPanel
        stPanel = new StatusPanel(cvPanel);
        stPanel.setPreferredSize(new Dimension(this.getWidth(), 25));

        //Create new CanvasPanel
        cvPanel = new CanvasPanel(stPanel);

        //Create new ButtonPanel
        btPanel = new ButtonPanel(cvPanel);
        btPanel.setPreferredSize(new Dimension(175, this.getHeight()));

        //Set panel positions and add to frame
        add(btPanel, BorderLayout.WEST);
        add(cvPanel, BorderLayout.CENTER);
        add(stPanel, BorderLayout.SOUTH);

        //Fit frame to ensure all panels/components are visible
        pack();

        //Create interaction listeners and add to relevant panels
        mIL = new MouseInterListener(cvPanel, btPanel);
        cvPanel.addMouseListener(mIL);
        mPL = new MousePositionListener(cvPanel, btPanel);
        cvPanel.addMouseMotionListener(mPL);

        //Determine centre of user's screen and position frame
        Toolkit k = Toolkit.getDefaultToolkit();
        Dimension d = k.getScreenSize();
        this.setLocation(d.width / 2 - this.getWidth() / 2, d.height / 2 - this.getHeight() / 2);

    }

    public void showIt() {
        //Display frame on screen
        this.setVisible(true);
    }

}
