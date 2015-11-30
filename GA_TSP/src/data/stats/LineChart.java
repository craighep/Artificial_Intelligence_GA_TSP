package data.stats;

import data.Path;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Exports average and best fitness (and distance) line graphs. Exports JPG
 * images representing the fitness and distances of tours over evolutions in a
 * solution.
 * @author Craig
 */
public class LineChart {

    /**
     * Takes a list of the average and best path solutions throughout each evolution 
     * during running of the GA and produces line graphs of each in image format.
     * @param bestEvolvedPaths List of best (highest fitness, lowest distance) paths
     * @param averageEvolvedPaths List of most average (average fitness, average distance) paths
     */
    public void exportDistanceGraph(ArrayList<Path> bestEvolvedPaths, ArrayList<Path> averageEvolvedPaths) {
        DefaultCategoryDataset bestDistanceChart = new DefaultCategoryDataset();
        DefaultCategoryDataset bestFitnessChart = new DefaultCategoryDataset();
        DefaultCategoryDataset averageDistanceChart = new DefaultCategoryDataset();
        DefaultCategoryDataset averageFitnessChart = new DefaultCategoryDataset();
        
        Iterator<Path> bestPathIterator = bestEvolvedPaths.iterator();
        Iterator<Path> averagePathIterator = averageEvolvedPaths.iterator();
        int i = 0;
        while (bestPathIterator.hasNext() && averagePathIterator.hasNext()) {
            Path bestpath = bestPathIterator.next();
            double bestFitness = round(bestpath.getFitness());
            bestDistanceChart.addValue(bestpath.getDistance(), "Distance", String.valueOf(i));
            bestFitnessChart.addValue(bestFitness, "Fitness", String.valueOf(i));

            Path averagepath = averagePathIterator.next();
            double averageFitness = round(bestpath.getFitness());
            averageDistanceChart.addValue(averagepath.getDistance(), "Distance", String.valueOf(i));
            averageFitnessChart.addValue(averageFitness, "Fitness", String.valueOf(i));
            i++;
        }
        exportGraphs(bestDistanceChart, bestFitnessChart, averageDistanceChart,
                averageFitnessChart);
    }

    private void exportGraphs(DefaultCategoryDataset bestDistanceChart, DefaultCategoryDataset bestFitnessChart,
            DefaultCategoryDataset averageDistanceChart, DefaultCategoryDataset averageFitnessChart) {
        JFreeChart bestDistanceChartObject = ChartFactory.createLineChart(
                "Best Distance Vs Evolutions", "Evolution",
                "Distance",
                bestDistanceChart, PlotOrientation.VERTICAL,
                true, true, false);
        JFreeChart bestFitnessChartObject = ChartFactory.createLineChart(
                "Best Fitness Vs Evolution", "Evolution",
                "Fitness",
                bestFitnessChart, PlotOrientation.VERTICAL,
                true, true, false);
        JFreeChart averageDistanceChartObject = ChartFactory.createLineChart(
                "Average Distance Vs Evolutions", "Evolution",
                "Distance",
                averageDistanceChart, PlotOrientation.VERTICAL,
                true, true, false);
        JFreeChart averageFitnessChartObject = ChartFactory.createLineChart(
                "Average Fitness Vs Evolutions", "Evolution",
                "Fitness",
                averageFitnessChart, PlotOrientation.VERTICAL,
                true, true, false);
        
        int width = 640;
        int height = 480;
        try {
            ChartUtilities.saveChartAsJPEG(new File("bestDistances.jpeg"), bestDistanceChartObject, width, height);
            ChartUtilities.saveChartAsJPEG(new File("BestFitnesses.jpeg"), bestFitnessChartObject, width, height);
            ChartUtilities.saveChartAsJPEG(new File("averageDistances.jpeg"), averageDistanceChartObject, width, height);
            ChartUtilities.saveChartAsJPEG(new File("averageFitnesses.jpeg"), averageFitnessChartObject, width, height);
        } catch (IOException ex) {
            Logger.getLogger(LineChart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private double round(double value) {
        return (double) Math.round(value * 100000d) / 100000d;
    }
}
