package gui.panel;

import data.SelectionType;
import gui.listener.ButtonListener;
import javax.swing.*;
import java.awt.Color;

/**
 * Provides the buttons and options for the GA.
 * Creates the labels and inputs on the button panel, each placed using the 
 * Spring layout mechanism. 
 * @author Craig
 */
public class ButtonPanel extends JPanel {

    private JLabel genLabel = new JLabel("--GA Options--");
    private JLabel mutationLabel = new JLabel("Mutation Rate");
    private JLabel evolutionLabel = new JLabel("Population Evolution");
    private JLabel selectionLabel = new JLabel("Selection Type");
    private JButton generateButton, clearButton, solveButton;

    public static JSlider generateAmount = new JSlider(JSlider.HORIZONTAL, 0, 50, 25);
    public static JTextField mutationInput = new JTextField("0.015");
    public static JTextField evolutionInput = new JTextField("1000");
    public static JComboBox selectionType = new JComboBox(new String[] {
        SelectionType.RANK.getName(), SelectionType.pathNAMENT.getName(), 
        SelectionType.ROULETTEWHEEL.getName()});
    
    public static JCheckBox elitsimEnabled = new JCheckBox("Elitism Enabled", false);
    private JSeparator titleSep = new JSeparator(JSeparator.HORIZONTAL);
    private JSeparator topSep = new JSeparator(JSeparator.HORIZONTAL);
    private JSeparator bottom = new JSeparator(JSeparator.HORIZONTAL);

    /**
     * Creates a new Button panel to hold user options for the GA. Adds a listener
     * to each button or option on the panel.
     * @param cP Canvas panel
     */
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
        
        this.add(mutationLabel);
        this.add(mutationInput);
        this.add(evolutionLabel);
        this.add(evolutionInput);
        this.add(selectionLabel);
        this.add(selectionType);
        this.add(elitsimEnabled);
        
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
        
        layout.putConstraint(SpringLayout.NORTH, mutationLabel, 40, SpringLayout.NORTH, bottom);
        layout.putConstraint(SpringLayout.WEST, mutationLabel, 12, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, mutationLabel, -12, SpringLayout.EAST, this);
        
        layout.putConstraint(SpringLayout.NORTH, mutationInput, 20, SpringLayout.NORTH, mutationLabel);
        layout.putConstraint(SpringLayout.WEST, mutationInput, 12, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, mutationInput, -12, SpringLayout.EAST, this);
        
        layout.putConstraint(SpringLayout.NORTH, evolutionLabel, 40, SpringLayout.NORTH, mutationInput);
        layout.putConstraint(SpringLayout.WEST, evolutionLabel, 12, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, evolutionLabel, -12, SpringLayout.EAST, this);
        
        layout.putConstraint(SpringLayout.NORTH, evolutionInput, 20, SpringLayout.NORTH, evolutionLabel);
        layout.putConstraint(SpringLayout.WEST, evolutionInput, 12, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, evolutionInput, -12, SpringLayout.EAST, this);
        
        layout.putConstraint(SpringLayout.NORTH, selectionLabel, 40, SpringLayout.NORTH, evolutionInput);
        layout.putConstraint(SpringLayout.WEST, selectionLabel, 12, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, selectionLabel, -12, SpringLayout.EAST, this);
        
        layout.putConstraint(SpringLayout.NORTH, selectionType, 20, SpringLayout.NORTH, selectionLabel);
        layout.putConstraint(SpringLayout.WEST, selectionType, 12, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, selectionType, -12, SpringLayout.EAST, this);
        
        layout.putConstraint(SpringLayout.NORTH, elitsimEnabled, 40, SpringLayout.NORTH, selectionType);
        layout.putConstraint(SpringLayout.WEST, elitsimEnabled, 12, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, elitsimEnabled, -12, SpringLayout.EAST, this);
        
        layout.putConstraint(SpringLayout.NORTH, solveButton, 40, SpringLayout.NORTH, elitsimEnabled);
        layout.putConstraint(SpringLayout.WEST, solveButton, 12, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, solveButton, -12, SpringLayout.EAST, this);

    }
}
