import java.util.ArrayList;
import java.util.Arrays;

public class detectCycleInDirectedGraph {
    private static boolean dfsCheck(int node, ArrayList<ArrayList<Integer>> adj, int[] vis, int[] pathvis) {
        vis[node] = 1;
        pathvis[node] = 1;

        for (int it : adj.get(node)) {
            if (vis[it] == 0) {
                if (dfsCheck(it, adj, vis, pathvis) == true) {
                    return true;
                } else if (pathvis[it] == 1) {
                    return true;
                }
            }
        }
        pathvis[node] = 0;
        return false;
    }

    public static boolean isCycle(int v, ArrayList<ArrayList<Integer>> adj) {
        int[] vis = new int[v];
        int[] pathvis = new int[v];

        for (int i = 0; i < v; i++) {
            if (vis[i] == 0 && dfsCheck(i, adj, vis, pathvis)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int v = 4; 
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        ArrayList<Integer> row1 = new ArrayList<>(Arrays.asList(1, 0, 0, 0));
        ArrayList<Integer> row2 = new ArrayList<>(Arrays.asList(0, 0, 1, 0));
        ArrayList<Integer> row3 = new ArrayList<>(Arrays.asList(0, 0, 0, 1));
        ArrayList<Integer> row4 = new ArrayList<>(Arrays.asList(0, 0, 0, 0));

        adj.add(row1);
        adj.add(row2);
        adj.add(row3);
        adj.add(row4);

        if (isCycle(v, adj)) {
            System.out.println("The directed graph contains a cycle.");
        } else {
            System.out.println("The directed graph is acyclic.");
        }
    }
}
