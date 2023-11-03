import java.util.List;
import java.util.ArrayList;

class disjointSet {
    List<Integer> rank = new ArrayList<>();
    List<Integer> parent = new ArrayList<>();
    List<Integer> size = new ArrayList<>();

    public disjointSet(int n) {
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
            size.set(ulpv, size.get(ulpv) + size.get(ulpu));
        } else {
            parent.set(ulpv, ulpu);
            size.set(ulpu, size.get(ulpu) + size.get(ulpv));
        }
    }
}

public class networkConnect {
    static int connectedNetwork(int[][] edges, int n) {
        disjointSet ds = new disjointSet(n);
        int cntExtras = 0;
        int m = edges.length;

        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            int v = edges[i][1];

            if (ds.findUPar(0) == ds.findUPar(v))
                cntExtras++;
            else
                ds.unionBySize(u, v);
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (ds.findUPar(i) == i)
                cnt++;
        }

        int ans = cnt - 1;
        if (cntExtras >= ans)
            return ans;
        return -1;
    }
}
