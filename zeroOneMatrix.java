import java.util.LinkedList;
import java.util.Queue;

class Node {
    int first;
    int second;

    public Node(int _first, int _second) {
        this.first = _first;
        this.second = _second;
    }
}

public class zeroOneMatrix {
    public static int[][] nearest(int[][] grid) {
        int n = grid.length;
        if (n == 0) {
            return new int[0][0];
        }
        int m = grid[0].length;

        for (int i = 1; i < n; i++) {
            if (grid[i].length != m) {
                throw new IllegalArgumentException("Input grid must have consistent column count.");
            }
        }

        int vis[][] = new int[n][m];
        int dis[][] = new int[n][m];

        Queue<Node> q = new LinkedList<>();

        // Initialize the queue and visited array
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    q.add(new Node(i, j));
                    vis[i][j] = 1;
                } else {
                    vis[i][j] = 0;
                    dis[i][j] = Integer.MAX_VALUE; // Initialize distances to a large value
                }
            }
        }

        int delrow[] = { -1, 0, 1, 0 };
        int delcol[] = { 0, +1, 0, -1 };

        while (!q.isEmpty()) {
            int row = q.peek().first;
            int col = q.peek().second;

            q.remove();

            for (int i = 0; i < 4; i++) {
                int nrow = row + delrow[i];
                int ncol = col + delcol[i];

                if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m) {
                    if (dis[nrow][ncol] > dis[row][col] + 1) {
                        dis[nrow][ncol] = dis[row][col] + 1;
                        q.add(new Node(nrow, ncol));
                    }
                }
            }
        }
        return dis;
    }

    public static void main(String[] args) {
        int grid[][] = {{1, 0, 1},
                        {1, 1, 0},
                        {1, 1, 0}};
        int[][] ans = nearest(grid);
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[0].length; j++) {
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }
    }
}

