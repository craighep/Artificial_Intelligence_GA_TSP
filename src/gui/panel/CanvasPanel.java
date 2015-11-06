package gui.panel;

import data.City;
import data.Tour;
import data.TourManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.*;

public class CanvasPanel extends JPanel implements ActionListener {

    private final StatusPanel statusPane;
    private JLabel totalLabel = new JLabel("");
    private JLabel initialLabel = new JLabel("");
    SpringLayout layout = new SpringLayout();
    private JSeparator statusSep = new JSeparator(JSeparator.VERTICAL);

    public CanvasPanel(StatusPanel sp) {
        SpringLayout layout = new SpringLayout();
        
        this.setBackground(Color.gray);
        this.setPreferredSize(new Dimension(800, 800)); //Set size of panel
        this.statusPane = sp;
        
        //Initialise and add components to panel
        this.setLayout(layout);
        this.add(totalLabel);
        totalLabel.setForeground(Color.white);
        this.add(initialLabel);
        initialLabel.setForeground(Color.white);

        layout.putConstraint(SpringLayout.SOUTH, initialLabel, -50, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.WEST, initialLabel, 10, SpringLayout.WEST, this);
        
        layout.putConstraint(SpringLayout.SOUTH, totalLabel, 30, SpringLayout.SOUTH, initialLabel);
        layout.putConstraint(SpringLayout.WEST, totalLabel, 10, SpringLayout.WEST, this);
    }

    public void exitProgram() {

        //Display question dialog
        int exit = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "UMLator | Exit", JOptionPane.YES_NO_OPTION);

        //If user selects "yes"
        if (exit == JOptionPane.YES_OPTION) {

            //Terminate the program
            System.exit(0);
        }
    }

    public void displayCoords(int x, int y) {
        //Update mouse coordinates display
        statusPane.setCoords(x, y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void paint(Graphics g) {
        if (g != null) {
            super.paint(g);
            Graphics2D g2 = (Graphics2D) g;
            ArrayList<City> cities = TourManager.getAll();
            for (int i = 0; i < cities.size(); i++) {
                City city = cities.get(i);
                g2.setPaint(Color.white);
                g2.drawString(String.valueOf(city.getID()), city.getX(), city.getY());
                g2.drawString("[" +city.getX() + "," + city.getY() + "]", city.getX(), city.getY()+30);
                g2.setPaint(Color.blue);
                g2.fillOval(city.getX(), city.getY(), 15, 15);
                
                if (TourManager.isSolved() && cities.size() > i + 1) {
                    City nextCity = cities.get(i + 1);
                    Line2D lin = new Line2D.Float(city.getX() + 7, city.getY() + 7, nextCity.getX() + 7, nextCity.getY() + 7);
                    g2.draw(lin);
                }
            }
            // then draw line back to starting point
            if(cities.size() > 1 && TourManager.isSolved()){
                City lastCity = cities.get(cities.size()-1);
                City firstCity = cities.get(0);
                Line2D lin = new Line2D.Float(lastCity.getX() + 7, lastCity.getY() + 7, firstCity.getX() + 7, firstCity.getY() + 7);
                g2.draw(lin);
            }
        }    }

    public void showStats(Tour tour) {
        totalLabel.setText("Total Solution Distance: " + tour.getDistance());
        initialLabel.setText("Initial Order Distance: " + tour.getInitialDistance());
    }
    
    public void hideStats(){
        totalLabel.setText("");
        initialLabel.setText("");
     }
}
