import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Pair {
    int first;
    int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

class Tuple {
    int first;
    int second;
    int third;

    public Tuple(int first, int second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
}
// i dont think so u neeed a explanation if u know about the dijakstra algorithm;
// the only twist we have here is that we are not using priority queue because we are storing the data in the ascending order 
// and if u know why we are using priority queue is to store the min so no point of using priority queue and end up using extra (logN) factor of the space

public class cheapestFlight {
    public static int cheapestFlightt(int n, int k, int src, int destination, int flights[][]) {
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>(); // new arraylist addding the pair here
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>(i));
        }
        
        for (int i = 0; i < flights.length; i++) {
            adj.get(flights[i][0]).add(new Pair(flights[i][1], flights[i][2])); // iteraitng over it
        }
        Queue<Tuple> q = new LinkedList<>();
        q.add(new Tuple(0, src, 0)); // initally added the steps 0 , src node and the total cost;
        int dist[] = new int[n]; 

        for (int i = 0; i < dist.length; i++) {
            dist[i] = (int) (1e9); // initalyy the dist array is marked ass infinity
        }
        dist[src] = 0;
        while (!q.isEmpty()) { // iterating over q;
            Tuple it = q.peek();
            int stops = it.first;
            int node = it.second;
            int cost = it.second;

            if (stops > k)
                continue;
          // and thts the simple algorithm;
            for (Pair itr : adj.get(node)) {
                int adjNode = itr.first;
                int edW = itr.second;

                if (cost + edW < dist[adjNode] && stops <= k) {
                    dist[adjNode] = cost + edW;
                    q.add(new Tuple(stops + 1, adjNode, cost + edW));
                }
            }
        }
        if (dist[destination] == (int) (1e9)) {
            return -1;
        }
        return dist[destination];

    }

    public static void main(String[] args) {
        int src = 0;
        int destination = 4;
        int k = 3;
        int n = 4;

        int flights[][] = { { 0, 1, 100 }, { 1, 2, 100 }, { 2, 0, 100 }, { 1, 3, 600 }, { 2, 3, 200 } };
        int ans = cheapestFlightt(n, k, src, destination, flights);
        System.out.println(ans);

    }
}
