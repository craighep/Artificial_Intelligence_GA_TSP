package data;

import data.types.SelectionType;
import data.stats.LineChart;
import data.types.CrossoverType;
import java.util.ArrayList;

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
     * @param selectionType Type of selection
     * @param crossoverType Type of crossover
     * @return SolutionPath
     */
    public Path calculatePath(ArrayList<City> cities, double mutation,
            int populationEvolution, boolean elitsim, SelectionType selectionType,
            CrossoverType crossoverType) {

        Population pop = new Population(cities.size(), false);
        int initialDistance = pop.getFittest().getDistance();
        System.out.println("Initial distance: " + initialDistance);
        ArrayList<Path> bestEvolvedPaths = new ArrayList<>();
        ArrayList<Path> averageEvolvedPaths = new ArrayList<>();
        bestEvolvedPaths.add(pop.getFittest());
        averageEvolvedPaths.add(pop.getAverage());

        long start = System.nanoTime();
        // Start the evolutions, using parameters selected
        pop = evolvePopulation(pop, elitsim, mutation, selectionType, crossoverType);
        for (int i = 0; i < populationEvolution; i++) {
            pop = evolvePopulation(pop, elitsim, mutation, selectionType, crossoverType);
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

        // Export solution to csv
        TSPGenerator TSPGenerator = new TSPGenerator();
        TSPGenerator.generateCitiesCsv("output.csv", solution.getAllInPath());
        
        solution.setInitialDistance(initialDistance);
        return solution;
    }

    /**
     * Evolves a population by selecting two parent chromosomes, creating a
     * child and then mutating it based on options passed.
     *
     * @param pop Current population of paths
     * @param elitism Flag signifying whether to use elitism or not
     * @param mutationRate Chance between 0 and 1 of mutating the current tour
     * @param selectionType Type of selection means
     * @param crossoverType Type of crossover means
     * @return population
     */
    private Population evolvePopulation(Population pop, boolean elitism,
            double mutationRate, SelectionType selectionType, CrossoverType crossoverType) {
        Population newPopulation = new Population(pop.getPopulationSize(), true);

        SelectionTool selectionTool = new SelectionTool();
        CrossoverTool crossoverTool = new CrossoverTool();
        
        int offset = (elitism) ? 1 : 0;
        if (elitism) {
            newPopulation.setPath(0, pop.getFittest());
        }

        Path parent1, parent2;
        for (int i = offset; i < newPopulation.getPopulationSize();) {
            // perform selection
            parent1 = selectionTool.performSelection(selectionType, pop);
            parent2 = selectionTool.performSelection(selectionType, pop);
            
            if (parent1 != null && parent2 != null) {
                // perform crossover
                Path[] children = crossoverTool.performCrossover(crossoverType ,parent1, parent2);
                
                newPopulation.setPath(i++, children[0]);
                if (i < newPopulation.getPopulationSize()) {
                    newPopulation.setPath(i++, children[1]);
                }
            }
        }
        // mutatate population
        for (int i = offset; i < newPopulation.getPopulationSize(); i++) {
            mutate(newPopulation.getPath(i), mutationRate);
        }
        return newPopulation;
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
