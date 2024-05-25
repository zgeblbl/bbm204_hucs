//Özge Bülbül 2220765008
import java.io.*;
import java.util.*;

class TrieNode {
    Map<Character, TrieNode> children;
    List<WeightedResult> results;

    public TrieNode() {
        children = new HashMap<>();
        results = new ArrayList<>();
    }
}

class Trie {
    private TrieNode root;
    public Trie() {
        root = new TrieNode();
    }
    public void insertNode(String word, long weight) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
            node.results.add(new WeightedResult(weight, word));
            node.results.sort((a, b) -> Long.compare(b.weight, a.weight));
        }
    }
    public List<WeightedResult> trieSearch(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            if (!node.children.containsKey(c)) {
                return new ArrayList<>();
            }
            node = node.children.get(c);
        }
        return node.results;
    }
}
class WeightedResult {
    long weight;
    String result;
    public WeightedResult(long weight, String result) {
        this.weight = weight;
        this.result = result;
    }
}

public class Quiz4 {
    public static void main(String[] args) throws IOException {
        //String databaseFile = "src/sample_database.txt";
        String databaseFile = args[0];
        //String queryFile = "src/sample_queries.txt";
        String queryFile = args[1];
        Trie trie = new Trie();

        BufferedReader br = new BufferedReader(new FileReader(databaseFile));
        int n = Integer.parseInt(br.readLine().trim());

        for (int i = 0; i < n; i++) {
            String[] parts = br.readLine().trim().split("\t");
            long weight = Long.parseLong(parts[0]);
            String result = parts[1].toLowerCase();
            trie.insertNode(result, weight);
        }
        br.close();

        BufferedReader breader = new BufferedReader(new FileReader(queryFile));
        String line;
        while ((line = breader.readLine()) != null) {
            String[] parts = line.trim().split("\t");
            String query = parts[0].toLowerCase();
            int limit = Integer.parseInt(parts[1]);
            System.out.println("Query received: \"" + query + "\" with limit " + limit + ". Showing results:");
            List<WeightedResult> results = trie.trieSearch(query);
            if (limit == 0 || results.size() == 0){
                System.out.println("No results.");
            }else{
                for (int i = 0; i < Math.min(limit, results.size()); i++) {
                    System.out.println("- "+ results.get(i).weight + " " + results.get(i).result);
                }
            }
        }
        breader.close();
    }
}
