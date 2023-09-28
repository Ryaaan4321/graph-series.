import java.util.ArrayList;
import java.util.Stack;

public class topologicalSortDfs {
    public static void dfs(int node, int vis[], Stack<Integer> st, ArrayList<ArrayList<Integer>> adj) {
        vis[node] = 1;
        for (int it : adj.get(node)) {
            if (vis[it] == 0) {
                dfs(it, vis, st, adj);
            }
        }
        st.push(node);
    }

    static int[] topologicalSort(int v, ArrayList<ArrayList<Integer>> adj) {
        int vis[] = new int[v];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < v; i++) {
            if (vis[i] == 0) {
                dfs(i, vis, st, adj);
            }
        }
        int ans[] = new int[v];
        int i = 0;
        while (!st.isEmpty()) {
            ans[i++] = st.peek();
            st.pop();
        }
        return ans;
    }

    public static void main(String[] args) {
        int v = 6; 

        
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }

        
        adj.get(5).add(2);
        adj.get(5).add(0);
        adj.get(4).add(0);
        adj.get(4).add(1);
        adj.get(2).add(3);
        adj.get(3).add(1);

        int[] result = topologicalSort(v, adj);

        
        for (int i = v - 1; i >= 0; i--) {
            System.out.print(result[i] + " ");
        }
    }
}
