package data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Craig
 */
public class TSPGenerator {

    public void generateCitiesCsv(String sFileName, List<City> cities) {
        try (FileWriter writer = new FileWriter(sFileName)) {
            writer.append("Id");
            writer.append(',');
            writer.append("X");
            writer.append(',');
            writer.append("Y");
            writer.append('\n');

            for (int i = 0; i < cities.size(); i++) {
                City city = cities.get(i);
                writer.append(String.valueOf(city.getID()));
                writer.append(',');
                writer.append(String.valueOf(city.getX()));
                writer.append(',');
                writer.append(String.valueOf(city.getY()));
                writer.append('\n');
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Randomly creates a path using a (given number of) random points in
     * between x and y values of 0 and a number passed.
     *
     * @param amount Number of points to generate
     * @param maxX Maximum x value of location
     * @param maxY Maximum y value of location
     */
    public void randomlyGenerateCities(int amount, int maxX, int maxY) {
        SecureRandom random = new SecureRandom();
        List<City> cities = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            int x = random.nextInt(maxX-50);
            int y = random.nextInt(maxY-50);
            City city = new City(x, y, cities.size()+1);
            cities.add(city);
            PathSolution.addCity(city);
        }
        generateCitiesCsv("input.csv", cities);
    }

    public ArrayList<City> readCitiesCsv(String filename) {
        BufferedReader br;
        String line = "";
        String cvsSplitBy = ",";
        ArrayList<City> cities = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null) {
                String[] coord = line.split(cvsSplitBy);
                if(coord[0].equals("Id"))
                    continue;
                City city = new City(Integer.parseInt(coord[1]), Integer.parseInt(coord[2]));
                cities.add(city);
                PathSolution.addCity(city);
            }
        } catch (FileNotFoundException e) {
        } catch (IOException ex) {
            Logger.getLogger(TSPGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cities;
    }
}
