package CompetitiveProgrammingQuestions.graphs;
import java.util.*;
import java.util.Comparator;
import java.util.Scanner;
/*Kruskal's Algorithm
Send Feedback
Given an undirected, connected and weighted graph G(V, E) with V number of vertices (which are numbered from 0 to V-1) and E number of edges.
Find and print the Minimum Spanning Tree (MST) using Kruskal's algorithm.
For printing MST follow the steps -
1. In one line, print an edge which is part of MST in the format -
v1 v2 w
where, v1 and v2 are the vertices of the edge which is included in MST and whose weight is w. And v1 <= v2 i.e. print the smaller vertex first while printing an edge.
2. Print V-1 edges in above format in different lines.
Note : Order of different edges doesn't matter.
Input Format :
Line 1: Two Integers V and E (separated by space)
Next E lines : Three integers ei, ej and wi, denoting that there exists an edge between vertex ei and vertex ej with weight wi (separated by space)
Output Format :
MST
Constraints :
2 <= V, E <= 10^5
Sample Input 1 :
4 4
0 1 3
0 3 5
1 2 1
2 3 8
Sample Output 1 :
1 2 1
0 1 3
0 3 5*/
class Solution{
    static class edge{
        int a;
        int b;
        int w;
        public edge(int a, int b, int w) {
            this.a = a;
            this.b = b;
            this.w = w;
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int v = s.nextInt();
        int e = s.nextInt();
        /* Write Your Code Here
         * Complete the Rest of the Program
         * You have to take input and print the output yourself
         */
        List<edge> edges = new ArrayList<>();
        for (int i = 0; i < e; i++) {
            int a =s.nextInt();
            int b = s.nextInt();
            int w = s.nextInt();
            if(w==0)continue;
            edges.add(new edge(a,b,w));
        }
        kruskalsAlgo( v, e,edges);
    }
    //when this count reaches v-1 we stop.
    static int count =0;

    private static void kruskalsAlgo(int v, int e,List<edge> edges) {
        //sort the edges by weight
        Collections.sort(edges, new Comparator<edge>() {
            @Override
            public int compare(edge o1, edge o2) {
                return o1.w-o2.w;
            }
        });
        //To apply unionfind algorithm , we need to maintain a parent array
        //this will be initialised with same vertex
        int[] p = new int[v];
        for(int i =0;i<v;i++){
            p[i] = i ;
        }
        //keep a boolean visited array
        boolean[] vis = new boolean[v];

        //iterate though edges 1 by 1 and check for cycle detection either by hasPath method or by unionFindalgorithm
        for(int i =0;i<e;i++){

            if(count == v-1){
                break;
            }
            if(detectCycleUsingUnionFindAlgorithm(edges.get(i),p,vis)){
                //skip this edge
            }else{
                printEdge(edges.get(i));
            }
        }

    }

    private static boolean detectCycleUsingUnionFindAlgorithm(edge edge,  int p[], boolean[] vis) {
        //if the two verteces have the same topmost parent , then return true, because they are in the same component,
        int p1 = findTopmostParent(edge.a,p);
        int p2 = findTopmostParent(edge.b,p);
        if(p1 == p2){
            return true;

        }else{
            //we return false(no cycle) only when the vertices are unvisited or they have different topmost parent.
            //when we reutrn false(no cycle) , we also update the parent array with the topmost vertex

            //mark verteces visited
            vis[edge.a] =true;
            vis[edge.b] =true;
            //update parent array, by updating vertex b's parent to vertex a's parent
            p[p2] = p1;

            //inc the count of edges
            count++;
            return false;
        }
    }

    private static int findTopmostParent(int vertex, int[] p) {
        int parent =p[vertex];
        while(parent != vertex){
            vertex =parent;
            parent = p[vertex];
        }
        return parent;
    }

    private static void printEdge(edge edge) {
        if(edge.a <edge.b){
            System.out.println(edge.a +" "+ edge.b +" "+ edge.w);
        }else{
            System.out.println(edge.b +" "+ edge.a +" "+ edge.w);
        }
    }
}