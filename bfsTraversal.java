package graphh;
import java.util.*;

public class bfsTraversal {
    public static ArrayList<Integer> bfsT(int v ,ArrayList<ArrayList<Integer>> adj){
        /*
         * so to go for the bfs traversal we need arraylist to store the 
         final bfsTrav , boolean array to check if the 
         * element is visited or not and the queue ds to 
         *check for the adjacent element of the node 
         * and the thing to remember is that ki we always
         *take a v+1 size of boolean array to avoid index out of bound error
         * once it is done now dive deeper into it
         * initially the 1 will be in the queue and we will mark as visited in the boolean array
         * once it is done now we will check for its adjacent element and will store them in  the 
         * queue and will mark it as visited now check for tht element adjacent 
         *if they are present or not if they are put them into the queue and check for their adjacent untill the queue is empty..!
         * 
         */
        ArrayList<Integer> bfs = new ArrayList<>();
        boolean vis[]=new boolean[v+1];
        Queue<Integer> q= new LinkedList<>();
        q.add(1);
        vis[1]=true;

        while(!q.isEmpty()){
            Integer node =q.poll();
            bfs.add(node);
            // bfs.add(q.poll());

            for(Integer it:adj.get(node)){
                if(vis[it]==false){
                    vis[it]=true;
                    q.add(it);
                }
            }
        }
        return bfs;



    }
    public static void main(String[]args){
        int v=10;
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        
        for(int i=0;i<v;i++){
            adj.add(new ArrayList<>());
        }
        adj.get(0).add(1);
        adj.get(0).add(2);
        adj.get(1).add(3);
        adj.get(1).add(4);
        adj.get(2).add(5);
        adj.get(2).add(6);
        adj.get(3).add(7);
        adj.get(3).add(8);
        adj.get(4).add(9);

        ArrayList<Integer> bfsresult = bfsT(v, adj);

        for(int vertex:bfsresult){
            System.out.println(vertex);
        }

    }
    
}





