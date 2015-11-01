package gui.listener;

import data.City;
import data.GA;
import data.Tour;
import data.TourManager;
import java.awt.event.*;
import gui.panel.*;

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

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) throws NullPointerException {
        City city = new City(e.getX(), e.getY());
        TourManager.addCity(city);
        if(TourManager.isSolved()){
            Tour solution = GA.calculateTour(TourManager.getAll());
            TourManager.setAll(solution.getAllInTour()); // Set current tour to solution for repaint
            canvasPane.showStats(solution);
        }  
        canvasPane.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
