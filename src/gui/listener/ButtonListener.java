package gui.listener;

import data.GA;
import data.Tour;
import data.TourManager;
import java.awt.event.*;
import gui.panel.*;

public class ButtonListener implements ActionListener {

    private CanvasPanel canvasPane;
    private ButtonPanel buttonPane;

    public ButtonListener(ButtonPanel bp, CanvasPanel cP) {
        canvasPane = cP;
        buttonPane = bp;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        String actionCommand = evt.getActionCommand();

        switch (actionCommand) {
            case "Generate Points":
                TourManager.clearAll();
                int generateAmount = ButtonPanel.generateAmount.getValue();
                TourManager.randomlyGenerateCities(generateAmount, 
                        canvasPane.getWidth(), canvasPane.getHeight());
                TourManager.setSolved(false);
                canvasPane.hideStats();
                canvasPane.repaint();
                break;
            case "Solve TSP":
                Tour solution = GA.calculateTour(TourManager.getAll());
                TourManager.setAll(solution.getAllInTour()); // Set current tour to solution for repaint
                TourManager.setSolved(true);
                canvasPane.showStats(solution);
                canvasPane.repaint();
                break;
            case "Clear Points":
                TourManager.clearAll();
                TourManager.setSolved(false);
                canvasPane.hideStats();
                canvasPane.repaint();
                break;
            case "Exit Program":
                canvasPane.exitProgram();
                break;
        }

    }

}
