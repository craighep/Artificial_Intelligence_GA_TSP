package gui.listener;

import data.City;
import data.GA;
import data.Path;
import data.PathSolution;
import data.SelectionType;
import java.awt.event.*;
import gui.panel.*;

/**
 * Listens on clicks on the main panel to place new points(cities).
 * Adds a new point to the map and initial solution path when the mouse is 
 * pressed on a given location within the canvas panel.
 * @author Craig
 */
public class MouseInterListener implements MouseListener {

    private CanvasPanel canvasPane;
    private ButtonPanel buttonPane;
    private int[] clickArray = new int[4];
    int count;
    int arrayCount = 0;

    /**
     * Initialises each pane to allow for methods such as showing solution stats
     * to be used.
     * @param cp Canvas panel
     * @param bp Button panel
     */
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
        PathSolution.addCity(city);
        if(PathSolution.isSolved()){
            double mutationRate = Double.valueOf(ButtonPanel.mutationInput.getText());
            int populationEvolution = Integer.parseInt(ButtonPanel.evolutionInput.getText());
            SelectionType selectionType = SelectionType.fromString(ButtonPanel.selectionType.getSelectedItem().toString());
            boolean elitism = ButtonPanel.elitsimEnabled.isSelected();
            GA TSPGeneticAlgorithm = new GA();
            Path solution = TSPGeneticAlgorithm.calculatePath(PathSolution.getAll(),
                    mutationRate, populationEvolution, elitism, selectionType);
            PathSolution.setAll(solution.getAllInPath()); // Set current path to solution for repaint
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
