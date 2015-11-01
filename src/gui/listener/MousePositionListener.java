package gui.listener;

import gui.panel.ButtonPanel;
import gui.panel.CanvasPanel;
import java.awt.event.*;

public class MousePositionListener implements MouseMotionListener {

    private CanvasPanel canvasPane;

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
