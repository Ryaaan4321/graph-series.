import java.util.ArrayList;
import java.util.Arrays;

public class bipartiteDfs {
    public static boolean dfs(int node , int col , int color[], ArrayList<ArrayList<Integer>> adj ){
        color[node]=col;
        for(int it: adj.get(node)){
            if(color[it]==-1){
                if(dfs(it,1-col,color,adj)==false){
                    return false;
                }else if(color[it]==col){
                    return false;
                }
            }

        }
        return true;
    }
    public static boolean isBipartite(int v, ArrayList<ArrayList<Integer>> adj){
        int color[]=new int[v];
        for(int i=0;i<v;i++)color[i]=-1;
        for(int i=0;i<v;i++){
            if(color[i]==-1){
                if(dfs( i,0, color, adj)==false) return false;
            }
        }
        return true;
    }
    public static void main(String[]args){
        int v=3;
        ArrayList<ArrayList<Integer>>adj=new ArrayList<>(v);

        ArrayList<Integer> row1= new ArrayList<>(Arrays.asList(0,1,0,1));
       ArrayList<Integer> row2=new ArrayList<>(Arrays.asList(1,0,0,1));
       ArrayList<Integer> row3=new ArrayList<>(Arrays.asList(1,0,0,1));

       adj.add(row1);
       adj.add(row2);
       adj.add(row3);

       if(isBipartite(v, adj)==true){
        System.out.println("Yes it is bipartite");
       }else{
        System.out.println("Sorry this is not a bipartite graph");
       }
        
    }
    
}
