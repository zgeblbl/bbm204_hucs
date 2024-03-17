import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import java.io.IOException;
import java.util.*;

class Main {
    public static void main(String args[]) throws IOException {
        String csvFile = "src/TrafficFlowDataset.csv";
        int[] flowDurations = CsvOperations.extractFlowDurations(csvFile);
        List<double[]> Sort_yAxes = SortingOperations.sort(flowDurations);
        List<double[]> Search_yAxes = SearchingOperations.search();
        // X axis data
        int[] inputAxis = {500, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, 250000};

        // Create sample data for linear runtime
        double[][] yAxis1 = new double[3][10];
        yAxis1[0] = Sort_yAxes.get(0);
        yAxis1[1] = Sort_yAxes.get(1);
        yAxis1[2] = Sort_yAxes.get(2);
        double[][] yAxis2 = new double[3][10];
        yAxis2[0] = Sort_yAxes.get(3);
        yAxis2[1] = Sort_yAxes.get(4);
        yAxis2[2] = Sort_yAxes.get(5);
        double[][] yAxis3 = new double[3][10];
        yAxis3[0] = Sort_yAxes.get(6);
        yAxis3[1] = Sort_yAxes.get(7);
        yAxis3[2] = Sort_yAxes.get(8);

        double[][] yAxis4 = new double[3][10];
        yAxis4[0] = Search_yAxes.get(0);
        yAxis4[1] = Search_yAxes.get(1);
        yAxis4[2] = Search_yAxes.get(2);

        // Save the char as .png and show it
        showAndSaveChart("Randomly Sorted Data Time Complexities", inputAxis, yAxis1, "Insertion Sort", "Merge Sort", "Counting Sort");
        showAndSaveChart("Sorted Data Time Complexities", inputAxis, yAxis2, "Insertion Sort", "Merge Sort", "Counting Sort");
        showAndSaveChart("Reversely Sorted Data Time Complexities", inputAxis, yAxis3, "Insertion Sort", "Merge Sort", "Counting Sort");
        showAndSaveChart("Search Time Complexities", inputAxis, yAxis4, "Linear Search on Random Data", "Linear Search on Sorted Data", "Binary Search on Sorted Data");
    }


    public static void showAndSaveChart(String title, int[] xAxis, double[][] yAxis, String line1, String line2, String line3) throws IOException {
        // Create Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).title(title)
                .yAxisTitle("Time in Milliseconds").xAxisTitle("Input Size").build();

        // Convert x axis to double[]
        double[] doubleX = Arrays.stream(xAxis).asDoubleStream().toArray();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        // Add a plot for a sorting algorithm
        chart.addSeries(line1, doubleX, yAxis[0]);
        chart.addSeries(line2, doubleX, yAxis[1]);
        chart.addSeries(line3, doubleX, yAxis[2]);

        // Save the chart as PNG
        BitmapEncoder.saveBitmap(chart, title + ".png", BitmapEncoder.BitmapFormat.PNG);
        // Show the chart
        new SwingWrapper(chart).displayChart();
    }
}
