import java.util.ArrayList;
import java.util.PriorityQueue;

class Pair {
    int first;
    int second;

    Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

public class NumberOfWaysDestination {
    public static int minimumPathsToreachh(int n, ArrayList<ArrayList<Integer>> roads) {
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < roads.size(); i++) {
            int u = roads.get(i).get(0);
            int v = roads.get(i).get(1);
            adj.get(u).add(new Pair(v, roads.get(i).get(2)));
            adj.get(v).add(new Pair(u, roads.get(i).get(2)));
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int distance[] = new int[n];
        int ways[] = new int[n];

        for (int i = 0; i < n; i++) {
            distance[i] = (int) (1e9);
            ways[i] = 0;
        }
        distance[0] = 0;
        ways[0] = 1;
        pq.add(new Pair(0, 0));
        int mod = (int) (1e9 + 7);
        while (pq.size() != 0) {
            int dis = pq.peek().first;
            int node = pq.peek().second;
            pq.remove();

            for (Pair it : adj.get(node)) {
                int adjNode = it.first;
                int edw = it.second;

                if (dis + edw < distance[adjNode]) {
                    distance[adjNode] = dis + edw;
                    pq.add(new Pair(edw + dis, adjNode));
                    ways[adjNode] = node;
                } else if (dis + edw == distance[adjNode]) {
                    ways[adjNode] = ways[adjNode] + ways[node] % mod;
                }
            }
        }
        return ways[n - 1] % mod;

    }
}
