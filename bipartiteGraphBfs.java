import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class bipartitegraph {
    private static boolean check(int start , int v, ArrayList<ArrayList<Integer>> adj , int color[]){
        Queue<Integer> q=new LinkedList<>();
        q.add(start);
        color[start]=0;
        while(!q.isEmpty()){
            int node = q.peek();
            for(int it:adj.get(node)){
                if(color[it]==-1){
                    color[it]=1-color[node];
                    q.add(node);
                }else if(color[it]==color[node]){
                    return false;

                }

            }
        }
        return true;
    }
    public static boolean isBipartite(int v , ArrayList<ArrayList<Integer>> adj){
        int color[]=new int[v];
        for(int i=0;i<v;i++){
            color[i]=-1;
        }
        for(int i=0;i<v;i++){
            if(color[i]==-1){
                if(check(i, v, adj, color)==false){
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[]args){
        int v=6;
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();

       ArrayList<Integer> row1= new ArrayList<>(Arrays.asList(0,1,0,1));
       ArrayList<Integer> row2=new ArrayList<>(Arrays.asList(1,0,0,1));
       ArrayList<Integer> row3=new ArrayList<>(Arrays.asList(1,0,0,1));

       adj.add(row1);
       adj.add(row2);
       adj.add(row3);


       if(isBipartite(v, adj)){
        System.out.println("Yes this graph is bipartite");

       }else{
        System.out.println("Sorry this graph is not bipartite");
       }

    }
}
