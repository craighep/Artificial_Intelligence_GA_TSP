package gui.panel;

import gui.listener.ButtonListener;
import javax.swing.*;

import java.awt.Color;

public class ButtonPanel extends JPanel {

    private JLabel genLabel = new JLabel("--GA Options--");
    private JButton generateButton, clearButton, solveButton;
    public static JSlider generateAmount = new JSlider(JSlider.HORIZONTAL, 0, 50, 25);
    private JSeparator titleSep = new JSeparator(JSeparator.HORIZONTAL);
    private JSeparator topSep = new JSeparator(JSeparator.HORIZONTAL);
    private JSeparator bottom = new JSeparator(JSeparator.HORIZONTAL);

    public ButtonPanel(CanvasPanel cP) {

        //Initialises action listener for button components on panel
        ButtonListener buttonList = new ButtonListener(this, cP);

        //Set new layout for  panel
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        //Add visual components to panel
        this.add(genLabel);
        this.add(titleSep);
        this.add(topSep);
        this.add(bottom);

        //Set EAST border of panel 
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.black));

        generateAmount = new JSlider(JSlider.HORIZONTAL, 0, 50, 25);
        generateAmount.setMinorTickSpacing(2);
        generateAmount.setMajorTickSpacing(10);
        generateAmount.setPaintTicks(true);
        generateAmount.setPaintLabels(true);
        generateAmount.setLabelTable(generateAmount.createStandardLabels(10));
        this.add(generateAmount);

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

        layout.putConstraint(SpringLayout.NORTH, genLabel, 10, SpringLayout.NORTH, topSep);
        layout.putConstraint(SpringLayout.WEST, genLabel, 30, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, genLabel, -30, SpringLayout.EAST, this);

        layout.putConstraint(SpringLayout.NORTH, titleSep, 30, SpringLayout.NORTH, genLabel);
        layout.putConstraint(SpringLayout.WEST, titleSep, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, titleSep, -5, SpringLayout.EAST, this);

        layout.putConstraint(SpringLayout.NORTH, generateAmount, 15, SpringLayout.NORTH, titleSep);
        layout.putConstraint(SpringLayout.WEST, generateAmount, 12, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, generateAmount, -12, SpringLayout.EAST, this);
        
        layout.putConstraint(SpringLayout.NORTH, generateButton, 50, SpringLayout.NORTH, generateAmount);
        layout.putConstraint(SpringLayout.WEST, generateButton, 12, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, generateButton, -12, SpringLayout.EAST, this);

        layout.putConstraint(SpringLayout.NORTH, clearButton, 40, SpringLayout.NORTH, generateButton);
        layout.putConstraint(SpringLayout.WEST, clearButton, 12, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, clearButton, -12, SpringLayout.EAST, this);
        
        layout.putConstraint(SpringLayout.NORTH, bottom, 40, SpringLayout.NORTH, clearButton);
        layout.putConstraint(SpringLayout.WEST, bottom, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, bottom, -5, SpringLayout.EAST, this);
        
        layout.putConstraint(SpringLayout.NORTH, solveButton, 40, SpringLayout.NORTH, bottom);
        layout.putConstraint(SpringLayout.WEST, solveButton, 12, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, solveButton, -12, SpringLayout.EAST, this);

    }
}
