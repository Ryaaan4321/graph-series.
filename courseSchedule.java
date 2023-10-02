import java.util.ArrayList;
import java.util.LinkedList;
import java.util.*;

public class courseSchedule {
    private static boolean isPossible(int v , int [][]prerequisite){
        ArrayList<ArrayList<Integer>> adj= new ArrayList<>();
        for(int i=0;i<v;i++){
            adj.add( new ArrayList<>());

        }
        int m=prerequisite.length;
        for(int i=0;i<m;i++){
            adj.get(prerequisite[i][0]).add(prerequisite[i][1]);
        }
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
        List<Integer> topo=new ArrayList<>();
        while(q.isEmpty()){
            int node = q.peek();
            q.remove();
            topo.add(node);
            for(int it:adj.get(node)){
                indegree[it]--;
                if(indegree[it]==0)q.add(it);
            }
        }
        if(topo.size()==v) return true;
        return false;
    }
    public static void main(String[]args){
        int v=4;
        int [][]prerequisite = {{1,0},{2,1},{3,2}};

        courseSchedule scheduler=new courseSchedule();
        boolean ispossible = scheduler.isPossible(v, prerequisite);

        if(ispossible){
            System.out.println("it is possible to schedule the course");
        }else{
            System.out.println("it is not possible to schedule the course");
        }
    }
    
}
