import java.util.ArrayList;
import java.util.Arrays;

public class safeNodes {
    private static boolean dfsCheck(int node, ArrayList<ArrayList<Integer>> adj, int pathvis[], int vis[]) {
        vis[node] = 1;
        pathvis[node] = 1;

        for (int it : adj.get(node)) {
            if (pathvis[it] == 1) {
                return true;
            }
            if (vis[it] == 0 && dfsCheck(it, adj, pathvis, vis)) {
                return true;
            }
        }

        pathvis[node] = 0;
        return false;
    }

    public static ArrayList<Integer> eventualSafeNodes(int v, ArrayList<ArrayList<Integer>> adj) {
        int vis[] = new int[v];
        int pathvis[] = new int[v];

        ArrayList<Integer> safeNode = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            if (dfsCheck(i, adj, pathvis, vis)) {
                safeNode.add(i);
            }
        }
        return safeNode;
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

        ArrayList<Integer> ans = eventualSafeNodes(v, adj);
        for (int i = 0; i < ans.size(); i++) {
            System.out.print(ans.get(i) + " ");
        }
    }
}
