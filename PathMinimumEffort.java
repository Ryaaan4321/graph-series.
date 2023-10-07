import java.util.PriorityQueue;

class Tuple {
    int dist;
    int row;
    int col;

    public Tuple(int dist, int row, int col) {
        this.dist = dist;
        this.row = row;
        this.col = col;
    }
}

public class PathWithMiniMumEffort {
    static int minimumEffort(int heights[][]) {
        PriorityQueue<Tuple> pq = new PriorityQueue<>((x, y) -> x.dist - y.dist);
        int n = heights.length;
        int m = heights[0].length;
        int distance[][] = new int[n][m];

        // marking each and every box of the matrix as infinityyy;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                distance[i][j] = (int) (1e9);
            }
        }
        distance[0][0] = 0; // from source node to node the distance 0;
        // adding that node into the pq;
        pq.add(new Tuple(0, 0, 0));

        int[] drow = { -1, 0, 1, 0 };
        int[] dcol = { 0, 1, 0, -1 };
        // inttializee these two arrays to travese int all 4 direction of tht specific
        // node;

        while (pq.size() != 0) {
            Tuple it = pq.peek(); // took the top element of the priority queue;
            pq.remove();

            // initlaizing the dist, row , and col of tht specifit node
            int diff = it.dist;
            int col = it.row;
            int row = it.col;
             
            // to traverse in all the 4 directions taking a help of for loop;
            for (int i = 0; i < 4; i++) {
                int newr = row + drow[i];
                int newc = col + dcol[i];
                

                // checking this simple conditions if they are okaay then we can mover forward u got this  okayyy  lets get it..💪 
                if (newr >= 0 && newc >= 0 && newr < n && newc < m) {
                    int newEffort = Math.max(Math.abs(heights[row][col] - heights[newr][newc]), diff);
                    if (newEffort < distance[newr][newc]) { // if it is greater than tht then add it on the pq with its adjcent row and coll;
                        distance[newr][newc] = newEffort;
                        pq.add(new Tuple(newEffort, newr, newc));
                    }
                }
            }

        }
        return 0;

    }
    public static void main(String[]args){
        int heights[][]={{1,2,2},{3,8,2},{5,3,5}};
        int n=heights.length;
        int m=heights[0].length;

        int ans = minimumEffort(heights);

        System.out.println(ans);
    }

}
