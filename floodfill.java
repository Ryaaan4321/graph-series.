package graphh;

 public class floodfill {
    public static int[][] floodfil(int[][] image, int sr, int sc, int newColour) {
        int iniColour = image[sr][sc];
        int[][] ans = new int[image.length][image[0].length]; // Create a new array for the result

        int delrow[] = { -1, 0, +1, 0 };
        int delcol[] = { 0, +1, 0, -1 };

        dfs(sc, sr, image, ans, iniColour, newColour, delrow, delcol);

        return ans;
    }

    private static void dfs(int col, int row, int[][] image, int[][] ans, int iniColour, int newColour, int delrow[],
                            int delcol[]) {
        ans[row][col] = newColour;
        int n = image.length;
        int m = image[0].length;

        for (int i = 0; i < 4; i++) {
            int nrow = row + delrow[i];
            int ncol = col + delcol[i];

            if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m && image[nrow][ncol] == iniColour
                    && ans[nrow][ncol] != newColour) {
                dfs(ncol, nrow, image, ans, iniColour, newColour, delrow, delcol);
            }
        }
    }

    public static void main(String[] args) {
        int image[][] = {
                { 1, 1, 0 },
                { 2, 2, 0 },
                { 2, 2, 2 }
        };
        int sr = 1;
        int sc = 0;
        int newColour = 3;

        int[][] finalans = floodfil(image, sr, sc, newColour);

        for (int i = 0; i < finalans.length; i++) {
            for (int j = 0; j < finalans[i].length; j++) {
                System.out.print(finalans[i][j] + " ");
            }
            System.out.println();
        }
    }
}
