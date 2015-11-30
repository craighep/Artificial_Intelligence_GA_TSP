/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import data.types.SelectionType;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Craig
 */
public class SelectionTool {

    public Path performSelection(SelectionType selectionType, Population pop) {
        Path parent;
        switch (selectionType) {
            case RANK:
                parent = rankSelection(pop);
                break;
            case TOURNAMENT:
                parent = tournamentSelection(pop);
                break;
            case ROULETTEWHEEL:
                parent = rouletteWheelSelection(pop);
                break;
            default:
                parent = null;
                break;
        }
        return parent;
    }
    
    /**
     * Selects a tour from the population using tournament selection using 5
     * random paths from the current population.
     *
     * @param pop Current population of paths
     * @return path
     */
    private Path tournamentSelection(Population pop) {
        int tournamentSize = 5;
        Population tournament = new Population(tournamentSize, true);
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.getPopulationSize());
            tournament.setPath(i, pop.getPath(randomId));
        }
        return tournament.getFittest();
    }

    /**
     * Selects a tour from the population using roulette wheel selection. A
     * random number is created
     *
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
     *
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

}
