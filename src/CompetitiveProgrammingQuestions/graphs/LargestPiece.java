package CompetitiveProgrammingQuestions.graphs;

import java.util.Scanner;
/*Largest Piece
Send Feedback
Its Gary's birthday today and he has ordered his favourite square cake consisting of '0's and '1's . But Gary wants the biggest piece of '1's and no '0's . A piece of cake is defined as a part which consist of only '1's, and all '1's share an edge with eachother on the cake. Given the size of cake N and the cake , can you find the size of the biggest piece of '1's for Gary ?
Constraints :
1<=N<=50
Input Format :
Line 1 : An integer N denoting the size of cake
Next N lines : N characters denoting the cake
Output Format :
Size of the biggest piece of '1's and no '0's
Sample Input :
2
11
01
Sample Output :
3*/
public class LargestPiece {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = Integer.parseInt(s.nextLine());
        String cake[] = new String[n];
        for(int i =0;i<n;i++){
            cake[i] =s.nextLine();
        }

        System.out.println(solve(n,cake));

       }
       static class Count{
        int c;

           public Count(int c) {
               this.c = c;
           }
       }
    static int solve(int n ,String[] cake){
        boolean visited[][] = new boolean[n][n];
        int maxCount =1;
        for(int i=0;i<n;i++) {
            for (int j = 0; j < n; j++) {
               Count count = new Count(0);
               //find  1 in cake which is unvisited, and call dfs
                if(cake[i].charAt(j)-'0' ==1 && !visited[i][j]) {
                    DFS(cake, n, i, j, visited, count);
                    //count number of visited nodes from local visited and also update the global visted
                    maxCount = Math.max(count.c, maxCount);
                }
            }
        }
        return maxCount;
    }

    private static void DFS(String[] am, int n, int i,int j, boolean[][] vis,Count count) {
        vis[i][j] = true;
        count.c++;
        if(i+1<n && am[i+1].charAt(j)=='1' && !vis[i+1][j])
            DFS(am, n, i+1,j, vis,count);
        if(i-1>=0 && am[i-1].charAt(j)=='1' && !vis[i-1][j])
            DFS(am, n, i-1,j, vis,count);
        if(j+1<n && am[i].charAt(j+1)=='1' && !vis[i][j+1])
            DFS(am, n, i,j+1, vis,count);
        if(j-1>=0 && am[i].charAt(j-1)=='1' && !vis[i][j-1])
            DFS(am, n, i,j-1, vis,count);


    }

}