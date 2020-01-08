package CompetitiveProgrammingQuestions.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.*;
/*Code : All connected components
Send Feedback
Given an undirected graph G(V,E), find and print all the connected components of the given graph G.
V is the number of vertices present in graph G and vertices are numbered from 0 to V-1.
E is the number of edges present in graph G.
You need to take input in main and create a function which should return all the connected components. And then print them in the main, not inside function.
Print different components in new line. And each component should be printed in increasing order (separated by space). Order of different components doesn't matter.
Input Format :
Line 1: Two Integers V and E (separated by space)
Next 'E' lines, each have two space-separated integers, 'a' and 'b', denoting that there exists an edge between Vertex 'a' and Vertex 'b'.
Output Format :
Different components in new line
Constraints :
2 <= V <= 1000
1 <= E <= 1000
Sample Input 1:
4 2
0 1
2 3
Sample Output 1:
0 1
2 3
Sample Input 2:
4 3
0 1
1 3
0 3
Sample Output 2:
0 1 3
2*/
public class AllConnectedComponenets {

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
                List<List<Integer>> allPaths = getConnectedComponents(v, am);
    //        System.out.println(allPaths);
        allPaths.stream().forEach(x->{
            x.stream().sorted().forEach(y-> System.out.print(y+" "));
            System.out.println();
        } );
            }

    private static List<List<Integer>> getConnectedComponents(int v, int[][] am) {
        List<List<Integer>> allPaths = new ArrayList<>();
//        BFS(am,v,e);

        boolean vis[] =new boolean[v];
        for(int i =0;i<v;i++){
            if(vis[i]  == false) {
                List<Integer> path = new ArrayList<>();
                DFS(am, v, i, vis, path);
                allPaths.add(path);
            }
        }           //if all vis then connected else false
        return allPaths;
    }

    private static void DFS(int[][] am, int v, int node, boolean[] vis, List<Integer> path) {
        vis[node] =true;
        path.add(node);
        for(int i=0;i<v;i++){
            //for each adj and not visited
            if(am[node][i] == 1 && !vis[i] ){
                DFS(am,v,i,vis,path);
            }
        }
        return;
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
