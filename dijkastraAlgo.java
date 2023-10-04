import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

class Pair {
    int dist;
    int node;

    Pair(int dist, int node) {
        this.dist = dist;
        this.node = node;
    }
}

public class DijkastraAlgo {
    public static int[] dijkstraAlgo(int v, ArrayList<ArrayList<ArrayList<Pair>>> adj, int s) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> x.dist - y.dist); // defing the priority queue

        int distance[] = new int[v];
      //defined the distance array and marked all the element with the infinity (infinity = (1e9)
        for (int i = 0; i < v; i++) {
            distance[i] = (int) (1e9);
        }

        distance[s] = 0; // bcs from node to node dist is always equals to 0;

        pq.add(new Pair(0, s)); // adding it into the priority q

        while (pq.size() != 0) {// extracting the data from the priority q 
            int distt = pq.peek().dist;
            int node = pq.peek().node;
            pq.remove();

            for (int i = 0; i < adj.get(node).size(); i++) { // using for loopp to get the dist of their respective nodeesss
                int edgeWeight = adj.get(node).get(i).get(1).dist; // Use .dist
                int adjNode = adj.get(node).get(i).get(0).node; // Use .node

                if (distt + edgeWeight < distance[adjNode]) {// comparing if they are greater or smaller than updating them accordinglyy
                  // and if we found the pair which gives min distance then adding that into the priority queue
                    distance[adjNode] = distt + edgeWeight;
                    pq.add(new Pair(distance[adjNode], adjNode));
                }
            }
        }
        return distance;
    }

  /*
  AND ONE THING IS THAT DIJKASTRA ALGO DOESNT SUPPORT FOR THE NEGATIVE WEIGHT GRAPHS BECAUSE IT ENDS UP GIVING US THE MIN RESULT IN 
  EACH ITERATION AND BY DOING SO WE END UP RUNNING THE LOOP FOR (1e9)infinite times
  */

    public static void main(String[] args) {
        ArrayList<ArrayList<ArrayList<Pair>>> adj = new ArrayList<>();
        int v = 4; // Number of vertices

        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }

        // Adding edges to the graph
        adj.get(0).add(new ArrayList<>(Arrays.asList(new Pair(1, 1), new Pair(1, 3)))); // (vertex, weight) pairs for
                                                                                        // vertex 0
        adj.get(1).add(new ArrayList<>(Arrays.asList(new Pair(2, 2), new Pair(4, 3)))); // (vertex, weight) pairs for
                                                                                        // vertex 1
        adj.get(2).add(new ArrayList<>(Collections.singletonList(new Pair(3, 2)))); // (vertex, weight) pair for vertex
                                                                                    // 2
        adj.get(3).add(new ArrayList<>()); // No outgoing edges for vertex 3

        int startVertex = 0; // The starting vertex for Dijkstra's algorithm

        int[] distances = DijkastraAlgo.dijkstraAlgo(v, adj, startVertex);

        // Print the distances to each vertex
        for (int i = 0; i < v; i++) {
            System.out.println(" minimum distance from vertex "+ startVertex + " to vertex " + i + ": " + distances[i]);
        }

    }
}
