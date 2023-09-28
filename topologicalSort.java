import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class topologicalSort {
    static int[] topologicalSort(int v, ArrayList<ArrayList<Integer>> adj) {
        int indegree[] = new int[v];
        for (int i = 0; i < v; i++) {
            for (int it : adj.get(i)) {
                indegree[it]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < v; i++) {
            if (indegree[i] == 0)
                q.add(i);
        }
        int topo[] = new int[v];
        int i = 0;
        while (!q.isEmpty()) {
            if (i >= v) {
                System.out.println("Error: Too many elements in the topological order.");
                break;  
            }

            int node = q.peek();
            q.remove();
            topo[i++] = node;
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
