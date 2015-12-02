/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import data.types.CrossoverType;
import static data.types.CrossoverType.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Craig
 */
public class CrossoverTool {

    public Path[] performCrossover(CrossoverType crossoverType, Path parent1, Path parent2) {
        Path[] children = new Path[2];
        switch (crossoverType) {
            case ORDERED:
                children = orderedCrossover(parent1, parent2);
                break;
            case CYCLIC:
                children = CyclicCrossover(parent1, parent2);
                break;
        }
        return children;
    }

    private Path[] orderedCrossover(Path parent1, Path parent2) {
        final int size = parent1.pathSize();

        int number1 = (int) (Math.random() * (size-1));
        int number2 = (int) (Math.random() * size);

        final int start = Math.min(number1, number2);
        final int end = Math.max(number1, number2);

        ArrayList<City> parent1Rep = parent1.getAllInPath();
        ArrayList<City> parent2Rep = parent2.getAllInPath();
        ArrayList<City> child1Rep = new ArrayList<>(size);
        ArrayList<City> child2Rep = new ArrayList<>(size);

        child1Rep.addAll(parent1Rep.subList(start, end));
        child2Rep.addAll(parent2Rep.subList(start, end));

        int currentCityIndex = 0;
        City currentCityInTour1;
        City currentCityInTour2;
        for (int i = 0; i < size; i++) {

            // get the index of the current city
            currentCityIndex = (end + i) % size;

            // get the city at the current index in each of the two parent tours
            currentCityInTour1 = parent1.getCity(currentCityIndex);
            currentCityInTour2 = parent2.getCity(currentCityIndex);

            // if child 1 does not already contain the current city in tour 2, add it
            if (!child1Rep.contains(currentCityInTour2)) {
                child1Rep.add(currentCityInTour2);
            }

            // if child 2 does not already contain the current city in tour 1, add it
            if (!child2Rep.contains(currentCityInTour1)) {
                child2Rep.add(currentCityInTour1);
            }
        }
        Collections.rotate(child1Rep, start);
        Collections.rotate(child2Rep, start);
        Path child1 = new Path(child1Rep);
        Path child2 = new Path(child2Rep);
        return new Path[]{child1, child2};
    }

    protected Path[] CyclicCrossover(Path parent1, Path parent2){
        final int length = parent1.getAllInPath().size();

        ArrayList<City> parent1Rep = parent1.getAllInPath();
        ArrayList<City> parent2Rep = parent2.getAllInPath();
        ArrayList<City> child1Rep = new ArrayList<City>(parent1.getAllInPath());
        ArrayList<City> child2Rep = new ArrayList<City>(parent2.getAllInPath());

        // the set of all visited indices so far
        Set<Integer> visitedIndices = new HashSet<Integer>(length);
        // the indices of the current cycle
        List<Integer> indices = new ArrayList<Integer>(length);

        // determine the starting index
        int idx = (int) (Math.random() * (length-1));
        int cycle = 1;

        while (visitedIndices.size() < length) {
            indices.add(idx);

            City item = parent2Rep.get(idx);
            idx = parent1Rep.indexOf(item);

            while (idx != indices.get(0)) {
                // add that index to the cycle indices
                indices.add(idx);
                // get the item in the second parent at that index
                item = parent2Rep.get(idx);
                // get the index of that item in the first parent
                idx = parent1Rep.indexOf(item);
            }

            // for even cycles: swap the child elements on the indices found in this cycle
            if (cycle++ % 2 != 0) {
                for (int i : indices) {
                    City tmp = child1Rep.get(i);
                    child1Rep.set(i, child2Rep.get(i));
                    child2Rep.set(i, tmp);
                }
            }

            visitedIndices.addAll(indices);
            // find next starting index: last one + 1 until we find an unvisited index
            idx = (indices.get(0) + 1) % length;
            while (visitedIndices.contains(idx) && visitedIndices.size() < length) {
                idx++;
                if (idx >= length) {
                    idx = 0;
                }
            }
            indices.clear();
        }
        Path child1 = new Path(child1Rep);
        Path child2 = new Path(child2Rep);
        return new Path[]{child1, child2};
    }
}
