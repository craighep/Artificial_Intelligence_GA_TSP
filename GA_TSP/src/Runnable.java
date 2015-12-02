
import data.City;
import data.GA;
import data.TSPGenerator;
import data.types.CrossoverType;
import data.types.SelectionType;
import gui.frame.TSPFrame;
import java.util.ArrayList;

/**
 * Starts the application by creating and showing the GUI frame.
 * @author Craig
 */
public class Runnable {

    /**
     * Creates and runs the TSP solver window. Alternatively runs by command line
     * if the user provides the required arguments.
     * @param args Application runtime arguments
     */
    public static void main(String[] args) {
        // If user is using application via console
        if(args.length == 6){
            GA TSPSolver = new GA();
            TSPGenerator TSPReader = new TSPGenerator();
            ArrayList<City> cities = TSPReader.readCitiesCsv(args[0]);
            System.out.println(cities.get(10));
            // Send arguments
            TSPSolver.calculatePath(cities, Double.parseDouble(args[1]),
                Integer.parseInt(args[2]), Boolean.parseBoolean(args[3]), 
                SelectionType.fromString(args[4]), CrossoverType.fromString(args[5]));
        }
        // Else, run the GUI and allow entry there
        else{
            TSPFrame frame = new TSPFrame();
            frame.showIt();
        }
    }
}
