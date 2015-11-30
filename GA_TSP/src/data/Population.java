package data;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Holds a population of paths.
 * Keeps all possible solutions at any stage of the computation, and holds the
 * size of possible solutions.
 * @author Craig
 */
public class Population {

    Path[] paths;

    /**
     * Constructs a new population of paths given a size. Can also use the current
     * best path to copy its contents into it.
     * @param size Number of paths to create
     * @param empty Flag deciding whether to leave tours null or not.
     */
    public Population(int size, boolean empty) {
        paths = new Path[size];
        if (!empty) {
            for (int i = 0; i < size; i++) {
                Path newpath = new Path();
                newpath.createPathShuffled();
                setPath(i, newpath);
            }
        }
    }

    /**
     * Saves a path to a given location in the population.
     * @param pos Location to save the path
     * @param path Path to be saved
     */
    final void setPath(int pos, Path path) {
        paths[pos] = path;
    }

    /**
     * Gets a path from the population given a location.
     * @param pos Location to get the path
     * @return path
     */
    public Path getPath(int pos) {
        return paths[pos];
    }
    
    /**
     * Gets all the paths in the population.
     * @return allPaths
     */
    public ArrayList<Path> getAllpaths(){
        return new ArrayList<>(Arrays.asList(paths));
    }

    /**
     * Gets the fittest (best) path in the population.
     * @return path
     */
    public Path getFittest() {
        Path fittest = paths[0];
        for (int i = 1; i < getPopulationSize(); i++) {
            if (fittest.getFitness() <= getPath(i).getFitness()) {
                fittest = getPath(i);
            }
        }
        return fittest;
    }
    
    /**
     * Calculates the average fitness and distance in the population and creates
     * a path to hold this information for statistical uses.
     * @return path
     */
    public Path getAverage() {
        int distance = 0;
        double fitness = 0;
        for (int i = 1; i < getPopulationSize(); i++) {
            distance += paths[i].getDistance();
            fitness += paths[i].getFitness();
        }
        Path average = new Path();
        average.setDistance(distance);
        average.setFitness(fitness);
        return average;
    }

    /**
     * Gets the number of paths in the population.
     * @return populationSize
     */
    final int getPopulationSize() {
        return paths.length;
    }

    double getTotalFitness() {
        double totalFitness = 0;
        for (Path path : paths) {
            totalFitness += path.getFitness();
        }
        return totalFitness;
    }
}
