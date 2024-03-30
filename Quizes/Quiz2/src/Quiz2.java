// Ozge Bulbul 2220765008
import java.util.*;
import java.io.*;

public class Quiz2 {
    public static void main(String[] args) throws IOException {

        String fileName = args[0];
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String[] arraySizes = br.readLine().split(" ");
            int M = Integer.parseInt(arraySizes[0]);
            int n = Integer.parseInt(arraySizes[1]);

            String[] kilogramsString = br.readLine().split(" ");
            int[] kgArray = new int[n];
            for (int i = 0; i < n; i++) {
                kgArray[i] = Integer.parseInt(kilogramsString[i]);
            }

            int[] maxMass = new int[M + 1];
            for (int i = 0; i < n; i++) {
                for (int m = M; m >= kgArray[i]; m--) {
                    maxMass[m] = Math.max(maxMass[m], maxMass[m - kgArray[i]] + kgArray[i]);
                }
            }

            int[][] loadMemo = new int[M + 1][n + 1];
            for (int i = 0; i <= n; i++) {
                loadMemo[0][i] = 1;
            }
            for (int m = 1; m <= M; m++) {
                for (int i = 1; i <= n; i++) {
                    if (i == 0 && m == 0) {
                        loadMemo[m][i] = 1;
                    } else if (i == 0 && m > 0) {
                        loadMemo[m][i] = 0;
                    } else if (i > 0 && kgArray[i - 1] > m) {
                        loadMemo[m][i] = loadMemo[m][i - 1];
                    } else {
                        loadMemo[m][i] = loadMemo[m][i - 1] | loadMemo[m - kgArray[i - 1]][i - 1];
                    }
                }
            }
            System.out.println(maxMass[M]);
            for (int m = 0; m <= M; m++) {
                for (int i = 0; i <= n; i++) {
                    System.out.print(loadMemo[m][i]);
                }
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // TODO: Use the first command line argument (args[0]) as the file to read the input from
        // TODO: Your code goes here
        // TODO: Print the solution to STDOUT
    }
}
