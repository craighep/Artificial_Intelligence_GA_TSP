package gui.listener;

import gui.panel.ButtonPanel;
import gui.panel.CanvasPanel;
import java.awt.event.*;

public class MousePositionListener implements MouseMotionListener {

    private CanvasPanel canvasPane;

    public MousePositionListener(CanvasPanel cp, ButtonPanel bp) {
        canvasPane = cp;
    }

    public void mouseMoved(MouseEvent e) {

        //Display the mouse position coordinates in the status bar in UMLFrame
        canvasPane.displayCoords(e.getX(), e.getY());

    }

    public void mouseDragged(MouseEvent e) {

    }
}
