import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class disjointset {
    List<Integer> rank = new ArrayList<>();
    List<Integer> parent = new ArrayList<>();
    List<Integer> size = new ArrayList<>();

    public disjointset(int n) {
        for (int i = 0; i <= n; i++) {
            rank.add(0);
            parent.add(i);
            size.add(1);

        }

    }

    public int findUPar(int node) {
        if (node == parent.get(node)) {
            return node;
        }
        int ulp = findUPar(parent.get(node));
        parent.set(node, ulp);
        return parent.get(node);
    }

    public void unionByRank(int u, int v) {
        int ulpu = findUPar(u);
        int ulpv = findUPar(v);

        if (ulpu == ulpv)
            return;
        if (rank.get(ulpu) < rank.get(ulpv)) {
            parent.set(ulpu, ulpv);

        } else if (rank.get(ulpv) < rank.get(ulpu)) {
            parent.set(ulpv, ulpu);
        } else {
            parent.set(ulpv, ulpu);
            int rankU = rank.get(ulpu);
            rank.set(ulpu, rankU + 1);
        }
    }

    public void unionBySize(int u, int v) {
        int ulpu = findUPar(u);
        int ulpv = findUPar(v);

        if (ulpu == ulpv)
            return;
        if (size.get(u) == size.get(ulpv)) {
            parent.set(ulpu, ulpv);
            size.set(ulpu, size.get(ulpv) + size.get(ulpu));
        } else {
            parent.set(ulpv, ulpu);
            size.set(ulpu, size.get(ulpu) + size.get(ulpv));
        }
    }

}

public class numberofprovinces2 {
    static int numprovinces(ArrayList<ArrayList<Integer>> adj, int v) {
        disjointSet ds = new disjointSet(v);
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                if (adj.get(i).get(j) == 1) {
                    ds.unionBysize(i, j);
                }
            }
        }
        int cnt = 0;
        for (int i = 0; i < v; i++) {
            if (ds.parent.get(i) == i)
                cnt++;
        }
        return cnt;
    }

    public static void main(String[] args) {
        int V = 3;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>() {
            {
                add(new ArrayList<Integer>(Arrays.asList(1, 0, 1)));
                add(new ArrayList<Integer>(Arrays.asList(0, 1, 0)));
                add(new ArrayList<Integer>(Arrays.asList(1, 0, 1)));
            }

        };
        numberofprovinces2 obj = new numberofprovinces2();
        int ans=obj.numprovinces(adj, V);
        System.out.println(ans);

    }

}
