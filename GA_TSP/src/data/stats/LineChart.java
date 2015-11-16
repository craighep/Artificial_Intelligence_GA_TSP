package data.stats;

import data.Tour;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.ChartUtilities; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChart
{
   public void exportDistanceGraph(ArrayList<Tour> bestEvolvedTours, ArrayList<Tour> averageEvolvedTours)
   {
      DefaultCategoryDataset bestDistanceChart = new DefaultCategoryDataset();
      DefaultCategoryDataset bestFitnessChart = new DefaultCategoryDataset();
      DefaultCategoryDataset averageDistanceChart = new DefaultCategoryDataset();
      DefaultCategoryDataset averageFitnessChart = new DefaultCategoryDataset();
      Iterator<Tour> bestTourIterator = bestEvolvedTours.iterator();
      Iterator<Tour> averageTourIterator = averageEvolvedTours.iterator();
      int i =0;
      while(bestTourIterator.hasNext() && averageTourIterator.hasNext()){
        Tour besttour = bestTourIterator.next();
        double bestFitness = round(besttour.getFitness());
        bestDistanceChart.addValue( besttour.getDistance(), "Distance" , String.valueOf(i++) );
        bestFitnessChart.addValue( bestFitness, "Fitness" , String.valueOf(i++) );
        
        Tour averageTour = averageTourIterator.next();
        double averageFitness = round(besttour.getFitness());
        averageDistanceChart.addValue( averageTour.getDistance(), "Distance" , String.valueOf(i++) );
        averageFitnessChart.addValue( averageFitness, "Fitness" , String.valueOf(i++) );
      }
      JFreeChart bestDistanceChartObject = ChartFactory.createLineChart(
         "Best Distance Vs Evolutions","Evolution",
         "Distance",
         bestDistanceChart,PlotOrientation.VERTICAL,
         true,true,false);
      JFreeChart bestFitnessChartObject = ChartFactory.createLineChart(
         "Best Fitness Vs Evolution","Evolution",
         "Fitness",
         bestFitnessChart,PlotOrientation.VERTICAL,
         true,true,false);
      JFreeChart averageDistanceChartObject = ChartFactory.createLineChart(
         "Average Distance Vs Evolutions","Evolution",
         "Distance",
         averageDistanceChart,PlotOrientation.VERTICAL,
         true,true,false);
      JFreeChart averageFitnessChartObject = ChartFactory.createLineChart(
         "Average Fitness Vs Evolutions","Evolution",
         "Fitness",
         averageFitnessChart,PlotOrientation.VERTICAL,
         true,true,false);
      int width = 640; /* Width of the image */
      int height = 480; /* Height of the image */ 
       try {
           ChartUtilities.saveChartAsJPEG(new File( "bestDistances.jpeg" ) ,bestDistanceChartObject, width ,height);
           ChartUtilities.saveChartAsJPEG(new File( "BestFitnesses.jpeg" ) ,bestFitnessChartObject, width ,height);
           ChartUtilities.saveChartAsJPEG(new File( "averageDistances.jpeg" ) ,averageDistanceChartObject, width ,height);
           ChartUtilities.saveChartAsJPEG(new File( "averageFitnesses.jpeg" ) ,averageFitnessChartObject, width ,height);
       } catch (IOException ex) {
           Logger.getLogger(LineChart.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
   private double round(double value) {
        return (double)Math.round(value * 100000d) / 100000d;
   }
}