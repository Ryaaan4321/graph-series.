import java.util.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Pair {
    int first;
    int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

public class detectCycle {
    public static boolean checkForCycle(int src, int v, ArrayList<ArrayList<Integer>> adj, boolean vis[]) {
        vis[src] = true;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(src, -1));

        while (!q.isEmpty()) {
            int node = q.peek().first;
            int parent = q.peek().second;

            q.remove();

            for (int adjacentNode : adj.get(node)) {
                if (!vis[adjacentNode]) {
                    vis[adjacentNode] = true;
                    q.add(new Pair(adjacentNode, node));
                } else if (parent != adjacentNode) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isCycle(int v, ArrayList<ArrayList<Integer>> adj) {
        boolean vis[] = new boolean[v];
        for (int i = 0; i < v; i++) {
            vis[i] = false;
        }

        for (int i = 0; i < v; i++) {
            if (!vis[i]) {
                if (checkForCycle(i, v, adj, vis))
                    return true;
            }
        }
        return false;
    }

    private static boolean dfs(int node, int parent, boolean vis[], ArrayList<ArrayList<Integer>> adj) {
        vis[node] = true;
        for (int adjacentNode : adj.get(node)) {
            if (!vis[adjacentNode]) {
                if (dfs(adjacentNode, node, vis, adj))
                    return true;
            } else if (adjacentNode != parent)
                return true;
        }
        return false;
    }

    public static boolean isCycle2(int v, ArrayList<ArrayList<Integer>> adj) {
        boolean vis[] = new boolean[v];

        for (int i = 0; i < v; i++) {
            if (!vis[i]) {
                if (dfs(i, -1, vis, adj))
                    return true;
            }
        }
        return false;
    }

    public static void addEdge(ArrayList<ArrayList<Integer>> adjacencyList, int from, int to) {
        adjacencyList.get(from).add(to);
        adjacencyList.get(to).add(from); 
    }

    public static void main(String[] args) {
        int numVertices = 4; 
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(numVertices);

        for (int i = 0; i < numVertices; i++) {
            adj.add(new ArrayList<>());
        }

        addEdge(adj, 0, 1);
        addEdge(adj, 1, 2);
        addEdge(adj, 2, 0);
        addEdge(adj, 1, 3);
        

        if (isCycle(numVertices, adj)) {
            System.out.println("Cycle detected using method 1");
        } else {
            System.out.println("No cycle using method 1");
        }

        if (isCycle2(numVertices, adj)) {
            System.out.println("Cycle detected using method 2");
        } else {
            System.out.println("No cycle using method 2");
        }
    }
}
