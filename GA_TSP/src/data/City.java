package data;
/**
 * A node on the travelling salesman path. Holds the x and y coordinates of
 * the city, and the ID for identifying the position in a solution.
 * @author Craig
 */
public class City {

    private int x;
    private int y;
    private int id;

    /**
     * Constructs a new city with location x and y.
     * @param x x coord
     * @param y y coord
     */
    public City(int x, int y) {
        this.x = x;
        this.y = y;
        this.id = PathSolution.getSize()+1;
    }

    /**
     * Returns the x coordinate.
     * @return x
     */
    public int getX() {
        return this.x;
    }

    /**
     * Returns the y coordinate.
     * @return y
     */
    public int getY() {
        return this.y;
    }
    
    /**
     * Returns the ID of the coordinate.
     * @return ID
     */
    public int getID() {
        return this.id;
    }

    /**
     * Calculates the distance between cities using Pythagoras' theorem.
     * @param city City to calculate distance from
     * @return distance 
     */
    public double getDistanceBetween(City city) {
        int xDistance = Math.abs(this.x-city.getX());
        int yDistance = Math.abs(this.y-city.getY());
        return Math.sqrt((xDistance * xDistance) + (yDistance * yDistance));
    }
}
