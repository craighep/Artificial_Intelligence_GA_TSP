package data;

import java.util.ArrayList;
import java.util.Iterator;

/*
* TSP_GA.java
* Create a tour and evolve a solution
*/
//http://www.theprojectspot.com/tutorial-post/applying-a-genetic-algorithm-to-the-travelling-salesman-problem/5
public class TSP_GA {

    public void calculateTour(ArrayList<City> cities){
        
        Iterator<City> iterator = cities.iterator();
        while(iterator.hasNext()) {
            City next = iterator.next();
            TourManager.addCity(next);
        }
        
        // Initialize population
        Population pop = new Population(cities.size(), true);
        System.out.println("Initial distance: " + pop.getFittest().getDistance());

        // Evolve population for 100 generations
        pop = GA.evolvePopulation(pop);
        for (int i = 0; i < 100; i++) {
            pop = GA.evolvePopulation(pop);
        }

        // Print final results
        System.out.println("Finished");
        System.out.println("Final distance: " + pop.getFittest().getDistance());
        System.out.println("Solution:");
        System.out.println(pop.getFittest());
    }
}
