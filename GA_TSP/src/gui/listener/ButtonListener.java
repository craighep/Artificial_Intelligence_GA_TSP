package gui.listener;

import data.GA;
import data.Path;
import data.PathSolution;
import data.types.CrossoverType;
import data.types.SelectionType;
import java.awt.event.*;
import gui.panel.*;

/**
 * A listener for buttons on the button panel in the GUI.
 * Calls methods to generate points, complete solutions, clear points or to close
 * the application.
 * @author Craig
 */
public class ButtonListener implements ActionListener {

    private CanvasPanel canvasPane;
    private ButtonPanel buttonPane;

    /**
     * Initialises each pane to allow for methods such as showing solution stats
     * to be used.
     * @param bp Button panel
     * @param cP Canvas panel
     */
    public ButtonListener(ButtonPanel bp, CanvasPanel cP) {
        canvasPane = cP;
        buttonPane = bp;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        String actionCommand = evt.getActionCommand();

        switch (actionCommand) {
            case "Generate Points":
                PathSolution.clearAll();
                int generateAmount = ButtonPanel.generateAmount.getValue();
                PathSolution.randomlyGenerateCities(generateAmount, 
                        canvasPane.getWidth(), canvasPane.getHeight());
                PathSolution.setSolved(false);
                canvasPane.hideStats();
                canvasPane.repaint();
                break;
            case "Solve TSP":
                double mutationRate = Double.valueOf(ButtonPanel.mutationInput.getText());
                int populationEvolution = Integer.parseInt(ButtonPanel.evolutionInput.getText());
                SelectionType selectionType = SelectionType.fromString(ButtonPanel.selectionType.getSelectedItem().toString());
                CrossoverType crossoverType = CrossoverType.fromString(ButtonPanel.crossoverType.getSelectedItem().toString());
                boolean elitism = ButtonPanel.elitsimEnabled.isSelected();
                GA TSPGeneticAlgorithm = new GA();
                Path solution = TSPGeneticAlgorithm.calculatePath(PathSolution.getAll(), 
                        mutationRate, populationEvolution, elitism, selectionType,
                        crossoverType);
                PathSolution.setAll(solution.getAllInPath()); // Set current path to solution for repaint
                PathSolution.setSolved(true);
                canvasPane.showStats(solution);
                canvasPane.repaint();
                break;
            case "Clear Points":
                PathSolution.clearAll();
                PathSolution.setSolved(false);
                canvasPane.hideStats();
                canvasPane.repaint();
                break;
            case "Exit Program":
                canvasPane.exitProgram();
                break;
        }

    }

}
