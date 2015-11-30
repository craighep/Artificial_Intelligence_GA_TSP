package data;

import data.stats.LineChart;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The Genetic algorithm for solving the Travelling salesman problem. Selects
 * chromosomes, evolves and mutates them to find the best possible solution
 * depending on the selection technique chosen.
 *
 * @author Craig
 */
public class GA {

    /**
     * Takes an array of cities to be visited and generates a solution to the
     * travelling salesman problem. Takes additional parameters to change how
     * the GA works, and also produces graphs and statistics.
     *
     * @param cities List of cities
     * @param mutation Rate at which to mutate solutions
     * @param populationEvolution Number of evolutions
     * @param elitsim Elitism toggle
     * @param selectionType Type of crossover selection
     * @return SolutionPath
     */
    public Path calculatePath(ArrayList<City> cities, double mutation,
            int populationEvolution, boolean elitsim, SelectionType selectionType) {

        Population pop = new Population(cities.size(), true);
        int initialDistance = pop.getFittest().getDistance();
        System.out.println("Initial distance: " + initialDistance);
        ArrayList<Path> bestEvolvedPaths = new ArrayList<>();
        ArrayList<Path> averageEvolvedPaths = new ArrayList<>();
        bestEvolvedPaths.add(pop.getFittest());
        averageEvolvedPaths.add(pop.getAverage());

        long start = System.nanoTime();
        // Start the evolutions, using parameters selected
        pop = evolvePopulation(pop, elitsim, mutation, selectionType);
        for (int i = 0; i < populationEvolution; i++) {
            pop = evolvePopulation(pop, elitsim, mutation, selectionType);
            bestEvolvedPaths.add(pop.getFittest());
            averageEvolvedPaths.add(pop.getAverage());
        }
        Path solution = pop.getFittest();

        // Print final results
        long end = System.nanoTime();
        System.out.println("Took: " + ((end - start) / 1000000) + "ms");
        System.out.println("Finished");
        System.out.println("Final distance: " + solution.getDistance());
        LineChart fitnessChart = new LineChart();

        // Export statitics to graphs
        fitnessChart.exportDistanceGraph(bestEvolvedPaths, averageEvolvedPaths);

        solution.setInitialDistance(initialDistance);
        return solution;
    }

    /**
     * Evolves a population by selecting two parent chromosomes, creating a
     * child and then mutating it based on options passed.
     * @param pop Current population of paths
     * @param elitism Flag signifying whether to use elitism or not
     * @param mutationRate Chance between 0 and 1 of mutating the current tour
     * @param selectionType Type of selection means
     * @return population
     */
    private Population evolvePopulation(Population pop, boolean elitism,
            double mutationRate, SelectionType selectionType) {
        Population newPopulation = new Population(pop.getPopulationSize(), false);

        int offset = (elitism) ? 1 : 0;
        if (elitism)
            newPopulation.setPath(0, pop.getFittest());

        Path parent1, parent2;
        for (int i = offset; i < newPopulation.getPopulationSize(); i++) {
            switch (selectionType) {
                // Select parents
                case RANK:
                    parent1 = rankSelection(pop);
                    parent2 = rankSelection(pop);
                    break;
                case pathNAMENT:
                    parent1 = tournamentSelection(pop);
                    parent2 = tournamentSelection(pop);
                    break;
                case ROULETTEWHEEL:
                    parent1 = rouletteWheelSelection(pop);
                    parent2 = rouletteWheelSelection(pop);
                    break;
                default:
                    parent1 = null;
                    parent2 = null;
                    break;
            }
            if (parent1 != null && parent2 != null) {
                Path child = crossover(parent1, parent2);
                newPopulation.setPath(i, child);
            }
        }
        for (int i = offset; i < newPopulation.getPopulationSize(); i++) {
            mutate(newPopulation.getPath(i), mutationRate);
        }
        return newPopulation;
    }

    /**
     * Selects a tour from the population using tournament selection using 5 
     * random paths from the current population.
     * @param pop Current population of paths
     * @return path
     */
    private Path tournamentSelection(Population pop) {
        int tournamentSize = 5;
        Population tournament = new Population(tournamentSize, false);
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.getPopulationSize());
            tournament.setPath(i, pop.getPath(randomId));
        }
        return tournament.getFittest();
    }

    /**
     * Selects a tour from the population using roulette wheel selection. A random
     * number is created 
     * @param pop Current population of paths
     * @return path
     */
    private Path rouletteWheelSelection(Population pop) {
        double totalFitness = pop.getTotalFitness();
        double rouletteBall = Math.random() * totalFitness;
        for (int i = 0; i < pop.getPopulationSize(); i++) {
            Path path = pop.getPath(i);
            rouletteBall -= path.getFitness();
            if (rouletteBall <= 0) {
                return path;
            }
        }
        return null;
    }

    /**
     * Selects a tour from the population using tournament selection using 5 
     * random paths from the current population.
     * @param pop Current population of paths
     * @return path
     */
    private Path rankSelection(Population pop) {
        ArrayList<Path> paths = pop.getAllpaths();
        Collections.sort(paths);
        for (int i = 0; i < paths.size(); i++) {
            Path rankedpath = paths.get(i);
            rankedpath.setFitness(paths.size() - i);
            pop.setPath(i, rankedpath);
        }
        return rouletteWheelSelection(pop);
    }

    private Path crossover(Path parent1, Path parent2) {
        Path child = new Path();

        int startPos = (int) (Math.random() * parent1.pathSize());
        int endPos = (int) (Math.random() * parent1.pathSize());

        for (int i = 0; i < child.pathSize(); i++) {
            if (startPos < endPos && i > startPos && i < endPos) {
                child.setCity(i, parent1.getCity(i));
            } else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    child.setCity(i, parent1.getCity(i));
                }
            }
        }

        for (int i = 0; i < parent2.pathSize(); i++) {
            if (!child.containsCity(parent2.getCity(i))) {
                for (int ii = 0; ii < child.pathSize(); ii++) {
                    if (child.getCity(ii) == null) {
                        child.setCity(ii, parent2.getCity(i));
                        break;
                    }
                }
            }
        }
        return child;
    }

    private void mutate(Path path, double mutationRate) {
        for (int pathPos1 = 0; pathPos1 < path.pathSize(); pathPos1++) {
            if (Math.random() < mutationRate) {
                int pathPos2 = (int) (path.pathSize() * Math.random());

                City city1 = path.getCity(pathPos1);
                City city2 = path.getCity(pathPos2);

                // Swap them around
                path.setCity(pathPos2, city1);
                path.setCity(pathPos1, city2);
            }
        }
    }
}
