import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

class MissingNumber {
    public static String[] reader(String path) {
        int i = 0;
        try {
            int length = Files.readAllLines(Paths.get(path)).size();
            String[] results = new String[length];
            for (String line : Files.readAllLines(Paths.get(path))) {
                results[i++] = line;
            }
            return results;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) {
        String[] inputArray = reader(args[1]);
        ArrayList<Integer> nums = new ArrayList<>();
        for (String line : inputArray) {
            if(!(Objects.equals(line, ""))){
                String[] elements = line.split(" ");
                for (String element : elements) {
                    nums.add(Integer.parseInt(element));
                }
            }
        }
        int output = 0;
        for (int i = 0; i <= nums.size(); i++) {
            if (!nums.contains(i)){
                output = i;
            }
        }
        System.out.println(output);
        //IoOperations.writer()


        // First command-line argument refers to the number of integers
        // Second command-line argument contains the path to the input file
        // Your program should only print a single integer to the standard output
        // For the sample input, your output should be:
        // 2
    }

}