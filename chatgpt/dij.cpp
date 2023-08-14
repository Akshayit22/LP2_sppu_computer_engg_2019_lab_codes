#include <iostream>
#include <vector>
#include <queue>
#include <limits.h>
using namespace std;

#define MAX_V 100000 // Maximum number of vertices

vector<pair<int, int>> adj[MAX_V]; // Adjacency list

void dijkstra(int start, vector<int> &dist) {
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    dist[start] = 0;
    pq.push({0, start});
    while (!pq.empty()) {
        int u = pq.top().second;
        int d = pq.top().first;
        pq.pop();
        if (d > dist[u]) continue;
        for (auto v : adj[u]) {
            if (dist[u] + v.second < dist[v.first]) {
                dist[v.first] = dist[u] + v.second;
                pq.push({dist[v.first], v.first});
            }
        }
    }
}

int main() {
    int V, E, start;
    cin >> V >> E >> start; // Number of vertices, number of edges, starting vertex
    vector<int> dist(V, INT_MAX);
    for (int i = 0; i < E; i++) {
        int u, v, w;
        cin >> u >> v >> w; // Input edge (u, v) with weight w
        adj[u].push_back({v, w});
    }
    dijkstra(start, dist);
    for (int i = 0; i < V; i++) {
        if (dist[i] == INT_MAX) cout << "INF\n";
        else cout << dist[i] << '\n';
    }
    return 0;
}

