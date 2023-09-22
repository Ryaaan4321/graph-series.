package graphh;
import java.util.*;

public class noofProvinces {
    private static void dfs(int node , ArrayList<ArrayList<Integer>> adjls, int vis[]){
        vis[node]=1;
        for(Integer it:adjls.get(node)){
            if(vis[it]==0){
                dfs(it, adjls, vis);
            }
        }

    }
    public static int numProvinces(ArrayList<ArrayList<Integer>> adj , int v){
        ArrayList<ArrayList<Integer>> adjls = new ArrayList<>();
        for(int i=0;i<v;i++){
            adjls.add(new ArrayList<Integer> ());
        }
        for(int i=0;i<v;i++){
            for(int j=0;j<v;j++){
                if(adj.get(i).get(j)==1 && i!=j){
                    adjls.get(i).add(j);
                    adjls.get(j).add(i);
                }
            }
        }
        int vis[]=new int[v];
        int cnt=0;
        for(int i=0;i<v;i++){
            if(vis[i]==0){
                cnt++;
                dfs(i, adjls, vis);
            }
        }
        return cnt;
    }
    public static void main(String[]args){
        int v=4;
        ArrayList<ArrayList<Integer>> adjMat=new ArrayList<>();
        int [][] graph={
            {1, 1 , 0 ,0},
            {1,1,0,0},
            {0,0,1,1},
            {0,0,1,1}
        };
        for(int i=0;i<v;i++){
            ArrayList<Integer> row= new ArrayList<>();
            for(int j=0;j<v;j++){
                row.add(graph[i][j]);
            }
            adjMat.add(row);
        }

        int ans= numProvinces(adjMat, v);
        System.out.println("Number of provinces " + ans);
    }
   
    
}
