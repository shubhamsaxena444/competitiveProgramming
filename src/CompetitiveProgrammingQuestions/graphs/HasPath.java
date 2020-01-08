package CompetitiveProgrammingQuestions.graphs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
Code : Has Path
Send Feedback
Given an undirected graph G(V, E) and two vertices v1 and v2(as integers), check if there exists any path between them or not. Print true or false.
V is the number of vertices present in graph G and vertices are numbered from 0 to V-1.
E is the number of edges present in graph G.
Input Format :
Line 1: Two Integers V and E (separated by space)
Next E lines : Two integers a and b, denoting that there exists an edge between vertex a and vertex b (separated by space)
Line (E+2) : Two integers v1 and v2 (separated by space)
Output Format :
true or false
Constraints :
2 <= V <= 1000
1 <= E <= 1000
0 <= v1, v2 <= V-1
Sample Input 1 :
4 4
0 1
0 3
1 2
2 3
1 3
Sample Output 1 :
true
Sample Input 2 :
6 3
5 3
0 1
3 4
0 3
Sample Output 2 :
false
 */
public class HasPath {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int v = s.nextInt();
        int e = s.nextInt();
        int am[][] =new int[v][v];
       //add undirected edge
        for(int i =0;i<e;i++){
            int a = s.nextInt();
            int b = s.nextInt();
            am[a][b] =1;
            am[b][a] =1;
        }
        int v1 = s.nextInt();
        int v2 = s.nextInt();
        BFS(am,v1,v2);
//        boolean vis[] =new boolean[v];
//        DFS(am,v,v1,vis);
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

    private static void BFS(int[][] am, int startVertex, int endVertex) {
        Queue<Integer> q =new LinkedList<>();
        int v = am.length;
        boolean hasPath=false;
        boolean vis[] =new boolean[v];
        //add startVertex
        q.add(startVertex);
        vis[startVertex] =true;
        while (!q.isEmpty()) {
            int temp = q.poll();
            if(temp == endVertex) {
//                        System.out.print(temp + " ");
                hasPath=true;
                break;
            }
            for (int adj = 0; adj < v; adj++) {
                //for each adj and not visited
                if (am[temp][adj] == 1 && !vis[adj]) {
                    q.add(adj);
                    vis[adj] = true;
                }
            }
        }
        System.out.println(hasPath);
    }

}
