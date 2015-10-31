package data;

import java.awt.Graphics;

/*
 * City.java
 * Models a city
 */
public class City {

    int x;
    int y;

    // Constructs a city at chosen x, y location
    public City(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Gets city's x coordinate
    public int getX() {
        return this.x;
    }

    // Gets city's y coordinate
    public int getY() {
        return this.y;
    }

    // Gets the distance to given city
    public double distanceTo(City city) {
        int xDistance = Math.abs(getX() - city.getX());
        int yDistance = Math.abs(getY() - city.getY());
        double distance = Math.sqrt((xDistance * xDistance) + (yDistance * yDistance));

        return distance;
    }
}
