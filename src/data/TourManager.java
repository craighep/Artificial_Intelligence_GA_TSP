package data;

/*
 * TourManager.java
 * Holds the cities of a tour
 */
import java.util.ArrayList;
import java.util.Random;

public class TourManager extends ArrayList<City> {

    private static ArrayList cities = new ArrayList<>();
    private static boolean isSolved = false;

    public static void addCity(City city) {
        cities.add(city);
    }

    public static ArrayList getAll() {
        return cities;
    }

    public static void setAll(ArrayList<City> cityList) {
        cities = cityList;
    }

    public static City getCity(int index) {
        return (City) cities.get(index);
    }

    public static int numberOfCities() {
        return cities.size();
    }

    public static void randomlyGenerateCities(int amount) {
        Random randomGen = new Random();
        for (int i = 0; i < amount; i++) {
            int x = randomGen.nextInt(900);
            int y = randomGen.nextInt(900);
            City city = new City(x, y);
            addCity(city);
        }
    }

    public static void clearAll() {
        //Clear the vector
        getAll().clear();
    }

    public static boolean isSolved() {
        return isSolved;
    }

    public static void setSolved(boolean solved) {
        isSolved = solved;
    }
}
