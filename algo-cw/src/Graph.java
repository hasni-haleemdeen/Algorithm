//Name: Hasni Haleemdeen
//Student ID: 20211337

//Task 2
import java.util.*;

public class Graph {
    private List<List<Integer>> graph;

    public Graph(int numVertices) {
        // Initialize the adjacency list with empty lists for each vertex
        this.graph = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            graph.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v) {
        // Add a directed edge from u to v
        graph.get(u).add(v);
    }

    public void removeVertex(int u) {
        // Remove the vertex u and all its outgoing edges
        graph.remove(u);
        for (List<Integer> neighbors : graph) {
            neighbors.removeIf(v -> v == u);
        }
    }

    public Integer findSink() {
        // Iterate over all vertices to find a sink
        for (int u = 0; u < graph.size(); u++) {
            if (graph.get(u).isEmpty()) {
                // Vertex has no outgoing edges, so it is a sink
                return u;
            }
        }
        // No sink found
        return null;
    }
}

