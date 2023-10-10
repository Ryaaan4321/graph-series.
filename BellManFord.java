import java.util.ArrayList;

class Tuples {
    int first;
    int second;
    int third;

    Tuples(int first, int second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
}

public class bellManFord {
    public static int[] bellmanFord(int V, ArrayList<ArrayList<Integer>> edges, int s) {
        int distance[] = new int[V]; // the same distance array is created as we are creating till now in the dijakstra
        for (int i = 0; i < V; i++) {
            distance[i] = (int) (1e8);// intializing it with infinity
        }
        distance[s] = 0;// because from source to source is always 0;
        for (int i = 0; i < V; i++) {
            for (ArrayList<Integer> it : edges) {
                int u = it.get(0);
                int v = it.get(1);
                int wt = it.get(2);

                if (distance[u] != 1e8 && distance[u] + wt < distance[v]) {
                    distance[v] = distance[u] + wt; // if it is true then initializing the distance[] = with this;
                }
            }
        }

        for (ArrayList<Integer> it : edges) {
            int u = it.get(0);
            int v = it.get(1);
            int wt = it.get(2);
            if (distance[u] != 1e8 && distance[u] + wt < distance[v]) { // and if is not then returning the empty array becuause thats wht they said to return..
                int temp[] = new int[1];
                temp[0] = -1;
                return temp;

            }
        }
        return distance;
    }
}
// one of the most important plus factor of the bellman ford is tht we can find the distance in the negative weighted edges also ..
// its not  like dijakstra tht keeps on giving us the minimum valueee;
