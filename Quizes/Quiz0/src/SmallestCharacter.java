import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class SmallestCharacter {
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
        String[] inputArray = reader(args[0]);
        ArrayList<String> queries = new ArrayList<>();
        ArrayList<String> words = new ArrayList<>();
        int lineNum = 0;
        for (String line : inputArray) {
            String[] elements = line.split(" ");
            if (lineNum == 0) {
                queries.addAll(Arrays.asList(elements));
            } else if (lineNum == 1) {
                words.addAll(Arrays.asList(elements));
            }
            lineNum++;
        }

        ArrayList<Integer> results = new ArrayList<>();
        for (String query : queries) {
            char smallest = 'z';
            for (int i = 1; i < query.length(); i++) {
                char current = query.charAt(i);
                if (current < smallest) {
                    smallest = current;
                }
            }
            int freq = 0;
            for (int i = 0; i < query.length(); i++) {
                if (query.charAt(i) == smallest) {
                    freq++;
                }
            }
            int result = 0;
            for (String word : words) {
                char smallestInWord = 'z';
                for (int i = 1; i < word.length(); i++) {
                    char current = word.charAt(i);
                    if (current < smallestInWord) {
                        smallestInWord = current;
                    }
                }
                int freqInWord = 0;
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == smallestInWord) {
                        freqInWord++;
                    }
                }
                if (freq < freqInWord){
                    result++;
                }
            }
            results.add(result);
        }
        System.out.println(results);
    }
        // First command-line argument contains the path to the input file
        // Structured as below:
        // Line 0: <query-1> <query-2> <query-3> ...
        // Line 1: <word-1> <word-2> <word-3> ...

        // Your program should print to the standard output.
        // Your output should match the given format character-by-character.
        // For example for the sample input:
        // [1]
        // If there are more than one integer to print then:
        // [1,2,3,4,5]
}

