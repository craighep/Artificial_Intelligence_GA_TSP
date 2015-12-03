package data;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Stores a possible solution.
 * Contains a list of nodes in a path that forms a route around all nodes 
 * provided initially.
 * @author Craig
 */
public class Path extends Chromosome implements Comparable {

    private ArrayList path = new ArrayList();
    private double fitness = 0;
    private int distance = 0;
    private int initialDistance = 0;

    /**
     * Construct a new empty path.
     */
    public Path() {
        for (int i = 0; i < PathSolution.getSize(); i++) {
            path.add(null);
        }
    }

    /**
     * Construct a path from a current path.
     * @param path existing path
     */
    public Path(ArrayList path) {
        this.path = path;
    }

    /**
     * Gets a given city in a path by location.
     * @param pos Location of city in path
     * @return city
     */
    public City getCity(int pos) {
        return (City) path.get(pos);
    }

    /**
     * Gets all cities in a path.
     * @return path
     */
    public ArrayList getAllInPath() {
        return path;
    }
    
    /**
     * Adds a city to the path.
     * @param city A city
     */
    public void addCity(City city){
        path.add(city);
    }

    /**
     * Sets a city to a certain location in the tour.
     * @param pos Location to add the new city
     * @param city A city
     */
    public void setCity(int pos, City city) {
        path.set(pos, city);
        fitness = 0;
        distance = 0;
    }
    
    /**
     * Generates a new path from the current best path, and shuffles it.
     */
    public void createPathShuffled() {
        for (int cityIndex = 0; cityIndex < PathSolution.getSize(); cityIndex++) {
            setCity(cityIndex, PathSolution.getCity(cityIndex));
        }
        Collections.shuffle(path);
    }

    /**
     * Get the fitness of the path.
     * @return fitness
     */
    public double getFitness() {
        if (fitness == 0) {
            fitness = 1 / (double) getDistance();
        }
        return fitness;
    }
    
    /**
     * Sets the initial distance of the path when cities are initially added.
     * @param distance Initial distance of path
     */
    public void setInitialDistance(int distance) {
        initialDistance = distance;
    }
    
    /**
     * Gets the initial distance of the path when cities are initially added.
     * @return distance
     */
    public int getInitialDistance(){
        return initialDistance;
    }

    /**
     * Gets the current path total distance.
     * @return distance
     */
    public int getDistance() {
        if (distance == 0) {
            for (int cityIndex = 0; cityIndex < pathSize(); cityIndex++) {
                City fromCity = getCity(cityIndex);
                City destinationCity;
                // If there is no next city, calculate distance back to first one
                if (cityIndex + 1 < pathSize()) {
                    destinationCity = getCity(cityIndex + 1);
                } else {
                    destinationCity = getCity(0);
                }
                distance += fromCity.getDistanceBetween(destinationCity);
            }
        }
        return distance;
    }
    
    /**
     * Sets the total distance of the current path.
     * @param distance Total distance of path
     */
    public void setDistance(int distance){
        this.distance = distance;
    }
    
    /**
     * Sets the fitness of the current path.
     * @param fitness The fitness of the path
     */
    public void setFitness(double fitness){
        this.fitness = fitness;
    }

    /**
     * Returns the amount of cities in the path.
     * @return pathSize
     */
    public int pathSize() {
        return path.size();
    }

    /**
     * Returns a flag indicating whether a given city exists in the current path.
     * @param city A city
     * @return isInPath
     */
    public boolean containsCity(City city) {
        return path.contains(city);
    }

    @Override
    public String toString() {
        String geneString = "|";
        for (int i = 0; i < pathSize(); i++) {
            geneString += getCity(i) + "|";
        }
        return geneString;
    }
    
    @Override
    public int compareTo(Object t) {
        Path compariblePath = (Path)t;
        return(this.distance - compariblePath.getDistance());   
    }
}
