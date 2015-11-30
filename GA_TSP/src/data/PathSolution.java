package data;

import java.util.ArrayList;
import java.util.Random;

/**
 * Manages the current solution to a path, making it available
 * to various classes for statistics collection.
 * @author Craig
 */
public class PathSolution extends ArrayList<City> {

    private static ArrayList cities = new ArrayList<>();
    private static boolean isSolved = false;

    /**
     * Adds a given city to the solution path.
     * @param city A city
     */
    public static void addCity(City city) {
        cities.add(city);
    }

    /**
     * Gets all the cities in the current solution.
     * @return cities
     */
    public static ArrayList getAll() {
        return cities;
    }

    /**
     * Sets all the cities in the current solution.
     * @param cityList List of cities
     */
    public static void setAll(ArrayList<City> cityList) {
        cities = cityList;
    }

    /**
     * Gets a city in the path solution by index.
     * @param index Location in the tour
     * @return city
     */
    public static City getCity(int index) {
        return (City) cities.get(index);
    }

    /**
     * Gets the size of the path.
     * @return tourSize
     */
    public static int getSize() {
        return cities.size();
    }

    /**
     * Randomly creates a path using a (given number of) random points in between 
     * x and y values of 0 and a number passed.
     * @param amount Number of points to generate
     * @param maxX Maximum x value of location
     * @param maxY Maximum y value of location
     */
    public static void randomlyGenerateCities(int amount, int maxX, int maxY) {
        Random randomGen = new Random();
        for (int i = 0; i < amount; i++) {
            int x = randomGen.nextInt(maxX);
            int y = randomGen.nextInt(maxY);
            City city = new City(x, y);
            addCity(city);
        }
    }

    /**
     * Clears the current path solution
     */
    public static void clearAll() {
        getAll().clear();
    }

    /**
     * Returns the flag signifying if the path is declared as solved.
     * @return isSolved
     */
    public static boolean isSolved() {
        return isSolved;
    }

    /**
     * Declares the path to be solved or not.
     * @param solved Flag for declaring solved or not
     */
    public static void setSolved(boolean solved) {
        isSolved = solved;
    }
}
