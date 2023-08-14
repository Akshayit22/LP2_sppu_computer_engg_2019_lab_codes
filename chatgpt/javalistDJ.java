import java.util.*;

public class Dijkstra {
    public static void main(String[] args) {
        int n = 5;  // number of nodes
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        // add edges to graph
        graph.get(0).add(new Edge(1, 4));
        graph.get(0).add(new Edge(2, 1));
        graph.get(1).add(new Edge(3, 1));
        graph.get(2).add(new Edge(1, 2));
        graph.get(2).add(new Edge(3, 5));
        graph.get(4).add(new Edge(3, 1));

        int start = 0;  // starting node
        int[] dist = new int[n];  // shortest distance to each node
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int u = node.u;
            int d = node.dist;
            if (d > dist[u]) {
                continue;
            }
            for (Edge edge : graph.get(u)) {
                int v = edge.v;
                int w = edge.weight;
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.offer(new Node(v, dist[v]));
                }
            }
        }

        // print shortest distance to each node
        for (int i = 0; i < n; i++) {
            System.out.println("Shortest distance from node " + start + " to node " + i + ": " + dist[i]);
        }
    }
}

class Edge {
    int v;
    int weight;

    public Edge(int v, int weight) {
        this.v = v;
        this.weight = weight;
    }
}

class Node implements Comparable<Node> {
    int u;
    int dist;

    public Node(int u, int dist) {
        this.u = u;
        this.dist = dist;
    }

    public int compareTo(Node other) {
        return Integer.compare(dist, other.dist);
    }
}


