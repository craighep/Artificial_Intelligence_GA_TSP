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
 * Class holds all the crossover methods for the GA. Has methods for both 
 * ordered and cyclic crossovers.
 * @author Craig
 */
public class CrossoverTool {

    /**
     * Method takes in the crossover type, and then calls the appropriate method.
     * Returns the two children created from the two parents.
     * @param crossoverType Type of crossover to be used
     * @param parent1 Parent one to create children from
     * @param parent2 Parent two to create children from
     * @return children
     */
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
    
    /*
     * Performs ordered crossover on two parent chromosomes to create children.
     * Performs a two point crossover, and then checks if there are missing points
     * in the new children to avoid invalid paths. Rotates the child paths to ensure
     * the swapped cities remain in the same indexed position as when they started.
     * @param parent1 Parent one to create children from
     * @param parent2 Parent two to create children from
     * @return children
    */
    private Path[] orderedCrossover(Path parent1, Path parent2) {
        int size = parent1.pathSize();
        int number1 = (int) (Math.random() * (size-1));
        int number2 = (int) (Math.random() * size);
        int start, end;
        // ensure random start is smaller than end
        if(number1 > number2){
            start = number2;
            end = number1;
        }
        else{
            start = number1;
            end = number2;
        }
        // get raw city list
        ArrayList<City> parent1Rep = parent1.getAllInPath();
        ArrayList<City> parent2Rep = parent2.getAllInPath();
        // copy parent paths into children paths, using start and end points only
        ArrayList<City> child1Rep = new ArrayList<>(size);
        ArrayList<City> child2Rep = new ArrayList<>(size);
        child1Rep.addAll(parent1Rep.subList(start, end));
        child2Rep.addAll(parent2Rep.subList(start, end));

        int currentCityIndex;
        City current1;
        City current2;
        for (int i = 0; i < size; i++) {
            // get the remainder from mutliples of the end point plus the nth run from the total path size
            currentCityIndex = (end + i) % size;
            // get the city at the current index in each of the two parent tours
            current1 = parent1.getCity(currentCityIndex);
            current2 = parent2.getCity(currentCityIndex);
            // if child 1 does not already contain the current city in tour 2, add it
            if (!child1Rep.contains(current2)) {
                child1Rep.add(current2);
            }
            // if child 2 does not already contain the current city in tour 1, add it
            if (!child2Rep.contains(current1)) {
                child2Rep.add(current1);
            }
        }
        Collections.rotate(child1Rep, start);
        Collections.rotate(child2Rep, start);
        Path child1 = new Path(child1Rep);
        Path child2 = new Path(child2Rep);
        return new Path[]{child1, child2};
    }

    /*
     * Performs cyclic crossover of two parent paths to produce two children paths.
     * Functions by getting the indexes from both parents where the same city is present, 
     * then adds this each index to the cycle. Swaps the indexes in the children paths.
     * Repeats this untill all cities have
     * been visited in both parents.
     * Pseudo code originating from the apache commons library found at:
     * https://commons.apache.org/proper/commons-math/jacoco/org.apache.commons.math3.genetics/CycleCrossover.java.html
     * @param parent1 Parent one to create children from
     * @param parent2 Parent two to create children from
     * @return children
     */
    private Path[] CyclicCrossover(Path parent1, Path parent2){
        int length = parent1.getAllInPath().size();
        // get raw city list
        ArrayList<City> parent1Rep = parent1.getAllInPath();
        ArrayList<City> parent2Rep = parent2.getAllInPath();
        ArrayList<City> child1Rep = new ArrayList<>(parent1.getAllInPath());
        ArrayList<City> child2Rep = new ArrayList<>(parent2.getAllInPath());
        // the set of all visited cities so far
        Set<Integer> visitedCities = new HashSet<>(length);
        // the cities in the current cycle
        List<Integer> cities = new ArrayList<>(length);

        // create a random starting place in the cycle
        int index = (int) (Math.random() * (length-1));
        int cycle = 1;
        while (visitedCities.size() < length) {
            cities.add(index);
            City parentCity = parent2Rep.get(index);
            index = parent1Rep.indexOf(parentCity);
            
            while (index != cities.get(0)) {
                // add the index to the current cycle of cities
                cities.add(index);
                // get the item in the second parent at that index
                parentCity = parent2Rep.get(index);
                // get the index of that item in the first parent
                index = parent1Rep.indexOf(parentCity);
            }
            // swap the child elements on the locations of cities found in the current cycle
            if (cycle + 1 % 2 != 0) {
                for (int i : cities) {
                    City city = child1Rep.get(i);
                    child1Rep.set(i, child2Rep.get(i));
                    child2Rep.set(i, city);
                }
            }
            visitedCities.addAll(cities);
            // find next starting index: last one + 1 until an unvisited index is found
            index = (cities.get(0) + 1) % length;
            while (visitedCities.contains(index) && visitedCities.size() < length) {
                index++;
                if (index >= length) {
                    index = 0;
                }
            }
            cities.clear();
            cycle++;
        }
        Path child1 = new Path(child1Rep);
        Path child2 = new Path(child2Rep);
        return new Path[]{child1, child2};
    }
    
    
}
