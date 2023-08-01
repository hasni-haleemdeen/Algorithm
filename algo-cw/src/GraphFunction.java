//Name: Hasni Haleemdeen
//Student ID: 20211337

//Task 4
import java.util.*;

public class GraphFunction {
    private List<List<Integer>> graph;

    public GraphFunction(int numVertices) {
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

    public boolean isAcyclic() {
        // Create an array to keep track of the incoming edges to each vertex
        int[] inDegree = new int[graph.size()];
        for (int u = 0; u < graph.size(); u++) {
            for (int v : graph.get(u)) {
                inDegree[v]++;
            }
        }

        // Create a queue to store the vertices with no incoming edges
        Queue<Integer> queue = new LinkedList<>();
        for (int u = 0; u < graph.size(); u++) {
            if (inDegree[u] == 0) {
                queue.offer(u);
            }
        }

        // Perform a BFS, removing vertices with no incoming edges and updating the in-degree counts of their neighbors
        List<Integer> order = new ArrayList<>();
        while (!queue.isEmpty()) {
            int u = queue.poll();
            order.add(u);
            for (int v : graph.get(u)) {
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    queue.offer(v);
                }
            }
            if (graph.get(u).isEmpty()) {
                System.out.println("Sink found and eliminated: " + u);
            }
        }

        // If the size of the topological order is less than the number of vertices, the graph has a cycle
        if (order.size() < graph.size()) {
            // Find a cycle in the graph
            List<Integer> cycle = findCycle();
            System.out.println("The graph contains a cycle: " + cycle);
            return false;
        }

        // The graph is acyclic
        System.out.println("The graph is acyclic");
        return true;
    }


    private List<Integer> findCycle() {
        // Perform a DFS from each vertex to find a cycle
        Set<Integer> visited = new HashSet<>();
        List<Integer> cycle = new ArrayList<>();
        for (int u = 0; u < graph.size(); u++) {
            if (!visited.contains(u)) {
                List<Integer> path = new ArrayList<>();
                if (dfs(u, visited, path)) {
                    // Found a cycle
                    cycle.addAll(path);
                    Collections.reverse(cycle);
                    break;
                }
            }
        }
        return cycle;
    }

    private boolean dfs(int u, Set<Integer> visited, List<Integer> path) {
        visited.add(u);
        path.add(u);
        for (int v : graph.get(u)) {
            if (path.contains(v)) {
                // Found a cycle
                path.add(v);
                return true;
            }
            if (!visited.contains(v)) {
                if (dfs(v, visited, path)) {
                    return true;
                }
            }
        }
        path.remove(path.size() - 1);
        return false;
    }
}