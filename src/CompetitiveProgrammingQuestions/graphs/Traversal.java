package CompetitiveProgrammingQuestions.graphs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*Code : BFS Traversal
Send Feedback
Given an undirected and connected graph G(V, E), print its BFS traversal.
Here you need to consider that you need to print BFS path starting from vertex 0 only.
V is the number of vertices present in graph G and vertices are numbered from 0 to V-1.
E is the number of edges present in graph G.
Note : 1. Take graph input in the adjacency matrix.
2. Handle for Disconnected Graphs as well
Input Format :
Line 1: Two Integers V and E (separated by space)
Next 'E' lines, each have two space-separated integers, 'a' and 'b', denoting that there exists an edge between Vertex 'a' and Vertex 'b'.
Output Format :
BFS Traversal (separated by space)
Constraints :
2 <= V <= 1000
1 <= E <= 1000
Sample Input 1:
4 4
0 1
0 3
1 2
2 3
Sample Output 1:
0 1 3 2*/

/*
* */
public class Traversal {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int v = s.nextInt();
        int e = s.nextInt();
        int am[][] =new int[v][v];
        /* Write Your Code Here
         * Complete the Rest of the Program
         * You have to take input and print the output yourself
         */
        for(int i =0;i<e;i++){
            int a = s.nextInt();
            int b = s.nextInt();
            am[a][b] =1;
            am[b][a] =1;
        }
        int v1 = s.nextInt();
        int v2 = s.nextInt();
//        BFS(am,v,e);
        boolean vis[] =new boolean[v];
        DFS(am,v,v1,vis);
    }

    private static void DFS(int[][] am, int v, int node, boolean[] vis) {
        vis[node] =true;
        System.out.print(node + " ");
        for(int i=0;i<v;i++){
            //for each adj and not visited
            if(am[node][i] == 1 && !vis[i] ){
               DFS(am,v,i,vis);
            }
        }
    }

    private static void BFS(int[][] am, int v, int e) {
        Queue<Integer> q =new LinkedList<>();
        boolean vis[] =new boolean[v];
        for(int i =0;i<v ;i++) {
            if(!vis[i]) {
                q.add(i);
                vis[i] = true;
                while (!q.isEmpty()) {
                    int temp = q.poll();
                    System.out.print(temp + " ");
                    for (int adj = 0; adj < v; adj++) {
                        //for each adj and not visited
                        if (am[temp][adj] == 1 && !vis[adj]) {
                            q.add(adj);
                            vis[adj] = true;
                        }
                    }
                }
            }
        }
    }

}
