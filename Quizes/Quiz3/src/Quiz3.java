import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Quiz3 {
    static class Edge implements Comparable<Edge> {
        int src, dest;
        double weight;
        public Edge(int src, int dest, double weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
        public int compareTo(Edge other) {
            return Double.compare(this.weight, other.weight);
        }
    }
    static class Graph {
        int V;
        List<Edge>[] adjList;
        public Graph(int V) {
            this.V = V;
            adjList = new ArrayList[V];
            for (int i = 0; i < V; i++) {
                adjList[i] = new ArrayList<>();
            }
        }
        public void addEdge(int src, int dest, double weight) {
            Edge edge = new Edge(src, dest, weight);
            adjList[src].add(edge);
            adjList[dest].add(new Edge(dest, src, weight));
        }
        public List<Edge> primMST() {
            boolean[] visited = new boolean[V];
            PriorityQueue<Edge> minHeap = new PriorityQueue<>();
            List<Edge> MST = new ArrayList<>();
            visited[0] = true;
            minHeap.addAll(adjList[0]);
            while (!minHeap.isEmpty()) {
                Edge edge = minHeap.poll();
                int u = edge.dest;
                if (visited[u]) continue;
                visited[u] = true;
                MST.add(edge);
                for (Edge e : adjList[u]) {
                    if (!visited[e.dest]) {
                        minHeap.add(e);
                    }
                }
            }
            return MST;
        }
    }
    public static void main(String[] args) throws IOException {
        String inputFile = args[0];
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        int numCases = Integer.parseInt(reader.readLine().trim());
        ArrayList<Edge> MST = new ArrayList<>();
        for (int t = 0; t < numCases; t++) {
            String[] sp = reader.readLine().split(" ");
            int wds = Integer.parseInt(sp[0]);
            int numOfRegions = Integer.parseInt(sp[1]);
            int[][] regions = new int[numOfRegions][2];
            Graph graph = new Graph(numOfRegions);
            for (int j = 0; j < numOfRegions; j++) {
                String[] coords = reader.readLine().split(" ");
                regions[j][0] = Integer.parseInt(coords[0]);
                regions[j][1] = Integer.parseInt(coords[1]);
            }
            for (int i = 0; i < numOfRegions; i++) {
                for (int j = i + 1; j < numOfRegions; j++) {
                    int x1 = regions[i][0];
                    int y1 = regions[i][1];
                    int x2 = regions[j][0];
                    int y2 = regions[j][1];
                    double distance = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
                    graph.addEdge(i, j, distance);
                }
            }
            MST = (ArrayList<Edge>) graph.primMST();
            double[] distances = new double[MST.size()];
            for (int i = 0; i < MST.size(); i++) {
                distances[i] = MST.get(i).weight;
            }
            Arrays.sort(distances);
            System.out.printf("%.2f\n", distances[numOfRegions-wds-1]);
        }
    }
}
