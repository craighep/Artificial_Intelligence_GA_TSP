package gui.panel;

import gui.listener.ButtonListener;
import javax.swing.*;

import java.awt.Color;

public class ButtonPanel extends JPanel {

    private JLabel titleText = new JLabel("--System Menu--");
    private JButton generateButton, clearButton, solveButton;
    private JSeparator titleSep = new JSeparator(JSeparator.HORIZONTAL);
    private JSeparator menuSep = new JSeparator(JSeparator.HORIZONTAL);
    private JSeparator drawSep = new JSeparator(JSeparator.HORIZONTAL);
    private JSeparator topSep = new JSeparator(JSeparator.HORIZONTAL);
    private JSeparator bottomSep = new JSeparator(JSeparator.HORIZONTAL);

    public Boolean addVar = false;
    public Boolean addRel = false;
    public Boolean delItem = false;
    private String directEnd;

    public ButtonPanel(CanvasPanel cP) {

        //Initialises action listener for button components on panel
        ButtonListener buttonList = new ButtonListener(this, cP);

        //Set new layout for  panel
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        //Add visual components to panel
        this.add(titleText);
        this.add(titleSep);
        this.add(menuSep);
        this.add(drawSep);
        this.add(topSep);
        this.add(bottomSep);

        //Set EAST border of panel 
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.black));

        generateButton = new JButton("Generate Points");
        this.add(generateButton);
        generateButton.addActionListener(buttonList);

        clearButton = new JButton("Clear Points");
        this.add(clearButton);
        clearButton.addActionListener(buttonList);

        solveButton = new JButton("Solve TSP");
        this.add(solveButton);
        solveButton.addActionListener(buttonList);

        layout.putConstraint(SpringLayout.NORTH, topSep, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, topSep, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, topSep, -5, SpringLayout.EAST, this);

        layout.putConstraint(SpringLayout.NORTH, titleText, 10, SpringLayout.NORTH, topSep);
        layout.putConstraint(SpringLayout.WEST, titleText, 30, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, titleText, -30, SpringLayout.EAST, this);

        layout.putConstraint(SpringLayout.NORTH, titleSep, 30, SpringLayout.NORTH, titleText);
        layout.putConstraint(SpringLayout.WEST, titleSep, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, titleSep, -5, SpringLayout.EAST, this);

        layout.putConstraint(SpringLayout.NORTH, generateButton, 15, SpringLayout.NORTH, titleSep);
        layout.putConstraint(SpringLayout.WEST, generateButton, 12, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, generateButton, -12, SpringLayout.EAST, this);

        layout.putConstraint(SpringLayout.NORTH, clearButton, 40, SpringLayout.NORTH, generateButton);
        layout.putConstraint(SpringLayout.WEST, clearButton, 12, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, clearButton, -12, SpringLayout.EAST, this);

        layout.putConstraint(SpringLayout.NORTH, solveButton, 40, SpringLayout.NORTH, clearButton);
        layout.putConstraint(SpringLayout.WEST, solveButton, 12, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, solveButton, -12, SpringLayout.EAST, this);

    }
}
