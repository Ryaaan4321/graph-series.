import java.util.LinkedList;
import java.util.Queue;

class Tuple {
    int first;
    int second;
    int third;

    public Tuple(int first, int second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
}

public class ShortestDistMaze {
    public static int shortestPath(int grid[][], int source[], int destination[]) {
        if (source[0] == destination[0] && source[1] == destination[1])
            return 0;// the edge case..

        Queue<Tuple> q = new LinkedList<>();// using queue herre no need to use priority queue and using logn 
        int n = grid.length;// factor of space complexity
        int m = grid[0].length;
        int dist[][] = new int[n][m];// the distt array ;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dist[i][j] = (int) (1e9);// marking each and all in the dist array to infinite;
            }
        }
        dist[source[0]][source[1]] = 0;// the distance source to 0;
        q.add(new Tuple(0, source[0], source[1])); // adding to queue;

        int dr[] = { -1, 0, 1, 0 };
        int dc[] = { 0, 1, 0, -1 };

        while (!q.isEmpty()) {// iterating into q;
            Tuple it = q.peek();
            q.remove();

            int dis = it.first;// the dist
            int r = it.second;// the row;
            int c = it.third;//the col;

            for (int i = 0; i < 4; i++) {// iteratting into the for loop;
                int newr = r + dr[i];
                int newc = c + dc[i];
              // checking if all the conditions are true;
                if (newr >= 0 && newr < n && newc >= 0 && newc < m && grid[newr][newc] == 1
                        && dis + 1 < dist[newr][newc]) {
                    dist[newr][newc] = 1 + dis;
                    if (newr == destination[0] && newc == destination[1]) {
                        return dis + 1;
                    }// then adding into the q;
                    q.add(new Tuple(1 + dis, newr, newc));
                }
            }
        }
        return -1;
    }
    public static void main(String[]args){
        int grid[][]={{1,1,1,1},{1,1,0,1},{1,1,1,1},{1,1,1,0},{1,0,0,0}};
        int source[]={0,1};
        int destination[]={2,2};

        int ans=shortestPath(grid, source, destination);
         System.out.println(ans);
    }
}

