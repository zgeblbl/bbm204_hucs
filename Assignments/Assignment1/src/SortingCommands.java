import java.util.Arrays;

public class SortingCommands {
    public static int[] insertionSort(int[] array){
        for (int j = 1; j < array.length; j++) {
            int key = array[j];
            int i = j - 1;
            while (i >= 0 && array[i] > key) {
                array[i + 1] = array[i];
                i = i - 1;
            }
            array[i + 1] = key;
        }
        return array;
    }
    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int leftIndex = 0, rightIndex = 0, resultIndex = 0;
        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] <= right[rightIndex]) {
                result[resultIndex++] = left[leftIndex++];
            } else {
                result[resultIndex++] = right[rightIndex++];
            }
        }
        while (leftIndex < left.length) {
            result[resultIndex++] = left[leftIndex++];
        }
        while (rightIndex < right.length) {
            result[resultIndex++] = right[rightIndex++];
        }
        return result;
    }
    public static int[] mergeSort(int[] array){
        if (array.length <= 1) {
            return array;
        }
        int[] left = Arrays.copyOfRange(array, 0, (array.length / 2));
        int[] right = Arrays.copyOfRange(array, (array.length / 2)+1, array.length);
        left = mergeSort(left);
        right = mergeSort(right);
        return merge(left, right);
    }
    public static int[] countingSort(int[] array){
        int maxElement = array[0];
        for (int j = 1; j < array.length; j++) {
            if (array[j] > maxElement) {
                maxElement = array[j];
            }
        }
        int[] count = new int[maxElement + 1];
        int[] output = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            count[array[i]]++;
        }
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        for (int i = array.length - 1; i >= 0; i--) {
            output[count[array[i]] - 1] = array[i];
            count[array[i]]--;
        }
        return output;
    }
}
