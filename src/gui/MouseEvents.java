package gui;


import data.City;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MouseEvents implements MouseListener {

    public ArrayList<City> cities;
    
    public MouseEvents(){
        cities = new ArrayList<>();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        int x=e.getX();
        int y=e.getY();
        City city = new City(x, y);
        cities.add(city);
        System.out.println(x+","+y);//these co-ords are relative to the component
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}