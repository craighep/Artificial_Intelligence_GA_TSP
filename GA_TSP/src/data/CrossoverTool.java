/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import data.types.CrossoverType;
import static data.types.CrossoverType.ORDERED;
import static data.types.CrossoverType.UNIFORM;
import java.util.ArrayList;
import java.util.Collections;

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
            case UNIFORM:
                children = uniformCrossover(parent1, parent2);
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

    private Path[] uniformCrossover(Path parent1, Path parent2) {
        int length = parent1.pathSize();
        ArrayList<City> parent1Rep = parent1.getAllInPath();
        ArrayList<City> parent2Rep = parent2.getAllInPath();
        ArrayList<City> child1Rep = new ArrayList<>(length);
        ArrayList<City> child2Rep = new ArrayList<>(length);

        for (int index = 0; index < length; index++) {
            
            double random = Math.random() * 1;
            if (random < 0.7) {
                child1Rep.add(parent2Rep.get(index));
                child2Rep.add(parent1Rep.get(index));
            } else {
                child1Rep.add(parent1Rep.get(index));
                child2Rep.add(parent2Rep.get(index));
            }
        }
        Path child1 = new Path(child1Rep);
        Path child2 = new Path(child2Rep);
        return new Path[]{child1, child2};
    }
}
