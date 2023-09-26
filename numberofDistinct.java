import java.util.*;
class Pair{
    int first;
    int secod;
    public Pair(int first ,int second){
        this.first=first;
        this.secod=second;
    }
}
public class numberOfdistinctisland {
    public static void dfs(int row , int col, int [][]vis , int [][]grid , ArrayList<String> vec , int col0 ,  int row0){
        vis[row][col]=1;
        vec.add(toString(row-row0 , col-col0));
        int n=grid.length;
        int m=grid[0].length;
        int delrow[]={-1, 0 , 1,0};
        int delcol[]={0,-1,1,+1};

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                int nrow=row+delrow[i];
                int ncol=col+delcol[i];

                if(nrow>=0  && nrow<n && ncol>=0 && ncol<m && vis[nrow][ncol]==0 && grid[nrow][ncol]==1 ){
                    dfs(nrow, ncol, vis, grid, vec, col0, row0);
                }
            }
        }

    }
    private static int countDistinct(int [][]grid){
        int n=grid.length;
        int m=grid[0].length;
        int vis[][]=new int[n][m];

        HashSet<ArrayList<String>> set= new HashSet<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(vis[i][j]==0 && grid[i][j]==1 ){
                    ArrayList<String> vec=new ArrayList<>();
                    dfs(n, m, vis, grid, vec, i, j);
                    set.add(vec);
                }
            }
        }
        return set.size();
    }
    public static String toString(int r , int c){
        return Integer.toString(r) + "" + Integer.toString(c);
    }
    public static void main(String[]args){
        int grid[][]={{1,0,0,1},{1,1,1,0},{0,1,1,1},{0,0,0,1}};
        int ans=countDistinct(grid);
        System.out.println(ans);

    }
}
