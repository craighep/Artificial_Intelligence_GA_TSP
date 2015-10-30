package gui;


import data.City;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Runnable extends JFrame {

    public ArrayList<City> cities;
    
    public Runnable() {
       
        MouseEvents mouseListener = new MouseEvents();
        OptionEvents optionsListener = new OptionEvents();
        
        JPanel pointsPanel = new JPanel(new BorderLayout());
        pointsPanel.add(new JLabel("Path:", SwingConstants.LEFT), BorderLayout.PAGE_START);
        pointsPanel.addMouseListener(mouseListener);

        JLabel inputField = new JLabel();
        JButton sendBtn = new JButton("Create path");
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.LINE_AXIS));
        inputPanel.add(inputField);
        inputPanel.add(sendBtn);

        JPanel youLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        youLabelPanel.add(new JLabel("Co-ords:"));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        
        
        mainPanel.add(pointsPanel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(youLabelPanel);
        mainPanel.add(inputPanel);
        
        this.add(mainPanel);
        this.setPreferredSize(new Dimension(600, 600));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);

        // define the position
        int locX = 200;
        int locY = 200;

        // draw a line (there is no drawPoint..)
        g.drawLine(locX, locY, locX, locY); 
    }

    public static void main(String[] args) {
        Runnable test = new Runnable(); 
    }
}