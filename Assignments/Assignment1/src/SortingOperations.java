import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SortingOperations {
    static List<int[]> splitArrays;
    public static List<double[]> sort(int[] flowDurations){
        List<double[]> yAxes = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        for (int duration : flowDurations) {
            tempList.add(duration);
        }
        // Shuffle the list
        Collections.shuffle(tempList);
        // Convert the shuffled list back to an array
        for (int i = 0; i < flowDurations.length; i++) {
            flowDurations[i] = tempList.get(i);
        }
        int[] splitNumbers = {500, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, 250000};

        // Split the array based on the split numbers
        splitArrays = new ArrayList<>();
        int startIndex = 0;
        for (int splitNumber : splitNumbers) {
            int[] splitArray = new int[Math.min(splitNumber, flowDurations.length - startIndex)];
            System.arraycopy(flowDurations, startIndex, splitArray, 0, splitArray.length);
            splitArrays.add(splitArray);
        }
        List<int[]> sortedArrays = new ArrayList<>();

        //RANDOM DATA

        double[] insertionTimes = new double[10];
        double[] mergeTimes = new double[10];
        double[] countTimes = new double[10];
        for (int i = 0; i < splitArrays.size(); i++) {
            int[] array = splitArrays.get(i);
            long insertion_timeSum = 0;
            for (int j = 0; j < 10; j++) {
                shuffleArray(array);
                long insert_time1 = System.currentTimeMillis();
                SortingCommands.insertionSort(array);
                long insert_time2 = System.currentTimeMillis();
                insertion_timeSum += (insert_time2 - insert_time1);
            }
            insertionTimes[i] = insertion_timeSum / 10;
            System.out.println("Insertion sort on random data with " + array.length + " elements: " + (insertion_timeSum / 10));
        }
        for (int i = 0; i < splitArrays.size(); i++) {
            int[] array = splitArrays.get(i);
            long merge_timeSum = 0;
            for (int j = 0; j <10;j++) {
                shuffleArray(array);
                long merge_time1 = System.currentTimeMillis();
                SortingCommands.mergeSort(array);
                long merge_time2 = System.currentTimeMillis();
                merge_timeSum += (merge_time2-merge_time1);
            }
            System.out.println("Merge sort on random data with " + array.length + " elements: " + (merge_timeSum / 10));
            mergeTimes[i] = merge_timeSum/10;
        }
        for (int i = 0; i < splitArrays.size(); i++) {
            int[] array = splitArrays.get(i);
            long count_timeSum = 0;
            for (int j = 0; j <10;j++) {
                shuffleArray(array);
                long count_time1 = System.currentTimeMillis();
                SortingCommands.countingSort(array);
                long count_time2 = System.currentTimeMillis();
                count_timeSum += (count_time2-count_time1);
            }
            countTimes[i] = count_timeSum/10;
            sortedArrays.add(SortingCommands.insertionSort(array));
            System.out.println("Counting sort on random data with " + array.length + " elements: " + (count_timeSum / 10));
        }
        yAxes.add(insertionTimes);
        yAxes.add(mergeTimes);
        yAxes.add(countTimes);

        // SORTED DATA

        double[] insertionTimesSorted = new double[10];
        double[] mergeTimesSorted = new double[10];
        double[] countTimesSorted = new double[10];
        for (int i = 0; i < sortedArrays.size(); i++) {
            int[] array = sortedArrays.get(i);
            long timeSum = 0;
            for (int j = 0; j <10;j++) {
                long time1 = System.currentTimeMillis();
                SortingCommands.insertionSort(array);
                long time2 = System.currentTimeMillis();
                timeSum += time2-time1;
            }
            insertionTimesSorted[i] = timeSum/10;
            System.out.println("Insertion sort on sorted data with "+array.length+" elements: "+(timeSum/10));
        }
        for (int i = 0; i < sortedArrays.size(); i++) {
            int[] array = sortedArrays.get(i);
            long timeSum = 0;
            for (int j = 0; j <10;j++) {
                long time1 = System.currentTimeMillis();
                SortingCommands.mergeSort(array);
                long time2 = System.currentTimeMillis();
                timeSum += time2-time1;
            }
            mergeTimesSorted[i] = timeSum/10;
            System.out.println("Merge sort on sorted data with "+array.length+" elements: "+(timeSum/10));
        }
        for (int i = 0; i < sortedArrays.size(); i++) {
            int[] array = sortedArrays.get(i);
            long timeSum = 0;
            for (int j = 0; j <10;j++) {
                long time1 = System.currentTimeMillis();
                SortingCommands.countingSort(array);
                long time2 = System.currentTimeMillis();
                timeSum += time2-time1;
            }
            System.out.println("Counting sort on sorted data with "+array.length+" elements: "+(timeSum/10));
            countTimesSorted[i] = timeSum/10;
        }
        yAxes.add(insertionTimesSorted);
        yAxes.add(mergeTimesSorted);
        yAxes.add(countTimesSorted);

        // REVERSELY SORTED DATA

        double[] insertionTimesReverse = new double[10];
        double[] mergeTimesReverse = new double[10];
        double[] countTimesReverse = new double[10];
        for (int i = 0; i < sortedArrays.size(); i++) {
            int[] array = sortedArrays.get(i);
            long timeSum = 0;
            for (int j = 0; j <10;j++) {
                reverseArray(array);
                long time1 = System.currentTimeMillis();
                SortingCommands.insertionSort(array);
                long time2 = System.currentTimeMillis();
                timeSum += time2-time1;
            }
            System.out.println("Insertion sort on reversely sorted data with "+array.length+" elements: "+(timeSum/10));
            insertionTimesReverse[i] = timeSum/10;
        }
        for (int i = 0; i < sortedArrays.size(); i++) {
            int[] array = sortedArrays.get(i);
            long timeSum = 0;
            for (int j = 0; j <10;j++) {
                reverseArray(array);
                long time1 = System.currentTimeMillis();
                SortingCommands.mergeSort(array);
                long time2 = System.currentTimeMillis();
                timeSum += time2-time1;
            }
            System.out.println("Merge sort on reversely sorted data with "+array.length+" elements: "+(timeSum/10));
            mergeTimesReverse[i] = timeSum/10;
        }
        for (int i = 0; i < sortedArrays.size(); i++) {
            int[] array = sortedArrays.get(i);
            long timeSum = 0;
            for (int j = 0; j <10;j++) {
                reverseArray(array);
                long time1 = System.currentTimeMillis();
                SortingCommands.countingSort(array);
                long time2 = System.currentTimeMillis();
                timeSum += time2-time1;
            }
            System.out.println("Counting sort on reversely sorted data with "+array.length+" elements: "+(timeSum/10));
            countTimesReverse[i] = timeSum/10;
        }
        yAxes.add(insertionTimesReverse);
        yAxes.add(mergeTimesReverse);
        yAxes.add(countTimesReverse);
        return yAxes;
    }
    public static void shuffleArray(int[] array) {
        int n = array.length;
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            int randomIndex = i + random.nextInt(n - i);
            int temp = array[randomIndex];
            array[randomIndex] = array[i];
            array[i] = temp;
        }
    }
    public static void reverseArray(int[] array) {
        int start = 0;
        int end = array.length - 1;

        while (start < end) {
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }}
