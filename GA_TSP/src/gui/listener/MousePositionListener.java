package gui.listener;

import gui.panel.ButtonPanel;
import gui.panel.CanvasPanel;
import java.awt.event.*;
/**
 * Listens and updates the location of the mouse on the canvas panel.
 * Updates the status panel with the mouse coordinates on the canvas panel.
 * @author Craig
 */
public class MousePositionListener implements MouseMotionListener {

    private CanvasPanel canvasPane;

    /**
     * Initialises each pane to allow for methods such as showing mouse coords
     * to be used.
     * @param cp Canvas panel
     * @param bp Button panel
     */
    public MousePositionListener(CanvasPanel cp, ButtonPanel bp) {
        canvasPane = cp;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        canvasPane.displayCoords(e.getX(), e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }
}
