import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class CsvOperations {
    static int[] extractFlowDurations(String csvFile) {
        int[] flowDurations = null;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean headerSkipped = false; // Skip the header line
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }
                StringTokenizer tokenizer = new StringTokenizer(line, ",");
                // Skip fields until reaching the Flow Duration field
                for (int i = 0; i < 6; i++) {
                    tokenizer.nextToken();
                }
                // Extract and parse Flow Duration field
                String flowDurationStr = tokenizer.nextToken().trim();
                if (count == 0) {
                    flowDurations = new int[count + 1];
                } else {
                    int[] temp = new int[count + 1];
                    System.arraycopy(flowDurations, 0, temp, 0, flowDurations.length);
                    flowDurations = temp;
                }
                flowDurations[count++] = Integer.parseInt(flowDurationStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return flowDurations;
    }
}
