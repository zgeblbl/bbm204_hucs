import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SearchingOperations {
    public static List<double[]> search(){
        List<double[]> yAxes = new ArrayList<>();
        double[] linearRandomTimes = new double[10];
        double[] linearSortedTimes = new double[10];
        double[] binarySortedTimes = new double[10];
        for (int i = 0; i < SortingOperations.splitArrays.size(); i++) {
            int[] array = SortingOperations.splitArrays.get(i);
            long timeSum = 0;
            for (int j = 0; j < 1000; j++ ) {
                int randomIndex = new Random().nextInt(array.length);
                long linearRandom_time1 = System.nanoTime();
                int result = SearchingCommands.linearSearch(array, array[randomIndex]);
                long linearRandom_time2 = System.nanoTime();
                timeSum += linearRandom_time2-linearRandom_time1;
            }
            linearRandomTimes[i] = timeSum/1000;
            System.out.println("Linear searched random time for "+array.length+" : "+(timeSum/1000));
        }
        for (int i = 0; i < SortingOperations.splitArrays.size(); i++) {
            int[] array = SortingOperations.splitArrays.get(i);
            int[] sortedArray = SortingCommands.mergeSort(array);
            long timeSum = 0;
            for (int j = 0; j < 1000; j++ ) {
                int randomIndex = new Random().nextInt(sortedArray.length);
                long linearRandom_time1 = System.nanoTime();
                int result = SearchingCommands.linearSearch(sortedArray, sortedArray[randomIndex]);
                long linearRandom_time2 = System.nanoTime();
                timeSum += linearRandom_time2-linearRandom_time1;
            }
            linearSortedTimes[i] = timeSum/1000;
            System.out.println("Linear searched sorted time for "+sortedArray.length+" : "+(timeSum/1000));
        }
        for (int i = 0; i < SortingOperations.splitArrays.size(); i++) {
            int[] array = SortingOperations.splitArrays.get(i);
            int[] sortedArray = SortingCommands.mergeSort(array);
            long timeSum = 0;
            for (int j = 0; j < 1000; j++ ) {
                int randomIndex = new Random().nextInt(sortedArray.length);
                long binaryRandom_time1 = System.nanoTime();
                int result = SearchingCommands.binarySearch(sortedArray, sortedArray[randomIndex]);
                long binaryRandom_time2 = System.nanoTime();
                timeSum += binaryRandom_time2-binaryRandom_time1;
            }
            binarySortedTimes[i] = timeSum/1000;
            System.out.println("Binary searched time for "+sortedArray.length+" : "+(timeSum/1000));
        }
        yAxes.add(linearRandomTimes);
        yAxes.add(linearSortedTimes);
        yAxes.add(binarySortedTimes);
        return yAxes;
    }
}
