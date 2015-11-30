
import gui.frame.TSPFrame;

/**
 * Starts the application by creating and showing the GUI frame.
 * @author Craig
 */
public class Runnable {

    /**
     * Creates and runs the TSP solver window.
     * @param args Application runtime arguments
     */
    public static void main(String[] args) {
        TSPFrame frame = new TSPFrame();
        frame.showIt();
    }
}
