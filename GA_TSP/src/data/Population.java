package data;

/*
 * Population.java
 * Manages a population of candidate tours
 */
public class Population {

    // Holds population of tours
    Tour[] tours;

    // Construct a population
    public Population(int populationSize, boolean initialise) {
        tours = new Tour[populationSize];
        // If we need to initialise a population of tours do so
        if (initialise) {
            // Loop and create individuals
            for (int i = 0; i < populationSize(); i++) {
                Tour newTour = new Tour();
                newTour.generateIndividual();
                saveTour(i, newTour);
            }
        }
    }

    // Saves a tour
    void saveTour(int index, Tour tour) {
        tours[index] = tour;
    }

    // Gets a tour from population
    public Tour getTour(int index) {
        return tours[index];
    }

    // Gets the best tour in the population
    public Tour getFittest() {
        Tour fittest = tours[0];
        // Loop through individuals to find fittest
        for (int i = 1; i < populationSize(); i++) {
            if (fittest.getFitness() <= getTour(i).getFitness()) {
                fittest = getTour(i);
            }
        }
        return fittest;
    }
    
    // Gets a generic average tour
    public Tour getAverage() {
        // Loop through individuals to find fittest
        int distance = 0;
        double fitness = 0;
        for (int i = 1; i < populationSize(); i++) {
            distance += tours[i].getDistance();
            fitness += tours[i].getFitness();
        }
        Tour average = new Tour();
        average.setDistance(distance);
        average.setFitness(fitness);
        return average;
    }

    // Gets population size
    int populationSize() {
        return tours.length;
    }
}
