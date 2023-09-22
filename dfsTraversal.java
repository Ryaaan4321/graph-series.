package graphh;

import java.util.ArrayList;

public class dfsTraversal {
    private static void dfs(int node , boolean vis[],ArrayList<ArrayList<Integer>> adj,ArrayList<Integer> ls){
        vis[node]=true;
        ls.add(node);

        for(Integer it:adj.get(node)){
            if(vis[it]==false){
                dfs(node, vis, adj, ls);
            }
        }
    }
    public static ArrayList<Integer> dfsOfGraph(int v , ArrayList<ArrayList<Integer>> adj){
        boolean vis[]=new boolean[v+1];
        vis[0]=true;

        ArrayList<Integer> ls= new ArrayList<>();
        dfs(0, vis, adj, ls);
        return ls;
    }
    
    public static void main(String[]args){
        int v=10;
        ArrayList<ArrayList<Integer>> adj =new ArrayList<>();
        for(int i=0;i<v;i++){
            adj.add(new ArrayList<>());
        }
        adj.get(0).add(1);
        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(3).add(4);
        adj.get(4).add(5);
        adj.get(5).add(6);
        adj.get(6).add(7);
        adj.get(7).add(8);
        adj.get(8).add(9);
        adj.get(10).add(10);

        ArrayList<Integer> dfsResult=dfsOfGraph(v, adj);
        for(int vertex:dfsResult){
            System.out.println(vertex);
        }

    }
}
