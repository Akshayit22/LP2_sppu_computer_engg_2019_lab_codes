import java.util.*;

public class DijkstraAlgorithm {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of nodes: ");
        int n = scanner.nextInt();
        
        int[][] graph = new int[n][n];
        System.out.println("Enter adjacency matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }
        
        System.out.print("Enter start node: ");
        int start = scanner.nextInt();
        
        int[] distances = dijkstra(graph, start);
        
        System.out.println("Shortest distances from node " + start + ":");
        for (int i = 0; i < n; i++) {
            System.out.println(i + ": " + distances[i]);
        }
    }

    public static int[] dijkstra(int[][] graph, int start) {
        int n = graph.length;
        int[] distances = new int[n];
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        distances[start] = 0;

        for (int i = 0; i < n; i++) {
            int u = -1;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && (u == -1 || distances[j] < distances[u])) {
                    u = j;
                }
            }

            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (graph[u][v] > 0 && distances[u] + graph[u][v] < distances[v]) {
                    distances[v] = distances[u] + graph[u][v];
                }
            }
        }

        return distances;
    }
}


