import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class AlienDictionary {
    private static ArrayList<Integer> topoSort(int v, ArrayList<ArrayList<Integer>> adj) {
        int indegree[] = new int[v];
        for (int i = 0; i < v; i++) {
            for (int it : adj.get(i)) {
                indegree[i]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        ArrayList<Integer> topo = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            int node = q.peek();
            q.remove();
            topo.add(node);
            for (int it : adj.get(node)) {
                indegree[it]--;
                if (indegree[it] == 0) q.add(it);
            }
        }
        return topo;
    }

    public static void main(String[] args) {
        int v = 4; 
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }

        
        adj.get(0).add(1);
        adj.get(0).add(2);
        adj.get(1).add(3);
        adj.get(2).add(3);

        ArrayList<Integer> topologicalOrder = topoSort(v, adj);

        
        System.out.println("Topological Order: " + topologicalOrder);
    }
}

