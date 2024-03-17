import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;

public class Quiz1 {
    public static void main(String[] args) throws IOException {
        Locale.setDefault(Locale.ENGLISH);
        String[] lines = reader(args[0]);
        if (lines != null) {
            ArrayList<String> ignoreWords = new ArrayList<>();
            ArrayList<ArrayList<String>> titlesWords = new ArrayList<>();
            ArrayList<String> list2 = new ArrayList<>();
            processLines(lines, ignoreWords, list2);
            for (String sentence : list2) {
                String[] words = sentence.split("\\s+");
                ArrayList<String> temp = new ArrayList<>();
                for (String word : words) {
                    temp.add(word.toLowerCase(Locale.ROOT));
                }
                titlesWords.add(temp);
            }
            ArrayList<ArrayList<String>> keywords = new ArrayList<>();
            int sentenceNum = 0;
            for (ArrayList<String> list : titlesWords) {
                int wordIndex = 0;
                for (String word : list) {
                    boolean isIgnoreWord = false;
                    for(String ignore : ignoreWords) {
                        if (Objects.equals(word, ignore)){
                            isIgnoreWord = true;
                            break;
                        }
                    }
                    if (!isIgnoreWord){
                        ArrayList<String> temp = new ArrayList<String>();
                        temp.add(word);
                        temp.add(Integer.toString(sentenceNum));
                        temp.add(Integer.toString(wordIndex));
                        keywords.add(temp);

                    }
                    wordIndex++;
                }
                sentenceNum++;
            }
            Collections.sort(keywords, new Comparator<ArrayList<String>>() {
                @Override
                public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                    return o1.get(0).compareTo(o2.get(0));
                }
            });
            for (ArrayList<String> keyword : keywords){
                ArrayList<String> sentence = titlesWords.get(Integer.parseInt(keyword.get(1)));
                ArrayList<String> copy = new ArrayList<>(sentence); // Create a copy of the sentence list
                for (int i = 0; i < copy.size(); i++) {
                    String word = copy.get(i);
                    if (word.equals(keyword.get(0)) && i==Integer.parseInt(keyword.get(2))) {
                        copy.set(i, word.toUpperCase());
                    }
                }
                String concatenatedString = String.join(" ", copy);
                System.out.println(concatenatedString);
            }


            // TODO: Generate Keyword in Context index and further processing
        } else {
            System.out.println("Error reading file.");
        }
    }

    public static void processLines(String[] lines, ArrayList<String> list1, ArrayList<String> list2) {
        boolean foundSeparator = false;
        for (String line : lines) {
            if (!foundSeparator) {
                if (line.equals("...")) {
                    foundSeparator = true;
                } else {
                    String[] words = line.split("\\s+");
                    for (String word : words) {
                        if (!word.trim().isEmpty()) {
                            list1.add(word);
                        }
                    }
                }
            } else {
                list2.add(line);
            }
        }
    }
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
}
