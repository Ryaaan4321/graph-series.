import java.util.*;

public class safeNodes2TopoSort {
    static ArrayList<Integer> eventualSafeStates(int v, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<ArrayList<Integer>> adjRev = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adjRev.add(new ArrayList<>());
        }
        int[] indegree = new int[v];

        for (int i = 0; i < v; i++) {
            for (int it : adj.get(i)) {
                adjRev.get(it).add(i);
                indegree[i]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        ArrayList<Integer> safeNodes = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            int node = q.peek();
            q.remove();
            safeNodes.add(node);
            for (int it : adjRev.get(node)) {
                indegree[it]--;
                if (indegree[it] == 0) {
                    q.add(it);
                }
            }
        }
        Collections.sort(safeNodes);
        return safeNodes;
    }

    public static void main(String[] args) {
        int v = 5; 

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(v);

        adj.add(new ArrayList<>(Arrays.asList(1, 2)));
        adj.add(new ArrayList<>(Arrays.asList(2, 3)));
        adj.add(new ArrayList<>(Arrays.asList(4)));
        adj.add(new ArrayList<>());
        adj.add(new ArrayList<>(Arrays.asList(3)));

        ArrayList<Integer> safeNodes = eventualSafeStates(v, adj);

        System.out.println("Eventual Safe States: " + safeNodes);
    }
}
