//Name: Hasni Haleemdeen
//Student ID: 20211337

//Task 4 (File reader) and Task 5
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {

        // Create a new directed graph with 6 vertices
        GraphFunction graph = new GraphFunction(4);

        // Read input file and add edges to the graph
        try {
            Scanner scanner = new Scanner(new File("src/cyclic.txt"));
            while (scanner.hasNext()) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                graph.addEdge(u, v);

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Check if the graph is acyclic
        graph.isAcyclic();
    }
}





