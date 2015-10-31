package gui.listener;

import data.GA;
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
                TourManager.randomlyGenerateCities(10);
                TourManager.setSolved(false);
                canvasPane.repaint();
                break;
            case "Solve TSP":
                GA.calculateTour(TourManager.getAll());
                TourManager.setSolved(true);
                canvasPane.repaint();
                break;
            case "Clear Points":
                TourManager.clearAll();
                TourManager.setSolved(false);
                canvasPane.repaint();
                break;
            case "Exit Program":
                canvasPane.exitProgram();
                break;
        }

    }

}
