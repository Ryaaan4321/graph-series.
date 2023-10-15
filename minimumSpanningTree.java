import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;


class Pair {
    int node;
    int distance;

    public Pair(int node, int distance) {
        this.node = node;
        this.distance = distance;
    }
}

public class minimumSpanningTree {
    static int spanningTree(int v, ArrayList<ArrayList<ArrayList<Integer>>> adj) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int vis[] = new int[v];
        pq.add(new Pair(0, 0));
        int sum = 0;
        while (pq.size() != 0) {
            int wt = pq.peek().distance;
            int node = pq.peek().node;
            pq.remove();
            if (vis[node] == 1)
                continue;
            vis[node] = 1;
            sum += wt;

            for (int i = 0; i < adj.get(node).size(); i++) {
                int edw = adj.get(node).get(i).get(1);
                int adjnode = adj.get(node).get(i).get(0);

                if (vis[adjnode] == 0) {
                    pq.add(new Pair(edw, adjnode));
                }
            }
        }
        return sum;

    }
    //for the explanation how this code is working  i made a notes in my notes so sorryyy 
    public static void main(String[]args){
        int v=4;
        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>();
        for(int i=0;i<v;i++){
            adj.add(new ArrayList<>());

        }

        adj.get(0).add(new ArrayList<>(Arrays.asList(1,2)));  // Edge between 0 and 1 with weight 2
        adj.get(0).add(new ArrayList<>(Arrays.asList(2, 3))); // Edge between 0 and 2 with weight 3
        adj.get(0).add(new ArrayList<>(Arrays.asList(3, 1))); // Edge between 0 and 3 with weight 1
        adj.get(1).add(new ArrayList<>(Arrays.asList(2, 4))); // Edge between 1 and 2 with weight 4
        adj.get(2).add(new ArrayList<>(Arrays.asList(3, 5))); // Edge between 2 and 3 with weight 5
        
    }

}
