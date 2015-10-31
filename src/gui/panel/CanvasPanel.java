package gui.panel;

import data.City;
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

    public CanvasPanel(StatusPanel sp) {

        this.setBackground(Color.gray);
        this.setPreferredSize(new Dimension(900, 900)); //Set size of panel
        this.statusPane = sp;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void paint(Graphics g) {
        if (g != null) {
            super.paint(g);
            Graphics2D g2 = (Graphics2D) g;
            ArrayList<City> cities = TourManager.getAll();
            for (int i = 0; i < cities.size(); i++) {
                City city = cities.get(i);
                g2.setPaint(Color.red);
                g2.fillOval(city.getX(), city.getY(), 15, 15);

                if (TourManager.isSolved() && cities.size() > i + 1) {
                    City nextCity = cities.get(i + 1);
                    Line2D lin = new Line2D.Float(city.getX() + 7, city.getY() + 7, nextCity.getX() + 7, nextCity.getY() + 7);
                    g2.draw(lin);
                }
            }
        }
    }
}
