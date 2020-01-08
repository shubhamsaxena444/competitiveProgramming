package CompetitiveProgrammingQuestions.graphs;

import java.util.*;
/*Code : Get Path - BFS
Send Feedback
Given an undirected graph G(V, E) and two vertices v1 and v2(as integers), find and print the path from v1 to v2 (if exists). Print nothing if there is no path between v1 and v2.
Find the path using BFS and print the shortest path available.
V is the number of vertices present in graph G and vertices are numbered from 0 to V-1.
E is the number of edges present in graph G.
Print the path in reverse order. That is, print v2 first, then intermediate vertices and v1 at last.
Note : Save the input graph in Adjacency Matrix.
Input Format :
Line 1: Two Integers V and E (separated by space)
Next E lines : Two integers a and b, denoting that there exists an edge between vertex a and vertex b (separated by space)
Line (E+2) : Two integers v1 and v2 (separated by space)
Output Format :
Path from v1 to v2 in reverse order (separated by space)
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
3 0 1
Sample Input 2 :
6 3
5 3
0 1
3 4
0 3
Sample Output 2 :*/
public class GetPathBFS {

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
       getPathBFS(v, am, v1, v2);

    }

    private static void getPathBFS(int v, int[][] am, int v1, int v2) {
        //kisne kisko dala
        HashMap<Integer,Integer> path= new HashMap<>();
        Found found =new Found(false);
        BFS(am,v1,v2,path,found);
        if(found.isFound) {
            //we have a map path which can tell usintr
            int cur = v2;
            while (cur != v1) {
                System.out.print(cur + " ");
                int parent = path.get(cur);
                cur=parent;
            }
            System.out.print(v1);
            return ;
        }
        return;
    }


    static class Found{
        boolean isFound;
        Found(boolean is){
        isFound=is;
        }
    }
    private static Stack<Integer> getPath(int v, int[][] am, int v1, int v2) {
        boolean vis[] =new boolean[v];
        Stack<Integer> path= new Stack<>();
        vis[v1] =true;
        path.add(v1);
        Found found =new Found(false);
        DFS(am,v1,v2,vis,path,found);
        return found.isFound?path:null;
    }

    private static List<Integer> DFS(int[][] am, int startVertex, int endVertex, boolean[] vis, Stack<Integer> path,Found foundPath) {
        if(foundPath.isFound){
            return path;
        }
        int v =am.length;

        for(int i=0;i<v;i++){
            if(foundPath.isFound){
                return path;
            }
            //for each adj and not visited
            if(am[startVertex][i] == 1 && !vis[i] ){
                path.push(i);
                vis[i] =true;
                if(i==endVertex){
                    foundPath.isFound =true;
                    break;
                }
                DFS(am,i,endVertex,vis,path,foundPath);
            }
        }
        return path;
    }

    private static void BFS(int[][] am, int startVertex, int endVertex, HashMap<Integer, Integer> path, Found found) {
        Queue<Integer> q =new LinkedList<>();
        int v = am.length;
        boolean vis[] =new boolean[v];
        //add startVertex
        q.add(startVertex);
        vis[startVertex] =true;
        //start ko to start ne hi dala
        path.put(startVertex,startVertex);
        while (!q.isEmpty()) {
            int temp = q.poll();
            if(found.isFound){
               break;
            }
            for (int adj = 0; adj < v; adj++) {
                //for each adj and not visited
                if (am[temp][adj] == 1 && !vis[adj]) {
                    q.add(adj);
                    //adj is put by temp
                    path.put(adj,temp);
                    vis[adj] = true;
                    //jese hi endvertex queue me gya , break everything.
                    if(adj == endVertex) {
                        found.isFound=true;
                        break;
                    }
                }
            }
        }
    }

}
