import java.util.*;

public class DetectCycleKahnsAlgo {
    public static boolean isCyclic(int v , ArrayList<ArrayList<Integer>> adj){
        int indegree[]=new int[v];
        for(int i=0;i<v;i++){
            for(int it:adj.get(i)){
                indegree[it]++;
            }
        }
        Queue<Integer> q=new LinkedList<>();
        for(int i=0;i<v;i++){
            if(indegree[i]==0){
                q.add(i);
            }
        }
        int cnt=0;
        while(!q.isEmpty()){
            int node=q.peek();
            q.remove();cnt++;
            for(int it:adj.get(node)){
                indegree[it]--;
                if(indegree[it]==0) q.add(it);
            }
        }
        if(cnt==v) return false;
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

        if(isCyclic(v,adj)==true){
            System.out.println("yes this graph has a cycle");
        }else{
            System.out.println("this graph has not a cycle");
        }
    }
    
}
