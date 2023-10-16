import java.util.ArrayList;

public class disjointSet {
    ArrayList<Integer> rank = new ArrayList<>();
    ArrayList<Integer> parent = new ArrayList<>();
    ArrayList<Integer> size = new ArrayList<>();

    public disjointSet(int n) {
        for (int i = 0; i <= n; i++) {
            rank.add(0);
            parent.add(0);
        }
    }

    public int findPar(int node) {
        if (node == parent.get(node)) {
            return node;
        }
        int ulp = findPar(parent.get(node));
        parent.set(node, ulp);
        return parent.get(node);
    }

    public void unionByrank(int u, int v) {
        int ulp_u = findPar(u);
        int ulp_v = findPar(v);
        if (ulp_u == ulp_v)
            return;
        if (rank.get(ulp_u) < rank.get(ulp_v)) {
            parent.set(ulp_v, ulp_u);
        } else if (rank.get(ulp_v) < rank.get(u)) {
            parent.set(ulp_v, ulp_u);
        } else {
            parent.set(ulp_v, ulp_u);
            int ranku = rank.get(u);
            rank.set(ulp_u, ranku + 1);
        }

    }

    public void unionBysize(int u, int v) {
        int ulp_u = findPar(u);
        int ulp_v = findPar(v);
        if (ulp_u == ulp_v)
            return;

        if (size.get(ulp_u) < size.get(ulp_v)) {
            parent.set(ulp_u, ulp_v);
            size.set(ulp_v, size.get(ulp_v) + size.get(ulp_u));
        } else {
            parent.set(ulp_v, ulp_u);
            size.set(ulp_v, size.get(u) + size.get(ulp_v));
        }
    }

    public static void main(String[] args) {
        disjointSet ds = new disjointSet(7);
        ds.unionByrank(1, 2);
        ds.unionByrank(2, 3);
        ds.unionByrank(4, 5);
        ds.unionByrank(6, 7);
        ds.unionByrank(5, 6);

        if (ds.findPar(3) == ds.findPar(7)) {
            System.out.println("Same");
        } else {
            System.out.println("Not same");
        }
        ds.unionByrank(3, 7);
        if (ds.findPar(3) == ds.findPar(7)) {
            System.out.println("Same");
        } else {
            System.out.println("Not same");
        }
    }

}
