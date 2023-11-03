import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


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
        if (node == parent.get(node))
            return node;

        int ulp = findUPar(parent.get(node));;
        parent.set(node, ulp);
        return parent.get(node);
    }

    public void unionByRank(int u, int v) {
        int ulpu = findUPar(u);
        int ulpv = findUPar(v);
        if (ulpu == ulpv)
            return;

        if (rank.get(ulpu) < rank.get(ulpv)) {
            parent.set(ulpv, ulpu);
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

        if (size.get(u) < size.get(ulpv)) {
            parent.set(ulpu, ulpv);
            size.set(ulpv, size.get(ulpv) + size.get(ulpu));
        } else {
            parent.set(ulpv, ulpu);
            size.set(ulpu, size.get(ulpv) + size.get(ulpu));
        }
    }
}

public class MakinglargeIslands {
    private static boolean isValid(int adjr, int adjc, int n) {

        return adjr >= 0 && adjr < n && adjc >= 0 && adjc < n;
    }

    public static int maxConnections(int grid[][]) {
        int n = grid.length;

        disjointSet ds = new disjointSet(n * n);

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == 0)
                    continue;
                int dr[] = { -1, 0, 1, 0 };
                int dc[] = { 0, -1, 0, 1 };

                for (int ind = 0; ind < 4; ind++) {
                    int newr = row + dr[ind];
                    int newc = col + dc[ind];

                    if (isValid(newr, newc, n) && grid[newr][newc] == 1) {
                        int nodeNo = row * n + col; // because the disjoint set ds applied on the single values bt they
                                                    // are doubles in the form if(i,j) so thts why
                        int adjNodeno = newr * n + newc;
                        ds.unionBySize(nodeNo, adjNodeno);

                    }
                }
            }
        }
        int mx = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == 1)
                    continue;

                int dr[] = { -1, 0, 1, 0 };
                int dc[] = { 0, -1, 0, 1 };

                HashSet<Integer> components = new HashSet<>();
                for (int ind = 0; ind < 4; ind++) {
                    int newr = row + dr[ind];
                    int newc = col + dc[ind];

                    if (isValid(ind, newr, newc)) {
                        if (grid[newc][newc] == 1) {
                            components.add(ds.findUPar(newr * n + newc));
                        }
                    }
                }
                int sizeTotal = 0;
                for (Integer parents : components) {
                    sizeTotal += ds.size.get(parents);
                }
                mx = Math.max(mx, sizeTotal + 1);
            }
        }
        for (int ceilNo = 0; ceilNo < n; ceilNo++) {
            mx = Math.max(mx, ds.size.get(ds.findUPar(ceilNo)));
        }
        return mx;
    }

    public static void main(String[] args) {
        int grid[][] = { { 1, 1, 0, 1, 1 }, { 1, 1, 0, 1, 1 }, { 1, 1, 0, 1, 1 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 1, 1 },
                { 0, 0, 1, 1, 1 } };
        int ans = maxConnections(grid);

        System.out.println(ans);
    }

}


