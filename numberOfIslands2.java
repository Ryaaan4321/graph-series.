import java.util.ArrayList;
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

        if (size.get(u) == size.get(ulpv)) {
            parent.set(ulpu, ulpv);
            size.set(ulpv, size.get(ulpv) + size.get(ulpu));

        } else {
            parent.set(ulpv, ulpu);
            size.set(ulpu, size.get(ulpv) + size.get(ulpu));
        }
    }
}

public class numberOfislands2 {
    private boolean isValid(int adjr, int adjc, int n, int m) {
        return adjr >= 0 && adjr < n && adjc >= 0 && adjc < m;
    }

    public List<Integer> numOfislands(int n, int m, int[][] operators) {
        disjointSet ds = new disjointSet(n * m); // becuare thhats what the size of the matrix is;
        int vis[][] = new int[n][m]; // basic stuff;

        int cnt = 0;
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < operators.length; i++) {
            int row = operators[i][0];
            int col = operators[i][1];

            if (vis[row][col] == 1) { // the basic check if it is 1 then add tht indices in the ans;
                ans.add(cnt);
                continue;
            }
            vis[row][col] = 1;
            cnt++;

            int dr[] = { -1, 0, 1, 0 };// traversing in all the possible directions of that index line 97
            int dc[] = { 0, 1, 0, -1 };

            for (int ind = 0; ind < 4; ind++) {
                int adjr = row + dr[i];
                int adjc = col + dc[i];

                if (isValid(adjr, adjc, ind, m)) {
                    if (vis[adjr][adjc] == 1) {
                        int nodeNo = row * m + col; // to find out the no of row int the marix;
                        int adjNodeNo = adjr * m + adjc; // to find out the no of col in the matrix;

                        if (ds.findUPar(nodeNo) != ds.findUPar(adjNodeNo)) { //and checking if they belong to same parent or not if not then u know whats haapenig;
                            cnt--;
                            ds.unionBySize(nodeNo, adjNodeNo); // and then calling unionBySize to make the connections;
                        }

                    }
                }
            }
            ans.add(cnt);
        }
        return ans;
    }

    public static void main(String[] args) {
        numberOfislands2 solution = new numberOfislands2();
        int n = 3; 
        int m = 3;

       
        int[][] operators = {
                { 0, 0 },
                { 0, 1 },
                { 1, 2 },
                { 2, 2 },
        };

        List<Integer> result = solution.numOfislands(n, m, operators);

        
        System.out.println(result);
    }

}
