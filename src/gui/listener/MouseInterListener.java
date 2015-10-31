package gui.listener;

import data.City;
import data.TourManager;
import java.awt.event.*;
import gui.panel.*;
import java.awt.Rectangle;

public class MouseInterListener implements MouseListener {

    private CanvasPanel canvasPane;

    private ButtonPanel buttonPane;

    private int[] clickArray = new int[4];

    int count;
    int arrayCount = 0;

    public MouseInterListener(CanvasPanel cp, ButtonPanel bp) {
        canvasPane = cp;
        buttonPane = bp;
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) throws NullPointerException {
        City city = new City(e.getX(), e.getY());
        TourManager.addCity(city);
        canvasPane.repaint();
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }
}
