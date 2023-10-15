public class findTheCity {
    public static int findTheCity(int n, int m, int edges[][], int distanceThreshold) {
        int distance[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distance[i][j] = i == j ? 0 : Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];

            distance[u][v] = wt;
            distance[v][u] = wt;
        }

        for (int k = 0; k < n; k++) {
            for (int j = 0; j < n; j++) {
                for (int i = 0; i < n; i++) {
                    if (distance[i][k] != Integer.MAX_VALUE && distance[k][j] != Integer.MAX_VALUE) {
                        distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                    }
                }
            }
        }

        int cntCity = n;
        int cityNo = -1;

        for (int city = 0; city < n; city++) {
            int cnt = 0;
            for (int adjCity = 0; adjCity < n; adjCity++) {
                if (distance[city][adjCity] <= distanceThreshold) {
                    cnt++;
                }
            }
            if (cnt < cntCity) {
                cntCity = cnt;
                cityNo = city;
            }
        }
        return cityNo;
    }

    public static void main(String[] args) {
        
        int n = 4;
        int m = 4;
        int[][] edges = {
            {0, 1, 2},
            {1, 2, 3},
            {2, 3, 1},
            {0, 3, 4}
        };
        int distanceThreshold = 4;
        
        int result = findTheCity(n, m, edges, distanceThreshold);
        System.out.println("The city is: " + result);
    }
}
