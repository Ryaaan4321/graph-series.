import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class kahnsAlgorith {
    static int[] topoSort(int v, ArrayList<ArrayList<Integer>> adj) {
        int indegree[] = new int[v];
        for (int i = 0; i < v; i++) {
            for (int it : adj.get(i)) {
                indegree[it]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < v; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }
        int topo[] = new int[v];
        int i = 0;
        while (!q.isEmpty()) {
            int node = q.peek();
            q.remove();
            topo[i] = node; // Store the topological order
            i++;
            for (int it : adj.get(node)) {
                indegree[it]--;
                if (indegree[it] == 0) {
                    q.add(it);
                }
            }
        }
        return topo;
    }

    public static void main(String[] args) {
        int v = 4; 
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(v);

        ArrayList<Integer> row1 = new ArrayList<>(Arrays.asList(0, 2));
        ArrayList<Integer> row2 = new ArrayList<>(Arrays.asList(1, 3));
        ArrayList<Integer> row3 = new ArrayList<>(Arrays.asList(2));
        ArrayList<Integer> row4 = new ArrayList<>(Arrays.asList());

        adj.add(row1);
        adj.add(row2);
        adj.add(row3);
        adj.add(row4);

        int ans[] = topoSort(v, adj);

        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
        }
    }
}
