/*
 
Given a matrix of dimension M * N where each cell in the matrix can have values 0, 1 or 2 which has the following meaning:  

0: Empty cell
1: Cells have fresh oranges
2: Cells have rotten oranges
Determine what is the minimum time required so that all the oranges become rotten. 
A rotten orange at index (i,j ) can rot other fresh oranges which are its neighbours (up, down, left and right).
If it is impossible to rot every orange then simply return -1.
 */



import java.util.LinkedList;
import java.util.Queue;

class Pair {
    int row;
    int col;
    int time;

    Pair(int row, int col, int time) {
        this.row = row;
        this.col = col;
        this.time = time;
    }
}

public class rottenOranges {
    public static int OrangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;// just the row and col are initiallized;

        Queue<Pair> q = new LinkedList<>(); // took a q and it will store the Pair;
        int vis[][] = new int[n][m];// and taking vis array as per our rituals to check  if the element is visited or not

        int countFresh = 0;

        for (int i = 0; i < n; i++) {
            // traversing into the matrix if we found the 2 tht means it is rotten thn add it into the q ;
            // and move forward and check for its adjacent element
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    q.add(new Pair(i, j, 0));
                } else {
                    vis[i][j] = 0;// and if it is 0 then mark it as visiited 

                }
                if (grid[i][j] == 1)
                // and if it 1 then increment the countfresh
                    countFresh++;
            }

        }

        int time = 0;

        /*
         * SO HELLO THE "DELROW" AND "DELCOL" ARE USED TO TRAVERSE IN ALL THE 8
         * DIRECTIONS OF THE MATRIX WHICH WILL HELP US TO FIND
         * THE FIND ROTTEN ORANGES OR IT IS HELPFUL TO TRAVSERSE IN ALL THE 8 DIRECTIONS
         * WANNA KNOW HOW TAKE THE VALUES 1 BY 1 AND U END UP TRAVERSING IN ALL THE 8
         * DIRECTIONSS..!
         */
        int drow[] = { -1, 0, 1, 0 };
        int dcol[] = { 0, 1, 0, -1 };
        int cnt = 0;

        while (!q.isEmpty()) {
            int r = q.peek().row;
            int c = q.peek().col;
            int t = q.peek().time;

            time = Math.max(time, t);

            

            for (int i = 0; i < 4; i++) {
                /*
                 * traversing into the all the 8 directions and checking if they are rotten then incrementing the cnt;
                 */
                int nrow = r + drow[i];
                int ncol = c + dcol[i];

                if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m && vis[nrow][ncol] == 0 && grid[nrow][ncol] == 1) {
                    q.add(new Pair(nrow, ncol, time + 1));
                    vis[nrow][ncol] = 2;
                    cnt++;

                }
            }
        }
        if (cnt != countFresh)
            return -1;
        return time;

    }
    public static void main(String[]args){
        int grid[][]={ {2, 1, 0, 2, 1}, {1, 0, 1, 2, 1}, {1, 0, 0, 2, 1}};

        int ans= OrangesRotting(grid);


        System.out.println(ans);
    }

}
